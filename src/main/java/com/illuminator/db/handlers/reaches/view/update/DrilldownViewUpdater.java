package com.illuminator.db.handlers.reaches.view.update;

import com.illuminator.db.entity.main.Counter;
import com.illuminator.db.entity.source.SourceSuperclass;
import com.illuminator.db.handlers.DimensionsProperties;
import com.illuminator.db.handlers.reaches.view.dbupdate.SubDbViewUpdater;
import com.illuminator.db.handlers.reaches.view.parse.ViewSubRequestParser;
import com.illuminator.db.handlers.reaches.view.request.DrilldownViewRequestBuilder;
import com.illuminator.db.handlers.reaches.SubRequestBuilder;
import com.illuminator.db.processors.RequestProcessor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class DrilldownViewUpdater extends BaseViewUpdater {

    private final LocalDate updateDate;

    public DrilldownViewUpdater(RequestProcessor requestProcessor, LocalDate updateDate) {
        super(requestProcessor, updateDate);
        this.updateDate = updateDate;
    }

    @Override
    protected void handleCounterForSource(Counter counter, Class<? extends SourceSuperclass> source) {
        if (counterIsUpdated(counter, source)) return;
        updateCounter(counter, source);
    }

    private void updateCounter(Counter counter, Class<? extends SourceSuperclass> source) {
        String request = getRequest(counter, source);
        Map<String, Object> response = getDataWithAttempts(request);
        List<Map<String, Object>> subRequests = SubRequestBuilder.createSubRequests(response, request);
        subRequests.stream()
                .map(this::getExtendedData)
                .peek(System.out::println)
                .filter(Objects::nonNull)
                .map(ViewSubRequestParser::new)
                .map(SubDbViewUpdater::new)
                .forEach(SubDbViewUpdater::update);
    }

    private String getRequest(Counter counter, Class<? extends SourceSuperclass> source) {
        String dimensionString = DimensionsProperties.sourceToDimensionStringRegistry.get(source);
        String request = new DrilldownViewRequestBuilder(counter, dimensionString, updateDate).buildRequest();
        return request;
    }

    private Map<String, Object> getExtendedData(Map<String, Object> subRequest) {
        Map<String, Object> expandedData = getDataWithAttempts((String) subRequest.get("request"));
        if (expandedData != null)
            expandedData.put("dimensions", subRequest.get("dimensions"));
        return expandedData;
    }

}
