package handlers.reaches.view.parser;

import entity.ReachesSuperclass;
import handlers.DimensionsProperties;
import handlers.requestparsers.QueryRequestParser;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BaseViewParser extends QueryRequestParser {

    public List<Class<? extends ReachesSuperclass>> insertTableList;
    public final List<Map<String, Object>> data;

    public BaseViewParser(Map<String, Object> extendedData) {
        super((Map) extendedData.get("query"));
        data = (List) extendedData.get("data");
        insertTableList = mapMetricsToInsertTables();
    }

    private List<Class<? extends ReachesSuperclass>> mapMetricsToInsertTables() {
        return metrics.stream()
                .map(DimensionsProperties.metricStringToViewMap::get)
                .map(map -> (Class<? extends ReachesSuperclass>) map.get(dimension))
                .collect(Collectors.toList());
    }
}