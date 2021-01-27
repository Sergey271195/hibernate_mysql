package handlers.reaches.goal.updater;

import entity.goal.GoalReachesSuperclass;
import entity.main.Counter;
import entity.source.SourceSuperclass;
import handlers.DimensionsProperties;
import handlers.reaches.goal.builder.ByTimeGoalsRequestBuilder;
import handlers.reaches.goal.filler.RetryRequestHandler;
import handlers.requestparsers.ByTimeRequestParser;
import processors.RequestProcessor;

import java.time.LocalDate;
import java.util.List;

public class BaseUpdater extends RetryRequestHandler {

    private final LocalDate updateDate;

    public BaseUpdater(RequestProcessor requestProcessor, LocalDate updateDate) {
        super(requestProcessor);
        this.updateDate = updateDate;
    }

    @Override
    protected void handleCounterForSource(Counter counter, Class<? extends SourceSuperclass> source) {

        if (counterIsUpdated(counter, source)) return;
        getBatchedRequests(counter, source).stream()
                .map(this::getDataWithAttempts)
                .peek(r -> System.out.println(r))
                .map(ByTimeRequestParser::new)
                .map(ByTimeGoalsUpdater::new)
                .peek(ByTimeGoalsUpdater::update)
                .forEach(r -> System.out.println(r));

    }

    private boolean counterIsUpdated(Counter counter, Class<? extends SourceSuperclass> source) {
        Class<? extends GoalReachesSuperclass> insertTable =
                DimensionsProperties.insertTableRegistry.get(source);
        List result = doInTransaction(() ->
                getSession().createQuery(
                        "FROM " + insertTable.getSimpleName() +
                                " a WHERE a.date = :date AND a.counter = :counter"
                        ).setParameter("date", updateDate)
                        .setParameter("counter", counter)
                        .getResultList()
                );
        if (result.isEmpty()) {
            System.out.println("UPDATING COUNTER: " + counter.getMetrikaId() + " SOURCE: " + source.getSimpleName() + "");
            return false;
        }
        System.out.println("COUNTER: " + counter.getMetrikaId() + " SOURCE: " + source.getSimpleName() + " ALREADY UPDATED.");
        return true;
    }

    private List<String> getBatchedRequests(Counter counter, Class<? extends SourceSuperclass> source) {
        String dimension = DimensionsProperties.sourceToDimensionStringRegistry.get(source);
        List<String> batchedRequests =
                new ByTimeGoalsRequestBuilder(counter, dimension, updateDate).createRequest();
        return batchedRequests;
    }

}
