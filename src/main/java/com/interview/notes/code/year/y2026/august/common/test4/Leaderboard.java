package com.interview.notes.code.year.y2026.august.common.test4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leaderboard {

    // CHANGED: Transitioned from List<Integer> to Map<String, Integer> 
    // to map a playerId to their personal best score.
    private final Map<String, Integer> playerScores;
    private int highestScore;

    public Leaderboard() {
        this.playerScores = new HashMap<>();
        this.highestScore = 0;
    }

    public static void main(String[] args) {
        Leaderboard lb = new Leaderboard();

        // Player A plays 3 times. Only the 80 should be kept.
        lb.addScore("PlayerA", 50);
        lb.addScore("PlayerA", 80);
        lb.addScore("PlayerA", 70);

        // Player B and C submit scores
        lb.addScore("PlayerB", 90);
        lb.addScore("PlayerC", 60);

        System.out.println("Highest Score: " + lb.getHighestScore());
        // Expected: 90

        System.out.println("Top 3 Scores: " + lb.getTopScores(3));
        // Expected: [PlayerB: 90, PlayerA: 80, PlayerC: 60]
    }

    // CHANGED: Now accepts a playerId
    public void addScore(String playerId, int score) {
        // Get the player's current best score (default to 0 if they are new)
        int currentBest = this.playerScores.getOrDefault(playerId, 0);

        // Only update the map if the new score is strictly better
        if (score > currentBest) {
            this.playerScores.put(playerId, score);
        }

        // Keep tracking absolute highest score globally
        if (score > this.highestScore) {
            this.highestScore = score;
        }
    }

    public int getHighestScore() {
        return this.highestScore;
    }

    // CHANGED: Now returns a list of PlayerRecords instead of just Integers
    public List<PlayerRecord> getTopScores(int n) {
        List<PlayerRecord> sortedScores = new ArrayList<>();
        
        // Populate the list from our Map
        for (Map.Entry<String, Integer> entry : this.playerScores.entrySet()) {
            sortedScores.add(new PlayerRecord(entry.getKey(), entry.getValue()));
        }
        
        // Sort the list in descending order by score
        sortedScores.sort((a, b) -> Integer.compare(b.score, a.score));
        
        // Return the top 'n' records
        return sortedScores.subList(0, Math.min(n, sortedScores.size()));
    }

    // NEW: A simple helper class to bundle the player and their score together for the return list
    public static class PlayerRecord {
        public String playerId;
        public int score;

        public PlayerRecord(String playerId, int score) {
            this.playerId = playerId;
            this.score = score;
        }

        @Override
        public String toString() {
            return playerId + ": " + score;
        }
    }
}