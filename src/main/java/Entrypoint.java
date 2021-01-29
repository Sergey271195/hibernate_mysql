import dao.CounterDao;
import entity.main.Counter;
import handlers.reaches.goal.updater.BaseGoalsUpdater;
import handlers.reaches.view.ViewUpdater;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import processors.RequestProcessor;
import processors.parser.JsonParserImpl;
import processors.fetcher.FetcherImpl;
import processors.fetcher.Fetchable;
import processors.parser.JsonParser;
import utils.DbConnectionFactory;

import java.time.LocalDate;
import java.util.List;


public class Entrypoint {

    public static void main(String[] args) {

        JsonParser parser = new JsonParserImpl();
        Fetchable fetcher = new FetcherImpl();
        RequestProcessor requestProcessor = new RequestProcessor(parser, fetcher);

        SessionFactory sessionFactory = DbConnectionFactory.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        CounterDao counterDao = new CounterDao(sessionFactory);
        //54131236L Вектор плюс
        //23258257L Титан
        //62401888L Alleri
        //48050831L Трейд дизайн
        //45487302L Центр строит услуг ХочуДом
        //39578050L Ив силикат
        //20548771L Иваново Принт

        //FOR FILL

        //62111218L, 49911565L, 29736370L???, 24596720L
        //List<Long> forUpdate = List.of(62111218L, 49911565L, 29736370L, 24596720L, 26059395L, 24332956L, 57391372L);
        //DrilldownGoalsFiller bgf = new DrilldownGoalsFiller(requestProcessor);
        //for (Long id: forUpdate) {
        //    Counter counter = new CounterDao(sessionFactory).getByMetrikaId(Counter.class, id);
        //    bgf.handleCounter(counter);
        //}

        //FOR UPDATE

        List<Counter> forUpdate = counterDao.getAll();
        //BaseGoalsUpdater bgf = new BaseGoalsUpdater(requestProcessor, LocalDate.now().minusDays(1));
        ViewUpdater vu = new ViewUpdater(requestProcessor, LocalDate.now().minusDays(2));
        forUpdate.stream()
                .forEach(vu::handleCounter);
        transaction.commit();

    }

}
