package com.illuminator.spring.repository;

import com.illuminator.entity.main.Counter;
import org.springframework.data.repository.CrudRepository;

public interface CounterRepository extends CrudRepository<Counter, Long> {
    Counter findById(long id);
}
