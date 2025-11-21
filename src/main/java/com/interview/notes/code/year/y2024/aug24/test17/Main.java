package com.interview.notes.code.year.y2024.aug24.test17;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        VisitCounter counter = new VisitCounter();

        // Test case 1: Normal case
        Map<String, UserStats> service1 = new HashMap<>();
        service1.put("1", new UserStats(Optional.of(5L)));
        service1.put("2", new UserStats(Optional.of(6L)));

        Map<String, UserStats> service2 = new HashMap<>();
        service2.put("1", new UserStats(Optional.of(4L)));
        service2.put("3", new UserStats(Optional.of(3L)));

        System.out.println(counter.count(service1, service2));
        // Expected output: {1=9, 2=6, 3=3}

        // Test case 2: Invalid entries
        Map<String, UserStats> service3 = new HashMap<>();
        service3.put("invalid", new UserStats(Optional.of(5L)));
        service3.put("4", new UserStats(Optional.empty()));
        service3.put("5", null);

        System.out.println(counter.count(service3));
        // Expected output: {}

        // Test case 3: Null and empty inputs
        System.out.println(counter.count(null));
        // Expected output: {}
        System.out.println(counter.count(new HashMap<>()));
        // Expected output: {}
    }
}

// Assuming UserStats class is defined like this:
class UserStats {
    private final Optional<Long> visitCount;

    public UserStats(Optional<Long> visitCount) {
        this.visitCount = visitCount;
    }

    public Optional<Long> getVisitCount() {
        return visitCount;
    }
}
