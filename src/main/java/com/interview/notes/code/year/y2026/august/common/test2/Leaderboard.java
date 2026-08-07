package com.interview.notes.code.year.y2026.august.common.test2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Leaderboard {

    private final List<Integer> scores;
    private int highestScore;

    public Leaderboard() {
        this.scores = new ArrayList<>();
        this.highestScore = 0;
    }

    public static void main(String[] args) {
        Leaderboard lb = new Leaderboard();
        lb.addScore(50);
        lb.addScore(80);
        lb.addScore(70);

        // Added two more scores to better test Top N
        lb.addScore(90);
        lb.addScore(60);

        System.out.println("Highest Score: " + lb.getHighestScore()); // Expected: 90
        System.out.println("Top 3 Scores: " + lb.getTopScores(3));    // Expected: [90, 80, 70]
        System.out.println("Top 10 Scores: " + lb.getTopScores(10));  // Expected: [90, 80, 70, 60, 50]
    }

    public void addScore(int score) {
        this.scores.add(score);

        if (score > this.highestScore) {
            this.highestScore = score;
        }
    }

    public int getHighestScore() {
        return this.highestScore;
    }

    // --- NEW LEVEL 2 METHOD ADDED HERE ---
    public List<Integer> getTopScores(int n) {
        // Create a copy of the list so we don't disrupt the original
        List<Integer> sortedScores = new ArrayList<>(this.scores);

        // Sort the copied list in descending order
        sortedScores.sort(Collections.reverseOrder());

        // Return the first 'n' elements (or fewer, if the list is smaller than 'n')
        return sortedScores.subList(0, Math.min(n, sortedScores.size()));
    }
}