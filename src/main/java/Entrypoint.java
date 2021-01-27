import dao.CounterDao;
import entity.main.Counter;
import handlers.reaches.goal.filler.DrilldownGoalsFiller;
import handlers.reaches.goal.updater.BaseUpdater;
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
        //54131236L Вектор плюс
        //23258257L Титан
        //62401888L Alleri
        //48050831L Трейд дизайн
        //45487302L Центр строит услуг ХочуДом
        //39578050L Ив силикат
        //20548771L Иваново Принт

        //62111218L, 49911565L, 29736370L???, 24596720L
        List<Long> forUpdate = List.of(54131236L); //
        //DrilldownGoalsFiller bgf = new DrilldownGoalsFiller(requestProcessor);
        BaseUpdater bgf = new BaseUpdater(requestProcessor, LocalDate.now().minusDays(2));
        for (Long id: forUpdate) {
            Counter counter = new CounterDao(sessionFactory).getByMetrikaId(Counter.class, id);
            bgf.handleCounter(counter);
        }
        transaction.commit();

        //FOR GOALS UPDATES
        /*GoalFiller filler = new GoalFiller(requestProcessor);
        filler.fill();*/

        //FOR COUNTERS UPDATES
        /*CounterHandler counterHandler = new CounterHandler(requestProcessor);
        counterHandler.refreshCounters();*/

    }

}
