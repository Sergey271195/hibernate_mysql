package handlers.reaches.goal.reuqest.fillers;

import entity.EntityClassRegistry;
import entity.main.Counter;
import entity.source.*;
import exceptions.FetchException;
import handlers.BaseRequestHandler;
import handlers.reaches.goal.request.builders.DrilldownGoalsRequestBuilder;
import handlers.requestparsers.DrilldownRequestParser;
import handlers.requestparsers.SubRequestParser;
import processors.RequestProcessor;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class BaseFiller extends BaseRequestHandler {

    final static int ATTEMPTS_NUMBER = 5;

    public BaseFiller(RequestProcessor requestProcessor) {
        super(requestProcessor);
    }


    public void fill(Counter counter) {
        fillForSource(counter, SearchPhrase.class);
        //EntityClassRegistry.ENTITY_SOURCE_CLASS_REGISTRY.stream()
        //        .forEach(source -> fillForSource(counter, source));
    }

    protected abstract void fillForSource(Counter counter, Class<? extends SourceSuperclass> source);

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
