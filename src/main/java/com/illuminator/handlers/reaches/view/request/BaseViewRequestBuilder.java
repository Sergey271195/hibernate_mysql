package com.illuminator.handlers.reaches.view.request;

import com.illuminator.entity.main.Counter;
import com.illuminator.handlers.BaseRequestBuilder;

import java.time.LocalDate;

public abstract class BaseViewRequestBuilder extends BaseRequestBuilder {

    private static String nonCommercialMetrics = "ym:s:visits";
    private static String commercialMetrics = nonCommercialMetrics + ",ym:s:ecommercePurchases,ym:s:productPurchasedPrice";

    private final Counter counter;
    private final String dimension;
    private final LocalDate updateDate;

    public BaseViewRequestBuilder(Counter counter, String dimension, LocalDate updateDate) {
        this.counter = counter;
        this.dimension = dimension;
        this.updateDate = updateDate;
    }

    public abstract String buildRequest();

    protected void setDataForRequestBase() {
        setCounter(counter);
        setDimension(dimension);
        setStartingDate(updateDate);
        setEndingDate(updateDate);
        setMetrics(chooseMetrics());
    }

    private String chooseMetrics() {
        return this.counter.isCommercial()
                ? commercialMetrics
                : nonCommercialMetrics;
    }

}
