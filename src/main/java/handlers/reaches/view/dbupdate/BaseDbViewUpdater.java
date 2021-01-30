package handlers.reaches.view.dbupdate;

import dao.SourceDao;
import dao.ViewReachesDao;
import entity.ReachesSuperclass;
import entity.main.Counter;
import entity.source.SourceSuperclass;
import handlers.BaseSessionHandler;
import handlers.reaches.view.parser.BaseViewParser;

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
