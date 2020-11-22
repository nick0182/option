package com.shaidulin.option.race;

import com.shaidulin.AutowiredOptions;
import com.shaidulin.option.car.Car;
import com.shaidulin.option.car.impl.Mazda;
import com.shaidulin.option.car.impl.Peugeot;
import com.shaidulin.option.car.impl.Renault;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RaceServiceCasual implements RaceService {

    @AutowiredOptions(toAutowire = {Renault.class, Peugeot.class, Mazda.class})
    private final List<Car> cars;

    @Override
    public List<String> race() {
        return cars.stream().map(Car::drive).collect(Collectors.toList());
    }

}
