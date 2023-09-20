package com.interview.notes.code.months.july23.test3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 2. Team Formation In Java
 * <p>
 * FC Codelona is trying to assemble a team from a roster of available players. They have a minimum number of
 * players they want to sign, and each player needs to have a skill rating within a certain range. Given a list of players'
 * skill levels with desired upper and lower bounds, determine how many teams can be created from the list.
 * Example
 * skills =[12, 4, 6, 13, 5, 10]
 * minPlayers = 3
 * minLevel = 4
 * max Level = 10
 * •   The list includes players with skill levels [12, 4, 6, 13, 5, 10].
 * •  They want to hire at least 3 players with skill levels between 4 and 10, inclusive.
 * •   Four of the players with the following skill levels { 4, 6, 5,10} meet the criteria.
 * •  There are 5 ways to form a team of at least 3 players : [4, 5, 6], [4, 6, 10}, [4, 5,10}, [5, 6, 10}, and [4, 5, 6, 10}:
 * •   Return 5.
 * Function Description
 * Complete the function countTeams in the editor below.
 * countTeams has the following parameter(s):
 * int skills[n]: an array of integers that represent the skill level per player
 * int minPlayers: the minimum number of team members required
 * int minLevel: the lower limit for skill level, inclusive
 * int-maxLeveL the upper limit for skill level, inclusive
 * Return
 * /nt the total number of teams that can be formed per the criteria
 * <p>
 * <p>
 * <p>
 * <p>
 * Return
 * /nt: the total number of teams that can be formed per the criteria
 * Constraints
 * •   1 <n <20
 * •   1 < minPlayers < n
 * •   1 < minLevel < maxLevel < 1000
 * •   1 <skills[i]< 1000
 * <p>
 * <p>
 * <p>
 * Input Format for Custom Testing
 * Input from stdin will be processed as follows and passed to the function.
 * The first line contains an integer n, the size of the array skills.
 * The next n lines each contain an element skills[i] where 0 s. i < n.
 * The next line contains an integer, minPlayers, the minimum number of players to be included in the team.
 * The next line contains an integer, minLevel, the lower limit of skill level to select
 * 7he next line contains an integer, maxLevel, the upper limit of skill level to select
 * <p>
 * <p>
 * <p>
 * ▼ Sample Case
 * Sample Input 0
 * STDIN           Function
 * 4             ->     skills[] size n = 4
 * 4        ->         skills = [4, 8, 5, 6]
 * 8
 * 5
 * 6
 * 1          ->     minPlayers         = 1
 * 5          ->    minLevel =      5
 * 7          ->     maxLevel =     7
 */
public class TeamFormation {
    public static void main(String[] args) {
        int[] skills = {4, 8, 5, 6};
        System.out.println(countTeams(skills, 1, 5, 7));
    }

    public static int countTeams(int[] skills, int minPlayers, int minLevel, int maxLevel) {
        List<Integer> eligiblePlayers = Arrays.stream(skills)
                .filter(skill -> skill >= minLevel && skill <= maxLevel)
                .boxed()
                .collect(Collectors.toList());

        int teamCount = 0;
        for (int r = minPlayers; r <= eligiblePlayers.size(); r++) {
            teamCount += combination(eligiblePlayers.size(), r);
        }

        return teamCount;
    }

    public static int combination(int n, int r) {
        if (r == 0 || r == n) return 1;
        if (r > n) return 0;

        long num = 1;
        for (int i = 0; i < r; i++) {
            num *= (n - i);
            num /= (i + 1);
        }

        return (int) num;
    }
}
