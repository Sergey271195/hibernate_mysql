package handlers.reaches.view;

import entity.ReachesSuperclass;
import entity.goal.GoalReachesSuperclass;
import entity.main.Counter;
import entity.source.SourceSuperclass;
import handlers.DimensionsProperties;
import handlers.RetryRequestHandler;
import processors.RequestProcessor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class ViewUpdater extends RetryRequestHandler {

    private final LocalDate updateDate;

    public ViewUpdater(RequestProcessor requestProcessor, LocalDate updateDate) {
        super(requestProcessor);
        this.updateDate = updateDate;
    }

    @Override
    protected void handleCounterForSource(Counter counter, Class<? extends SourceSuperclass> source) {
        if (counterIsUpdated(counter,source)) return;
        String request = getRequest(counter, source);
        Map<String, Object> response = getDataWithAttempts(request);
        ViewRequestParser parser = new ViewRequestParser(response);
        ViewDbUpdater updater = new ViewDbUpdater(parser);
        updater.update();
    }

    private String getRequest(Counter counter, Class<? extends SourceSuperclass> source) {
        String dimensionString = DimensionsProperties.sourceToDimensionStringRegistry.get(source);
        String request = new ViewRequestBuilder(counter, dimensionString, updateDate).buildRequest();
        return request;
    }

    private boolean counterIsUpdated(Counter counter, Class<? extends SourceSuperclass> source) {
        Class<? extends ReachesSuperclass> insertTable =
                DimensionsProperties.viewInsertTableRegistry.get(source);
        List result = doInTransaction(() ->
                getSession().createQuery(
                        "FROM " + insertTable.getSimpleName() +
                                " a WHERE a.date = :date AND a.counter = :counter"
                ).setParameter("date", updateDate)
                        .setParameter("counter", counter)
                        .getResultList()
        );
        if (result.isEmpty()) {
            System.out.println("VIEWS | UPDATING COUNTER: " + counter.getMetrikaId() + " SOURCE: " + source.getSimpleName() + "");
            return false;
        }
        System.out.println("VIEWS | COUNTER: " + counter.getMetrikaId() + " SOURCE: " + source.getSimpleName() + " ALREADY UPDATED.");
        return true;
    }

}
