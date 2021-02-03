package com.illuminator.db.handlers.requestparsers;

import com.illuminator.db.entity.source.SourceSuperclass;
import com.illuminator.db.handlers.DimensionsProperties;
import com.illuminator.db.utils.StandardMethodGenerator;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class QueryRequestParser {

    public final LocalDate fillStartDate;
    public final LocalDate fillEndDate;
    public final List<String> metrics;
    public final List<String> dimensionsList;
    public final Class<? extends SourceSuperclass> dimension;
    public final Long ids;

    public QueryRequestParser(Map<String, Object> query) {
        fillStartDate = getfillStartDate(query);
        fillEndDate = getfillEndDate(query);
        metrics = (List) query.get("metrics");
        dimensionsList = (List) query.get("dimensions");
        dimension = parseDimension(dimensionsList);
        ids = (Long) ((List) query.get("ids")).get(0);
    }

    private Class<? extends SourceSuperclass> parseDimension(List<String> dimensionName) {
        return DimensionsProperties.registry.get(dimensionName.get(0));
    }

    public <T extends SourceSuperclass> T getNewSourceInstance() {
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

    private LocalDate getfillStartDate(Map<String, Object> query) {
        return query.get("date1") == null ? null : LocalDate.parse(String.valueOf(query.get("date1")));
    }

    private LocalDate getfillEndDate(Map<String, Object> query) {
        return query.get("date2") == null ? null : LocalDate.parse(String.valueOf(query.get("date2")));
    }

    @Override
    public String toString() {
        return StandardMethodGenerator.generateToStringMethod(this);
    }
}
