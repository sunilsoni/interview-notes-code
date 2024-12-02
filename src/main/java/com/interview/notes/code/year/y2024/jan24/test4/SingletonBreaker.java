package com.interview.notes.code.year.y2024.jan24.test4;

import com.interview.notes.code.year.y2023.Aug23.test3.Singleton;

import java.lang.reflect.Constructor;

public class SingletonBreaker {
    public static void main(String[] args) {
        try {
            // Get the Singleton class
            Class<?> singletonClass = Class.forName("Singleton");

            // Get the Singleton constructor (which is private)
            Constructor<?> constructor = singletonClass.getDeclaredConstructor();

            // Make the constructor accessible
            constructor.setAccessible(true);

            // Create multiple instances using reflection
            Singleton instance1 = (Singleton) constructor.newInstance();
            Singleton instance2 = (Singleton) constructor.newInstance();

            // Both instances are different, breaking the Singleton pattern
            System.out.println(instance1 == instance2); // Output: false
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
