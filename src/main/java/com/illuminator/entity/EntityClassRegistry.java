package com.illuminator.entity;

import com.illuminator.entity.ecommerce.price.*;
import com.illuminator.entity.ecommerce.purchase.*;
import com.illuminator.entity.goal.*;
import com.illuminator.entity.main.Counter;
import com.illuminator.entity.main.Goal;
import com.illuminator.entity.source.*;
import com.illuminator.entity.view.*;

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

    public final static List<Class> ENTITY_GOAL_REACHES_REGISTRY = List.of(
            AdvEngineGoalReaches.class,
            SearchEngineGoalReaches.class,
            ReferralSourceGoalReaches.class,
            SearchPhraseGoalReaches.class,
            SocialNetworkGoalReaches.class,
            TrafficSourceGoalReaches.class
    );

    public final static List<Class> ENTITY_VIEW_REACHES_REGISTRY = List.of(
            AdvEngineViewReaches.class,
            SearchEngineViewReaches.class,
            ReferralSourceViewReaches.class,
            SearchPhraseViewReaches.class,
            SocialNetworkViewReaches.class,
            TrafficSourceViewReaches.class
    );

    public final static List<Class> ENTITY_PRICE_REACHES_REGISTRY = List.of(
            AdvEnginePriceReaches.class,
            SearchEnginePriceReaches.class,
            ReferralSourcePriceReaches.class,
            SearchPhrasePriceReaches.class,
            SocialNetworkPriceReaches.class,
            TrafficSourcePriceReaches.class
    );

    public final static List<Class> ENTITY_PURCHASE_REACHES_REGISTRY = List.of(
            AdvEnginePurchaseReaches.class,
            SearchEnginePurchaseReaches.class,
            ReferralSourcePurchaseReaches.class,
            SearchPhrasePurchaseReaches.class,
            SocialNetworkPurchaseReaches.class,
            TrafficSourcePurchaseReaches.class
    );

}
