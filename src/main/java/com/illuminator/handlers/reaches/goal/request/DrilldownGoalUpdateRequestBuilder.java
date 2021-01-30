package com.illuminator.handlers.reaches.goal.request;

import com.illuminator.entity.ApplicationProperties;
import com.illuminator.entity.main.Counter;

import java.time.LocalDate;

public class DrilldownGoalUpdateRequestBuilder extends BaseGoalsRequestBuilder {

    private final Counter counter;
    private final String dimension;
    private final LocalDate updateDate;

    public DrilldownGoalUpdateRequestBuilder(Counter counter, String dimension, LocalDate updateDate) {
        super(counter);
        this.counter = counter;
        this.dimension = dimension;
        this.updateDate = updateDate;
    }

    @Override
    protected String buildRequest(String goalsRequest) {
        setDataForRequestBase(goalsRequest);
        String requestBase = buildRequestBase();
        return ApplicationProperties.JANDEX_DRILLDOWN + requestBase;
    }

    private void setDataForRequestBase(String goalsRequest) {
        setCounter(counter);
        setDimension(dimension + ",ym:s:datePeriodday");
        setEndingDate(updateDate);
        setStartingDate(updateDate);
        setMetrics(goalsRequest);
    }

}
