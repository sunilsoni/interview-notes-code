package com.interview.notes.code.months.aug24.test15;
import java.util.*;
import com.interview.notes.code.months.july24.test17.UserStats;

public class Main {
    public static void main(String[] args) {
        VisitCounter counter = new VisitCounter();

        // Test case 1: Normal case
        Map<String, UserStats> service1 = new HashMap<>();
        service1.put("1", new UserStats(5L));
        service1.put("2", new UserStats(3L));

        Map<String, UserStats> service2 = new HashMap<>();
        service2.put("1", new UserStats(2L));
        service2.put("3", new UserStats(4L));

        Map<Long, Long> result1 = counter.count(new Map[]{service1, service2});
        System.out.println("Test case 1 result: " + result1);
        // Expected: {1=7, 2=3, 3=4}

        // Test case 2: Handle invalid entries
        Map<String, UserStats> service3 = new HashMap<>();
        service3.put("invalid", new UserStats(1L));
        service3.put("4", new UserStats(null));  // Assuming null is allowed for no visits
        service3.put("5", null);

        Map<Long, Long> result2 = counter.count(new Map[]{service1, service2, service3});
        System.out.println("Test case 2 result: " + result2);
        // Expected: {1=7, 2=3, 3=4, 4=0}

        // Test case 3: Empty and null inputs
        Map<Long, Long> result3 = counter.count(new Map[]{new HashMap<>(), null});
        System.out.println("Test case 3 result: " + result3);
        // Expected: {}

        // Test case 4: Null input
        Map<Long, Long> result4 = counter.count((Map<String, UserStats>[]) null);
        System.out.println("Test case 4 result: " + result4);
        // Expected: {}
    }
}
