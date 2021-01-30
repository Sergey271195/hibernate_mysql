package com.illuminator.handlers.counter;

import com.illuminator.dao.CounterDao;
import com.illuminator.entity.ApplicationProperties;
import com.illuminator.entity.main.Counter;
import com.illuminator.exceptions.FetchException;
import com.illuminator.handlers.BaseRequestHandler;
import com.illuminator.processors.RequestProcessor;

import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CounterHandler extends BaseRequestHandler {

    CounterDao counterDao;

    public CounterHandler(RequestProcessor requestProcessor) {
        super(requestProcessor);
        counterDao = new CounterDao(sessionFactory);
    }

    public void refreshCounters() {
        doInTransaction(() -> {
            Map<Long, Counter> dbCounters = getCountersFromDb();
            createOrUpdateCounters(dbCounters);
        });
    }

    private Map<Long, Counter> getCountersFromDb() {
        return doInTransaction(() -> {
            List<Counter> dbCounters = getSession()
                    .createQuery("From Counter", Counter.class)
                    .getResultList();
            Map<Long, Counter> dbCountersMap = dbCounters.stream()
                    .peek(CounterHandler::updateRelevancy)
                    .collect(Collectors.toMap(
                            Counter::getMetrikaId, Function.identity())
                    );
            return dbCountersMap;
        });
    }

    private static void updateRelevancy(Counter counter) {
        counter.setRelevant(
                ApplicationProperties.relevantCounters.contains(counter.getMetrikaId())
        );
    }

    private void createOrUpdateCounters(Map<Long, Counter> dbCounters) {
        try {
            var countersData = getCountersFromMetrika();
            countersData.stream()
                    .filter(CounterRequestParser::counterIsRelevant)
                    .forEach(counterData ->
                            createOrUpdateCounter(dbCounters, counterData)
                    );
        } catch (FetchException err) {
            System.out.println("[FETCHING COUNTERS FROM METRIKA ERROR] " + err.getMessage());
        }
    }

    private List<Map<String, Object>> getCountersFromMetrika() throws FetchException {
        Map<String, Object>  responseData = requestProcessor.process(ApplicationProperties.COUNTERS_URI);
        return (List<Map<String, Object>>) responseData.get("counters");
    }

    private Counter createOrUpdateCounter(Map<Long, Counter> dbCounters, Map<String, Object> counterData) {
        Long metrikaId = CounterRequestParser.getMetrikaId(counterData);
        return dbCounters.containsKey(metrikaId)
                ? updateCounter(counterData, dbCounters.get(metrikaId))
                : createCounter(counterData);
    }

    private Counter updateCounter(Map<String, Object> counterData, Counter counter) {
        counter.setMetrikaId(CounterRequestParser.getMetrikaId(counterData));
        counter.setName(CounterRequestParser.getName(counterData));
        counter.setCounterUrl(CounterRequestParser.getCounterUrl(counterData));
        counter.setCreationDate(CounterRequestParser.getCreationDate(counterData));
        counter.setCommercial(CounterRequestParser.isCommercial(counterData) == 1L);
        counter.setRelevant(CounterRequestParser.counterIsRelevant(counterData));
        return counter;
    }

    private Counter createCounter(Map<String, Object> counterData) {
        Counter counter = new Counter();
        updateCounter(counterData, counter);
        counterDao.save(counter);
        return counter;
    }

}

class CounterRequestParser {
    public static long isCommercial(Map<String, Object> counterData) {
        return (Long) ((Map) counterData.get("code_options")).get("ecommerce");
    }

    public static Long getMetrikaId(Map<String, Object> counterData) {
        return (Long) counterData.get("id");
    }

    public static String getName(Map<String, Object> counterData) {
        return (String) counterData.get("name");
    }

    public static String getCounterUrl(Map<String, Object> counterData) {
        return (String) counterData.get("site");
    }

    public static LocalDate getCreationDate(Map<String, Object> counterData) {
        return ZonedDateTime.parse((String) counterData.get("create_time")).toLocalDate();
    }

    public static boolean counterIsRelevant(Map<String, Object> counterData) {
        return ApplicationProperties.relevantCounters.contains(getMetrikaId(counterData));
    }
}
