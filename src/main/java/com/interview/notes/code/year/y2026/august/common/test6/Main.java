package com.interview.notes.code.year.y2026.august.common.test6;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        String str = "Name:Rajiv;Skill:Java;Address:ABC";

        // Convert String to Map
        Map<String, String> map = Arrays.stream(str.split(";"))
                .map(s -> s.split(":", 2))
                .collect(Collectors.toMap(
                        a -> a[0],
                        a -> a[1]
                ));

        System.out.println("Original Map: " + map);

        // Sort Map based on value
        Map<String, String> sortedMap = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a,
                        LinkedHashMap::new
                ));

        System.out.println("Sorted Map: " + sortedMap);
    }
}