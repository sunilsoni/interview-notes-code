package com.interview.notes.code.months.aug24.test20;

import java.util.*;
import java.util.stream.Collectors;

class VisitCounter {
    public Map<Long, Long> count(Map<String, UserStats>... visits) {
        if (visits == null) {
            return Collections.emptyMap();
        }

        return Arrays.stream(visits)
                .filter(Objects::nonNull)
                .flatMap(map -> map.entrySet().stream())
                .filter(entry -> isValidEntry(entry.getKey(), entry.getValue()))
                .collect(Collectors.groupingBy(
                        entry -> Long.parseLong(entry.getKey()),
                        Collectors.summingLong(entry -> entry.getValue().getVisitCount().orElse(0L))
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

// Assume this class is provided and implemented
class UserStats {
    private Optional<Long> visitCount;

    public Optional<Long> getVisitCount() {
        return visitCount;
    }
}
