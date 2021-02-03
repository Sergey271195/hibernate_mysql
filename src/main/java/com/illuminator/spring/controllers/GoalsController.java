package com.illuminator.spring.controllers;

import com.illuminator.spring.query.PeriodDataRequestModel;
import com.illuminator.spring.repository.GoalAggregationInterface;
import com.illuminator.spring.repository.goalreaches.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Collection;


@RestController
@RequestMapping(path = "/api/goals")
public class GoalsController {

    @Autowired
    private AggSearchEngineGoalReaches aggSearchEngineGoalReaches;

    @Autowired
    private AggAdvEngineGoalReaches aggAdvEngineGoalReaches;

    @Autowired
    private AggSearchPhraseGoalReaches aggSearchPhraseGoalReaches;

    @Autowired
    private AggRefSourceGoalReaches aggRefSourceGoalReaches;

    @Autowired
    private AggSocNetworkGoalReaches aggSocNetworkGoalReaches;

    @Autowired
    private AggTraffSourceGoalReaches aggTraffSourceGoalReaches;


    @PostMapping(path="/agg/search")
    public ResponseEntity<Collection<GoalAggregationInterface>> aggSearchEngine(@RequestBody PeriodDataRequestModel requestModel) {
        return ResponseEntity.ok(aggSearchEngineGoalReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @GetMapping(path="/agg/search")
    public ResponseEntity<Collection<GoalAggregationInterface>> getAggSearchEngine() {
        return ResponseEntity.ok(aggSearchEngineGoalReaches.getAggreatedData(
                Long.valueOf(102),
                LocalDate.parse("2017-01-01"),
                LocalDate.parse("2021-01-01")
        ));
    }

    @PostMapping(path="/agg/ref")
    public ResponseEntity<Collection<GoalAggregationInterface>> aggRefSource(@RequestBody PeriodDataRequestModel requestModel) {
        return ResponseEntity.ok(aggRefSourceGoalReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @GetMapping(path="/agg/ref")
    public ResponseEntity<Collection<GoalAggregationInterface>> getAggRefSource() {
        return ResponseEntity.ok(aggRefSourceGoalReaches.getAggreatedData(
                Long.valueOf(102),
                LocalDate.parse("2017-01-01"),
                LocalDate.parse("2021-01-01")
        ));
    }

    @PostMapping(path="/agg/adv")
    public ResponseEntity<Collection<GoalAggregationInterface>> aggAdvEngine(@RequestBody PeriodDataRequestModel requestModel) {
        return ResponseEntity.ok(aggAdvEngineGoalReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @GetMapping(path="/agg/adv")
    public ResponseEntity<Collection<GoalAggregationInterface>> getAggAdvEngine() {
        return ResponseEntity.ok(aggAdvEngineGoalReaches.getAggreatedData(
                Long.valueOf(102),
                LocalDate.parse("2017-01-01"),
                LocalDate.parse("2021-01-01")
        ));
    }

    @PostMapping(path="/agg/traff")
    public ResponseEntity<Collection<GoalAggregationInterface>> aggTraffSource(@RequestBody PeriodDataRequestModel requestModel) {
        return ResponseEntity.ok(aggTraffSourceGoalReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @GetMapping(path="/agg/traff")
    public ResponseEntity<Collection<GoalAggregationInterface>> getTraffSource() {
        return ResponseEntity.ok(aggTraffSourceGoalReaches.getAggreatedData(
                Long.valueOf(102),
                LocalDate.parse("2017-01-01"),
                LocalDate.parse("2021-01-01")
        ));
    }

    @PostMapping(path="/agg/phrase")
    public ResponseEntity<Collection<GoalAggregationInterface>> aggSearchPhrase(@RequestBody PeriodDataRequestModel requestModel) {
        return ResponseEntity.ok(aggSearchPhraseGoalReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @GetMapping(path="/agg/phrase")
    public ResponseEntity<Collection<GoalAggregationInterface>> getAggSearchPhrase() {
        return ResponseEntity.ok(aggSearchPhraseGoalReaches.getAggreatedData(
                Long.valueOf(102),
                LocalDate.parse("2017-01-01"),
                LocalDate.parse("2021-01-01")
        ));
    }

    @PostMapping(path="/agg/soc")
    public ResponseEntity<Collection<GoalAggregationInterface>> aggSocNetwork(@RequestBody PeriodDataRequestModel requestModel) {
        return ResponseEntity.ok(aggSocNetworkGoalReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @GetMapping(path="/agg/soc")
    public ResponseEntity<Collection<GoalAggregationInterface>> getAggSocNetwork() {
        return ResponseEntity.ok(aggSocNetworkGoalReaches.getAggreatedData(
                Long.valueOf(102),
                LocalDate.parse("2017-01-01"),
                LocalDate.parse("2021-01-01")
        ));
    }


}
