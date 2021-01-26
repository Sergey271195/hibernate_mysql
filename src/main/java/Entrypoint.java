import dao.CounterDao;
import entity.main.Counter;
import exceptions.FetchException;
import handlers.reaches.goal.reuqest.fillers.DrilldownGoalsFiller;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import processors.RequestProcessor;
import processors.parser.JsonParserImpl;
import processors.fetcher.FetcherImpl;
import processors.fetcher.Fetchable;
import processors.parser.JsonParser;
import utils.DbConnectionFactory;


public class Entrypoint {

    public static void main(String[] args) {

        JsonParser parser = new JsonParserImpl();
        Fetchable fetcher = new FetcherImpl();
        RequestProcessor requestProcessor = new RequestProcessor(parser, fetcher);

        SessionFactory sessionFactory = DbConnectionFactory.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        DrilldownGoalsFiller bgf = new DrilldownGoalsFiller(requestProcessor);
        Counter counter = new CounterDao(sessionFactory).getByMetrikaId(Counter.class, 23258257L);
        bgf.fill(counter);
        transaction.commit();

        //FOR GOALS UPDATES
        /*GoalFiller filler = new GoalFiller(requestProcessor);
        filler.fill();*/

        //FOR COUNTERS UPDATES
        /*CounterHandler counterHandler = new CounterHandler(requestProcessor);
        counterHandler.refreshCounters();*/

    }

}
