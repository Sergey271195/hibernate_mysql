package com.illuminator.spring.exceptions;

public class CounterNotFoundException extends RuntimeException {

    public CounterNotFoundException(Long id) {
        super("Unable to find counter " + id);
    }

}
