package com.illuminator.handlers.reaches.view.dbupdate;

import com.illuminator.dao.SourceDao;
import com.illuminator.dao.ViewReachesDao;
import com.illuminator.entity.ReachesSuperclass;
import com.illuminator.entity.main.Counter;
import com.illuminator.entity.source.SourceSuperclass;
import com.illuminator.handlers.BaseSessionHandler;
import com.illuminator.handlers.reaches.view.parse.BaseViewParser;

public class BaseDbViewUpdater extends BaseSessionHandler {

    protected final ViewReachesDao viewReachesDao = new ViewReachesDao(sessionFactory);
    protected final SourceDao sourceDao = new SourceDao(sessionFactory);
    protected final BaseViewParser requestParser;

    protected Counter counter;
    protected SourceSuperclass source;

    public BaseDbViewUpdater(BaseViewParser requestParser) {
        this.requestParser = requestParser;
    }

    protected ReachesSuperclass createTableRowInstance(int index) {
        return viewReachesDao.getTableRowInstance(requestParser.insertTableList.get(index));
    }

    protected void setSourceAndCounter(ReachesSuperclass tableRow) {
        tableRow.setSourceSuperclass(source);
        tableRow.setCounter(counter);
    }

}
