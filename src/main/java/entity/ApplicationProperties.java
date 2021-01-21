package entity;

import entity.main.Counter;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ApplicationProperties {

    public static String METRIC_TOKEN = "AgAAAAANlc-jAAZlTcKTKqnOw00avXvI_402vGg";
            //System.getenv("METRIC_TOKEN");

    public static String JANDEX_STAT_BY_TIME = "https://api-metrika.yandex.net/stat/v1/data/bytime?ids=";
    public static String GOAL_BASE_URI = "https://api-metrika.yandex.net/management/v1/counter/";
    public static String COUNTERS_URI = "https://api-metrika.yandex.net/management/v1/counters";
    public static String JANDEX_STAT = "https://api-metrika.yandex.net/stat/v1/data?";
    public static String JANDEX_STAT_COMPARE = "https://api-metrika.yandex.net/stat/v1/data/comparison?";
    public static String JANDEX_DRILLDOWN = "https://api-metrika.yandex.net/stat/v1/data/drilldown?ids=";

    public static final Set<Long> relevantCounters = new HashSet<>(Arrays.asList(
            39578050L, 26059395L, 24332956L, 29736370L, 48050831L, 24596720L, 23258257L,
            15070123L, 19915630L, 56140561L, 45487302L, 55811590L, 20548771L, 54241003L,
            62401888L, 49911565L, 62111218L, 54131236L, 57391372L, 44494876L, 62095546L, 59162569L
    ));

    public static boolean isRelevant(Counter counter) {
        return relevantCounters.contains(counter.getMetrikaId());
    }

    public static boolean isNotRelevant(Counter counter) {
        return !isRelevant(counter);
    }

}
