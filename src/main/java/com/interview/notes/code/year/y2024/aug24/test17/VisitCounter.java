package com.interview.notes.code.year.y2024.aug24.test17;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

class VisitCounter {
    Map<Long, Long> count(Map<String, UserStats>... visits) {
        if (visits == null) {
            return new HashMap<>();
        }

        return Arrays.stream(visits)
                .filter(Objects::nonNull)
                .flatMap(map -> map.entrySet().stream())
                .filter(entry -> isValidEntry(entry.getKey(), entry.getValue()))
                .collect(Collectors.groupingBy(
                        entry -> Long.parseLong(entry.getKey()),
                        Collectors.summingLong(entry -> entry.getValue().getVisitCount().get())
                ));
    }

    private boolean isValidEntry(String key, UserStats value) {
        if (value == null || !value.getVisitCount().isPresent()) {
            return false;
        }
        try {
            Long.parseLong(key);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
