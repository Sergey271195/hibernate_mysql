package com.illuminator.exceptions;

public class JsonParseException extends FetchException {
    public JsonParseException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
