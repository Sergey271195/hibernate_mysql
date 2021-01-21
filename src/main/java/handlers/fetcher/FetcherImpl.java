package handlers.fetcher;

import entity.ApplicationProperties;
import exceptions.FetchException;

import java.io.IOException;
import java.net.ProxySelector;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class FetcherImpl implements Fetchable {

    private HttpRequest createRequest(String url) throws FetchException {
        try {
            URI requestUrl  = new URI(url);
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(requestUrl)
                    .headers("Authorization", "OAuth " + ApplicationProperties.METRIC_TOKEN)
                    .GET()
                    .build();
            return request;
        } catch (URISyntaxException err) {
            throw new FetchException("Incorrect uri syntax: " + url, err);
        }

    }

    private String getResponse(HttpRequest request) throws FetchException {
            try {
                HttpResponse<String> response = HttpClient.newBuilder()
                        .proxy(ProxySelector.getDefault())
                        .build()
                        .send(request, HttpResponse.BodyHandlers.ofString());
                System.out.println("RESPONSE STATUS CODE: " + response.statusCode());
                return response.body();
            } catch (InterruptedException | IOException err) {
                throw new FetchException("Exception while fetching response data" , err);
            }
    }

    @Override
    public String fetch(String url) throws FetchException {
        HttpRequest request = createRequest(url);
        String response = getResponse(request);
        return response;
    }
}
