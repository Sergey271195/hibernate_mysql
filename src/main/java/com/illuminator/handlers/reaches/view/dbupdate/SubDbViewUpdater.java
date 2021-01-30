package com.illuminator.handlers.reaches.view.dbupdate;

import com.illuminator.entity.ReachesSuperclass;
import com.illuminator.entity.main.Counter;
import com.illuminator.handlers.reaches.view.parse.ViewSubRequestParser;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class SubDbViewUpdater extends BaseDbViewUpdater {

    public SubDbViewUpdater(ViewSubRequestParser requestParser) {
        super(requestParser);
    }

    public void update() {
        doInTransaction(() -> {
            this.counter = sourceDao.getByMetrikaId(Counter.class, requestParser.ids);
            this.source = sourceDao.createOrFetchSourceFromData(
                    requestParser.dimension, ((ViewSubRequestParser) requestParser).parentData
            );
            requestParser.data.stream()
                    .forEach(this::updateForDimension);
        });
    }

    private void updateForDimension(Map<String, Object> data) {
        List<Double> metrics = getMetriks(data);
        LocalDate entryDate = getSubEntryDate(data);
        IntStream.range(0, requestParser.insertTableList.size())
                .filter(index -> metrics.get(index) != 0.0)
                .mapToObj(index -> {
                    ReachesSuperclass newTableRow = createTableRowInstance(index);
                    newTableRow.setReaches(metrics.get(index));
                    newTableRow.setDate(entryDate);
                    return newTableRow;
                }).peek(this::setSourceAndCounter)
                .peek(viewReachesDao::save)
                .forEach(r -> System.out.println(r + " " + r.getReaches() + " " + r.getDate()));
    }

    private List<Double> getMetriks(Map<String, Object> data) {
        return (List) data.get("metrics");
    }

    private LocalDate getSubEntryDate(Map<String, Object> data) {
        System.out.println(data);
        return LocalDate.parse(
                    String.valueOf(
                            ((Map) data.get("dimension")).get("id")
                ));
    }

}
