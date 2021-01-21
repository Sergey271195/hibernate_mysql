package handlers.goal;

import entity.ApplicationProperties;
import entity.main.Counter;
import entity.main.Goal;
import exceptions.FetchException;
import handlers.BaseRequestHandler;
import handlers.fetcher.Fetchable;
import handlers.parser.JsonParser;

import java.util.List;
import java.util.Map;

public class GoalHandler extends BaseRequestHandler {

    private Counter counter;
    
    public GoalHandler(Fetchable fetcher, JsonParser parser) {
        super(fetcher, parser);
    }

    public void refreshGoals(Counter counter) {
        try {
            Map<String, Object> responseData = fetchGoalsData(counter);
            List<Map<String, Object>> parsedData = parseGoalsData(responseData);
            parsedData.stream().map(this::createGoal)
                    .forEach(goal -> System.out.println(goal));
        } catch (FetchException e) {
            e.printStackTrace();
        }
    }
    
    private Map<String, Object> fetchGoalsData(Counter counter) throws FetchException {
        this.counter = counter;
        return parser.parse(
                fetcher.fetch(ApplicationProperties.GOAL_BASE_URI + counter.getMetrikaId() + "?field=goals")
        );
    }

    private List<Map<String, Object>> parseGoalsData(Map<String, Object> goalsData) {
        return (List<Map<String, Object>>) goalsData.get("goals");
    }

    private Goal createGoal(Map<String, Object> goalData) {
        Goal goal = new Goal();
        goal.setCounter(counter);
        goal.setMetrikaId(getMetriakId(goalData));
        goal.setName(getName(goalData));
        goal.setType(getType(goalData));
        return goal;
    }

    private Long getMetriakId(Map<String, Object> goalData) {
        return (Long) goalData.get("id");
    }

    private String getName(Map<String, Object> goalData) {
        return (String) goalData.get("name");
    }

    private String getType(Map<String, Object> goalData) {
        return (String) goalData.get("type");
    }
    
}