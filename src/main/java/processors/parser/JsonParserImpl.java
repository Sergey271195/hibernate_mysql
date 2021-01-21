package processors.parser;

import exceptions.JsonParseException;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.Map;

public class JsonParserImpl implements JsonParser {
    @Override
    public Map<String, Object> parse(String jsonString) throws JsonParseException {
        try {
            return (Map<String, Object>) new JSONParser().parse(jsonString);
        } catch (ParseException err) {
            throw new JsonParseException("Json parse exception. Parsed string:\n" + jsonString, err);
        }
    }
}
