package com.interview.notes.code.months.oct24.test4;


import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Random;

interface GameTracker {
    void addMatch(String team1, String team2, String score);

    String findFirst();
}

/*

WORKING:

Java: Volleyball Team Ranking
System
Description
In this task, you will implement a system to track the scores of volleyball teams based on match results. You will create a class that maintains team scores and provides a way to query the team with the highest score efficiently after recording all the match results.
Given an interface GameTracker, with the following functions:
• void addMatch(String team 1, String team2, String score);
• String findFirst);
Create a class called
VolleyballGameTracker implementing the Game Tracker interface. The specifications are given below.
1. Constructor:
1. VolleyballGame Tracker(int numTeams,
String|] teamNames): Initializes the tracker with the number of teams and their names.

Create a class called
VolleyballGame Tracker implementing the Game Tracker interface. The specifications are given below.
1. Constructor:
1. VolleyballGame Tracker(int numTeams,
String|| teamNames): Initializes the tracker with the number of teams and their names.
2. Methods:
1. void addMatch(String team 1, String team2, String score): Records the match result between two teams.
2. String findFirst): Returns the name of the team with the highest score.
Constraints
• The findFirst method should have a time complexity of less than O(n).
• Input Format for Custom Testing
Input from stdin will be processed as follows and passed to the function.
The first line contains an integer n, the number of teams.
The next n lines contain strings representing the names of the teams.
The line after the team names contains an integer m, the number of matches.
The next m lines each contain a string with

The first line contains an integer n, the number of teams.
The next n lines contain strings representing the names of the teams.
The line after the team names contains an integer m, the number of matches.
The next m lines each contain a string with the names of the two teams and their score in the format "team team2 x:y".
• Sample Case 0
Sample Input 0
4
TeamA
TeamB
TeamC
TeamD
3
TeamA TeamB 20:19
TeamC TeamD 25:25
TeamA TeamC 23:25
Sample Output 0
TeamC
Explanation
After the first match TeamA has 2 points and TeamB has 0 points
After the second match TeamC has 1 point and TeamD has 1 point
After the third match TeamA had 2 points and TeamC 3 points

Sample Output 0
TeamC
Explanation
After the first match TeamA has 2 points and TeamB has 0 points
After the second match TeamC has 1 point and TeamD has 1 point
After the third match TeamA had 2 points and TeamC 3 points
TeamC is the highest team with score of 3
points
 */
class Pair implements Comparable<Pair> {
    String team;
    int score;

    Pair(String team, int score) {
        this.team = team;
        this.score = score;
    }

    @Override
    public int compareTo(Pair other) {
        return Integer.compare(other.score, this.score);
    }
}

class VolleyballGameTracker implements GameTracker {
    private Map<String, Integer> teamScores;
    private PriorityQueue<Pair> maxHeap;

    public VolleyballGameTracker(int numTeams, String[] teamNames) {
        teamScores = new HashMap<>();
        maxHeap = new PriorityQueue<>();

        for (String team : teamNames) {
            teamScores.put(team, 0);
            maxHeap.offer(new Pair(team, 0));
        }
    }

    @Override
    public void addMatch(String team1, String team2, String score) {
        String[] scores = score.split(":");
        int score1 = Integer.parseInt(scores[0]);
        int score2 = Integer.parseInt(scores[1]);

        updateTeamScore(team1, score1, score2);
        updateTeamScore(team2, score2, score1);
    }

    private void updateTeamScore(String team, int teamScore, int opponentScore) {
        int points = (teamScore > opponentScore) ? 2 : (teamScore == opponentScore) ? 1 : 0;
        int newScore = teamScores.get(team) + points;
        teamScores.put(team, newScore);

        maxHeap.removeIf(pair -> pair.team.equals(team));
        maxHeap.offer(new Pair(team, newScore));
    }

    @Override
    public String findFirst() {
        return maxHeap.peek().team;
    }
}

public class VolleyballTeamRanking {
    public static void main(String[] args) {
        // Test cases
        runTestCase(new String[]{"TeamA", "TeamB", "TeamC", "TeamD"},
                new String[]{"TeamA TeamB 20:19", "TeamC TeamD 25:25", "TeamA TeamC 23:25"},
                "TeamC");

        runTestCase(new String[]{"Team1", "Team2", "Team3"},
                new String[]{"Team1 Team2 25:20", "Team2 Team3 15:25", "Team3 Team1 20:25"},
                "Team1");

        // Large input test case
        String[] largeTeams = new String[10000];
        for (int i = 0; i < 10000; i++) {
            largeTeams[i] = "Team" + i;
        }
        String[] largeMatches = new String[100000];
        Random rand = new Random();
        for (int i = 0; i < 100000; i++) {
            int team1 = rand.nextInt(10000);
            int team2 = rand.nextInt(10000);
            while (team2 == team1) {
                team2 = rand.nextInt(10000);
            }
            int score1 = rand.nextInt(26);
            int score2 = rand.nextInt(26);
            largeMatches[i] = "Team" + team1 + " Team" + team2 + " " + score1 + ":" + score2;
        }
        runTestCase(largeTeams, largeMatches, null);
    }

    private static void runTestCase(String[] teams, String[] matches, String expectedWinner) {
        long startTime = System.currentTimeMillis();

        VolleyballGameTracker tracker = new VolleyballGameTracker(teams.length, teams);
        for (String match : matches) {
            String[] parts = match.split(" ");
            tracker.addMatch(parts[0], parts[1], parts[2]);
        }
        String winner = tracker.findFirst();

        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;

        if (expectedWinner == null) {
            System.out.println("Large input test case completed in " + executionTime + " ms");
            System.out.println("Winner: " + winner);
        } else {
            System.out.println(winner.equals(expectedWinner) ? "PASS" : "FAIL");
        }
    }
}
