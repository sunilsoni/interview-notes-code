package com.interview.notes.code.year.y2025.july.common.test9;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Monitor {
    String value() default "active";
}
