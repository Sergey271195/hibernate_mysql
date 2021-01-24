package handlers.requestparsers;

import entity.source.SourceSuperclass;
import utils.StandardMethodGenerator;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class QueryRequestParser {

    public final LocalDate fillStartDate;
    public final LocalDate fillEndDate;
    public final List<String> metrics;
    public final Class<? extends SourceSuperclass> dimension;

    public QueryRequestParser(Map<String, Object> query) {
        fillStartDate = LocalDate.parse(String.valueOf(query.get("date1")));
        fillEndDate = LocalDate.parse(String.valueOf(query.get("date2")));
        metrics = (List) query.get("metrics");
        dimension = parseDimension((List) query.get("dimensions"));
    }

    private Class<? extends SourceSuperclass> parseDimension(List<String> dimension) {
        return DimensionsProperties.registry.get(dimension.get(0));
    }

    protected  <T extends SourceSuperclass> T getNewSourceInstance() {
        try {
            return (T) dimension.getDeclaredConstructor()
                    .newInstance();
        } catch (InstantiationException |
                 IllegalAccessException |
                 InvocationTargetException |
                 NoSuchMethodException err) {
            err.printStackTrace();
            return null;
        }
    }

    @Override
    public String toString() {
        return StandardMethodGenerator.generateToStringMethod(this);
    }
}
