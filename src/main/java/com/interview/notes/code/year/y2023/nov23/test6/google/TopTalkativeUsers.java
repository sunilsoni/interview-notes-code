package com.interview.notes.code.year.y2023.nov23.test6.google;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Complexity Analysis
 * <p>
 * Space Complexity: O(N), where N is the total number of words in the log file. This is due to the storage requirements of the user dictionary.
 * <p>
 * Time Complexity: O(N log N), where N is the total number of words in the log file. The main bottleneck is the sorting of the user dictionary by word count, which uses a heapsort algorithm with a worst-case time complexity of O(N
 */
public class TopTalkativeUsers {

    public static void main(String[] args) throws IOException {
        String filePath = args[0];
        int topNUsers = Integer.parseInt(args[1]);

        Map<String, Integer> userWordCountMap = parseLogFile(filePath);
        PriorityQueue<User> topTalkativeUsers = findTopTalkativeUsers(userWordCountMap, topNUsers);

        printTopTalkativeUsers(topTalkativeUsers);
    }

    private static Map<String, Integer> parseLogFile(String filePath) throws IOException {
        Map<String, Integer> userWordCountMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                processMessage(line, userWordCountMap);
            }
        }

        return userWordCountMap;
    }

    private static void processMessage(String line, Map<String, Integer> userWordCountMap) {
        String[] tokens = line.split(" ");
        String username = tokens[1];
        String messageContent = tokens[2];

        int wordCount = messageContent.split(" ").length;
        userWordCountMap.putIfAbsent(username, 0);
        userWordCountMap.put(username, userWordCountMap.get(username) + wordCount);
    }

    private static PriorityQueue<User> findTopTalkativeUsers(Map<String, Integer> userWordCountMap, int topNUsers) {
        PriorityQueue<User> topTalkativeUsers = new PriorityQueue<>(topNUsers, (u1, u2) -> u2.wordCount - u1.wordCount);

        for (Map.Entry<String, Integer> entry : userWordCountMap.entrySet()) {
            topTalkativeUsers.add(new User(entry.getKey(), entry.getValue()));
        }

        while (topTalkativeUsers.size() > topNUsers) {
            topTalkativeUsers.poll();
        }

        return topTalkativeUsers;
    }

    private static void printTopTalkativeUsers(PriorityQueue<User> topTalkativeUsers) {
        System.out.println("Top " + topTalkativeUsers.size() + " Most Talkative Users:");

        while (!topTalkativeUsers.isEmpty()) {
            User user = topTalkativeUsers.poll();
            System.out.println(user.username + ": " + user.wordCount);
        }
    }

    private static class User {
        String username;
        int wordCount;

        public User(String username, int wordCount) {
            this.username = username;
            this.wordCount = wordCount;
        }
    }
}
