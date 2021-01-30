package com.illuminator.handlers.reaches.goal.update;

import com.illuminator.entity.main.Counter;
import com.illuminator.entity.source.SourceSuperclass;
import com.illuminator.handlers.DimensionsProperties;
import com.illuminator.handlers.reaches.goal.dbupdate.ByTimeDbGoalUpdater;
import com.illuminator.handlers.reaches.goal.request.ByTimeGoalsRequestBuilder;
import com.illuminator.handlers.requestparsers.ByTimeRequestParser;
import com.illuminator.processors.RequestProcessor;

import java.time.LocalDate;
import java.util.List;

public class ByTimeGoalUpdater extends BaseGoalUpdater {

    public ByTimeGoalUpdater(RequestProcessor requestProcessor, LocalDate updateDate) {
        super(requestProcessor, updateDate);
    }

    @Override
    protected void handleCounterForSource(Counter counter, Class<? extends SourceSuperclass> source) {
        if (counterIsUpdated(counter, source)) return;
        getBatchedRequests(counter, source).stream()
                .map(this::getDataWithAttempts)
                .map(ByTimeRequestParser::new)
                .map(ByTimeDbGoalUpdater::new)
                .forEach(ByTimeDbGoalUpdater::update);
    }

    private List<String> getBatchedRequests(Counter counter, Class<? extends SourceSuperclass> source) {
        String dimension = DimensionsProperties.sourceToDimensionStringRegistry.get(source);
        List<String> batchedRequests =
                new ByTimeGoalsRequestBuilder(counter, dimension, updateDate).createRequest();
        return batchedRequests;
    }

}
