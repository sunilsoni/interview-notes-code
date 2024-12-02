package com.interview.notes.code.year.y2024.aug24.amz.test17;

/*

Town Judge
In a town, there are N people Labelled from 1 to N.
There is a rumor that one of these people is secretly the town judge.
If the town judge exists, then:
The town judge trusts nobody.
Everybody (except for the town judge) trusts the town judge.
There is exactly one person that satisfies properties 1 and 2.


    (1) The town judge trusts nobody.
    (2) Everybody (except for the town judge) trusts the town judge.
    (3) There is exactly one person that satisfies properties 1 and 2.
You are given trust, an array of pairs trust[i] = [a, b] representing that the person
labelled a trusts the person labelled b. If the town judge exists and can be identified,
return the label of the town judge. Otherwise, return -1.




Examples:
Input: N= 2, trust = [[1,2]]
Output: 2
Input: N= 3, trust = [[1,3], [2, 3]]
Output: 3
Input: N= 3, trust = [[1,3], [2, 3], [3,1]]
Output: -1
Input: N= 3, trust = [[1,2],[2, 3]]
Output: -1
Input: N= 4, trust = [[1,3],[1,4],[2,3],[2,4], [4,3]]
Output: 31
 */
public class TownJudge {
    public static int findJudge(int N, int[][] trust) {
        int[] trustCount = new int[N + 1];

        for (int[] relation : trust) {
            trustCount[relation[0]]--;
            trustCount[relation[1]]++;
        }

        int judgeCount = 0;
        int potentialJudge = -1;

        for (int i = 1; i <= N; i++) {
            if (trustCount[i] == N - 1) {
                judgeCount++;
                potentialJudge = i;
                if (judgeCount > 1) {
                    return -1;  // More than one person satisfies the conditions
                }
            }
        }

        return judgeCount == 1 ? potentialJudge : -1;
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(findJudge(2, new int[][]{{1, 2}}));  // Expected: 2
        System.out.println(findJudge(3, new int[][]{{1, 3}, {2, 3}}));  // Expected: 3
        System.out.println(findJudge(3, new int[][]{{1, 3}, {2, 3}, {3, 1}}));  // Expected: -1
        System.out.println(findJudge(3, new int[][]{{1, 2}, {2, 3}}));  // Expected: -1
        System.out.println(findJudge(4, new int[][]{{1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}}));  // Expected: 3

        // Additional test cases
        System.out.println(findJudge(1, new int[][]{}));  // Expected: 1
        System.out.println(findJudge(4, new int[][]{{1, 2}, {1, 3}, {2, 1}, {2, 3}, {3, 1}, {3, 2}, {4, 1}, {4, 2}, {4, 3}}));  // Expected: -1
    }
}
