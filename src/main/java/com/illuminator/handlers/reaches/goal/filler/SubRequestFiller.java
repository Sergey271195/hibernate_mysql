package com.illuminator.handlers.reaches.goal.filler;

import com.illuminator.dao.GoalReachesDao;
import com.illuminator.dao.SourceDao;
import com.illuminator.entity.goal.GoalReachesSuperclass;
import com.illuminator.entity.main.Counter;
import com.illuminator.entity.main.Goal;
import com.illuminator.entity.source.SourceSuperclass;
import com.illuminator.handlers.BaseSessionHandler;
import com.illuminator.handlers.requestparsers.GoalsSubRequestParser;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SubRequestFiller extends BaseSessionHandler {

    private final GoalsSubRequestParser subRequestParser;
    private final SourceDao sourceDao = new SourceDao(sessionFactory);
    private final GoalReachesDao goalReachesDao = new GoalReachesDao(sessionFactory);
    private final List<Goal> goals;
    private SourceSuperclass source;
    private Counter counter;

    public SubRequestFiller(GoalsSubRequestParser subRequestParser) {
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
                .forEach(goalReachesDao::save);
    }

    private GoalReachesSuperclass createTableRow() {
        return goalReachesDao.getTableRowInstance(subRequestParser.insertTable);
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
