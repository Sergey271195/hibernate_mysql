package processors.fetcher;

import exceptions.FetchException;

public interface Fetchable {
    String fetch(String url) throws FetchException;
}
