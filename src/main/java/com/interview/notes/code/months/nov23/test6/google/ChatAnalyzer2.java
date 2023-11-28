package com.interview.notes.code.months.nov23.test6.google;

import java.util.*;


/**
 * Imagine a log file that abridges chats. An example chat might be:
 * 10:00 <john> hi!
 * 10:01 <maria> hello!
 * 10:07  <john> can you link the design?
 * where  john said 6 total words and maria said 1.
 * <p>
 * <p>
 * Your goal is to find the top N most talkative users in descending order. You need to write a function that takes an integer N and a filepath.
 * You are provided a helper function, parse_log, which returns a list of usernames and word counts for each message parsed from the provided file
 * ((('john', 1), ('maria', 1), ('john', 5)] in the example above).
 */

/**
 * Complexity Analysis
 * Time Complexity: O(N log N), where N is the number of unique users. This is due to the sorting operation.
 * Space Complexity: O(N), as we store word counts for each user.
 */
public class ChatAnalyzer2 {

    // Main method for example execution
    public static void main(String[] args) {
        // Example usage
        List<String> topTalkers = findTopTalkativeUsers(2, "path/to/logfile.txt");
        topTalkers.forEach(System.out::println);
    }

    // Method to find top N talkative users
    public static List<String> findTopTalkativeUsers(int N, String filepath) {
        // Parse the log file using the provided helper function
        List<Tuple> messages = parse_log(filepath);

        // Aggregate word counts for each user
        Map<String, Integer> wordCounts = new HashMap<>();
        for (Tuple message : messages) {
            wordCounts.put(message.username, wordCounts.getOrDefault(message.username, 0) + message.wordCount);
        }

        // Sort users based on word counts in descending order
        List<Map.Entry<String, Integer>> sortedUsers = new ArrayList<>(wordCounts.entrySet());
        sortedUsers.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        // Select top N users
        List<String> topUsers = new ArrayList<>();
        for (int i = 0; i < Math.min(N, sortedUsers.size()); i++) {
            Map.Entry<String, Integer> user = sortedUsers.get(i);
            topUsers.add(user.getKey() + ": " + user.getValue());
        }

        return topUsers;
    }

    // Dummy implementation of parse_log (to be replaced with actual implementation)
    private static List<Tuple> parse_log(String filepath) {
        // This should be replaced with the actual implementation of parse_log
        return Arrays.asList(new Tuple("john", 6), new Tuple("maria", 1), new Tuple("john", 5));
    }

    // Helper class to represent username and word count tuples
    static class Tuple {
        String username;
        int wordCount;

        Tuple(String username, int wordCount) {
            this.username = username;
            this.wordCount = wordCount;
        }
    }
}
