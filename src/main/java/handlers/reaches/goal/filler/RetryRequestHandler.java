package handlers.reaches.goal.filler;

import entity.main.Counter;
import entity.source.*;
import exceptions.FetchException;
import handlers.BaseRequestHandler;
import processors.RequestProcessor;

import java.util.List;
import java.util.Map;

public abstract class RetryRequestHandler extends BaseRequestHandler {

    final static int ATTEMPTS_NUMBER = 5;

    public RetryRequestHandler(RequestProcessor requestProcessor) {
        super(requestProcessor);
    }

    public void handleCounter(Counter counter) {
        handleCounterForSource(counter, AdvEngine.class);
        //EntityClassRegistry.ENTITY_SOURCE_CLASS_REGISTRY.stream()
        //        .forEach(source -> fillForSource(counter, source));
    }

    protected abstract void handleCounterForSource(Counter counter, Class<? extends SourceSuperclass> source);

    protected Map<String, Object> getDataWithAttempts(String url) {
        try {
            Map<String, Object> response = null;
            for (int i = 0; i < ATTEMPTS_NUMBER; i ++) {
                response = requestProcessor.process(url);
                List responseData = (List) response.get("data");
                if (!responseData.isEmpty()) return response;
            }
            System.out.println("FAILED TO LOAD DATA FOR: " + url );
            return response;
        } catch (FetchException | java.lang.NullPointerException err) {
            System.out.println("[ERROR WHILE FETCHING DATA FOR URL] " + url + ".\n" + err);
            return null;
        }
    }


}
