package handlers.reaches.goal.updater;

import dao.ReachesDao;
import dao.SourceDao;
import entity.goal.GoalReachesSuperclass;
import entity.main.Counter;
import entity.main.Goal;
import entity.source.SourceSuperclass;
import handlers.BaseSessionHandler;
import handlers.requestparsers.ByTimeRequestParser;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ByTimeGoalsUpdater extends BaseSessionHandler {

    private final ByTimeRequestParser requestParser;
    private final SourceDao sourceDao = new SourceDao(sessionFactory);
    private final ReachesDao reachesDao = new ReachesDao(sessionFactory);

    private SourceSuperclass source;
    private Counter counter;
    private List<Goal> goals;

    public ByTimeGoalsUpdater(ByTimeRequestParser requestParser) {
        this.requestParser = requestParser;
        goals = mapGoalIdsToGoals();
    }

    public void update() {
        doInTransaction(() -> {
            this.counter = sourceDao.getByMetrikaId(Counter.class, requestParser.ids);
            requestParser.data.stream()
                    .forEach(data -> updateForDimension(data));
        });
        LocalDate updateDate = requestParser.timeIntervals.get(0);
        System.out.println(counter);

    }

    private void updateForDimension(Map<String, List> data) {
        List<List<Double>> metrics = getMetriks(data);
        Map<String, String> subDimension = getDimension(data);
        this.source = sourceDao.createOrFetchSourceFromData(requestParser.dimension, subDimension);
        IntStream.range(0, metrics.size())
                .mapToObj(index-> {
                    GoalReachesSuperclass newTableRow = createTableRow();
                    newTableRow.setGoal(goals.get(index));
                    newTableRow.setReaches(metrics.get(index).get(0));
                    newTableRow.setDate(getEntryDate());
                    return newTableRow;
                }).peek(this::setSourceAndCounter)
                .forEach(r -> System.out.println(r + " " + r.getReaches() + " " + r.getDate()));
        System.out.println(source);
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
        return reachesDao.getTableRowInstance(requestParser.insertTable);
    }

    private void setSourceAndCounter(GoalReachesSuperclass tableRow) {
        tableRow.setSourceSuperclass(source);
        tableRow.setCounter(counter);
    }

    private LocalDate getEntryDate() {
        return requestParser.timeIntervals.get(0);
    }

}
