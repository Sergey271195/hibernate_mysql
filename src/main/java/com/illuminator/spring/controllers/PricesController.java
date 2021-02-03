package com.illuminator.spring.controllers;

import com.illuminator.spring.query.PeriodDataRequestModel;
import com.illuminator.spring.repository.ViewAggregationInterface;
import com.illuminator.spring.repository.pricereaches.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping(path = "/api/prices")
public class PricesController {

    @Autowired
    private AggSearchEnginePriceReaches aggSearchEnginePriceReaches;

    @Autowired
    private AggAdvEnginePriceReaches aggAdvEnginePriceReaches;

    @Autowired
    private AggSearchPhrasePriceReaches aggSearchPhrasePriceReaches;

    @Autowired
    private AggRefSourcePriceReaches aggRefSourcePriceReaches;

    @Autowired
    private AggSocNetworkPriceReaches aggSocNetworkPriceReaches;

    @Autowired
    private AggTraffSourcePriceReaches aggTraffSourcePriceReaches;


    @PostMapping(path="/agg/search")
    public ResponseEntity<Collection<ViewAggregationInterface>> aggSearchEngine(@RequestBody PeriodDataRequestModel requestModel) {
        return ResponseEntity.ok(aggSearchEnginePriceReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @GetMapping(path="/agg/search")
    public ResponseEntity<Collection<ViewAggregationInterface>> getAggSearchEngine() {
        return ResponseEntity.ok(aggSearchEnginePriceReaches.getAggreatedData(
                Long.valueOf(102),
                LocalDate.parse("2017-01-01"),
                LocalDate.parse("2021-01-01")
        ));
    }

    @PostMapping(path="/agg/ref")
    public ResponseEntity<Collection<ViewAggregationInterface>> aggRefSource(@RequestBody PeriodDataRequestModel requestModel) {
        return ResponseEntity.ok(aggRefSourcePriceReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @GetMapping(path="/agg/ref")
    public ResponseEntity<Collection<ViewAggregationInterface>> getAggRefSource() {
        return ResponseEntity.ok(aggRefSourcePriceReaches.getAggreatedData(
                Long.valueOf(102),
                LocalDate.parse("2017-01-01"),
                LocalDate.parse("2021-01-01")
        ));
    }

    @PostMapping(path="/agg/adv")
    public ResponseEntity<Collection<ViewAggregationInterface>> aggAdvEngine(@RequestBody PeriodDataRequestModel requestModel) {
        return ResponseEntity.ok(aggAdvEnginePriceReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @GetMapping(path="/agg/adv")
    public ResponseEntity<Collection<ViewAggregationInterface>> getAggAdvEngine() {
        return ResponseEntity.ok(aggAdvEnginePriceReaches.getAggreatedData(
                Long.valueOf(102),
                LocalDate.parse("2017-01-01"),
                LocalDate.parse("2021-01-01")
        ));
    }

    @PostMapping(path="/agg/traff")
    public ResponseEntity<Collection<ViewAggregationInterface>> aggTraffSource(@RequestBody PeriodDataRequestModel requestModel) {
        return ResponseEntity.ok(aggTraffSourcePriceReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @GetMapping(path="/agg/traff")
    public ResponseEntity<Collection<ViewAggregationInterface>> getTraffSource() {
        return ResponseEntity.ok(aggTraffSourcePriceReaches.getAggreatedData(
                Long.valueOf(102),
                LocalDate.parse("2017-01-01"),
                LocalDate.parse("2021-01-01")
        ));
    }

    @PostMapping(path="/agg/phrase")
    public ResponseEntity<Collection<ViewAggregationInterface>> aggSearchPhrase(@RequestBody PeriodDataRequestModel requestModel) {
        return ResponseEntity.ok(aggSearchPhrasePriceReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @GetMapping(path="/agg/phrase")
    public ResponseEntity<Collection<ViewAggregationInterface>> getAggSearchPhrase() {
        return ResponseEntity.ok(aggSearchPhrasePriceReaches.getAggreatedData(
                Long.valueOf(102),
                LocalDate.parse("2017-01-01"),
                LocalDate.parse("2021-01-01")
        ));
    }

    @PostMapping(path="/agg/soc")
    public ResponseEntity<Collection<ViewAggregationInterface>> aggSocNetwork(@RequestBody PeriodDataRequestModel requestModel) {
        return ResponseEntity.ok(aggSocNetworkPriceReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @GetMapping(path="/agg/soc")
    public ResponseEntity<Collection<ViewAggregationInterface>> getAggSocNetwork() {
        return ResponseEntity.ok(aggSocNetworkPriceReaches.getAggreatedData(
                Long.valueOf(102),
                LocalDate.parse("2017-01-01"),
                LocalDate.parse("2021-01-01")
        ));
    }

}
