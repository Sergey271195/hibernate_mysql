package com.illuminator.db.handlers.reaches.view.update;

import com.illuminator.db.entity.main.Counter;
import com.illuminator.db.entity.source.ReferralSource;
import com.illuminator.db.entity.source.SearchPhrase;
import com.illuminator.db.entity.source.SourceSuperclass;
import com.illuminator.db.processors.RequestProcessor;

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
