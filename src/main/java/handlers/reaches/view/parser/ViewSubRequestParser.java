package handlers.reaches.view.parser;
import java.util.Map;

public class ViewSubRequestParser extends BaseViewParser {

    public final Map<String, String> parentData;

    public ViewSubRequestParser(Map<String, Object> extendedData) {
        super(extendedData);
        parentData = (Map) extendedData.get("dimensions");
    }


}
