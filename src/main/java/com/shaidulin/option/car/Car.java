package com.shaidulin.option.car;

public interface Car {
    default String drive() {
        return this.getClass().getSimpleName();
    }
}
