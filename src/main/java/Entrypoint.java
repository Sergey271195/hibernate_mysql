import entity.ApplicationProperties;
import entity.goal.AdvEngineGoalReaches;
import entity.main.Counter;
import entity.source.AdvEngine;
import entity.source.SearchEngine;
import exceptions.FetchException;
import handlers.counter.CounterHandler;
import handlers.fetcher.Fetchable;
import handlers.fetcher.FetcherImpl;
import handlers.goal.GoalHandler;
import handlers.parser.JsonParser;
import handlers.parser.JsonParserImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import utils.DbConnectionFactory;
import utils.StandardMethodGenerator;

import java.util.Map;


public class Entrypoint {

    public static void main(String[] args) {

        JsonParser parser = new JsonParserImpl();
        Fetchable fetcher = new FetcherImpl();

        Counter testCounter = new Counter();
        testCounter.setId(62401888L);

        GoalHandler goalHandler = new GoalHandler(fetcher, parser);
        goalHandler.refreshGoals(testCounter);

        //CounterHandler counterHandler = new CounterHandler(fetcher, parser);
        //counterHandler.refreshCounters();



        /*SessionFactory sessionFactory = DbConnectionFactory.getSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM AdvEngine");
        query.getResultList().forEach(r -> System.out.println(r));
        session.getTransaction().commit();
        session.close();*/
    }

}
