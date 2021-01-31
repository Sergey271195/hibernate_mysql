package com.illuminator.handlers.requestparsers;

import com.illuminator.entity.goal.GoalReachesSuperclass;
import com.illuminator.handlers.DimensionsProperties;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GoalsSubRequestParser extends QueryRequestParser {

    public final List<Map<String, Object>> data;
    public final Map<String, String> parentData;
    public final Class<? extends GoalReachesSuperclass> insertTable;
    public final List<Long> goalIds;

    public GoalsSubRequestParser(Map<String, Object> expandedData) {
        super((Map) expandedData.get("com/illuminator/spring/query"));
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
