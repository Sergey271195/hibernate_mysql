package handlers;

import entity.ReachesSuperclass;
import entity.ecommerce.price.*;
import entity.ecommerce.purchase.*;
import entity.goal.*;
import entity.source.*;
import entity.view.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class DimensionsProperties {

    public final static Map<String, Class<? extends SourceSuperclass>> registry = new HashMap<>();
    static {
        registry.put("ym:s:lastsignAdvEngine", AdvEngine.class);
        registry.put("ym:s:lastsignReferalSource", ReferralSource.class);
        registry.put("ym:s:lastsignSearchEngineRoot", SearchEngine.class);
        registry.put("ym:s:lastsignSearchPhrase", SearchPhrase.class);
        registry.put("ym:s:lastsignSocialNetwork", SocialNetwork.class);
        registry.put("ym:s:lastsignTrafficSource", TrafficSource.class);
    }

    public final static Map<Class<? extends SourceSuperclass>, String> sourceToDimensionStringRegistry = new HashMap<>();
    static {
        sourceToDimensionStringRegistry.put(AdvEngine.class, "ym:s:lastsignAdvEngine");
        sourceToDimensionStringRegistry.put(ReferralSource.class, "ym:s:lastsignReferalSource");
        sourceToDimensionStringRegistry.put(SearchEngine.class, "ym:s:lastsignSearchEngineRoot");
        sourceToDimensionStringRegistry.put(SearchPhrase.class, "ym:s:lastsignSearchPhrase");
        sourceToDimensionStringRegistry.put(SocialNetwork.class, "ym:s:lastsignSocialNetwork");
        sourceToDimensionStringRegistry.put(TrafficSource.class, "ym:s:lastsignTrafficSource");
    }

    public final static Map<Class<? extends SourceSuperclass>, Class<? extends GoalReachesSuperclass>>
            insertTableRegistry = new HashMap<>();
    static {
        insertTableRegistry.put(AdvEngine.class, AdvEngineGoalReaches.class);
        insertTableRegistry.put(ReferralSource.class, ReferralSourceGoalReaches.class);
        insertTableRegistry.put(SearchEngine.class, SearchEngineGoalReaches.class);
        insertTableRegistry.put(SearchPhrase.class, SearchPhraseGoalReaches.class);
        insertTableRegistry.put(SocialNetwork.class, SocialNetworkGoalReaches.class);
        insertTableRegistry.put(TrafficSource.class, TrafficSourceGoalReaches.class);
    }

    public final static Map<Class<? extends SourceSuperclass>, Class<? extends ReachesSuperclass>>
            viewInsertTableRegistry = new HashMap<>();
    static {
        viewInsertTableRegistry.put(AdvEngine.class, AdvEngineViewReaches.class);
        viewInsertTableRegistry.put(ReferralSource.class, ReferralSourceViewReaches.class);
        viewInsertTableRegistry.put(SearchEngine.class, SearchEngineViewReaches.class);
        viewInsertTableRegistry.put(SearchPhrase.class, SearchPhraseViewReaches.class);
        viewInsertTableRegistry.put(SocialNetwork.class, SocialNetworkViewReaches.class);
        viewInsertTableRegistry.put(TrafficSource.class, TrafficSourceViewReaches.class);
    }

    public final static Map<Class<? extends SourceSuperclass>, Class<? extends ReachesSuperclass>>
            priceInsertTableRegistry = new HashMap<>();
    static {
        priceInsertTableRegistry.put(AdvEngine.class, AdvEnginePriceReaches.class);
        priceInsertTableRegistry.put(ReferralSource.class, ReferralSourcePriceReaches.class);
        priceInsertTableRegistry.put(SearchEngine.class, SearchEnginePriceReaches.class);
        priceInsertTableRegistry.put(SearchPhrase.class, SearchPhrasePriceReaches.class);
        priceInsertTableRegistry.put(SocialNetwork.class, SocialNetworkPriceReaches.class);
        priceInsertTableRegistry.put(TrafficSource.class, TrafficSourcePriceReaches.class);
    }

    public final static Map<Class<? extends SourceSuperclass>, Class<? extends ReachesSuperclass>>
            purchaseInsertTableRegistry = new HashMap<>();
    static {
        purchaseInsertTableRegistry.put(AdvEngine.class, AdvEnginePurchaseReaches.class);
        purchaseInsertTableRegistry.put(ReferralSource.class, ReferralSourcePurchaseReaches.class);
        purchaseInsertTableRegistry.put(SearchEngine.class, SearchEnginePurchaseReaches.class);
        purchaseInsertTableRegistry.put(SearchPhrase.class, SearchPhrasePurchaseReaches.class);
        purchaseInsertTableRegistry.put(SocialNetwork.class, SocialNetworkPurchaseReaches.class);
        purchaseInsertTableRegistry.put(TrafficSource.class, TrafficSourcePurchaseReaches.class);
    }

    public final static Map<String, Map> metricStringToViewMap = new HashMap<>();
    static {
        metricStringToViewMap.put("ym:s:visits", viewInsertTableRegistry);
        metricStringToViewMap.put("ym:s:ecommercePurchases", purchaseInsertTableRegistry);
        metricStringToViewMap.put("ym:s:productPurchasedPrice", priceInsertTableRegistry);
    }

    public static SourceSuperclass getInstance(Class<? extends SourceSuperclass> clazz) {
        try {
            return clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException err) {
            System.out.println(err.getMessage());
            return null;
        }
    }


}
