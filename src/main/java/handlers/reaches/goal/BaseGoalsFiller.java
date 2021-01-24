package handlers.reaches.goal;

import entity.ApplicationProperties;
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

        goalsMetrikaRequest.stream()
                .filter(Objects::nonNull)
                .map(this::getMetrikaData)
                .peek(System.out::println)
                .map(ByTimeRequestParser::new)
                .map(GoalsDbFiller::new)
                .forEach(GoalsDbFiller::fill);
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
