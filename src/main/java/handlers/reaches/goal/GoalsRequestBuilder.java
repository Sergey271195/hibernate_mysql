package handlers.reaches.goal;

import entity.ApplicationProperties;
import entity.main.Counter;
import entity.main.Goal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GoalsRequestBuilder {

    private final static int REQUEST_LIMIT = 100_000;
    private final static int REQUEST_BATCH_SIZE = 15;
    private final static LocalDate MIN_FILL_DATE = LocalDate.parse("2015-01-01");

    private final Counter counter;
    private final String dimension;

    public GoalsRequestBuilder(Counter counter, String dimension) {
        this.counter = counter;
        this.dimension = dimension;
    }

    public List<String> createRequest() {
        return createGoalsRequest(counter).stream()
                .map(this::buildRequest)
                .collect(Collectors.toList());
    }

    public static List<String> createGoalsRequest(Counter counter) {
        Set<Goal> counterGoals = counter.getGoals();
        if (counterGoals.size() == 0) return null;
        return counterGoals.size() > REQUEST_BATCH_SIZE
                ? createMultipleBatchRequests(counterGoals)
                : List.of(createBatchRequest(counterGoals));
    }

    private static String createBatchRequest(Set<Goal> goals) {
        return goals.stream()
                .map(goal -> "ym:s:goal" + goal.getMetrikaId() + "reaches")
                .collect(Collectors.joining(","));
    }

    private static List<String> createMultipleBatchRequests(Set<Goal> goals) {
        List<String> batches = new ArrayList<>();
        Set<Goal> subSet = new HashSet<>();
        int counter = 0;
        for (Goal goal: goals) {
            if (counter % REQUEST_BATCH_SIZE == 0 && counter != 0) {
                batches.add(createBatchRequest(subSet));
                subSet = new HashSet<>();
            }
            subSet.add(goal);
            counter++;
        }
        batches.add(createBatchRequest(subSet));
        return batches;
    }

    private String buildRequest(String goalsRequest) {
        String requestBase = buildRequestBase(goalsRequest);
        return ApplicationProperties.JANDEX_DRILLDOWN + requestBase;
    }

    private String buildStatByTimeRequest(String goalsRequest) {
        String requestBase = buildRequestBase(goalsRequest);
        return ApplicationProperties.JANDEX_DRILLDOWN + requestBase;
    }

    private String buildRequestBase(String goalsRequest) {
        LocalDate fillStartDate = getStartDate(counter);
        StringBuilder requestBuilder = new StringBuilder();
        requestBuilder.append(counter.getMetrikaId())
                .append("&metrics=").append(goalsRequest)
                .append("&dimensions=ym:s:lastsign").append(dimension).append(",ym:s:datePeriodday")
                .append("&group=day").append("&limit=").append(REQUEST_LIMIT)
                .append("&date1=").append(fillStartDate)
                .append("&date2=").append("2021-01-24");
        return requestBuilder.toString();
    }

    private static LocalDate getStartDate(Counter counter) {
        return counter.getCreationDate().compareTo(MIN_FILL_DATE) > 0
                ? counter.getCreationDate()
                : MIN_FILL_DATE;
    }

}
