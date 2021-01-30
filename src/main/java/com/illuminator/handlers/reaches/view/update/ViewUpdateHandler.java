package com.illuminator.handlers.reaches.view.update;

import com.illuminator.entity.main.Counter;
import com.illuminator.entity.source.ReferralSource;
import com.illuminator.entity.source.SearchPhrase;
import com.illuminator.entity.source.SourceSuperclass;
import com.illuminator.processors.RequestProcessor;

import java.time.LocalDate;

public class ViewUpdateHandler extends BaseViewUpdater {

    private final DrilldownViewUpdater drilldownUpdater;
    private final ByTimeViewUpdater byTimeUpdater;

    public ViewUpdateHandler(RequestProcessor requestProcessor, LocalDate updateDate) {
        super(requestProcessor, updateDate);
        this.byTimeUpdater = new ByTimeViewUpdater(requestProcessor, updateDate);
        this.drilldownUpdater = new DrilldownViewUpdater(requestProcessor, updateDate);
    }

    @Override
    protected void handleCounterForSource(Counter counter, Class<? extends SourceSuperclass> source) {
        if (source == ReferralSource.class || source == SearchPhrase.class) {
            drilldownUpdater.handleCounterForSource(counter, source);
        } else {
            byTimeUpdater.handleCounterForSource(counter, source);
        }
    }
}
