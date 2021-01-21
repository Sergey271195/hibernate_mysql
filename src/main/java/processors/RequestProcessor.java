package processors;

import exceptions.FetchException;
import processors.fetcher.Fetchable;
import processors.parser.JsonParser;

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
