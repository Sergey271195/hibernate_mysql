package handlers.reaches.goal.reuqest.fillers;

import entity.main.Counter;
import entity.source.SourceSuperclass;
import handlers.DimensionsProperties;
import handlers.reaches.goal.request.builders.DrilldownGoalsRequestBuilder;
import handlers.requestparsers.DrilldownRequestParser;
import handlers.requestparsers.SubRequestParser;
import processors.RequestProcessor;

import javax.persistence.NoResultException;
import java.util.*;

public class DrilldownGoalsFiller extends BaseFiller {

    public DrilldownGoalsFiller(RequestProcessor requestProcessor) {
        super(requestProcessor);
    }

    @Override
    protected void fillForSource(Counter counter, Class<? extends SourceSuperclass> source) {

        System.out.println("FILLING GOALS FOR COUNTER: " + counter.getMetrikaId());

        getBatchedRequests(counter, source).stream()
                .peek(r -> System.out.println("BATCH: " + r))
                .map(this::getDataWithAttempts)
                .map(DrilldownRequestParser::new)
                .map(DrilldownRequestParser::createSubRequests)
                .flatMap(List::stream)
                .map(this::getExpandedData)
                .filter(Objects::nonNull)
                .map(SubRequestParser::new)
                .map(SubRequestFiller::new)
                .forEach(SubRequestFiller::fill);
    }

    private List<String> getBatchedRequests(Counter counter, Class<? extends SourceSuperclass> source) {
        String dimension = DimensionsProperties.sourceToDimensionStringRegistry.get(source);
        List<String> batchedRequests =
                new DrilldownGoalsRequestBuilder(counter, dimension).createRequest();
        return batchedRequests;
    }

    private Map<String, Object> getExpandedData(Map<String, Object> subRequestData) {
            Map<String, Object> expandedData = getDataWithAttempts((String) subRequestData.get("request"));
            if (expandedData != null)
                expandedData.put("dimensions", subRequestData.get("dimensions"));
            return expandedData;
    }

    private Map<String, Object> getExpandedDataWithFilter(Map<String, Object> subRequestData) {
        System.out.println("EXPANDED DATA: " + subRequestData);
        try {
            var r = doInTransaction(() ->
                    getSession().createQuery("FROM SearchPhrase s WHERE s.name = :name")
                            .setParameter("name", ((Map)subRequestData.get("dimensions")).get("name"))
                            .getSingleResult()
            );
            System.out.println("SKIPPING DIMENSION: " + subRequestData.get("dimensions"));
            return null;
        } catch (NoResultException e) {
            Map<String, Object> expandedData = getDataWithAttempts((String) subRequestData.get("request"));
            if (expandedData != null)
                expandedData.put("dimensions", subRequestData.get("dimensions"));
            return expandedData;
        }
    }

}
