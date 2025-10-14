package com.interview.notes.code.year.y2025.october.common.test6;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Solution {
    // Example usage
    public static void main(String[] args) {
        Map<String, List<RecordItem>> grouped = new HashMap<>();

        // Sample data
        grouped.put("TypeA", Arrays.asList(
            new RecordItem("Charlie", "TypeA"),
            new RecordItem("Alpha", "TypeA")
        ));

        grouped.put("TypeB", Arrays.asList(
            new RecordItem("Delta", "TypeB"),
            new RecordItem("Beta", "TypeB")
        ));

        Solution solution = new Solution();
        solution.processMap1(grouped);  // or processMap2(grouped)
    }

    // Traditional approach
    public void processMap1(Map<String, List<RecordItem>> grouped) {
        // First sort each list by name
        for (Map.Entry<String, List<RecordItem>> entry : grouped.entrySet()) {
            entry.getValue().sort(Comparator.comparing(RecordItem::getName));
        }

        // Then assign global record numbers
        int counter = 1;
        for (List<RecordItem> list : grouped.values()) {
            for (RecordItem item : list) {
                item.setRecordNumber(counter++);
            }
        }
    }

    // Stream approach
    public void processMap2(Map<String, List<RecordItem>> grouped) {
        AtomicInteger counter = new AtomicInteger(1);

        grouped.values().stream()
            .flatMap(List::stream)
            .sorted(Comparator.comparing(RecordItem::getName))
            .forEach(item -> item.setRecordNumber(counter.getAndIncrement()));
    }
}
