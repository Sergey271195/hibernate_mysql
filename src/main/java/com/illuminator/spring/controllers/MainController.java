package com.illuminator.spring.controllers;

import com.illuminator.db.entity.main.Counter;
import com.illuminator.spring.exceptions.CounterNotFoundException;
import com.illuminator.spring.repository.CounterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api")
public class MainController  {
    @Autowired
    private CounterRepository counterRepository;

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

}
