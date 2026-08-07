package com.interview.notes.code.year.y2026.august.common.test1;

import java.util.ArrayList;
import java.util.List;

public class Leaderboard {

    // List to store all scores for future ranking features (Level 2+)
    private final List<Integer> scores;
    
    // Variable to track the max score for O(1) retrieval
    private int highestScore;

    public Leaderboard() {
        this.scores = new ArrayList<>();
        this.highestScore = 0; // Assumes scores will be non-negative
    }

    public static void main(String[] args) {
        // Simple Test Case
        Leaderboard lb = new Leaderboard();
        lb.addScore(50);
        lb.addScore(80);
        lb.addScore(70);

        System.out.println("Highest Score: " + lb.getHighestScore()); // Expected: 80
    }

    public void addScore(int score) {
        this.scores.add(score);

        // Instantly update the highest score if the new score is better
        if (score > this.highestScore) {
            this.highestScore = score;
        }
    }

    public int getHighestScore() {
        return this.highestScore;
    }
}