package handlers.requestparsers;

import entity.ApplicationProperties;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DrilldownRequestParser extends QueryRequestParser {

    public final List<Map<String, Object>> data;
    public final String subRequestBase;

    public DrilldownRequestParser(Map<String, Object> responseData) {
        super((Map) responseData.get("query"));
        data = (List<Map<String, Object>>) responseData.get("data");
        subRequestBase = createSubRequestBase();
    }

    public List<Map<String, Object>> createSubRequests() {
        return data.stream()
                .filter(entry -> (Boolean) entry.get("expand") == true)
                .map(entry -> appendParentToSubRequest((Map) entry.get("dimension")))
                .collect(Collectors.toList());
    }

    private String createSubRequestBase() {
        StringBuilder subRequestBuilder = new StringBuilder();
        subRequestBuilder.append(ApplicationProperties.JANDEX_DRILLDOWN)
                .append(ids).append("&group=day&metrics=")
                .append(metrics.stream().collect(Collectors.joining(",")))
                .append("&dimensions=").append(dimensionsList.stream().collect(Collectors.joining(",")))
                .append("&date1=").append(fillStartDate)
                .append("&date2=").append(fillEndDate)
                .append("&limit=100000");
        return subRequestBuilder.toString();
    }

    private Map<String, Object> appendParentToSubRequest(Map dimension) {
        Map newMap = new HashMap();
        String parentIdName = returnDimensionIdName(dimension);
        String subRequest = subRequestBase + "&parent_id=" + "[%22" + parentIdName.replace(" ", "%20") +"%22]";
        newMap.put("request", subRequest);
        newMap.put("dimensions", dimension);
        return newMap;
    }

    private String returnDimensionIdName(Map<String, String> dimension) {
        return dimension.get("id") == null
                ? dimension.get("name") : dimension.get("id");
    }

}
