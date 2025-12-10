package com.interview.notes.code.year.y2025.december.microsoft.test2;

import java.util.Arrays;
import java.util.List;

public class StreamStringTransform {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("sunil", "renu", "ravi", "aspira");

        names.stream()
             .map(str -> new StringBuilder(str).reverse().toString()) // reverse string
             .map(str -> str.substring(0,1).toUpperCase() + str.substring(1)) // capitalize first char
             .forEach(System.out::println); // print result
    }
}
