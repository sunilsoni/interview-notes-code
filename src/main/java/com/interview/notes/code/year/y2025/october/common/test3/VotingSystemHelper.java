package com.interview.notes.code.year.y2025.october.common.test3;

import java.util.*;
import java.util.stream.Collectors;

/**
 * VotingSystemHelper class
 * ------------------------
 * Simulates a voting system that:
 *  - Loads all votes once when object is created
 *  - Automatically counts, sorts, and caches results (preprocessing)
 *  - Provides fast lookups for:
 *      1. Top winner(s)
 *      2. K-th winner(s)
 *      3. Common candidates between two persons
 */
public class VotingSystemHelper {

    // ----------------- Instance Variables -----------------
    private final List<Vote> votes;  // all votes
    private Map<String, Long> voteCountCache;              // candidate → votes count
    private List<Map.Entry<String, Long>> sortedCandidatesCache; // sorted list of candidates
    private List<String> winnerCache;                      // top winner(s)
    // -------------------------------------------------------
    // 🏗️ Constructor — loads and preprocesses data ONCE
    // -------------------------------------------------------
    public VotingSystemHelper(List<Vote> votes) {
        this.votes = votes;
        processVotes(); // ✅ precompute everything immediately when object is created
    }

    // -------------------------------------------------------
    // 🧪 main() — test all functionalities
    // -------------------------------------------------------
    public static void main(String[] args) {

        // --------------- Small Example ---------------
        List<Vote> input = new ArrayList<>();
        input.add(new Vote("Person1", "Candidate2"));
        input.add(new Vote("Person2", "Candidate1"));
        input.add(new Vote("Person3", "Candidate2"));
        input.add(new Vote("Person4", "Candidate3"));
        input.add(new Vote("Person5", "Candidate2"));
        input.add(new Vote("Person1", "Candidate1"));

        // Constructor automatically loads and processes all votes
        VotingSystemHelper helper = new VotingSystemHelper(input);

        System.out.println("Winner => " + helper.findWinner());
        System.out.println("2nd Winner => " + helper.findKthWinner(2));
        System.out.println("Common Votes (Person1, Person2) => " +
                helper.findCommonVotesBetweenPersons("Person1", "Person2"));

        // --------------- Performance Example ---------------
        // Generate large dataset with 10 million votes and 10k candidates
        List<Vote> bigList = new ArrayList<>();
        for (int i = 1; i <= 10_000_000; i++) {
            bigList.add(new Vote("P" + i, "C" + (i % 10_000)));
        }

        long start = System.currentTimeMillis();
        // Process and cache done inside constructor
        VotingSystemHelper largeHelper = new VotingSystemHelper(bigList);
        long end = System.currentTimeMillis();
        System.out.println("Preprocessing (10M votes) took: " + (end - start) + " ms");

        // Instant queries now
        System.out.println("Winner => " + largeHelper.findWinner());
        System.out.println("5th Winner => " + largeHelper.findKthWinner(5));
    }

    // -------------------------------------------------------
    // ⚙️ processVotes() — compute counts, sort, cache winners
    // -------------------------------------------------------
    private void processVotes() {
        // Step 1: Count total votes for each candidate using groupingBy
        voteCountCache = votes.stream()
                .collect(Collectors.groupingBy(v -> v.votedToCandidate, Collectors.counting()));

        // Step 2: Sort all candidates by number of votes (descending order)
        sortedCandidatesCache = voteCountCache.entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .collect(Collectors.toList());

        // Step 3: Identify top vote count (first element after sorting)
        long maxVotes = sortedCandidatesCache.get(0).getValue();

        // Step 4: Cache all candidates who received same top votes (tie handling)
        winnerCache = sortedCandidatesCache.stream()
                .filter(e -> e.getValue() == maxVotes)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // -------------------------------------------------------
    // 🥇 findWinner() — returns top winner(s) instantly (cached)
    // -------------------------------------------------------
    public List<String> findWinner() {
        return winnerCache;
    }

    // -------------------------------------------------------
    // 🥈 findKthWinner() — returns k-th ranked candidate(s)
    // -------------------------------------------------------
    // 🥈 Fixed: findKthWinner with rank grouping
    public List<String> findKthWinner(int k) {
        if (k <= 0) return Collections.emptyList();

        // Get unique vote counts (like rank levels)
        List<Long> distinctVoteCounts = sortedCandidatesCache.stream()
                .map(Map.Entry::getValue)
                .distinct()
                .collect(Collectors.toList());

        // If k is beyond available ranks, no result
        if (k > distinctVoteCounts.size())
            return Collections.emptyList();

        // Find candidates belonging to the k-th rank
        long kthVoteCount = distinctVoteCounts.get(k - 1);

        return sortedCandidatesCache.stream()
                .filter(e -> e.getValue() == kthVoteCount)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // -------------------------------------------------------
    // 👥 findCommonVotesBetweenPersons() — shared candidates voted
    // -------------------------------------------------------
    public List<String> findCommonVotesBetweenPersons(String person1, String person2) {
        // Step 1: Collect candidates voted by person1
        Set<String> votesByPerson1 = votes.stream()
                .filter(v -> v.castedByPerson.equals(person1))
                .map(v -> v.votedToCandidate)
                .collect(Collectors.toSet());

        // Step 2: Collect candidates voted by person2
        Set<String> votesByPerson2 = votes.stream()
                .filter(v -> v.castedByPerson.equals(person2))
                .map(v -> v.votedToCandidate)
                .collect(Collectors.toSet());

        // Step 3: Retain common candidates only
        votesByPerson1.retainAll(votesByPerson2);

        return new ArrayList<>(votesByPerson1);
    }

    // -------------------------------------------------------
    // 🔁 resetCache() — if votes list changes later
    // -------------------------------------------------------
    public void resetCache() {
        voteCountCache = null;
        sortedCandidatesCache = null;
        winnerCache = null;
        processVotes(); // recompute fresh cache
    }

    // ----------------- Inner Vote Class -----------------
    static class Vote {
        public String castedByPerson;   // Who gave the vote
        public String votedToCandidate; // Which candidate received the vote

        public Vote(String castedByPerson, String votedToCandidate) {
            this.castedByPerson = castedByPerson;
            this.votedToCandidate = votedToCandidate;
        }
    }
}