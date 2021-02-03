package com.illuminator.db.handlers.reaches.goal.update;

import com.illuminator.db.entity.main.Counter;
import com.illuminator.db.entity.source.ReferralSource;
import com.illuminator.db.entity.source.SearchPhrase;
import com.illuminator.db.entity.source.SourceSuperclass;
import com.illuminator.db.handlers.RetryRequestHandler;
import com.illuminator.db.processors.RequestProcessor;

import java.time.LocalDate;

public class GoalUpdateHandler extends RetryRequestHandler {

    private final ByTimeGoalUpdater byTimeUpdater;
    private final DrilldownGoalUpdater drilldownUpdater;

    public GoalUpdateHandler(RequestProcessor requestProcessor, LocalDate updateDate) {
        super(requestProcessor);
        this.byTimeUpdater = new ByTimeGoalUpdater(requestProcessor, updateDate);
        this.drilldownUpdater = new DrilldownGoalUpdater(requestProcessor, updateDate);
    }

    @Override
    protected void handleCounterForSource(Counter counter, Class<? extends SourceSuperclass> source) {
        if (source == ReferralSource.class || source ==  SearchPhrase.class) {
            drilldownUpdater.handleCounterForSource(counter, source);
        } else {
            byTimeUpdater.handleCounterForSource(counter, source);
        }
    }

}
