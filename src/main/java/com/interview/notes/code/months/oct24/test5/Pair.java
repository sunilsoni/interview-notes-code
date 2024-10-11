package com.interview.notes.code.months.oct24.test5;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

class Pair {
    String team;
    int score;
    
    public Pair(String team, int score) {
        this.team = team;
        this.score = score;
    }
}

interface GameTracker {
    void addMatch(String team1, String team2, String score);
    String findFirst();
}

class VolleyballGameTracker implements GameTracker {
    private Map<String, Integer> teamScores; 
    private PriorityQueue<Pair> maxHeap;

    public VolleyballGameTracker(String[] teamNames) {
        teamScores = new HashMap<>();
        maxHeap = new PriorityQueue<>((a, b) -> b.score - a.score);
        
        // Initialize team scores
        for (String team : teamNames) {
            teamScores.put(team, 0); // Initially, all teams have 0 points
            maxHeap.offer(new Pair(team, 0));
        }
    }

    public void addMatch(String team1, String team2, String score) {
        String[] scores = score.split(":");
        int score1 = Integer.parseInt(scores[0]);
        int score2 = Integer.parseInt(scores[1]);

        // Update scores for both teams
        int newScore1 = teamScores.get(team1) + (score1 > score2 ? 2 : 1); 
        int newScore2 = teamScores.get(team2) + (score2 > score1 ? 2 : 1); 

        teamScores.put(team1, newScore1);
        teamScores.put(team2, newScore2);

        // Update the heap with the new scores
        maxHeap.offer(new Pair(team1, newScore1));
        maxHeap.offer(new Pair(team2, newScore2));
    }

    public String findFirst() {
        // Remove stale entries in the heap
        while (!maxHeap.isEmpty()) {
            Pair top = maxHeap.peek();
            if (teamScores.get(top.team) == top.score) {
                return top.team;
            }
            maxHeap.poll(); // Remove invalid pairs
        }
        return null; // In case no valid teams are found
    }
}
