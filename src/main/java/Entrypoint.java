import dao.CounterDao;
import dao.GoalDao;
import entity.main.Counter;
import entity.main.Goal;
import handlers.reaches.goal.BaseGoalsFiller;
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
        //Goal goal = new GoalDao(sessionFactory).getByMetrikaId(Goal.class, 12L);
        //System.out.println(goal);
        BaseGoalsFiller bgf = new BaseGoalsFiller(requestProcessor);
        Counter counter = new CounterDao(sessionFactory).getByMetrikaId(Counter.class, 54131236L);
        bgf.fillCounter(counter);
        transaction.commit();

        //FOR GOALS UPDATES
        /*GoalFiller filler = new GoalFiller(requestProcessor);
        filler.fill();*/

        //FOR COUNTERS UPDATES
        /*CounterHandler counterHandler = new CounterHandler(requestProcessor);
        counterHandler.refreshCounters();*/

    }

}
