package com.shaidulin.option;

import com.shaidulin.option.car.impl.Honda;
import com.shaidulin.option.car.impl.Peugeot;
import com.shaidulin.option.car.impl.PeugeotToProxy;
import com.shaidulin.option.car.impl.Volvo;
import com.shaidulin.option.race.RaceService;
import com.shaidulin.option.race.RaceServiceToProxy;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionApplicationTestsWithServiceProxy {

    @SpringBootTest(classes = Configuration1.class)
    static class Test1 {
        @Autowired
        private RaceService raceServiceToProxy;

        @Test
        void validOptions() {
            List<String> result = raceServiceToProxy.race();
            assertEquals(result, Lists.newArrayList("Volvo", "Peugeot", "Honda"));
        }
    }

    @SpringBootTest(classes = Configuration2.class)
    static class Test2 {
        @Autowired
        private RaceService raceServiceToProxy;

        @Test
        void validOptions() {
            List<String> result = raceServiceToProxy.race();
            assertAll(
                    () -> assertEquals(result.size(), 2),
                    () -> assertEquals(result.get(0), "Volvo"),
                    () -> assertTrue(result.get(1).contains("PeugeotToProxy$$EnhancerBySpringCGLIB"))
            );
        }
    }

    @Configuration
    @Import({RaceServiceToProxy.class, Volvo.class, Peugeot.class, Honda.class})
    static class Configuration1 {
    }

    @Configuration
    @Import({RaceServiceToProxy.class, Volvo.class, PeugeotToProxy.class})
    static class Configuration2 {
    }

}
