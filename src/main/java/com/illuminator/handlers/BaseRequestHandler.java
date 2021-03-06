package com.illuminator.handlers;

import com.illuminator.processors.RequestProcessor;

public class BaseRequestHandler extends BaseSessionHandler {

    protected final RequestProcessor requestProcessor;

    public BaseRequestHandler(RequestProcessor requestProcessor) {
        this.requestProcessor = requestProcessor;
    }

}
