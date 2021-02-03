package com.illuminator.db.processors.parser;

import com.illuminator.db.exceptions.JsonParseException;

import java.util.Map;

public interface JsonParser {
    Map<String, Object> parse(String jsonString) throws JsonParseException;
}
