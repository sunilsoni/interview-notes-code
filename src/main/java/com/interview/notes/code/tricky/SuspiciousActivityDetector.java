package com.interview.notes.code.tricky;

import java.util.*;

public class SuspiciousActivityDetector {

    public static List<String> processLogs(String[] logs, int threshold) {
        Map<String, Integer> transactions = new HashMap<String, Integer>();
        for (String log : logs) {
            String[] fields = log.split(" ");
            String sender = fields[0];
            String recipient = fields[1];
            Integer senderTransactions = transactions.get(sender);
            if (senderTransactions == null) {
                senderTransactions = 0;
            }
            transactions.put(sender, senderTransactions + 1);
            if (!sender.equals(recipient)) {
                Integer recipientTransactions = transactions.get(recipient);
                if (recipientTransactions == null) {
                    recipientTransactions = 0;
                }
                transactions.put(recipient, recipientTransactions + 1);
            }
        }
        List<String> suspiciousUsers = new ArrayList<String>();
        for (Map.Entry<String, Integer> entry : transactions.entrySet()) {
            if (entry.getValue() >= threshold) {
                suspiciousUsers.add(entry.getKey());
            }
        }
        Collections.sort(suspiciousUsers, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return Integer.parseInt(a) - Integer.parseInt(b);
            }
        });
        return suspiciousUsers;
    }

    public static void main(String[] args) {
       // String[] logs = {"88 99 200", "88 99 300", "99 32 100", "12 12 157"};
        String[] logs = { "9 7 50", "22 7 20", "33 7 55","22 7 30"};
        int threshold = 3;
        List<String> suspiciousUsers = processLogs(logs, threshold);
        for (String user : suspiciousUsers) {
            System.out.println(user);
        }
    }
}
