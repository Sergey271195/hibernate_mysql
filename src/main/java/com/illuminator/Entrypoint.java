package com.illuminator;

import com.illuminator.dao.CounterDao;
import com.illuminator.entity.main.Counter;
import com.illuminator.handlers.reaches.goal.filler.DrilldownGoalsFiller;
import com.illuminator.handlers.reaches.goal.update.GoalUpdateHandler;
import com.illuminator.handlers.reaches.view.update.ViewUpdateHandler;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import com.illuminator.processors.RequestProcessor;
import com.illuminator.processors.parser.JsonParserImpl;
import com.illuminator.processors.fetcher.FetcherImpl;
import com.illuminator.processors.fetcher.Fetchable;
import com.illuminator.processors.parser.JsonParser;
import com.illuminator.utils.DbConnectionFactory;

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

        /*try {
            Map r = requestProcessor.process("https://api-metrika.yandex.net/stat/v1/data/drilldown?ids=20548771&metrics=ym:s:visits&dimensions=ym:s:lastsignSearchPhrase&group=day&limit=100000&date1=2021-01-29&date2=2021-01-29");
            System.out.println(r);
        } catch (FetchException e) {
            e.printStackTrace();
        }*/

        CounterDao counterDao = new CounterDao(sessionFactory);
        //54131236L Вектор плюс
        //23258257L Титан
        //62401888L Alleri
        //48050831L Трейд дизайн
        //45487302L Центр строит услуг ХочуДом
        //39578050L Ив силикат
        //20548771L Иваново Принт

        //Проверить
        //62111218L БМ ЗЧМ
        //49911565L Анжело
        //29736370L,
        //24596720L,
        //26059395L,
        //24332956L,
        //57391372L,
        //19915630L,
        //44494876L,
        //15070123L, Error!!!
        //62095546L,
        //55811590L,
        //54241003L,
        //59162569L,
        //56140561L

        //FOR FILL

        /*List<Long> forUpdate = List.of(
                15070123L
        );
        DrilldownGoalsFiller bgf = new DrilldownGoalsFiller(requestProcessor);
        for (Long id: forUpdate) {
            Counter counter = new CounterDao(sessionFactory).getByMetrikaId(Counter.class, id);
            bgf.handleCounter(counter);
        }*/

        //FOR UPDATE

        List<Counter> forEverydayUpdate = counterDao.getAll();
        System.out.println(forEverydayUpdate.stream().count());
        /*GoalUpdateHandler gu = new GoalUpdateHandler(requestProcessor, LocalDate.now().minusDays(1));
        ViewUpdateHandler vu = new ViewUpdateHandler(requestProcessor, LocalDate.now().minusDays(1));
        forEverydayUpdate.stream()
                .peek(gu::handleCounter)
                .forEach(vu::handleCounter);*/

        transaction.commit();

    }

}
