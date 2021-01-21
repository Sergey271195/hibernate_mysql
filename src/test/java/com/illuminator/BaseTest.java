package com.illuminator;

import processors.fetcher.Fetchable;
import processors.fetcher.FetcherImpl;
import processors.parser.JsonParser;
import processors.parser.JsonParserImpl;

public class BaseTest {

    protected JsonParser parser = new JsonParserImpl();
    protected Fetchable fetcher = new FetcherImpl();

}
