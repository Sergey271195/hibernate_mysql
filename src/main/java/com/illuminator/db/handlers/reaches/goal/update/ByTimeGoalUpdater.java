package com.illuminator.db.handlers.reaches.goal.update;

import com.illuminator.db.entity.main.Counter;
import com.illuminator.db.entity.source.SourceSuperclass;
import com.illuminator.db.handlers.DimensionsProperties;
import com.illuminator.db.handlers.reaches.goal.dbupdate.ByTimeDbGoalUpdater;
import com.illuminator.db.handlers.reaches.goal.request.ByTimeGoalsRequestBuilder;
import com.illuminator.db.handlers.requestparsers.ByTimeRequestParser;
import com.illuminator.db.processors.RequestProcessor;

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
