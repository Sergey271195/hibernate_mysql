package handlers.reaches.view;

import dao.SourceDao;
import dao.ViewReachesDao;
import entity.ReachesSuperclass;
import entity.goal.GoalReachesSuperclass;
import entity.main.Counter;
import entity.source.SourceSuperclass;
import handlers.BaseSessionHandler;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;


public class ViewDbUpdater extends BaseSessionHandler {

    private final ViewRequestParser requestParser;
    private final ViewReachesDao viewReachesDao = new ViewReachesDao(sessionFactory);
    private final SourceDao sourceDao = new SourceDao(sessionFactory);

    private Counter counter;
    private SourceSuperclass source;

    public ViewDbUpdater(ViewRequestParser requestParser) {
        this.requestParser = requestParser;
    }

    public void update() {
        doInTransaction(() -> {
            this.counter = sourceDao.getByMetrikaId(Counter.class, requestParser.ids);
            requestParser.data.stream()
                    .forEach(data -> updateForDimension(data));
        });
    }

    private void updateForDimension(Map<String, List> data) {
        List<List<Double>> metrics = getMetriks(data);
        Map<String, String> subDimension = getDimension(data);
        this.source = sourceDao.createOrFetchSourceFromData(requestParser.dimension, subDimension);
        IntStream.range(0, requestParser.insertTableList.size())
                .filter(index -> metrics.get(index).get(0) != 0.0)
                .mapToObj(index -> {
                    ReachesSuperclass newTableRow = createTableRowInstance(index);
                    newTableRow.setReaches(metrics.get(index).get(0));
                    newTableRow.setDate(requestParser.fillStartDate);
                    return newTableRow;
                }).peek(this::setSourceAndCounter)
                .peek(viewReachesDao::save)
                .forEach(r -> System.out.println(r + " " + r.getReaches() + " " + r.getDate()));
    }

    private List<List<Double>> getMetriks(Map<String, List> data) {
        return data.get("metrics");
    }

    private Map<String, String> getDimension(Map<String, List> data) {
        return (Map) data.get("dimensions").get(0);
    }

    private ReachesSuperclass createTableRowInstance(int index) {
        return viewReachesDao.getTableRowInstance(requestParser.insertTableList.get(index));
    }

    private void setSourceAndCounter(ReachesSuperclass tableRow) {
        tableRow.setSourceSuperclass(source);
        tableRow.setCounter(counter);
    }

}
