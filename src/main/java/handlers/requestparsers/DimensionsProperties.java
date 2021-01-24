package handlers.requestparsers;

import entity.source.*;

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

    public static SourceSuperclass getInstance(Class<? extends SourceSuperclass> clazz) {
        try {
            return clazz.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException err) {
            System.out.println(err.getMessage());
            return null;
        }
    }


}
