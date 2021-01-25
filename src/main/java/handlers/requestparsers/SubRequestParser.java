package handlers.requestparsers;

import entity.goal.GoalReachesSuperclass;
import entity.main.Counter;
import entity.main.Goal;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SubRequestParser extends QueryRequestParser {

    public final Counter counter;
    public final List<Map<String, Object>> data;
    public final Map<String, String> parentData;
    public final Class<? extends GoalReachesSuperclass> insertTable;
    public final List<Long> goalIds;

    public SubRequestParser(Counter counter, Map<String, Object> expandedData) {
        super((Map) expandedData.get("query"));
        this.counter = counter;
        this.data = (List) expandedData.get("data");
        this.parentData = (Map) expandedData.get("dimensions");
        this.insertTable = DimensionsProperties.insertTableRegistry.get(dimension);
        this.goalIds = mapMetricsToGoalIds();
    }

    private List<Long> mapMetricsToGoalIds() {
        Pattern goalPattern = Pattern.compile("ym:s:goal(\\d+)reaches");
        return metrics.stream()
                    .map(goal -> goalPattern.matcher(goal))
                    .map(matcher -> {
                        matcher.find();
                        return Long.valueOf(matcher.group(1));
                    }).collect(Collectors.toList());
    }

}
