package com.illuminator;

import com.illuminator.entity.main.Counter;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CounterRepository extends CrudRepository<Counter, Long> {
    Counter findById(long id);
}
