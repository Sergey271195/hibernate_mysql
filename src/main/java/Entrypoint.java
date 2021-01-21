import entity.main.Counter;
import entity.main.Goal;
import handlers.BaseSessionHandler;
import handlers.counter.CounterHandler;
import handlers.goal.GoalFiller;
import handlers.goal.GoalHandler;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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

        GoalFiller filler = new GoalFiller(requestProcessor);
        filler.fill();

        //FOR COUNTERS UPDATES
        /*CounterHandler counterHandler = new CounterHandler(requestProcessor);
        counterHandler.refreshCounters();*/

    }

}
