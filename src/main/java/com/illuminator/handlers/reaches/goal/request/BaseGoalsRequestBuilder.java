package com.illuminator.handlers.reaches.goal.request;

import com.illuminator.entity.main.Counter;
import com.illuminator.entity.main.Goal;
import com.illuminator.handlers.BaseRequestBuilder;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public abstract class BaseGoalsRequestBuilder extends BaseRequestBuilder {

    private final static int REQUEST_BATCH_SIZE = 15;

    private final Counter counter;

    public BaseGoalsRequestBuilder(Counter counter) {
        this.counter = counter;
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

    protected abstract String buildRequest(String goalsRequest);

}
