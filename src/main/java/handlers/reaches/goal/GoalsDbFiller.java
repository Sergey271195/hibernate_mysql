package handlers.reaches.goal;

import dao.GoalDao;
import dao.SourceDao;
import entity.goal.GoalReachesSuperclass;
import entity.main.Goal;
import entity.source.SourceSuperclass;
import handlers.BaseSessionHandler;
import handlers.requestparsers.ByTimeRequestParser;
import handlers.DimensionsProperties;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GoalsDbFiller extends BaseSessionHandler {

    private final ByTimeRequestParser requestParser;
    private final GoalDao goalDao = new GoalDao(sessionFactory);
    private final SourceDao sourceDao = new SourceDao(sessionFactory);

    private final Class<? extends GoalReachesSuperclass> insertTable;
    private List<Goal> goals;

    public GoalsDbFiller(ByTimeRequestParser requestParser) {
        this.requestParser = requestParser;
        insertTable = DimensionsProperties.insertTableRegistry.get(requestParser.dimension);
        System.out.println("InsertTableClass: " + insertTable.getSimpleName());
        goals = mapMetricsToGoals();
    }

    public void fill() {
        createInsertStatements();
    }

    private List<Goal> mapMetricsToGoals() {
        Pattern goalPattern = Pattern.compile("ym:s:goal(\\d+)reaches");
        return doInTransaction(() ->
                requestParser.metrics.stream()
                    .map(goal -> goalPattern.matcher(goal))
                    .map(matcher -> {
                        matcher.find();
                        return Long.valueOf(matcher.group(1));
                    }).map(goalMetrikaId -> goalDao.getByMetrikaId(Goal.class, goalMetrikaId))
                    .collect(Collectors.toList())
        );
    }

    private void createInsertStatements() {
        doInTransaction(() -> {
            requestParser.mapMetricsToDimensions().entrySet().stream()
                .forEach(entry -> processData(entry));
        });
    }

    private void processData(Map.Entry<? extends SourceSuperclass, List<List<Double>>> entrySet) {
        SourceSuperclass source = createOrFetchSource(entrySet.getKey());
        var valuesList = entrySet.getValue();
        for (int i = 0; i < valuesList.size(); i++) {
            List<Double> reachesList = valuesList.get(i);
            Goal goal = goals.get(i);
            System.out.println(goal);
            for (int j = 0; j < reachesList.size(); j++) {
                if (reachesList.get(j) != 0.0) {
                    GoalReachesSuperclass reachesSuperclass = getTableRowInstance();
                    reachesSuperclass.setGoal(goal);
                    reachesSuperclass.setDate(requestParser.timeIntervals.get(j));
                    reachesSuperclass.setCounter(goal.getCounter());
                    reachesSuperclass.setSourceSuperclass(source);
                    reachesSuperclass.setReaches(reachesList.get(j));
                    sourceDao.save(reachesSuperclass);
                }
            }
        }

    }

    private <T extends SourceSuperclass> T createOrFetchSource(T source) {
        Serializable metrikaId = (Serializable) source.getMetrikaId();
        T managedSource = (T) sourceDao.getByMetrikaId(source.getClass(), metrikaId);
        if (managedSource == null) {
            sourceDao.save(source);
            System.out.println("Inserting new source: " + source);
            return source;
        }
        return managedSource;
    }

    protected  <T extends GoalReachesSuperclass> T getTableRowInstance() {
        try {
            return (T) insertTable.getDeclaredConstructor()
                    .newInstance();
        } catch (InstantiationException |
                IllegalAccessException |
                InvocationTargetException |
                NoSuchMethodException err) {
            err.printStackTrace();
            return null;
        }
    }

}
