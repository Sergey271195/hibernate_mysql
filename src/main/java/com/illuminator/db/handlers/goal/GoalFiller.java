package com.illuminator.db.handlers.goal;

import com.illuminator.db.dao.CounterDao;
import com.illuminator.db.handlers.BaseRequestHandler;
import com.illuminator.db.processors.RequestProcessor;

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
