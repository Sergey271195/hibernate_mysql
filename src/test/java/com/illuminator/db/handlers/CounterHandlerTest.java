package com.illuminator.db.handlers;

import com.illuminator.BaseTest;
import com.illuminator.db.entity.main.Counter;
import org.junit.jupiter.api.Test;

public class CounterHandlerTest extends BaseTest {

    @Test
    void createOrUpdateCounter() {
        Counter relevantCounter = new Counter();
        relevantCounter.setId(1);
        relevantCounter.setMetrikaId(62401888L);
    }

}
