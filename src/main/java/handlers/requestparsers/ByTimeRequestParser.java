package handlers.requestparsers;

import entity.source.SourceSuperclass;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ByTimeRequestParser extends GoalsSubRequestParser {

    public final List<LocalDate> timeIntervals;
    public final List<Map<String, List>> data;

    public ByTimeRequestParser(Map<String, Object> responseData) {
        super(responseData);
        timeIntervals = parseTimeIntervals((List<List<String>>) responseData.get("time_intervals"));
        data = (List<Map<String, List>>) responseData.get("data");
    }

    private List<LocalDate> parseTimeIntervals(List<List<String>> timeIntervals) {
        return timeIntervals.stream()
                .map(interval -> LocalDate.parse(interval.get(0)))
                .collect(Collectors.toList());
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
