package com.illuminator.spring.query;

import java.time.LocalDate;

public class PeriodDataRequestModel {

    private Long id;
    private LocalDate startDate;
    private LocalDate endDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "ReuqestModel[id=" + id + ", startDate=" + startDate + ", endDate=" + endDate +']';
    }
}
