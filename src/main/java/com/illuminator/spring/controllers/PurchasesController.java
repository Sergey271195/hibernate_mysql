package com.illuminator.spring.controllers;

import com.illuminator.spring.query.PeriodDataRequestModel;
import com.illuminator.spring.repository.ViewAggregationInterface;
import com.illuminator.spring.repository.pricereaches.*;
import com.illuminator.spring.repository.purchasereaches.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping(path = "/api/purchases")
public class PurchasesController {

    @Autowired
    private AggSearchEnginePurchaseReaches aggSearchEnginePurchaseReaches;

    @Autowired
    private AggAdvEnginePurchaseReaches aggAdvEnginePurchaseReaches;

    @Autowired
    private AggSearchPhrasePurchaseReaches aggSearchPhrasePurchaseReaches;

    @Autowired
    private AggRefSourcePurchaseReaches aggRefSourcePurchaseReaches;

    @Autowired
    private AggSocNetworkPurchaseReaches aggSocNetworkPurchaseReaches;

    @Autowired
    private AggTraffSourcePurchaseReaches aggTraffSourcePurchaseReaches;


    @PostMapping(path="/agg/search")
    public ResponseEntity<Collection<ViewAggregationInterface>> aggSearchEngine(@RequestBody PeriodDataRequestModel requestModel) {
        return ResponseEntity.ok(aggSearchEnginePurchaseReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @GetMapping(path="/agg/search")
    public ResponseEntity<Collection<ViewAggregationInterface>> getAggSearchEngine() {
        return ResponseEntity.ok(aggSearchEnginePurchaseReaches.getAggreatedData(
                Long.valueOf(102),
                LocalDate.parse("2017-01-01"),
                LocalDate.parse("2021-01-01")
        ));
    }

    @PostMapping(path="/agg/ref")
    public ResponseEntity<Collection<ViewAggregationInterface>> aggRefSource(@RequestBody PeriodDataRequestModel requestModel) {
        return ResponseEntity.ok(aggRefSourcePurchaseReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @GetMapping(path="/agg/ref")
    public ResponseEntity<Collection<ViewAggregationInterface>> getAggRefSource() {
        return ResponseEntity.ok(aggRefSourcePurchaseReaches.getAggreatedData(
                Long.valueOf(102),
                LocalDate.parse("2017-01-01"),
                LocalDate.parse("2021-01-01")
        ));
    }

    @PostMapping(path="/agg/adv")
    public ResponseEntity<Collection<ViewAggregationInterface>> aggAdvEngine(@RequestBody PeriodDataRequestModel requestModel) {
        return ResponseEntity.ok(aggAdvEnginePurchaseReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @GetMapping(path="/agg/adv")
    public ResponseEntity<Collection<ViewAggregationInterface>> getAggAdvEngine() {
        return ResponseEntity.ok(aggAdvEnginePurchaseReaches.getAggreatedData(
                Long.valueOf(102),
                LocalDate.parse("2017-01-01"),
                LocalDate.parse("2021-01-01")
        ));
    }

    @PostMapping(path="/agg/traff")
    public ResponseEntity<Collection<ViewAggregationInterface>> aggTraffSource(@RequestBody PeriodDataRequestModel requestModel) {
        return ResponseEntity.ok(aggTraffSourcePurchaseReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @GetMapping(path="/agg/traff")
    public ResponseEntity<Collection<ViewAggregationInterface>> getTraffSource() {
        return ResponseEntity.ok(aggTraffSourcePurchaseReaches.getAggreatedData(
                Long.valueOf(102),
                LocalDate.parse("2017-01-01"),
                LocalDate.parse("2021-01-01")
        ));
    }

    @PostMapping(path="/agg/phrase")
    public ResponseEntity<Collection<ViewAggregationInterface>> aggSearchPhrase(@RequestBody PeriodDataRequestModel requestModel) {
        return ResponseEntity.ok(aggSearchPhrasePurchaseReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @GetMapping(path="/agg/phrase")
    public ResponseEntity<Collection<ViewAggregationInterface>> getAggSearchPhrase() {
        return ResponseEntity.ok(aggSearchPhrasePurchaseReaches.getAggreatedData(
                Long.valueOf(102),
                LocalDate.parse("2017-01-01"),
                LocalDate.parse("2021-01-01")
        ));
    }

    @PostMapping(path="/agg/soc")
    public ResponseEntity<Collection<ViewAggregationInterface>> aggSocNetwork(@RequestBody PeriodDataRequestModel requestModel) {
        return ResponseEntity.ok(aggSocNetworkPurchaseReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @GetMapping(path="/agg/soc")
    public ResponseEntity<Collection<ViewAggregationInterface>> getAggSocNetwork() {
        return ResponseEntity.ok(aggSocNetworkPurchaseReaches.getAggreatedData(
                Long.valueOf(102),
                LocalDate.parse("2017-01-01"),
                LocalDate.parse("2021-01-01")
        ));
    }

}
