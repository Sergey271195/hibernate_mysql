package com.illuminator.db.handlers.reaches.view.dbupdate;

import com.illuminator.db.dao.SourceDao;
import com.illuminator.db.dao.ViewReachesDao;
import com.illuminator.db.entity.ReachesSuperclass;
import com.illuminator.db.entity.main.Counter;
import com.illuminator.db.entity.source.SourceSuperclass;
import com.illuminator.db.handlers.BaseSessionHandler;
import com.illuminator.db.handlers.reaches.view.parse.BaseViewParser;

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
