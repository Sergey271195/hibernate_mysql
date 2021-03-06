package com.illuminator.handlers.reaches.goal.request;

import com.illuminator.entity.ApplicationProperties;
import com.illuminator.entity.main.Counter;

import java.time.LocalDate;

public class DrilldownGoalFillRequestBuilder extends BaseGoalsRequestBuilder {

    private final static LocalDate MIN_FILL_DATE = LocalDate.parse("2017-01-01");

    private final Counter counter;
    private final String dimension;

    public DrilldownGoalFillRequestBuilder(Counter counter, String dimension) {
        super(counter);
        this.counter = counter;
        this.dimension = dimension;
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
        setEndingDate(LocalDate.parse("2021-01-24"));
        setStartingDate(getStartDate(counter));
        setMetrics(goalsRequest);
    }

    private static LocalDate getStartDate(Counter counter) {
        return counter.getCreationDate().compareTo(MIN_FILL_DATE) > 0
                ? counter.getCreationDate()
                : MIN_FILL_DATE;
    }

}
