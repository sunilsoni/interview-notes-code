package com.interview.notes.code.year.y2026.august.common.test3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Leaderboard {

    private final List<Integer> scores = new ArrayList<>();
    private int highestScore = Integer.MIN_VALUE;

    public static void main(String[] args) {
        Leaderboard lb = new Leaderboard();

        lb.addScore(50);
        lb.addScore(80);
        lb.addScore(70);
        lb.addScore(90);
        lb.addScore(60);

        System.out.println("Highest Score: " + lb.getHighestScore());
        System.out.println("Top 3 Scores: " + lb.getTopScores(3));

        System.out.println(
                lb.getTopScores(3).equals(List.of(90, 80, 70))
                        ? "PASS"
                        : "FAIL"
        );
    }

    public void addScore(int score) {
        scores.add(score);
        highestScore = Math.max(highestScore, score);
    }

    public int getHighestScore() {
        return highestScore;
    }

    public List<Integer> getTopScores(int n) {
        return scores.stream()
                .sorted(Comparator.reverseOrder())
                .limit(Math.max(0, n))
                .toList();
    }
}