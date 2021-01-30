package com.illuminator;

import com.illuminator.entity.main.Counter;
import com.illuminator.exceptions.CounterNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api")
public class MainController {
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
