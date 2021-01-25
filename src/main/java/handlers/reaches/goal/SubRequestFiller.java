package handlers.reaches.goal;

import dao.GoalDao;
import dao.ReachesDao;
import dao.SourceDao;
import entity.goal.GoalReachesSuperclass;
import entity.main.Goal;
import entity.source.SourceSuperclass;
import handlers.BaseSessionHandler;
import handlers.requestparsers.SubRequestParser;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SubRequestFiller extends BaseSessionHandler {

    private final SubRequestParser subRequestParser;
    private final GoalDao goalDao = new GoalDao(sessionFactory);
    private final SourceDao sourceDao = new SourceDao(sessionFactory);
    private final ReachesDao reachesDao = new ReachesDao(sessionFactory);
    private final List<Goal> goals;
    private SourceSuperclass source;

    public SubRequestFiller(SubRequestParser subRequestParser) {
        this.subRequestParser = subRequestParser;
        this.goals = mapGoalIdsToGoals();
    }

    public void fill() {
        doInTransaction(() -> {
            this.source = sourceDao
                    .createOrFetchSourceFromData(subRequestParser.dimension, subRequestParser.parentData);
            subRequestParser.data.stream()
                    .forEach(this::createInsertStatement);
        });
    }

    private void createInsertStatement(Map<String, Object> entry) {
        System.out.println("MAIN INSERT STATEMENT LOOP " + subRequestParser.parentData);
        LocalDate entryDate = getEntryDate(entry);
        List<Double> metrics = (List) entry.get("metrics");
        IntStream.range(0, metrics.size())
                .forEach(index -> insertEntry(goals.get(index), metrics.get(index), entryDate));
    }

    private void insertEntry(Goal goal, Double reaches, LocalDate entryDate) {
        if (reaches == 0.0) return;
        GoalReachesSuperclass newTableRow = reachesDao.getTableRowInstance(subRequestParser.insertTable);
        newTableRow.setSourceSuperclass(source);
        newTableRow.setCounter(subRequestParser.counter);
        newTableRow.setDate(entryDate);
        newTableRow.setReaches(reaches);
        newTableRow.setGoal(goal);
        reachesDao.save(newTableRow);
    }

    private LocalDate getEntryDate(Map<String, Object> entry) {
        return LocalDate.parse((String) ((Map) entry.get("dimension")).get("id"));
    }

    private List<Goal> mapGoalIdsToGoals() {
        return doInTransaction(() -> subRequestParser.goalIds.stream()
                    .map(goalDao::getByMetrikaId)
                        .collect(Collectors.toList())
        );
    }

}
