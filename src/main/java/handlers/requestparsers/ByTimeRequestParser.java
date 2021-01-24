package handlers.requestparsers;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ByTimeRequestParser extends DataRequestParser {

    public final List<LocalDate> timeIntervals;

    public ByTimeRequestParser(Map<String, Object> responseData) {
        super(responseData);
        timeIntervals = parseTimeIntervals((List<List<String>>) responseData.get("time_intervals"));
    }

    private List<LocalDate> parseTimeIntervals(List<List<String>> timeIntervals) {
        return timeIntervals.stream()
                .map(interval -> LocalDate.parse(interval.get(0)))
                .collect(Collectors.toList());
    }

}
