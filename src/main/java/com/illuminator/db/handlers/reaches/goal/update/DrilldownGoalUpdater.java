package com.illuminator.db.handlers.reaches.goal.update;

import com.illuminator.db.entity.main.Counter;
import com.illuminator.db.entity.source.SourceSuperclass;
import com.illuminator.db.handlers.DimensionsProperties;
import com.illuminator.db.handlers.reaches.SubRequestBuilder;
import com.illuminator.db.handlers.reaches.goal.dbupdate.SubDbGoalUpdater;
import com.illuminator.db.handlers.reaches.goal.request.DrilldownGoalUpdateRequestBuilder;
import com.illuminator.db.handlers.requestparsers.GoalsSubRequestParser;
import com.illuminator.db.processors.RequestProcessor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DrilldownGoalUpdater extends BaseGoalUpdater {

    public DrilldownGoalUpdater(RequestProcessor requestProcessor, LocalDate updateDate) {
        super(requestProcessor, updateDate);
    }

    @Override
    protected void handleCounterForSource(Counter counter, Class<? extends SourceSuperclass> source) {
        if (counterIsUpdated(counter, source)) return;
        updateCounter(counter, source);
    }

    private void updateCounter(Counter counter, Class<? extends SourceSuperclass> source) {
        for (String request: getBatchedRequests(counter, source)) {
            Map<String, Object> response = getDataWithAttempts(request);
            List<Map<String, Object>> subRequests = SubRequestBuilder.createSubRequests(response, request);
            subRequests.stream()
                    .peek(System.out::println)
                    .map(this::getExtendedData)
                    .peek(System.out::println)
                    .filter(Objects::nonNull)
                    .map(GoalsSubRequestParser::new)
                    .map(SubDbGoalUpdater::new)
                    .forEach(SubDbGoalUpdater::update);
        }

    }

    private List<String> getBatchedRequests(Counter counter, Class<? extends SourceSuperclass> source) {
        String dimension = DimensionsProperties.sourceToDimensionStringRegistry.get(source);
        List<String> batchedRequests =
                new DrilldownGoalUpdateRequestBuilder(counter, dimension, updateDate).createRequest();
        return batchedRequests;
    }

    private Map<String, Object> getExtendedData(Map<String, Object> subRequest) {
        Map<String, Object> expandedData = getDataWithAttempts((String) subRequest.get("request"));
        if (expandedData != null)
            expandedData.put("dimensions", subRequest.get("dimensions"));
        return expandedData;
    }
}
