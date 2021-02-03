package com.illuminator.db.handlers.reaches.view.request;

import com.illuminator.db.entity.ApplicationProperties;
import com.illuminator.db.entity.main.Counter;

import java.time.LocalDate;

public class DrilldownViewRequestBuilder extends BaseViewRequestBuilder {

    public DrilldownViewRequestBuilder(Counter counter, String dimension, LocalDate updateDate) {
        super(counter, dimension+ ",ym:s:datePeriodday", updateDate);
    }

    @Override
    public String buildRequest() {
        setDataForRequestBase();
        String requestBase = buildRequestBase();
        return ApplicationProperties.JANDEX_DRILLDOWN + requestBase;
    }
}
