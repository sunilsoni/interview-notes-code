package com.interview.notes.code.year.y2025.october.common;

import java.util.*;
import java.util.stream.Collectors;

/**
 * VotingSystemHelper class
 * ------------------------
 * Simulates a voting system where multiple people vote for candidates.
 * The class supports:
 *   1. Finding the top winner(s)
 *   2. Finding the k-th winner(s)
 *   3. Finding common candidates voted by two persons
 *   4. Caching results for very fast repeated queries
 */
public class VotingSystemHelper {

    private final List<Vote> votes;  // All votes given in this election

    // ------------------------------------------------------------
    // 🔒 Instance variables (data and cache)
    // ------------------------------------------------------------
    // Cache data structures (computed once, reused later)
    private boolean processed = false;  // To check if we have already computed everything
    private Map<String, Long> voteCountCache; // candidate → number of votes
    private List<Map.Entry<String, Long>> sortedCandidatesCache; // list sorted by vote count
    private List<String> winnerCache; // list of top winner(s)
    // Constructor: initialize the object with the list of votes
    public VotingSystemHelper(List<Vote> votes) {
        this.votes = votes;
    }

    // ------------------------------------------------------------
    // 🧪 5️⃣ Testing method (main)
    // ------------------------------------------------------------
    public static void main(String[] args) {

        // ---------- SMALL EXAMPLE ----------
        // Create a small list of votes for testing
        List<Vote> input = new ArrayList<>();
        input.add(new Vote("Person1", "Candidate2"));
        input.add(new Vote("Person2", "Candidate1"));
        input.add(new Vote("Person3", "Candidate2"));
        input.add(new Vote("Person4", "Candidate3"));
        input.add(new Vote("Person5", "Candidate2"));
        input.add(new Vote("Person1", "Candidate1")); // Person1 votes again

        // Initialize helper with votes
        VotingSystemHelper helper = new VotingSystemHelper(input);

        // First call will compute and cache results
        long start1 = System.currentTimeMillis();
        System.out.println("Winner 1st call: " + helper.findWinner());
        long end1 = System.currentTimeMillis();

        // Second call will reuse cached data (super fast)
        long start2 = System.currentTimeMillis();
        System.out.println("Winner 2nd call: " + helper.findWinner());
        long end2 = System.currentTimeMillis();

        System.out.println("First Call Time: " + (end1 - start1) + " ms");
        System.out.println("Second Call Time: " + (end2 - start2) + " ms");

        // ---------- LARGE DATA TEST ----------
        // Simulate 10 million votes and 10,000 unique candidates
        List<Vote> bigList = new ArrayList<>();
        for (int i = 1; i <= 10_000_000; i++) {
            // Each person votes for one of 10,000 candidates
            bigList.add(new Vote("P" + i, "C" + (i % 10_000)));
        }

        // Create a new helper for the large dataset
        VotingSystemHelper largeHelper = new VotingSystemHelper(bigList);

        // First call builds cache (heavy but one-time)
        long s1 = System.currentTimeMillis();
        System.out.println("Top Winner (first call): " + largeHelper.findWinner());
        long e1 = System.currentTimeMillis();

        // Second call uses cache instantly
        long s2 = System.currentTimeMillis();
        System.out.println("Top Winner (cached): " + largeHelper.findWinner());
        long e2 = System.currentTimeMillis();

        System.out.println("Initial Compute: " + (e1 - s1) + " ms");
        System.out.println("Cached Fetch: " + (e2 - s2) + " ms");
    }

    // ------------------------------------------------------------
    // ⚙️ Helper method to pre-compute and cache results
    // ------------------------------------------------------------
    private void processVotesIfNeeded() {
        // Only compute if not already processed
        if (!processed) {

            // Step 1: Count votes for each candidate
            // groupingBy groups all votes by "votedToCandidate"
            // counting() counts how many times each candidate appears
            voteCountCache = votes.stream()
                    .collect(Collectors.groupingBy(v -> v.votedToCandidate, Collectors.counting()));

            // Step 2: Sort candidates by number of votes in descending order
            sortedCandidatesCache = voteCountCache.entrySet().stream()
                    .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                    .collect(Collectors.toList());

            // Step 3: Find top vote count (first element since list is sorted)
            long maxVotes = sortedCandidatesCache.get(0).getValue();

            // Step 4: Find all candidates who got the same top votes (handles ties)
            winnerCache = sortedCandidatesCache.stream()
                    .filter(e -> e.getValue() == maxVotes)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            // Step 5: Mark as processed so next time we skip recomputation
            processed = true;
        }
    }

    // ------------------------------------------------------------
    // 🥇 1️⃣ Find overall winner(s)
    // ------------------------------------------------------------
    public List<String> findWinner() {
        // Ensure data is processed before using cache
        processVotesIfNeeded();

        // Return precomputed winner(s)
        return winnerCache;
    }

    // ------------------------------------------------------------
    // 🥈 2️⃣ Find k-th winner(s) using pre-sorted cache
    // ------------------------------------------------------------
    public List<String> findKthWinner(int k) {
        // Make sure data is processed
        processVotesIfNeeded();

        // If k is invalid (like negative or beyond list size), return empty list
        if (k <= 0 || k > sortedCandidatesCache.size())
            return Collections.emptyList();

        // Find how many votes the k-th candidate got
        long kthVotes = sortedCandidatesCache.get(k - 1).getValue();

        // Return all candidates with same vote count (for ties)
        return sortedCandidatesCache.stream()
                .filter(e -> e.getValue() == kthVotes)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // ------------------------------------------------------------
    // 👥 3️⃣ Find common candidates between two persons
    // ------------------------------------------------------------
    public List<String> findCommonVotesBetweenPersons(String person1, String person2) {

        // Step 1: Collect all candidates voted by person1 into a Set (unique)
        Set<String> votesByPerson1 = votes.stream()
                .filter(v -> v.castedByPerson.equals(person1))
                .map(v -> v.votedToCandidate)
                .collect(Collectors.toSet());

        // Step 2: Collect all candidates voted by person2 into another Set
        Set<String> votesByPerson2 = votes.stream()
                .filter(v -> v.castedByPerson.equals(person2))
                .map(v -> v.votedToCandidate)
                .collect(Collectors.toSet());

        // Step 3: Retain only the common candidates between both sets
        votesByPerson1.retainAll(votesByPerson2);

        // Convert Set back to List before returning
        return new ArrayList<>(votesByPerson1);
    }

    // ------------------------------------------------------------
    // 🔁 4️⃣ Method to reset cache (if votes list changes)
    // ------------------------------------------------------------
    public void resetCache() {
        processed = false;
        voteCountCache = null;
        sortedCandidatesCache = null;
        winnerCache = null;
    }

    // ------------------------------------------------------------
    // 🧱 Inner static class Vote
    // ------------------------------------------------------------
    // Represents a single vote cast by one person for one candidate.
    static class Vote {
        public String castedByPerson;   // Who gave the vote
        public String votedToCandidate; // Which candidate received the vote

        // Constructor initializes the fields when a new Vote object is created
        public Vote(String castedByPerson, String votedToCandidate) {
            this.castedByPerson = castedByPerson;
            this.votedToCandidate = votedToCandidate;
        }
    }
}