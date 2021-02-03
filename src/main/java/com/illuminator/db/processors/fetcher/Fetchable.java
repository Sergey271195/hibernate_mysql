package com.illuminator.db.processors.fetcher;

import com.illuminator.db.exceptions.FetchException;

public interface Fetchable {
    String fetch(String url) throws FetchException;
}
