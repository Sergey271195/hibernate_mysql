package handlers.reaches.goal.request.builders;

import entity.ApplicationProperties;
import entity.main.Counter;

public class ByTimeGoalsRequestBuilder extends BaseGoalsRequestBuilder {

    public ByTimeGoalsRequestBuilder(Counter counter, String dimension) {
        super(counter, dimension);
    }

    @Override
    protected String buildRequest(String goalsRequest) {
        String requestBase = buildRequestBase(goalsRequest);
        return ApplicationProperties.JANDEX_DRILLDOWN + requestBase;
    }
}
