package com.shaidulin.option;

import com.shaidulin.option.race.RaceService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class OptionApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(OptionApplication.class, args);
        run.getBean(RaceService.class).race();
    }
}
