package com.illuminator.spring.controllers;

import com.illuminator.spring.query.PeriodDataRequestModel;
import com.illuminator.spring.repository.ViewAggregationInterface;
import com.illuminator.spring.repository.viewreaches.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;

@RestController
@RequestMapping(path = "/api/views")
public class ViewsController {

    @Autowired
    private AggSearchEngineViewReaches aggSearchEngineViewReaches;

    @Autowired
    private AggAdvEngineViewReaches aggAdvEngineViewReaches;

    @Autowired
    private AggSearchPhraseViewReaches aggSearchPhraseViewReaches;

    @Autowired
    private AggRefSourceViewReaches aggRefSourceViewReaches;

    @Autowired
    private AggSocNetworkViewReaches aggSocNetworkViewReaches;

    @Autowired
    private AggTraffSourceViewReaches aggTraffSourceViewReaches;


    @PostMapping(path="/agg/search")
    public ResponseEntity<Collection<ViewAggregationInterface>> aggSearchEngine(@RequestBody PeriodDataRequestModel requestModel) {
        return ResponseEntity.ok(aggSearchEngineViewReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @GetMapping(path="/agg/search")
    public ResponseEntity<Collection<ViewAggregationInterface>> getAggSearchEngine() {
        return ResponseEntity.ok(aggSearchEngineViewReaches.getAggreatedData(
                Long.valueOf(102),
                LocalDate.parse("2017-01-01"),
                LocalDate.parse("2021-01-01")
        ));
    }

    @PostMapping(path="/agg/ref")
    public ResponseEntity<Collection<ViewAggregationInterface>> aggRefSource(@RequestBody PeriodDataRequestModel requestModel) {
        return ResponseEntity.ok(aggRefSourceViewReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @GetMapping(path="/agg/ref")
    public ResponseEntity<Collection<ViewAggregationInterface>> getAggRefSource() {
        return ResponseEntity.ok(aggRefSourceViewReaches.getAggreatedData(
                Long.valueOf(102),
                LocalDate.parse("2017-01-01"),
                LocalDate.parse("2021-01-01")
        ));
    }

    @PostMapping(path="/agg/adv")
    public ResponseEntity<Collection<ViewAggregationInterface>> aggAdvEngine(@RequestBody PeriodDataRequestModel requestModel) {
        return ResponseEntity.ok(aggAdvEngineViewReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @GetMapping(path="/agg/adv")
    public ResponseEntity<Collection<ViewAggregationInterface>> getAggAdvEngine() {
        return ResponseEntity.ok(aggAdvEngineViewReaches.getAggreatedData(
                Long.valueOf(102),
                LocalDate.parse("2017-01-01"),
                LocalDate.parse("2021-01-01")
        ));
    }

    @PostMapping(path="/agg/traff")
    public ResponseEntity<Collection<ViewAggregationInterface>> aggTraffSource(@RequestBody PeriodDataRequestModel requestModel) {
        return ResponseEntity.ok(aggTraffSourceViewReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @GetMapping(path="/agg/traff")
    public ResponseEntity<Collection<ViewAggregationInterface>> getTraffSource() {
        return ResponseEntity.ok(aggTraffSourceViewReaches.getAggreatedData(
                Long.valueOf(102),
                LocalDate.parse("2017-01-01"),
                LocalDate.parse("2021-01-01")
        ));
    }

    @PostMapping(path="/agg/phrase")
    public ResponseEntity<Collection<ViewAggregationInterface>> aggSearchPhrase(@RequestBody PeriodDataRequestModel requestModel) {
        return ResponseEntity.ok(aggSearchPhraseViewReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @GetMapping(path="/agg/phrase")
    public ResponseEntity<Collection<ViewAggregationInterface>> getAggSearchPhrase() {
        return ResponseEntity.ok(aggSearchPhraseViewReaches.getAggreatedData(
                Long.valueOf(102),
                LocalDate.parse("2017-01-01"),
                LocalDate.parse("2021-01-01")
        ));
    }

    @PostMapping(path="/agg/soc")
    public ResponseEntity<Collection<ViewAggregationInterface>> aggSocNetwork(@RequestBody PeriodDataRequestModel requestModel) {
        return ResponseEntity.ok(aggSocNetworkViewReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @GetMapping(path="/agg/soc")
    public ResponseEntity<Collection<ViewAggregationInterface>> getAggSocNetwork() {
        return ResponseEntity.ok(aggSocNetworkViewReaches.getAggreatedData(
                Long.valueOf(102),
                LocalDate.parse("2017-01-01"),
                LocalDate.parse("2021-01-01")
        ));
    }

}
