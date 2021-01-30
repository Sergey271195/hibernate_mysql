package com.illuminator;

import com.illuminator.handlers.BaseSessionHandler;
import com.illuminator.processors.RequestProcessor;
import com.illuminator.processors.fetcher.Fetchable;
import com.illuminator.processors.fetcher.FetcherImpl;
import com.illuminator.processors.parser.JsonParser;
import com.illuminator.processors.parser.JsonParserImpl;

public class BaseTest extends BaseSessionHandler {

    private final JsonParser jsonParser = new JsonParserImpl();
    private final Fetchable fetcher = new FetcherImpl();
    protected final RequestProcessor requestProcessor = new RequestProcessor(jsonParser, fetcher);

}
