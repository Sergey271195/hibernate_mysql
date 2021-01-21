package handlers.counter;

import dao.CounterDao;
import entity.ApplicationProperties;
import entity.main.Counter;
import exceptions.FetchException;
import handlers.BaseRequestHandler;
import handlers.fetcher.Fetchable;
import handlers.parser.JsonParser;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class CounterHandler extends BaseRequestHandler {

    CounterDao counterDao;

    public CounterHandler(Fetchable fetcher, JsonParser parser) {
        super(fetcher, parser);
        counterDao = new CounterDao(sessionFactory);
    }

    public void refreshCounters() {
        doInTransaction(() -> {
            Set<Long> relevantCounterIds = getRelevantCountersFromDB();
            System.out.println("[RELEVANT COUNTERS] " + relevantCounterIds);
            updateOrCreateCounters(relevantCounterIds);
            updateRelevancy();
        });
    }

    private List<Map<String, Object>> fetchCountersFromMetrics() throws FetchException {
        String countersResponse = fetcher.fetch(ApplicationProperties.COUNTERS_URI);
        Map<String, Object> countersMap = parser.parse(countersResponse);
        var countersData = (List<Map<String, Object>>) countersMap.get("counters");
        return countersData;
    }

    private void updateOrCreateCounters(Set<Long> relevantCounterIds) {
        try {
            var countersData = fetchCountersFromMetrics();
            countersData.stream().map(CounterHandler::createCounter)
                    .filter(Counter::isRelevant)
                    .forEach(counter -> createOrUpdateCounter(relevantCounterIds, counter));
        } catch (FetchException err) {
            System.out.println("[FETCHING COUNTERS FROM METRIKA ERROR] " + err.getMessage());
        }
    }

    private void createOrUpdateCounter(Set<Long> relevantCounters, Counter counter) {
        if ( relevantCounters.contains(counter.getMetrikaId()) ) {
            System.out.println("[UPDATING COUNTER] " + counter);
            Long id = counterDao.getByMetrikaId(counter.getMetrikaId()).getId();
            counter.setId(id);
            counterDao.update(counter);
        } else {
            System.out.println("[SAVING COUNTER] " + counter);
            counterDao.save(counter);
        }
    }

    private static Counter createCounter(Map<String, Object> counterData) {
        Counter counter = new Counter();
        counter.setMetrikaId(getMetrikaId(counterData));
        counter.setName(getName(counterData));
        counter.setCounterUrl(getCounterUrl(counterData));
        counter.setCreationDate(getCreationDate(counterData));
        counter.setCommercial(isCommercial(counterData) == 1L);
        counter.setRelevant(isRelevant(counterData));
        return counter;
    }

    private Set<Long> getRelevantCountersFromDB() {
        return doInTransaction(() -> {
            Set<Long> dbCounters = ApplicationProperties.relevantCounters.stream()
                    .map(counterId -> counterDao.getByMetrikaId(counterId))
                    .filter(Objects::nonNull)
                    .filter(ApplicationProperties::isRelevant)
                    .map(Counter::getMetrikaId)
                    .collect(Collectors.toSet());
            return dbCounters;
        });
    }

    private void updateRelevancy() {
        doInTransaction(() -> {
           ApplicationProperties.relevantCounters.stream()
                    .map(counterId -> counterDao.getByMetrikaId(counterId))
                    .filter(Objects::nonNull)
                    .filter(ApplicationProperties::isNotRelevant)
                    .forEach(counter -> counter.setRelevant(false));
        });
    }

    private static long isCommercial(Map<String, Object> counterData) {
        return (Long) ((Map) counterData.get("code_options")).get("ecommerce");
    }

    private static Long getMetrikaId(Map<String, Object> counterData) {
        return (Long) counterData.get("id");
    }

    private static String getName(Map<String, Object> counterData) {
        return (String) counterData.get("name");
    }

    private static String getCounterUrl(Map<String, Object> counterData) {
        return (String) counterData.get("site");
    }

    private static LocalDate getCreationDate(Map<String, Object> counterData) {
        return ZonedDateTime.parse((String) counterData.get("create_time")).toLocalDate();
    }

    private static boolean isRelevant(Map<String, Object> counterData) {
        return ApplicationProperties.relevantCounters.contains(getMetrikaId(counterData));
    }


}
