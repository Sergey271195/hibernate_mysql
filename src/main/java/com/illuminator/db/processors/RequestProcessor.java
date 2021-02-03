package com.illuminator.db.processors;

import com.illuminator.db.exceptions.FetchException;
import com.illuminator.db.processors.fetcher.Fetchable;
import com.illuminator.db.processors.parser.JsonParser;

import java.util.Map;

public class RequestProcessor {

    private final JsonParser parser;
    private final Fetchable fetcher;

    public RequestProcessor(JsonParser parser, Fetchable fetcher) {
        this.fetcher = fetcher;
        this.parser = parser;
    }

    public Map<String,Object> process(String url) throws FetchException {
        String response = fetcher.fetch(url);
        return parser.parse(response);
    }

}
