package com.illuminator.handlers.goal;

import com.illuminator.dao.CounterDao;
import com.illuminator.handlers.BaseRequestHandler;
import com.illuminator.processors.RequestProcessor;

public class GoalFiller extends BaseRequestHandler {

    private final CounterDao counterDao = new CounterDao(sessionFactory);

    public GoalFiller(RequestProcessor requestProcessor) {
        super(requestProcessor);
    }

    public void fill() {
        doInTransaction(() ->
                counterDao.getAllEager().stream()
                        .map(counter -> new GoalHandler(requestProcessor, counter))
                        .forEach(GoalHandler::refreshGoals)
        );

    }
}
