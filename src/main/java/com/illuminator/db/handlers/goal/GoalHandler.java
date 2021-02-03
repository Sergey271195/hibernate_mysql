package com.illuminator.db.handlers.goal;

import com.illuminator.db.dao.GoalDao;
import com.illuminator.db.entity.ApplicationProperties;
import com.illuminator.db.entity.main.Counter;
import com.illuminator.db.entity.main.Goal;
import com.illuminator.db.exceptions.FetchException;
import com.illuminator.db.handlers.BaseRequestHandler;
import com.illuminator.db.processors.RequestProcessor;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GoalHandler extends BaseRequestHandler {

    private final Counter counter;
    private final GoalDao goalDao = new GoalDao(sessionFactory);
    
    public GoalHandler(RequestProcessor requestProcessor, Counter counter) {
        super(requestProcessor);
        this.counter = counter;
    }

    public void refreshGoals() {
        doInTransaction(() -> {
            try {
                List<Map<String, Object>> responseData = fetchGoalsFromMetirka(counter);
                Map<Long, Goal> dbGoals = putDbGoalsToMap(counter);
                persistNewGoals(responseData, dbGoals);
            } catch (FetchException err) {
                System.out.println("[FETCHING GOALS FROM METRIKA ERROR] " + err.getMessage());
            }
        });
    }
    
    private List<Map<String, Object>> fetchGoalsFromMetirka(Counter counter) throws FetchException {
        Map<String, Object> goalsData = requestProcessor.process(
                ApplicationProperties.GOAL_BASE_URI + counter.getMetrikaId() + "?field=goals"
        );
        return (List<Map<String, Object>>) ((Map) goalsData.get("counter")).get("goals");
    }

    private Map<Long, Goal> putDbGoalsToMap(Counter counter) {
        return counter.getGoals().stream()
                    .peek(goal -> System.out.println(goal))
                    .collect(Collectors.toMap(
                            Goal::getMetrikaId, Function.identity()
                    ));
    }

    private void persistNewGoals(List<Map<String, Object>> responseData, Map<Long, Goal> dbGoals) {
        responseData.stream()
                .filter(goalData ->
                        !dbGoals.containsKey(GoalRequestParser.getMetriakId(goalData))
                ).map(this::createGoal)
                .forEach(goal -> System.out.println("Persisted new goal: " + goal));
    }

    private Goal createGoal(Map<String, Object> goalData) {
        Goal goal = new Goal();
        goal.setCounter(counter);
        goal.setMetrikaId(GoalRequestParser.getMetriakId(goalData));
        goal.setName(GoalRequestParser.getName(goalData));
        goal.setType(GoalRequestParser.getType(goalData));
        goal.setRelevant(true);
        goalDao.save(goal);
        return goal;
    }

}

class GoalRequestParser {

    public static Long getMetriakId(Map<String, Object> goalData) {
            return (Long) goalData.get("id");
    }

    public static String getName(Map<String, Object> goalData) {
            return (String) goalData.get("name");
    }

    public static String getType(Map<String, Object> goalData) {
            return (String) goalData.get("type");
    }

}