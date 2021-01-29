package handlers.reaches.view;

import entity.ApplicationProperties;
import entity.main.Counter;
import handlers.BaseRequestBuilder;

import java.time.LocalDate;

public class ViewRequestBuilder extends BaseRequestBuilder {

    private static String nonCommercialMetrics = "ym:s:visits";
    private static String commercialMetrics = nonCommercialMetrics + ",ym:s:ecommercePurchases,ym:s:productPurchasedPrice";

    private final Counter counter;
    private final String dimension;
    private final LocalDate updateDate;

    public ViewRequestBuilder(Counter counter, String dimension, LocalDate updateDate) {
        this.counter = counter;
        this.dimension = dimension;
        this.updateDate = updateDate;
    }

    public String buildRequest() {
        setDataForRequestBase();
        String requestBase = buildRequestBase();
        return ApplicationProperties.JANDEX_STAT_BY_TIME + requestBase;
    }

    private void setDataForRequestBase() {
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
