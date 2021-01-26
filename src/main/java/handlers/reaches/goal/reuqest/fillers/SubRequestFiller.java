package handlers.reaches.goal.reuqest.fillers;

import dao.CounterDao;
import dao.GoalDao;
import dao.ReachesDao;
import dao.SourceDao;
import entity.goal.GoalReachesSuperclass;
import entity.main.Counter;
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
    private final SourceDao sourceDao = new SourceDao(sessionFactory);
    private final ReachesDao reachesDao = new ReachesDao(sessionFactory);
    private final List<Goal> goals;
    private SourceSuperclass source;
    private Counter counter;

    public SubRequestFiller(SubRequestParser subRequestParser) {
        this.subRequestParser = subRequestParser;
        this.goals = mapGoalIdsToGoals();
    }

    public void fill() {
        doInTransaction(() -> {
            this.counter = sourceDao.getByMetrikaId(Counter.class, subRequestParser.ids);
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
                .filter(index -> metrics.get(index) != 0.0)
                .mapToObj(index -> {
                    GoalReachesSuperclass newTableRow = createTableRow();
                    newTableRow.setGoal(goals.get(index));
                    newTableRow.setReaches(metrics.get(index));
                    newTableRow.setDate(entryDate);
                    return newTableRow;
                }).peek(this::setSourceAndCounter)
                .peek(s -> System.out.println(" " + s + s.getReaches()))
                .forEach(reachesDao::save);
    }

    private GoalReachesSuperclass createTableRow() {
        return reachesDao.getTableRowInstance(subRequestParser.insertTable);
    }

    private void setSourceAndCounter(GoalReachesSuperclass tableRow) {
        tableRow.setSourceSuperclass(source);
        tableRow.setCounter(counter);
    }

    private LocalDate getEntryDate(Map<String, Object> entry) {
        return LocalDate.parse((String) ((Map) entry.get("dimension")).get("id"));
    }

    private List<Goal> mapGoalIdsToGoals() {
        return doInTransaction(() -> subRequestParser.goalIds.stream()
                    .map(metrikaId -> sourceDao.getByMetrikaId(Goal.class, metrikaId))
                        .collect(Collectors.toList())
        );
    }

}
