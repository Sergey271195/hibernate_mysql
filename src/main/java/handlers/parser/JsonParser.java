package handlers.parser;

import exceptions.JsonParseException;

import java.util.Map;

public interface JsonParser {
    Map<String, Object> parse(String jsonString) throws JsonParseException;
}
