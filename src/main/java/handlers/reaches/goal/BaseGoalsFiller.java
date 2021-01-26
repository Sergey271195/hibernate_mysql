package handlers.reaches.goal;

import entity.main.Counter;
import exceptions.FetchException;
import handlers.BaseRequestHandler;
import handlers.reaches.goal.request.builders.DrilldownGoalsRequestBuilder;
import handlers.requestparsers.DrilldownRequestParser;
import handlers.requestparsers.SubRequestParser;
import processors.RequestProcessor;

import java.util.*;

public class BaseGoalsFiller extends BaseRequestHandler {

    public BaseGoalsFiller(RequestProcessor requestProcessor) {
        super(requestProcessor);
    }

    public void fillCounter(Counter counter) {
        System.out.println("Filling Goals for counter: " + counter.getMetrikaId());
        List<String> goalsMetrikaRequest =
                new DrilldownGoalsRequestBuilder(counter, "SearchEngineRoot").createRequest();

        goalsMetrikaRequest.stream()
                .filter(Objects::nonNull)
                .map(this::getMetrikaData)
                .map(DrilldownRequestParser::new)
                .map(DrilldownRequestParser::createSubRequests)
                .flatMap(List::stream)
                .map(this::getExpandedData)
                .map(expandedData -> new SubRequestParser(counter, expandedData))
                .map(SubRequestFiller::new)
                .forEach(SubRequestFiller::fill);
                //.map(GoalsDbFiller::new)
                //.forEach(GoalsDbFiller::fill);
    }

    private Map<String, Object> getExpandedData(Map<String, Object> subRequestData) {
        Map<String, Object> metrikaResponse = getMetrikaData((String) subRequestData.get("request"));
        metrikaResponse.put("dimensions", subRequestData.get("dimensions"));
        return metrikaResponse;
    }


    private Map<String, Object> getMetrikaData(String url) {
        try {
            Map<String, Object> response = null;
            for (int i = 0; i < 6; i ++) {
                response = requestProcessor.process(url);
                List responseData = (List) response.get("data");
                if (!responseData.isEmpty()) return response;
            }
            System.out.println("FAILED TO LOAD DATA FOR: " + url );
            return response;
        } catch (FetchException err) {
            System.out.println("[ERROR WHILE FETCHING DATA FOR URL] " + url + ".\n" + err);
            return null;
        }
    }


}
