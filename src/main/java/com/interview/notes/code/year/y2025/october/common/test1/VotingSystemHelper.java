package com.interview.notes.code.year.y2025.october.common.test1;

import java.util.*;
import java.util.stream.Collectors;

public class VotingSystemHelper {

    private final List<Vote> votes;
    private boolean processed = false; // has data been computed already?
    private Map<String, Long> voteCountCache;
    private List<Map.Entry<String, Long>> sortedCandidatesCache;
    private List<String> winnerCache;
    public VotingSystemHelper(List<Vote> votes) {
        this.votes = votes;
    }

    // ----------------- Testing -----------------
    public static void main(String[] args) {

        List<Vote> input = new ArrayList<>();
        input.add(new Vote("Person1", "Candidate2"));
        input.add(new Vote("Person2", "Candidate1"));
        input.add(new Vote("Person3", "Candidate2"));
        input.add(new Vote("Person4", "Candidate3"));
        input.add(new Vote("Person5", "Candidate2"));
        input.add(new Vote("Person1", "Candidate1"));

        VotingSystemHelper helper = new VotingSystemHelper(input);

        // first time (builds cache)
        long start1 = System.currentTimeMillis();
        System.out.println("Winner 1st call: " + helper.findWinner());
        long end1 = System.currentTimeMillis();

        // second time (uses cache)
        long start2 = System.currentTimeMillis();
        System.out.println("Winner 2nd call: " + helper.findWinner());
        long end2 = System.currentTimeMillis();

        System.out.println("First Call Time: " + (end1 - start1) + " ms");
        System.out.println("Second Call Time: " + (end2 - start2) + " ms");

        // Large scale test (10 million votes)
        List<Vote> bigList = new ArrayList<>();
        for (int i = 1; i <= 10_000_000; i++) {
            bigList.add(new Vote("P" + i, "C" + (i % 10_000)));
        }

        VotingSystemHelper largeHelper = new VotingSystemHelper(bigList);

        long s1 = System.currentTimeMillis();
        System.out.println("Top Winner: " + largeHelper.findWinner());
        long e1 = System.currentTimeMillis();

        long s2 = System.currentTimeMillis();
        System.out.println("Next Call Winner: " + largeHelper.findWinner());
        long e2 = System.currentTimeMillis();

        System.out.println("Initial Compute: " + (e1 - s1) + " ms");
        System.out.println("Cached Fetch: " + (e2 - s2) + " ms");
    }

    // ----------------- Private Helper -----------------
    private void processVotesIfNeeded() {
        if (!processed) {
            // Build count map once
            voteCountCache = votes.stream()
                    .collect(Collectors.groupingBy(v -> v.votedToCandidate, Collectors.counting()));

            // Sort by descending votes once
            sortedCandidatesCache = voteCountCache.entrySet().stream()
                    .sorted((a, b) -> Long.compare(b.getValue(), a.getValue()))
                    .collect(Collectors.toList());

            // Store top winner(s)
            long maxVotes = sortedCandidatesCache.get(0).getValue();
            winnerCache = sortedCandidatesCache.stream()
                    .filter(e -> e.getValue() == maxVotes)
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            processed = true; // mark as processed
        }
    }

    // ----------------- 1️⃣ Find Winner -----------------
    public List<String> findWinner() {
        processVotesIfNeeded();
        return winnerCache;
    }

    // ----------------- 2️⃣ Find Kth Winner -----------------
    public List<String> findKthWinner(int k) {
        processVotesIfNeeded();

        if (k <= 0 || k > sortedCandidatesCache.size())
            return Collections.emptyList();

        long kthVotes = sortedCandidatesCache.get(k - 1).getValue();

        return sortedCandidatesCache.stream()
                .filter(e -> e.getValue() == kthVotes)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    // ----------------- 3️⃣ Common Votes -----------------
    public List<String> findCommonVotesBetweenPersons(String person1, String person2) {
        Set<String> votesByPerson1 = votes.stream()
                .filter(v -> v.castedByPerson.equals(person1))
                .map(v -> v.votedToCandidate)
                .collect(Collectors.toSet());

        Set<String> votesByPerson2 = votes.stream()
                .filter(v -> v.castedByPerson.equals(person2))
                .map(v -> v.votedToCandidate)
                .collect(Collectors.toSet());

        votesByPerson1.retainAll(votesByPerson2);
        return new ArrayList<>(votesByPerson1);
    }

    // ----------------- Inner Vote Class -----------------
    static class Vote {
        public String castedByPerson;
        public String votedToCandidate;

        public Vote(String castedByPerson, String votedToCandidate) {
            this.castedByPerson = castedByPerson;
            this.votedToCandidate = votedToCandidate;
        }
    }
}