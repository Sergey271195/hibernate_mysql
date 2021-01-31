package com.illuminator;

import com.illuminator.entity.main.Counter;
import com.illuminator.exceptions.CounterNotFoundException;
import com.illuminator.spring.query.PeriodDataReuqestModel;
import com.illuminator.spring.repository.AggregationInterface;
import com.illuminator.spring.repository.goalreaches.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.illuminator.spring.repository.CounterRepository;

import java.time.LocalDate;
import java.util.Collection;


@RestController
@RequestMapping(path = "/api")
public class MainController {
    @Autowired
    private CounterRepository counterRepository;

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

    @GetMapping(path="/counters")
    public ResponseEntity<Iterable<Counter>> getAllCounters() {
        return ResponseEntity.ok(counterRepository.findAll());
    }

    @GetMapping(path="/counters/{id}")
    public ResponseEntity<Counter> getCounter(@PathVariable Long id) {
        Counter counter =  counterRepository.findById(id)
                .orElseThrow(() -> new CounterNotFoundException(id));
        return ResponseEntity.ok().body(counter);
    }

    @GetMapping(path="/goals/{id}")
    public ResponseEntity<Collection<AggregationInterface>> getAggregate(@PathVariable Long id) {
        return ResponseEntity.ok(aggSearchEngineGoalReaches.getAggreatedData(
                id, LocalDate.parse("2017-01-01"), LocalDate.parse("2021-01-30")
        ));
    }

    @PostMapping(path="/agg/search")
    public ResponseEntity<Collection<AggregationInterface>> aggSearchEngine(@RequestBody PeriodDataReuqestModel requestModel) {
        return ResponseEntity.ok(aggSearchEngineGoalReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @PostMapping(path="/agg/ref")
    public ResponseEntity<Collection<AggregationInterface>> aggRefSource(@RequestBody PeriodDataReuqestModel requestModel) {
        return ResponseEntity.ok(aggRefSourceGoalReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @PostMapping(path="/agg/adv")
    public ResponseEntity<Collection<AggregationInterface>> aggAdvEngine(@RequestBody PeriodDataReuqestModel requestModel) {
        return ResponseEntity.ok(aggAdvEngineGoalReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @PostMapping(path="/agg/traff")
    public ResponseEntity<Collection<AggregationInterface>> aggTraffSource(@RequestBody PeriodDataReuqestModel requestModel) {
        return ResponseEntity.ok(aggTraffSourceGoalReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @PostMapping(path="/agg/phrase")
    public ResponseEntity<Collection<AggregationInterface>> aggSearchPhrase(@RequestBody PeriodDataReuqestModel requestModel) {
        return ResponseEntity.ok(aggSearchPhraseGoalReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }

    @PostMapping(path="/agg/soc")
    public ResponseEntity<Collection<AggregationInterface>> aggSocNetwork(@RequestBody PeriodDataReuqestModel requestModel) {
        return ResponseEntity.ok(aggSocNetworkGoalReaches.getAggreatedData(
                requestModel.getId(),
                requestModel.getStartDate(),
                requestModel.getEndDate()
        ));
    }


}
