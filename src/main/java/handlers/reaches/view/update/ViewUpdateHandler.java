package handlers.reaches.view.update;

import entity.main.Counter;
import entity.source.ReferralSource;
import entity.source.SearchPhrase;
import entity.source.SourceSuperclass;
import processors.RequestProcessor;

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
