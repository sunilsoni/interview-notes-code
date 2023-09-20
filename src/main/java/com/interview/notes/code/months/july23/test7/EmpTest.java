package com.interview.notes.code.months.july23.test7;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class EmpTest {
    public static void main(String[] args) {
        Map<Integer, Employee> employeeMap = new HashMap<>();
// Assume the map is populated with employees

        LinkedHashMap<Integer, Employee> sortedMap = employeeMap.entrySet()
                .stream()
                .sorted(Comparator.comparing(entry -> entry.getValue().getSalary()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

    }
}
