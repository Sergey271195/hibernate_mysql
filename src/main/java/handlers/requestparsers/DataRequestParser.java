package handlers.requestparsers;

import entity.source.SourceSuperclass;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DataRequestParser extends QueryRequestParser {

    public final List<Map<String, List>> data;

    public DataRequestParser(Map<String, Object> responseData) {
        super((Map) responseData.get("query"));
        data = (List<Map<String, List>>) responseData.get("data");
    }

    public Map<? extends SourceSuperclass, List<List<Double>>> mapMetricsToDimensions() {
        return data.stream()
                .peek(s -> System.out.println(s))
                .collect(Collectors.toMap(
                        entry -> getNewSourceInstance()
                                .createSourceFromMetrikData((Map) entry.get("dimensions").get(0)),
                        entry -> entry.get("metrics")
                ));
    }

}
