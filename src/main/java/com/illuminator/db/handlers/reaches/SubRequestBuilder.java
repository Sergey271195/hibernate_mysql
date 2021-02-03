package com.illuminator.db.handlers.reaches;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class SubRequestBuilder {

    public static List<Map<String, Object>> createSubRequests(Map<String, Object> responseData, String request) {
        List<Map<String, Object>> data = (List) responseData.get("data");
        return data.stream()
                .filter(entry -> (Boolean) entry.get("expand") == true)
                .map(entry -> createSubRequest((Map) entry.get("dimension"), request))
                .collect(Collectors.toList());
    }

    private static Map<String, Object> createSubRequest(Map dimension, java.lang.String request) {
        Map newMap = new HashMap();
        java.lang.String parentIdName = returnDimensionIdName(dimension);
        java.lang.String subRequest = request + "&parent_id=" + "[%22" + encodeValue(parentIdName) +"%22]";
        newMap.put("request", subRequest);
        newMap.put("dimensions", dimension);
        return newMap;
    }

    private static String returnDimensionIdName(Map<String, String> dimension) {
        return dimension.get("id") == null
                ? dimension.get("name") : dimension.get("id");
    }

    private static String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString()).replace("+", "%20");
        } catch (UnsupportedEncodingException e) {
            System.out.println("UNSUPPORTED ENCODING: " + e.getMessage());
            return value;
        }
    }

}
