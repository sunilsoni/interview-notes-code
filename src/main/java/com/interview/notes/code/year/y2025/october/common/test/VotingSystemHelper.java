package com.interview.notes.code.year.y2025.october.common.test;

import java.util.*;
import java.util.stream.Collectors;

public class VotingSystemHelper {

    // List of all votes given in this voting system
    private final List<Vote> votes;

    // Constructor to initialize votes list
    public VotingSystemHelper(List<Vote> votes) {
        this.votes = votes;
    }

    // ------------------ 4️⃣ Testing (No JUnit) ------------------
    public static void main(String[] args) {
        // Prepare input data
        List<Vote> input = new ArrayList<>();
        input.add(new Vote("Person1", "Candidate2"));
        input.add(new Vote("Person2", "Candidate1"));
        input.add(new Vote("Person3", "Candidate2"));
        input.add(new Vote("Person4", "Candidate3"));
        input.add(new Vote("Person5", "Candidate2"));
        input.add(new Vote("Person1", "Candidate1")); // Person1 voted twice (allowed)

        VotingSystemHelper helper = new VotingSystemHelper(input);

        // Test 1: findWinner
        List<String> expected1 = List.of("Candidate2");
        List<String> result1 = helper.findWinner();
        System.out.println("Test 1 - findWinner => " +
            (result1.equals(expected1) ? "PASS" : "FAIL | Expected: " + expected1 + ", Got: " + result1));

        // Test 2: findKthWinner(1)
        List<String> expected2 = List.of("Candidate2");
        List<String> result2 = helper.findKthWinner(1);
        System.out.println("Test 2 - findKthWinner(1) => " +
            (result2.equals(expected2) ? "PASS" : "FAIL | Expected: " + expected2 + ", Got: " + result2));

        // Test 3: findKthWinner(2)
        List<String> expected3 = List.of("Candidate1");
        List<String> result3 = helper.findKthWinner(2);
        System.out.println("Test 3 - findKthWinner(2) => " +
            (result3.equals(expected3) ? "PASS" : "FAIL | Expected: " + expected3 + ", Got: " + result3));

        // Test 4: findKthWinner(3)
        List<String> expected4 = List.of("Candidate3");
        List<String> result4 = helper.findKthWinner(3);
        System.out.println("Test 4 - findKthWinner(3) => " +
            (result4.equals(expected4) ? "PASS" : "FAIL | Expected: " + expected4 + ", Got: " + result4));

        // Test 5: findCommonVotesBetweenPersons
        List<String> expected5 = List.of("Candidate1");
        List<String> result5 = helper.findCommonVotesBetweenPersons("Person1", "Person2");
        System.out.println("Test 5 - findCommonVotesBetweenPersons => " +
            (result5.equals(expected5) ? "PASS" : "FAIL | Expected: " + expected5 + ", Got: " + result5));

        // Large Data Performance Test
        List<Vote> largeVotes = new ArrayList<>();
        for (int i = 1; i <= 100000; i++) {
            largeVotes.add(new Vote("P" + i, "C" + (i % 50))); // 50 candidates repeated
        }
        VotingSystemHelper largeHelper = new VotingSystemHelper(largeVotes);
        long start = System.currentTimeMillis();
        List<String> top = largeHelper.findKthWinner(1);
        long end = System.currentTimeMillis();
        System.out.println("Large Data Test => Completed in " + (end - start) + " ms | Result: " + top);
    }

    // ------------------ 1️⃣ findWinner ------------------
    // Finds candidate(s) who received the highest votes
    public List<String> findWinner() {
        // Step 1: Group by candidate name and count number of votes
        Map<String, Long> voteCount = votes.stream()
                .collect(Collectors.groupingBy(v -> v.votedToCandidate, Collectors.counting()));

        // Step 2: Find max votes
        long maxVotes = voteCount.values().stream().max(Long::compareTo).orElse(0L);

        // Step 3: Return all candidates who got max votes (in case of tie)
        return voteCount.entrySet().stream()
                .filter(e -> e.getValue() == maxVotes)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // ------------------ 2️⃣ findKthWinner ------------------
    // Finds the Kth winner (based on descending order of votes)
    public List<String> findKthWinner(int k) {
        // Step 1: Count votes for each candidate
        Map<String, Long> voteCount = votes.stream()
                .collect(Collectors.groupingBy(v -> v.votedToCandidate, Collectors.counting()));

        // Step 2: Sort by votes in descending order
        List<Map.Entry<String, Long>> sortedList = voteCount.entrySet().stream()
                .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                .collect(Collectors.toList());

        // Step 3: Find the kth winner
        if (k <= 0 || k > sortedList.size()) return Collections.emptyList();

        long kthVotes = sortedList.get(k - 1).getValue();

        // Step 4: Return all candidates with same kth vote count (handles ties)
        return sortedList.stream()
                .filter(e -> e.getValue() == kthVotes)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // ------------------ 3️⃣ findCommonVotesBetweenPersons ------------------
    // Finds common candidates that both persons voted for
    public List<String> findCommonVotesBetweenPersons(String castedByPerson1, String castedByPerson2) {
        // Step 1: Get all votes given by person 1
        Set<String> person1Votes = votes.stream()
                .filter(v -> v.castedByPerson.equals(castedByPerson1))
                .map(v -> v.votedToCandidate)
                .collect(Collectors.toSet());

        // Step 2: Get all votes given by person 2
        Set<String> person2Votes = votes.stream()
                .filter(v -> v.castedByPerson.equals(castedByPerson2))
                .map(v -> v.votedToCandidate)
                .collect(Collectors.toSet());

        // Step 3: Find intersection (common votes)
        person1Votes.retainAll(person2Votes);
        return new ArrayList<>(person1Votes);
    }

    // Inner static class to store each vote (who voted and to whom)
    static class Vote {
        public String castedByPerson;
        public String votedToCandidate;

        public Vote(String castedByPerson, String votedToCandidate) {
            // Save who cast the vote
            this.castedByPerson = castedByPerson;
            // Save to whom the vote was given
            this.votedToCandidate = votedToCandidate;
        }
    }
}