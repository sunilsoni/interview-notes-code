package com.interview.notes.code.tricky;

import java.util.*;

public class SuspiciousActivityDetector1 {
    public static List<String> processLogs(String[] logs, int threshold) {
        Map<String, Integer> counts = new HashMap<>();
        for (String log : logs) {
            String[] components = log.split(" ");
            String sender = components[0];
            String recipient = components[1];
            counts.put(sender, counts.getOrDefault(sender, 0) + 1);
            if (!sender.equals(recipient)) {
                counts.put(recipient, counts.getOrDefault(recipient, 0) + 1);
            }
        }

        List<String> suspiciousUsers = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : counts.entrySet()) {
            String user = entry.getKey();
            int count = entry.getValue();
            if (count >= threshold) {
                suspiciousUsers.add(user);
            }
        }

        Collections.sort(suspiciousUsers, new Comparator<String>() {
            @Override
            public int compare(String user1, String user2) {
                return Integer.parseInt(user1) - Integer.parseInt(user2);
            }
        });

        return suspiciousUsers;
    }

    public static void main(String[] args) {
        String[] logs = {"88 99 200", "88 99 300", "99 32 100", "12 12 157"};
        int threshold = 2;
        List<String> suspiciousUsers = SuspiciousActivityDetector1.processLogs(logs, threshold);
        for (String user : suspiciousUsers) {
            System.out.println(user);
        }
    }

}
