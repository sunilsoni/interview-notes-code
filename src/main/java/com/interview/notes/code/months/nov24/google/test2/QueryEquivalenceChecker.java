import java.util.*;

public class QueryEquivalenceChecker {
    private List<Set<String>> synonymGroups;

    public QueryEquivalenceChecker(List<List<String>> synonymPairs) {
        buildSynonymGroups(synonymPairs);
    }

    public static void main(String[] args) {
        // Test cases
        List<List<String>> synonymPairs = Arrays.asList(
                Arrays.asList("rate", "rated"),
                Arrays.asList("best", "top"),
                Arrays.asList("movies", "films"),
                Arrays.asList("rate", "rates")
        );

        QueryEquivalenceChecker checker = new QueryEquivalenceChecker(synonymPairs);

        // Test Case 1: Queries with synonymous words
        boolean result1 = checker.areQueriesEquivalent(
                "The best rate movie",
                "The top rate movie"
        );
        System.out.println("Test Case 1: " + (result1 ? "PASS" : "FAIL"));

        // Test Case 2: Queries with completely different words
        boolean result2 = checker.areQueriesEquivalent(
                "st rate moe",
                "The best rate movie"
        );
        System.out.println("Test Case 2: " + (!result2 ? "PASS" : "FAIL"));

        // Test Case 3: Queries with words in different order (not equivalent)
        boolean result3 = checker.areQueriesEquivalent(
                "Top movie rated",
                "Top rated movie"
        );
        System.out.println("Test Case 3: " + (!result3 ? "PASS" : "FAIL"));

        // Additional test cases for edge scenarios
        boolean result4 = checker.areQueriesEquivalent(
                "",
                ""
        );
        System.out.println("Edge Case 1 (Empty strings): " + (result4 ? "PASS" : "FAIL"));

        // Large data test case
        StringBuilder longQuery1 = new StringBuilder();
        StringBuilder longQuery2 = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            longQuery1.append("best rate movies ");
            longQuery2.append("top rated films ");
        }

        long startTime = System.currentTimeMillis();
        boolean result5 = checker.areQueriesEquivalent(
                longQuery1.toString().trim(),
                longQuery2.toString().trim()
        );
        long endTime = System.currentTimeMillis();

        System.out.println("Large Data Test: " + (result5 ? "PASS" : "FAIL"));
        System.out.println("Processing time: " + (endTime - startTime) + "ms");
    }

    // Builds groups of synonyms using Union-Find approach
    private void buildSynonymGroups(List<List<String>> synonymPairs) {
        Map<String, Set<String>> wordToGroup = new HashMap<>(); // Maps each word to its synonym group
        synonymGroups = new ArrayList<>(); // List of synonym groups

        for (List<String> pair : synonymPairs) {
            String word1 = pair.get(0);
            String word2 = pair.get(1);

            Set<String> group1 = wordToGroup.get(word1); // Get the synonym group for word1
            Set<String> group2 = wordToGroup.get(word2); // Get the synonym group for word2

            if (group1 == null && group2 == null) {
                // Both words are not yet part of any group, create a new group
                Set<String> newGroup = new HashSet<>();
                newGroup.add(word1);
                newGroup.add(word2);
                synonymGroups.add(newGroup);
                wordToGroup.put(word1, newGroup);
                wordToGroup.put(word2, newGroup);
            } else if (group1 == null) {
                // Only word1 is not part of any group, add it to the group of word2
                group2.add(word1);
                wordToGroup.put(word1, group2);
            } else if (group2 == null) {
                // Only word2 is not part of any group, add it to the group of word1
                group1.add(word2);
                wordToGroup.put(word2, group1);
            } else if (group1 != group2) {
                // Both words are part of different groups, merge the groups
                group1.addAll(group2);
                for (String word : group2) {
                    wordToGroup.put(word, group1); // Update the mapping for all words in group2
                }
                synonymGroups.remove(group2); // Remove the old group as it has been merged
            }
        }
    }

    public boolean areQueriesEquivalent(String query1, String query2) {
        if (query1 == null || query2 == null) {
            // If either query is null, they are equivalent only if both are null
            return query1 == query2;
        }

        // Split queries into words, ignoring case and splitting by whitespace
        String[] words1 = query1.toLowerCase().split("\\s+");
        String[] words2 = query2.toLowerCase().split("\\s+");

        // If the number of words is different, the queries cannot be equivalent
        if (words1.length != words2.length) {
            return false;
        }

        // Check each word pair from both queries
        for (int i = 0; i < words1.length; i++) {
            if (words1[i].equals(words2[i])) {
                // Words are identical, no need to check synonyms
                continue;
            }

            boolean foundSynonym = false;
            // Check if the words are synonyms by looking through the synonym groups
            for (Set<String> group : synonymGroups) {
                if (group.contains(words1[i]) && group.contains(words2[i])) {
                    foundSynonym = true;
                    break;
                }
            }

            // If no synonym group contains both words, the queries are not equivalent
            if (!foundSynonym) {
                return false;
            }
        }
        return true;
    }
}
