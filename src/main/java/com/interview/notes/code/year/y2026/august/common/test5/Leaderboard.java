package com.interview.notes.code.year.y2026.august.common.test5;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Leaderboard {

    private final Map<String, Integer> playerScores = new HashMap<>();
    private int highestScore = 0;

    public static void main(String[] args) {
        Leaderboard lb = new Leaderboard();

        lb.addScore("PlayerA", 50);
        lb.addScore("PlayerA", 80);
        lb.addScore("PlayerA", 70);

        lb.addScore("PlayerB", 90);
        lb.addScore("PlayerC", 60);

        System.out.println("Highest Score: " + lb.getHighestScore());
        // Expected: 90

        System.out.println("Top 3 Scores: " + lb.getTopScores(3));
        // Expected: [PlayerRecord[playerId=PlayerB, score=90], PlayerRecord[playerId=PlayerA, score=80], ...]
    }

    public void addScore(String playerId, int score) {
        // MODERN JAVA: Map.merge() elegantly inserts or updates the max score for the player
        playerScores.merge(playerId, score, Integer::max);
        
        // Keep tracking the absolute highest score in O(1) time
        highestScore = Math.max(highestScore, score);
    }

    public int getHighestScore() {
        return highestScore;
    }

    public List<PlayerRecord> getTopScores(int n) {
        // JAVA STREAMS: Replaces the manual List creation, iteration, and sorting
        return playerScores.entrySet().stream()
                .map(entry -> new PlayerRecord(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparingInt(PlayerRecord::score).reversed())
                .limit(n)
                .toList(); // JAVA 16+ FEATURE: Stream.toList() returns an unmodifiable list natively
    }

    // JAVA 17 FEATURE: 'record' eliminates boilerplate class code (getters, constructors, toString)
    public record PlayerRecord(String playerId, int score) {}
}