package com.interview.notes.code.year.y2025.november.common.test1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DistinctWords {
    public static void main(String[] args) {
        String[] cities = {"Hotel", "Houston", "Texas", "Chicago", "Episodes", "New York", "Texas"};

        List<String> distinctCities = Arrays.stream(cities)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(distinctCities);
    }
}
