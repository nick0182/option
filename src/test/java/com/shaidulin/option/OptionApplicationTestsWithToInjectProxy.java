package com.shaidulin.option;

import com.shaidulin.option.car.impl.Honda;
import com.shaidulin.option.car.impl.PeugeotToProxy;
import com.shaidulin.option.car.impl.Volvo;
import com.shaidulin.option.race.RaceService;
import com.shaidulin.option.race.RaceServiceCasual;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class OptionApplicationTestsWithToInjectProxy {

    @SpringBootTest(classes = Configuration1.class)
    static class Test1 {
        @Autowired
        private RaceService raceServiceCasual;

        @Test
        void validOptions() {
            List<String> result = raceServiceCasual.race();
            assertAll(
                    () -> assertEquals(result.size(), 3),
                    () -> assertTrue(result.get(1).contains("PeugeotToProxy$$EnhancerBySpringCGLIB")),
                    () -> assertEquals(result.get(0), "Volvo"),
                    () -> assertEquals(result.get(2), "Honda")
            );
        }
    }

    @Configuration
    @Import({RaceServiceCasual.class, Volvo.class, PeugeotToProxy.class, Honda.class})
    static class Configuration1 {}

}
