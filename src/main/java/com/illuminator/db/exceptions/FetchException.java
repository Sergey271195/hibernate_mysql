package com.illuminator.db.exceptions;

public class FetchException extends Exception {
    public FetchException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
