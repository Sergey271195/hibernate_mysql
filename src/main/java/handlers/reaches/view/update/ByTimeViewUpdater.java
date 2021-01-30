package handlers.reaches.view.update;

import entity.main.Counter;
import entity.source.SourceSuperclass;
import handlers.DimensionsProperties;
import handlers.reaches.view.dbupdate.DbViewUpdater;
import handlers.reaches.view.parser.BaseViewParser;
import handlers.reaches.view.request.ByTimeViewRequestBuilder;
import processors.RequestProcessor;

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
