package com.interview.notes.code.months.year2023.june23.test11;

/**
 * In Java  Sprint Training :
 * <p>
 * Pat is an ordinary kid who works hard to be a great runner. As part of training,
 * Pat must run sprints of different intervals on a straight trail. The trail has
 * numbered markers that the coach uses as goals. Pat's coach provides a list of
 * goals to reach in order. Each time Pat starts at, stops at, or passes a marker it is
 * considered a visit. Determine the lowest numbered marker that is visited the
 * most times during Pat's day of training.
 * <p>
 * <p>
 * Example
 * n=5
 * sprints = [2, 4,  7, 3]
 * <p>
 * <p>
 * if the number of markers on the trail, n = 5, and assigned sprints = [2, 4, 1, 3],
 * Pat first sprints from position 2—> 4. The next sprint is from position 4—> 7, and
 * then 7 -> 3. A marker numbered position p is considered to be visited each time
 * Pat either starts or ends a sprint there and each time it is passed while sprinting.
 * The total number of visits to each position in the example is calculated like so:
 * <p>
 * <p>
 * <p>
 * Pat has visited markers 2 and 3 a total of 3 times each. Since 2 <3, the lowest
 * numbered marker that is visited the most times during Pat's day of training is 2.
 * Function Description
 * Complete the function getMostVisited'\r\ the editor below.
 * getMostVisited has the following parameter(s):
 * intn: an integer denoting the number of markers along the trail
 * intsprints[m]: an array of integers denoting the sequence of markers to
 * reach, beginning at the marker shown in sprints[O].
 * <p>
 * <p>
 * Returns
 * /nt: an integer denoting Pat's most visited position on the trail after performing
 * all m - 1 sprints. If there are multiple such answers, return the smallest one.
 * Constraints
 * •   l^n^lO5
 * •  2<m<1&
 * •   7 <sprints[i]<m(where 0<i<m)
 * •  sprintsfi-1 ] *sprintsfi](where 0 <i<m)
 * <p>
 * <p>
 * ▼ Input Format for Custom Testing
 * Input from stdin will be processed as follows and passed to the function.
 * The first line contains an integer n, the number of markers along the path.
 * The second line contains an integer m, the number of markers in the list of
 * goals.
 * The next m lines each contain an element sprints[i]where 0 < i < m.
 * ▼ Sample Case
 */
public class SprintTraining {
    public static int getMostVisited(int n, int[] sprints) {
        int[] visits = new int[n + 1];
        for (int i = 0; i < sprints.length - 1; i++) {
            int start = Math.min(sprints[i], sprints[i + 1]);
            int end = Math.max(sprints[i], sprints[i + 1]);
            for (int j = start; j <= end; j++) {
                visits[j]++;
            }
        }
        int maxVisits = 0;
        int mostVisited = 0;
        for (int i = 1; i <= n; i++) {
            if (visits[i] > maxVisits) {
                maxVisits = visits[i];
                mostVisited = i;
            }
        }
        return mostVisited;
    }

    public static void main(String[] args) {
        int[] sprints = {2, 4, 1, 3};
        System.out.println(getMostVisited(5, sprints));  // prints 2

        sprints = new int[]{2, 4, 7, 3};
        System.out.println(getMostVisited(7, sprints));  // prints 2
    }
}
