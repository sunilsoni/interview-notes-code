package com.interview.notes.code.year.y2024.aug24.test9;

import java.util.*;

public class AquaintlyAnalyzer {

    public static List<List<String>> grouping(String[][] events, int N) {
        Map<String, Set<String>> connections = new HashMap<>();

        for (String[] event : events) {
            String action = event[0];
            String user1 = event[1];
            String user2 = event[2];

            if (action.equals("CONNECT")) {
                connections.computeIfAbsent(user1, k -> new HashSet<>()).add(user2);
                connections.computeIfAbsent(user2, k -> new HashSet<>()).add(user1);
            } else if (action.equals("DISCONNECT")) {
                connections.getOrDefault(user1, new HashSet<>()).remove(user2);
                connections.getOrDefault(user2, new HashSet<>()).remove(user1);
            }
        }

        List<String> lessThanN = new ArrayList<>();
        List<String> NOrMore = new ArrayList<>();

        for (Map.Entry<String, Set<String>> entry : connections.entrySet()) {
            if (entry.getValue().size() < N) {
                lessThanN.add(entry.getKey());
            } else {
                NOrMore.add(entry.getKey());
            }
        }

        Collections.sort(lessThanN);
        Collections.sort(NOrMore);

        return Arrays.asList(lessThanN, NOrMore);
    }

    public static void main(String[] args) {
        String[][] events = {
                {"CONNECT", "Charlie", "Bob"},
                {"CONNECT", "Alice", "Bob"},
                {"DISCONNECT", "Bob", "Alice"},
                {"CONNECT", "Alice", "Charlie"},
                {"CONNECT", "Dennis", "Bob"},
                {"CONNECT", "Pam", "Dennis"},
                {"DISCONNECT", "Pam", "Dennis"},
                {"CONNECT", "Pam", "Dennis"},
                {"CONNECT", "Edward", "Bob"},
                {"CONNECT", "Dennis", "Charlie"},
                {"CONNECT", "Alice", "Nicole"},
                {"CONNECT", "Pam", "Edward"},
                {"DISCONNECT", "Dennis", "Charlie"},
                {"CONNECT", "Dennis", "Edward"}
        };

        // Test case 1: N = 3
        List<List<String>> result1 = grouping(events, 3);
        System.out.println("grouping(events, 3) => " + result1);

        // Test case 2: N = 1
        List<List<String>> result2 = grouping(events, 1);
        System.out.println("grouping(events, 1) => " + result2);

        // Test case 3: N = 10
        List<List<String>> result3 = grouping(events, 10);
        System.out.println("grouping(events, 10) => " + result3);
    }
}
