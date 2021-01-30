package com.illuminator.handlers.reaches.goal.dbupdate;

import com.illuminator.dao.GoalReachesDao;
import com.illuminator.dao.SourceDao;
import com.illuminator.entity.goal.GoalReachesSuperclass;
import com.illuminator.entity.main.Counter;
import com.illuminator.entity.main.Goal;
import com.illuminator.entity.source.SourceSuperclass;
import com.illuminator.handlers.BaseSessionHandler;
import com.illuminator.handlers.requestparsers.ByTimeRequestParser;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ByTimeDbGoalUpdater extends BaseSessionHandler {

    private final ByTimeRequestParser requestParser;
    private final SourceDao sourceDao = new SourceDao(sessionFactory);
    private final GoalReachesDao goalReachesDao = new GoalReachesDao(sessionFactory);

    private SourceSuperclass source;
    private Counter counter;
    private List<Goal> goals;

    public ByTimeDbGoalUpdater(ByTimeRequestParser requestParser) {
        this.requestParser = requestParser;
        goals = mapGoalIdsToGoals();
    }

    public void update() {
        doInTransaction(() -> {
            this.counter = sourceDao.getByMetrikaId(Counter.class, requestParser.ids);
            requestParser.data.stream()
                    .forEach(data -> updateForDimension(data));
        });
    }

    private void updateForDimension(Map<String, List> data) {
        List<List<Double>> metrics = getMetriks(data);
        Map<String, String> subDimension = getDimension(data);
        this.source = sourceDao.createOrFetchSourceFromData(requestParser.dimension, subDimension);
        IntStream.range(0, metrics.size())
                .filter(index -> metrics.get(index).get(0) != 0.0)
                .mapToObj(index-> {
                    GoalReachesSuperclass newTableRow = createTableRow();
                    newTableRow.setGoal(goals.get(index));
                    newTableRow.setReaches(metrics.get(index).get(0));
                    newTableRow.setDate(getEntryDate());
                    return newTableRow;
                }).peek(this::setSourceAndCounter)
                .peek(goalReachesDao::save)
                .forEach(r -> System.out.println(r + " " + r.getReaches() + " " + r.getDate()));
    }

    private List<Goal> mapGoalIdsToGoals() {
        return doInTransaction(() -> requestParser.goalIds.stream()
                .map(metrikaId -> sourceDao.getByMetrikaId(Goal.class, metrikaId))
                .collect(Collectors.toList())
        );
    }

    private List<List<Double>> getMetriks(Map<String, List> data) {
        return data.get("metrics");
    }

    private Map<String, String> getDimension(Map<String, List> data) {
        return (Map) data.get("dimensions").get(0);
    }

    private GoalReachesSuperclass createTableRow() {
        return goalReachesDao.getTableRowInstance(requestParser.insertTable);
    }

    private void setSourceAndCounter(GoalReachesSuperclass tableRow) {
        tableRow.setSourceSuperclass(source);
        tableRow.setCounter(counter);
    }

    private LocalDate getEntryDate() {
        return requestParser.timeIntervals.get(0);
    }

}
