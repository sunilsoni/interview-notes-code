package com.interview.notes.code.months.nov23.test6.google;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatLogAnalyzer {
    public static void main(String[] args) {
        // Example execution
        ChatLogAnalyzer analyzer = new ChatLogAnalyzer();
        List<String> topUsers = analyzer.findTopTalkativeUsers(5, "chat.log");
        System.out.println("Top 5 most talkative users: " + topUsers);
    }

    public List<String> findTopTalkativeUsers(int N, String filepath) {
        List<String> topUsers = new ArrayList<>();

        try {
            List<String> lines = Files.readAllLines(Path.of(filepath));
            List<String> parsedData = parse_log(lines);

            Map<String, Integer> userWordCounts = new HashMap<>();

            for (String data : parsedData) {
                String[] parts = data.split(",");
                String username = parts[0];
                int wordCount = Integer.parseInt(parts[1]);

                // Update word count for the user
                userWordCounts.put(username, userWordCounts.getOrDefault(username, 0) + wordCount);
            }

            // Sort users by word counts in descending order
            List<Map.Entry<String, Integer>> sortedUsers = new ArrayList<>(userWordCounts.entrySet());
            sortedUsers.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

            // Get the top N users
            for (int i = 0; i < N && i < sortedUsers.size(); i++) {
                topUsers.add(sortedUsers.get(i).getKey());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return topUsers;
    }

    // Helper function to parse the log file and return a list of usernames and word counts
    private List<String> parse_log(List<String> lines) {
        List<String> parsedData = new ArrayList<>();

        for (String line : lines) {
            // Parse the line and extract username and word count
            String[] parts = line.split(" ");
            String username = parts[1].substring(1, parts[1].length() - 1);
            int wordCount = parts.length - 2;

            parsedData.add(username + "," + wordCount);
        }

        return parsedData;
    }
}
