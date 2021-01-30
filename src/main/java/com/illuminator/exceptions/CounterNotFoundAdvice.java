package com.illuminator.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class CounterNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(CounterNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String counterNotFoundHandler(CounterNotFoundException err) {
        return err.getMessage();
    }

}
