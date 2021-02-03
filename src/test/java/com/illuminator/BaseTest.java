package com.illuminator;

import com.illuminator.db.handlers.BaseSessionHandler;
import com.illuminator.db.processors.RequestProcessor;
import com.illuminator.db.processors.fetcher.Fetchable;
import com.illuminator.db.processors.fetcher.FetcherImpl;
import com.illuminator.db.processors.parser.JsonParser;
import com.illuminator.db.processors.parser.JsonParserImpl;

public class BaseTest extends BaseSessionHandler {

    private final JsonParser jsonParser = new JsonParserImpl();
    private final Fetchable fetcher = new FetcherImpl();
    protected final RequestProcessor requestProcessor = new RequestProcessor(jsonParser, fetcher);

}
