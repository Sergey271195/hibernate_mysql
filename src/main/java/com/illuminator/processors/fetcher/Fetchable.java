package com.illuminator.processors.fetcher;

import com.illuminator.exceptions.FetchException;

public interface Fetchable {
    String fetch(String url) throws FetchException;
}
