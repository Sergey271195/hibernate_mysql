package handlers.reaches.view.dbupdate;

import dao.SourceDao;
import dao.ViewReachesDao;
import entity.ReachesSuperclass;
import entity.main.Counter;
import entity.source.SourceSuperclass;
import handlers.BaseSessionHandler;
import handlers.reaches.view.parser.BaseViewParser;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;


public class DbViewUpdater extends BaseDbViewUpdater {

    public DbViewUpdater(BaseViewParser requestParser) {
        super(requestParser);
    }

    public void update() {
        doInTransaction(() -> {
            this.counter = sourceDao.getByMetrikaId(Counter.class, requestParser.ids);
            requestParser.data.stream()
                    .forEach(data -> updateForDimension(data));
        });
    }

    private void updateForDimension(Map<String, Object> data) {
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

    private List<List<Double>> getMetriks(Map<String, Object> data) {
        return (List) data.get("metrics");
    }

    private Map<String, String> getDimension(Map<String, Object> data) {
        return (Map)((List) data.get("dimensions")).get(0);
    }


}
