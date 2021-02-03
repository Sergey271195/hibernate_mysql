package com.illuminator.db.handlers;

import com.illuminator.db.entity.main.Counter;

import java.time.LocalDate;

public class BaseRequestBuilder {

    private final static int REQUEST_LIMIT = 100_000;

    private Counter counter;
    private String dimension;
    private LocalDate startingDate;
    private LocalDate endingDate;
    private String metrics;
    private String group;

    protected String buildRequestBase() {
        defaultInitializer();
        StringBuilder requestBuilder = new StringBuilder();
        requestBuilder.append(counter.getMetrikaId())
                .append("&metrics=").append(metrics)
                .append("&dimensions=").append(dimension)
                .append("&group=").append(group)
                .append("&limit=").append(REQUEST_LIMIT)
                .append("&date1=").append(startingDate)
                .append("&date2=").append(endingDate);
        return requestBuilder.toString();
    }

    private void defaultInitializer() {
        group = group == null ? "day": group;
        startingDate = startingDate == null ? LocalDate.now().minusDays(1) : startingDate;
        endingDate = endingDate == null ? LocalDate.now().minusDays(1) : endingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public void setEndingDate(LocalDate endingDate) {
        this.endingDate = endingDate;
    }

    public void setMetrics(String metrics) {
        this.metrics = metrics;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
    }

    public void setCounter(Counter counter) {
        this.counter = counter;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
