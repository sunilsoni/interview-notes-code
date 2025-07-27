package com.interview.notes.code.year.y2025.july.common.test9;

import java.lang.reflect.Method;

public class AnnotationScanner {
    public static void main(String[] args) {
        Class<MobilityService> clazz = MobilityService.class;

        for (Method method : clazz.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Monitor.class)) {
                Monitor m = method.getAnnotation(Monitor.class);
                System.out.println("Found @Monitor on: " + method.getName() + ", level=" + m.value());
            }
        }
    }
}
