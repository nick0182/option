package com.shaidulin;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AutowiredOptions {
    Class<?>[] toAutowire();
}
