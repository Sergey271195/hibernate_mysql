package com.illuminator.handlers;

import com.illuminator.BaseTest;
import com.illuminator.entity.main.Counter;
import com.illuminator.entity.main.Goal;
import com.illuminator.handlers.reaches.goal.request.DrilldownGoalFillRequestBuilder;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GoalsRequestBuilderTest extends BaseTest {

    @Test
    void createGoalsRequestTest_31() {

        Counter counter = new Counter();
        counter.setMetrikaId(1234L);

        Set<Goal> goals_31 = createNGoals(31);
        counter.setGoals(goals_31);

        List<String> goalRequest = DrilldownGoalFillRequestBuilder.createGoalsRequest(counter);
        Long goalsNumber = numberOfGoalsInRequest(goalRequest);

        Assert.assertEquals(goalRequest.size(), 3);
        Assert.assertEquals(goalsNumber, Long.valueOf(31));

    }

    @Test
    void createGoalsRequestTest_30() {

        Counter counter = new Counter();
        counter.setMetrikaId(1234L);

        Set<Goal> goals_30 = createNGoals(30);
        counter.setGoals(goals_30);

        List<String> goalRequest = DrilldownGoalFillRequestBuilder.createGoalsRequest(counter);
        Long goalsNumber = numberOfGoalsInRequest(goalRequest);

        Assert.assertEquals(goalsNumber, Long.valueOf(30));
        Assert.assertEquals(goalRequest.size(), 2);
    }

    @Test
    void createGoalsRequestTest_29() {

        Counter counter = new Counter();
        counter.setMetrikaId(1234L);

        Set<Goal> goals_29 = createNGoals(29);
        counter.setGoals(goals_29);

        List<String> goalRequest = DrilldownGoalFillRequestBuilder.createGoalsRequest(counter);
        Long goalsNumber = numberOfGoalsInRequest(goalRequest);

        Assert.assertEquals(goalRequest.size(), 2);
        Assert.assertEquals(goalsNumber, Long.valueOf(29));

    }

    @Test
    void createGoalsRequestTest_10() {

        Counter counter = new Counter();
        counter.setMetrikaId(1234L);

        Set<Goal> goals_10 = createNGoals(10);
        counter.setGoals(goals_10);

        List<String> goalRequest = DrilldownGoalFillRequestBuilder.createGoalsRequest(counter);
        Long goalsNumber = numberOfGoalsInRequest(goalRequest);

        Assert.assertEquals(goalRequest.size(), 1);
        Assert.assertEquals(goalsNumber, Long.valueOf(10));

    }

    @Test
    void createGoalsRequestTest_15() {

        Counter counter = new Counter();
        counter.setMetrikaId(1234L);

        Set<Goal> goals_15 = createNGoals(15);
        counter.setGoals(goals_15);

        List<String> goalRequest = DrilldownGoalFillRequestBuilder.createGoalsRequest(counter);
        Long goalsNumber = numberOfGoalsInRequest(goalRequest);

        Assert.assertEquals(goalRequest.size(), 1);
        Assert.assertEquals(goalsNumber, Long.valueOf(15));

    }

    @Test
    void createGoalsRequestTest_0() {

        Counter counter = new Counter();
        counter.setMetrikaId(1234L);

        Set<Goal> goals_0 = createNGoals(0);
        counter.setGoals(goals_0);
        List<String> goalRequest = DrilldownGoalFillRequestBuilder.createGoalsRequest(counter);

        Assert.assertNull(goalRequest);

    }

    @Test
    void createGoalsRequestTest_18() {

        Counter counter = new Counter();
        counter.setMetrikaId(1234L);

        Set<Goal> goals_18 = createNGoals(18);
        counter.setGoals(goals_18);

        List<String> goalRequest = DrilldownGoalFillRequestBuilder.createGoalsRequest(counter);
        Long goalsNumber = numberOfGoalsInRequest(goalRequest);

        Assert.assertEquals(goalRequest.size(), 2);
        Assert.assertEquals(goalsNumber, Long.valueOf(18));

    }

    private Set<Goal> createNGoals(int goalsNumber) {
        return IntStream.range(0, goalsNumber)
                .mapToObj(i -> {
                    Goal goal = new Goal();
                    goal.setMetrikaId(i);
                    return goal;
                }).collect(Collectors.toSet());
    }

    private Long numberOfGoalsInRequest(List<String> goalRequest) {
        return goalRequest.stream()
                .map(string -> string.split(","))
                .flatMap(strings -> Arrays.stream(strings))
                .collect(Collectors.counting());
    }

}
