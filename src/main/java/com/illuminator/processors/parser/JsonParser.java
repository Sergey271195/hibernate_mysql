package com.illuminator.processors.parser;

import com.illuminator.exceptions.JsonParseException;

import java.util.Map;

public interface JsonParser {
    Map<String, Object> parse(String jsonString) throws JsonParseException;
}
