package com.illuminator.handlers.reaches.goal.update;

import com.illuminator.entity.goal.GoalReachesSuperclass;
import com.illuminator.entity.main.Counter;
import com.illuminator.entity.source.SourceSuperclass;
import com.illuminator.handlers.DimensionsProperties;
import com.illuminator.handlers.RetryRequestHandler;
import com.illuminator.processors.RequestProcessor;

import java.time.LocalDate;
import java.util.List;

public abstract class BaseGoalUpdater extends RetryRequestHandler {

    protected final LocalDate updateDate;

    public BaseGoalUpdater(RequestProcessor requestProcessor, LocalDate updateDate) {
        super(requestProcessor);
        this.updateDate = updateDate;
    }

    protected boolean counterIsUpdated(Counter counter, Class<? extends SourceSuperclass> source) {
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
            System.out.println("GOAL | UPDATING COUNTER: " + counter.getMetrikaId() + " SOURCE: " + source.getSimpleName() + "");
            return false;
        }
        System.out.println("GOAL | COUNTER: " + counter.getMetrikaId() + " SOURCE: " + source.getSimpleName() + " ALREADY UPDATED.");
        return true;
    }

}
