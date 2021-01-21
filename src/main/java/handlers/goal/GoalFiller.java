package handlers.goal;

import dao.CounterDao;
import handlers.BaseRequestHandler;
import processors.RequestProcessor;

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
