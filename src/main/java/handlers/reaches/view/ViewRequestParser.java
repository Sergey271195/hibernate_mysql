package handlers.reaches.view;

import java.lang.Object;

import entity.ReachesSuperclass;
import handlers.DimensionsProperties;
import handlers.requestparsers.QueryRequestParser;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ViewRequestParser extends QueryRequestParser {

    public List<Class<? extends ReachesSuperclass>> insertTableList;
    public final List<Map<String, List>> data;

    public ViewRequestParser(Map<String, Object> responseData) {
        super((Map) responseData.get("query"));
        insertTableList = mapMetricsToInsertTables();
        data = (List) responseData.get("data");
    }

    private List<Class<? extends ReachesSuperclass>> mapMetricsToInsertTables() {
        return metrics.stream()
                .map(DimensionsProperties.metricStringToViewMap::get)
                .map(map -> (Class<? extends ReachesSuperclass>) map.get(dimension))
                .collect(Collectors.toList());
    }



}
