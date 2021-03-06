package com.illuminator.handlers.reaches.view.request;

import com.illuminator.entity.ApplicationProperties;
import com.illuminator.entity.main.Counter;

import java.time.LocalDate;

public class ByTimeViewRequestBuilder extends BaseViewRequestBuilder {

    public ByTimeViewRequestBuilder(Counter counter, String dimension, LocalDate updateDate) {
        super(counter, dimension, updateDate);
    }

    @Override
    public String buildRequest() {
        setDataForRequestBase();
        String requestBase = buildRequestBase();
        return ApplicationProperties.JANDEX_STAT_BY_TIME + requestBase;
    }

}
