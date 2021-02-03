package com.illuminator.db.handlers;

import com.illuminator.db.entity.main.Counter;
import com.illuminator.db.entity.source.*;
import com.illuminator.db.exceptions.FetchException;
import com.illuminator.db.handlers.reaches.goal.update.BaseGoalUpdater;
import com.illuminator.db.handlers.reaches.view.update.BaseViewUpdater;
import com.illuminator.db.processors.RequestProcessor;

import java.util.List;
import java.util.Map;

public abstract class RetryRequestHandler extends BaseRequestHandler {

    final static int ATTEMPTS_NUMBER_FOR_UPDATE = 2;
    final static int ATTEMPTS_NUMBER_FOR_FILL = 5;

    public RetryRequestHandler(RequestProcessor requestProcessor) {
        super(requestProcessor);
    }

    public void handleCounter(Counter counter) {
        //handleCounterForSource(counter, AdvEngine.class);
        List<Class> classList = List.of(SearchPhrase.class);
        //EntityClassRegistry.ENTITY_SOURCE_CLASS_REGISTRY.stream()
        classList.stream()
                .forEach(source -> handleCounterForSource(counter, source));
    }

    protected abstract void handleCounterForSource(Counter counter, Class<? extends SourceSuperclass> source);

    protected Map<String, Object> getDataWithAttempts(String url) {
        int ATTEMPTS = (this instanceof BaseGoalUpdater || this instanceof BaseViewUpdater)
                ? ATTEMPTS_NUMBER_FOR_UPDATE : ATTEMPTS_NUMBER_FOR_FILL;
        try {
            Map<String, Object> response = null;
            for (int i = 0; i < ATTEMPTS; i ++) {
                response = requestProcessor.process(url);
                List responseData = (List) response.get("data");
                if (!responseData.isEmpty()) return response;
            }
            System.out.println("EMPTY RESPONSE DATA FOR " + ATTEMPTS + " ATTEMPTS! \nURL: " + url );
            return response;
        } catch (FetchException | java.lang.NullPointerException err) {
            System.out.println("[ERROR WHILE FETCHING DATA FOR URL] " + url + ".\n" + err);
            return null;
        }
    }


}
