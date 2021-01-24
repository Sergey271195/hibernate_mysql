package com.illuminator;

import handlers.BaseSessionHandler;
import processors.RequestProcessor;
import processors.fetcher.Fetchable;
import processors.fetcher.FetcherImpl;
import processors.parser.JsonParser;
import processors.parser.JsonParserImpl;

public class BaseTest extends BaseSessionHandler {

    private final JsonParser jsonParser = new JsonParserImpl();
    private final Fetchable fetcher = new FetcherImpl();
    protected final RequestProcessor requestProcessor = new RequestProcessor(jsonParser, fetcher);

}
