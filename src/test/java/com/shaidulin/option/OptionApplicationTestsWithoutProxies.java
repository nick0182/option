package com.shaidulin.option;

import com.shaidulin.option.car.impl.*;
import com.shaidulin.option.race.RaceService;
import com.shaidulin.option.race.RaceServiceCasual;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionApplicationTestsWithoutProxies {

    @SpringBootTest(classes = Configuration1.class)
    static class Test1 {
        @Autowired
        private RaceService raceServiceCasual;

        @Test
        void validOptions() {
            List<String> result = raceServiceCasual.race();
            assertEquals(result, Lists.newArrayList("Volvo", "Honda"));
        }
    }

    @SpringBootTest(classes = Configuration2.class)
    static class Test2 {
        @Autowired
        private RaceService raceServiceCasual;

        @Test
        void validOptions() {
            List<String> result = raceServiceCasual.race();
            assertEquals(result, Lists.newArrayList("Honda", "BMW", "Renault", "Mazda", "Peugeot", "Volvo"));
        }
    }

    @SpringBootTest(classes = Configuration3.class)
    static class Test3 {
        @Autowired
        private RaceService raceServiceCasual;

        @Test
        void validOptions() {
            List<String> result = raceServiceCasual.race();
            assertEquals(result, Lists.newArrayList("Mazda"));
        }
    }

    @Configuration
    @Import({RaceServiceCasual.class, Volvo.class, Honda.class})
    static class Configuration1 {}

    @Configuration
    @Import({RaceServiceCasual.class, Honda.class, BMW.class, Renault.class, Mazda.class, Peugeot.class, Volvo.class})
    static class Configuration2 {}

    @Configuration
    @Import({RaceServiceCasual.class, Mazda.class})
    static class Configuration3 {}

}
