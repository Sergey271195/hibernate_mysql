package handlers.reaches.view.update;

import entity.ReachesSuperclass;
import entity.main.Counter;
import entity.source.SourceSuperclass;
import handlers.DimensionsProperties;
import handlers.RetryRequestHandler;
import processors.RequestProcessor;

import java.time.LocalDate;
import java.util.List;

public abstract class BaseViewUpdater extends RetryRequestHandler {

    public final LocalDate updateDate;

    public BaseViewUpdater(RequestProcessor requestProcessor, LocalDate updateDate) {
        super(requestProcessor);
        this.updateDate = updateDate;
    }

    protected boolean counterIsUpdated(Counter counter, Class<? extends SourceSuperclass> source) {
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
