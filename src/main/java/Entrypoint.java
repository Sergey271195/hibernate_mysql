import entity.main.Counter;
import entity.source.SearchEngine;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import utils.DbConnectionFactory;
import utils.StandardMethodGenerator;


public class Entrypoint {

    public static void main(String[] args) {


        SearchEngine searchEngine = new SearchEngine();
        System.out.println(StandardMethodGenerator.generateToStringMethod(searchEngine));

        Counter newCounter = new Counter();
        newCounter.setName("newName");
        newCounter.setMetrikaId(12345L);
        newCounter.setCommercial(true);
        System.out.println(newCounter);

        Counter newCounter2 = new Counter();
        newCounter2.setName("newName");
        newCounter2.setMetrikaId(12345L);
        newCounter2.setCommercial(false);
        System.out.println(newCounter2);

        Boolean equals = StandardMethodGenerator.generateEqualsMethod(newCounter, newCounter2, "name", "metrikaId", "commercial");
        System.out.println(equals);
        SessionFactory sessionFactory = DbConnectionFactory.getSessionFactory();

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.createQuery("FROM AdvEngine");
        query.getResultList().forEach(r -> System.out.println(r));
        session.getTransaction().commit();
        session.close();
    }

}
