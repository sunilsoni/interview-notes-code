package com.interview.notes.code.months.jan24.test1;

import java.util.HashMap;

/**
 * This problem involves calculating the winner of a series of competitions between teams. Each competition is between two teams,
 * and the winner is determined by an array of results. Teams earn 3 points for a win and 0 for a loss. The task is to determine the overall winner based on the most points accumulated.
 */
public class CompetitionWinner {
    public static void main(String[] args) {
        String[][] competitions = {{"HTML", "C#"}, {"C#", "Python"}, {"Python", "HTML"}};
        int[] results = {0, 0, 1};

        String winner = calculateWinner(competitions, results);
        System.out.println("Winner: " + winner);
    }

    private static String calculateWinner(String[][] competitions, int[] results) {
        HashMap<String, Integer> scores = new HashMap<>();
        String currentBestTeam = "";
        scores.put(currentBestTeam, 0);

        for (int i = 0; i < competitions.length; i++) {
            String homeTeam = competitions[i][0];
            String awayTeam = competitions[i][1];
            String winningTeam = (results[i] == 1) ? homeTeam : awayTeam;

            updateScores(scores, winningTeam, 3);

            if (scores.get(winningTeam) > scores.get(currentBestTeam)) {
                currentBestTeam = winningTeam;
            }
        }

        return currentBestTeam;
    }

    private static void updateScores(HashMap<String, Integer> scores, String team, int points) {
        if (!scores.containsKey(team)) {
            scores.put(team, 0);
        }
        scores.put(team, scores.get(team) + points);
    }
}
