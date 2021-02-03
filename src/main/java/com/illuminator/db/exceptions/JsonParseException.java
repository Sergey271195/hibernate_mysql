package com.illuminator.db.exceptions;

public class JsonParseException extends FetchException {
    public JsonParseException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
