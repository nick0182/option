package com.shaidulin.option;

import com.shaidulin.option.car.impl.*;
import com.shaidulin.option.race.RaceService;
import com.shaidulin.option.race.RaceServiceImpl;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptionApplicationTests {

    @SpringBootTest(classes = Configuration1.class)
    static class Test1 {
        @Autowired
        private RaceService raceService;

        @Test
        void validOptions() {
            List<String> result = raceService.race();
            assertEquals(result, Lists.newArrayList("Volvo", "Honda"));
        }
    }

    @SpringBootTest(classes = Configuration2.class)
    static class Test2 {
        @Autowired
        private RaceService raceService;

        @Test
        void validOptions() {
            List<String> result = raceService.race();
            assertEquals(result, Lists.newArrayList("Honda", "BMW", "Renault", "Mazda", "Peugeot", "Volvo"));
        }
    }

    @SpringBootTest(classes = Configuration3.class)
    static class Test3 {
        @Autowired
        private RaceService raceService;

        @Test
        void validOptions() {
            List<String> result = raceService.race();
            assertEquals(result, Lists.newArrayList("Mazda"));
        }
    }

    @Configuration
    @Import({RaceServiceImpl.class, Volvo.class, Honda.class})
    static class Configuration1 {}

    @Configuration
    @Import({RaceServiceImpl.class, Honda.class, BMW.class, Renault.class, Mazda.class, Peugeot.class, Volvo.class})
    static class Configuration2 {}

    @Configuration
    @Import({RaceServiceImpl.class, Mazda.class})
    static class Configuration3 {}

}
