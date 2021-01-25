package handlers.reaches.goal;

import entity.main.Counter;
import exceptions.FetchException;
import handlers.BaseRequestHandler;
import handlers.requestparsers.ByTimeRequestParser;
import processors.RequestProcessor;

import java.util.*;

public class BaseGoalsFiller extends BaseRequestHandler {

    public BaseGoalsFiller(RequestProcessor requestProcessor) {
        super(requestProcessor);
    }

    public void fillCounter(Counter counter) {
        System.out.println("Filling Goals for counter: " + counter.getMetrikaId());
        List<String> goalsMetrikaRequest =
                new GoalsRequestBuilder(counter, "SearchEngineRoot").createRequest();
        System.out.println(goalsMetrikaRequest);
        goalsMetrikaRequest.stream()
                .filter(Objects::nonNull)
                .peek(k -> System.out.println(k))
                .map(this::getMetrikaData)
                .forEach(data -> System.out.println("Data: " + data));
                //.map(ByTimeRequestParser::new)
                //.map(GoalsDbFiller::new)
                //.forEach(GoalsDbFiller::fill);
    }


    private Map<String, Object> getMetrikaData(String url) {
        try {
            return requestProcessor.process(url);
        } catch (FetchException err) {
            System.out.println("[ERROR WHILE FETCHING DATA FOR URL] " + url + ".\n" + err);
            return null;
        }
    }


}
