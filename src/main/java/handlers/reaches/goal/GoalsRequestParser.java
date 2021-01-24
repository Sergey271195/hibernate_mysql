package handlers.reaches.goal;

import handlers.requestparsers.DataRequestParser;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GoalsRequestParser extends DataRequestParser {

    public final List<LocalDate> timeIntervals;

    public GoalsRequestParser(Map<String, Object> responseData) {
        super(responseData);
        timeIntervals = parseTimeIntervals((List<List<String>>) responseData.get("time_intervals"));
    }

    private List<LocalDate> parseTimeIntervals(List<List<String>> timeIntervals) {
        return timeIntervals.stream()
                .map(interval -> LocalDate.parse(interval.get(0)))
                .collect(Collectors.toList());
    }

    public void parse() {
        System.out.println(this);
        System.out.println("Number of dates entry: " + (fillEndDate.toEpochDay() - fillStartDate.toEpochDay() + 1));
        System.out.println("Number of entries per goal: " + ((List)data.get(0).get("metrics").get(0)).size());
        var mappedMetriks = mapMetricsToDimensions();
        mappedMetriks.keySet().stream()
                .forEach(key -> System.out.println(key + "\n" + mappedMetriks.get(key)));
    }

}
