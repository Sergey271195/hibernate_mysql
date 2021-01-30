package com.illuminator.handlers.reaches.view.update;

import com.illuminator.entity.main.Counter;
import com.illuminator.entity.source.SourceSuperclass;
import com.illuminator.handlers.DimensionsProperties;
import com.illuminator.handlers.reaches.view.dbupdate.DbViewUpdater;
import com.illuminator.handlers.reaches.view.parse.BaseViewParser;
import com.illuminator.handlers.reaches.view.request.ByTimeViewRequestBuilder;
import com.illuminator.processors.RequestProcessor;

import java.time.LocalDate;
import java.util.Map;

public class ByTimeViewUpdater extends BaseViewUpdater {

    public ByTimeViewUpdater(RequestProcessor requestProcessor, LocalDate updateDate) {
        super(requestProcessor, updateDate);
    }

    @Override
    protected void handleCounterForSource(Counter counter, Class<? extends SourceSuperclass> source) {
        if (counterIsUpdated(counter,source)) return;
        updateCounter(counter, source);
    }

    private void updateCounter(Counter counter, Class<? extends SourceSuperclass> source) {
        String request = getRequest(counter, source);
        Map<String, Object> response = getDataWithAttempts(request);
        BaseViewParser parser = new BaseViewParser(response);
        DbViewUpdater updater = new DbViewUpdater(parser);
        updater.update();
    }

    private String getRequest(Counter counter, Class<? extends SourceSuperclass> source) {
        String dimensionString = DimensionsProperties.sourceToDimensionStringRegistry.get(source);
        String request = new ByTimeViewRequestBuilder(counter, dimensionString, updateDate).buildRequest();
        return request;
    }
}
