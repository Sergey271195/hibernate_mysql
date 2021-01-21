package handlers;

import entity.source.TrafficSource;
import handlers.fetcher.Fetchable;
import handlers.parser.JsonParser;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import utils.DbConnectionFactory;

import java.util.Optional;
import java.util.function.Supplier;

public class BaseRequestHandler extends BaseSessionHandler {

    protected final Fetchable fetcher;
    protected final JsonParser parser;

    public BaseRequestHandler(Fetchable fetcher, JsonParser parser) {
        this.fetcher = fetcher;
        this.parser = parser;
    }

}
