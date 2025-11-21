package com.interview.notes.code.year.y2023.july23.test3;

import java.io.IOException;
import java.util.*;

public class GetMostTalkativeUsers {

    public static List<String> getMostTalkativeUsers(int N, String filepath) {
        List<String> topUsers = new ArrayList<>();
        Map<String, Integer> userWordCountMap = new HashMap<>();
        List<Pair> parsedLog = parseLog(filepath);

        // Aggregate word counts for each user
        for (Pair p : parsedLog) {
            String username = p.getUsername();
            int wordCount = p.getWordCount();
            userWordCountMap.put(username, userWordCountMap.getOrDefault(username, 0) + wordCount);
        }

        // Create a priority queue
        PriorityQueue<Pair> pq = new PriorityQueue<>((a, b) -> b.getWordCount() - a.getWordCount());

        // Add all users to the priority queue
        for (Map.Entry<String, Integer> entry : userWordCountMap.entrySet()) {
            pq.add(new Pair(entry.getKey(), entry.getValue()));
        }

        // Get the top N users
        for (int i = 0; i < N && !pq.isEmpty(); i++) {
            topUsers.add(pq.poll().getUsername());
        }

        return topUsers;
    }

    // Assume this function is provided
    public static List<Pair> parseLog(String filepath) {
        // Parse the log and return a list of usernames and word counts
        // ...
        return null;
    }

    public static void main(String[] args) throws IOException {
        List<String> mostTalkativeUsers = getMostTalkativeUsers(10, "path_to_your_log_file");
        for (String user : mostTalkativeUsers) {
            System.out.println(user);
        }
    }

    public static class Pair {
        private final String username;
        private final int wordCount;

        public Pair(String username, int wordCount) {
            this.username = username;
            this.wordCount = wordCount;
        }

        public String getUsername() {
            return username;
        }

        public int getWordCount() {
            return wordCount;
        }
    }
}
