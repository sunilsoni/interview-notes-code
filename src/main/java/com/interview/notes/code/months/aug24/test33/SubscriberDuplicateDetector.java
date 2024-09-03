package com.interview.notes.code.months.aug24.test33;

import java.util.*;
import java.util.stream.Collectors;

class Subscriber {
    private int lineNum;
    private int subscriberId;
    private String firstNameVal;
    private String lastNameVal;

    // Constructor
    public Subscriber(int lineNum, int subscriberId, String firstNameVal, String lastNameVal) {
        this.lineNum = lineNum;
        this.subscriberId = subscriberId;
        this.firstNameVal = firstNameVal;
        this.lastNameVal = lastNameVal;
    }

    // Getters
    public int getLineNum() {
        return lineNum;
    }

    public int getSubscriberId() {
        return subscriberId;
    }

    public String getFirstNameVal() {
        return firstNameVal;
    }

    public String getLastNameVal() {
        return lastNameVal;
    }
}

public class SubscriberDuplicateDetector {

    public static void locateDuplicateEntries(List<Subscriber> subscriberList) {
        Map<Integer, Subscriber> idToSubscriberMap = new HashMap<>();
        Map<Integer, List<Integer>> duplicateRecords = new TreeMap<>();

        for (Subscriber currentSubscriber : subscriberList) {
            int currentId = currentSubscriber.getSubscriberId();
            if (idToSubscriberMap.containsKey(currentId)) {
                duplicateRecords.computeIfAbsent(currentId, k -> new ArrayList<>())
                        .add(currentSubscriber.getLineNum());
                if (duplicateRecords.get(currentId).size() == 1) {
                    duplicateRecords.get(currentId).add(idToSubscriberMap.get(currentId).getLineNum());
                }
            } else {
                idToSubscriberMap.put(currentId, currentSubscriber);
            }
        }

        // Output results
        System.out.println("Id\tDuplicate Record");
        for (Map.Entry<Integer, List<Integer>> entry : duplicateRecords.entrySet()) {
            System.out.println(entry.getKey() + "\t" +
                    entry.getValue().stream()
                            .sorted()
                            .map(String::valueOf)
                            .collect(Collectors.joining(", ")));
        }
    }

    public static void main(String[] args) {
        // Test Case 1: Original example
        List<Subscriber> testCase1 = Arrays.asList(
                new Subscriber(1, 111, "A", "B"),
                new Subscriber(2, 222, "D", "E"),
                new Subscriber(3, 333, "F", "G"),
                new Subscriber(4, 111, "A", "B"),
                new Subscriber(5, 666, "H", "I"),
                new Subscriber(6, 555, "A", "D"),
                new Subscriber(7, 333, "F", "G"),
                new Subscriber(8, 777, "D", "B"),
                new Subscriber(9, 333, "F", "G"),
                new Subscriber(10, 999, "Q", "P")
        );

        System.out.println("Test Case 1 Results:");
        locateDuplicateEntries(testCase1);

        // Test Case 2: No duplicates
        List<Subscriber> testCase2 = Arrays.asList(
                new Subscriber(1, 100, "X", "Y"),
                new Subscriber(2, 200, "Z", "W"),
                new Subscriber(3, 300, "V", "U")
        );

        System.out.println("\nTest Case 2 Results:");
        locateDuplicateEntries(testCase2);

        // Test Case 3: All duplicates
        List<Subscriber> testCase3 = Arrays.asList(
                new Subscriber(1, 999, "M", "N"),
                new Subscriber(2, 999, "M", "N"),
                new Subscriber(3, 999, "M", "N")
        );

        System.out.println("\nTest Case 3 Results:");
        locateDuplicateEntries(testCase3);

        // Test Case 4: Mixed scenario with multiple duplicates
        List<Subscriber> testCase4 = Arrays.asList(
                new Subscriber(1, 111, "A", "B"),
                new Subscriber(2, 222, "C", "D"),
                new Subscriber(3, 111, "A", "B"),
                new Subscriber(4, 333, "E", "F"),
                new Subscriber(5, 222, "C", "D"),
                new Subscriber(6, 444, "G", "H"),
                new Subscriber(7, 111, "A", "B")
        );

        System.out.println("\nTest Case 4 Results:");
        locateDuplicateEntries(testCase4);
    }
}
