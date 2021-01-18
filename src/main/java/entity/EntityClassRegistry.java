package entity;

import entity.main.Counter;
import entity.main.Goal;
import entity.source.*;

import java.util.List;

public class EntityClassRegistry {

    public final static List<Class> ENTITY_SOURCE_CLASS_REGISTRY = List.of(
            AdvEngine.class,
            ReferralSource.class,
            SearchEngine.class,
            SearchPhrase.class,
            SocialNetwork.class,
            TrafficSource.class
    );

    public final static  List<Class> ENTITY_MAIN_CLASS_REGISTRY = List.of(
            Goal.class,
            Counter.class
    );

}
