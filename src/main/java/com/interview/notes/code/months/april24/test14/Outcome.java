package com.interview.notes.code.months.april24.test14;

import java.util.Arrays;
import java.util.List;

/**
 * Java
 * RubbleRover
 * You have built a small robot named RubbleRover, which collects trash and has a radar. You want to use it for recycling. Here is what you need to know:
 * • There are garbage blocks lined up in a row, with equal distance between them.
 * • RubbleRover can tell if there is a garbage block at a certain distance from it. If it finds a block, it moves to that block and collects it.
 * • If RubbleRover cannot tell if the garbage is on its left or right, it will not go there to save energy and work efficiently.
 * • RubbleRover will keep checking further if it is unsure about the garbage's location.
 * You are given the following:
 * The first line tells where RubbleRover starts, P.
 * The second line tells how many positions have garbage, N.
 * The third line has an array of 0s and 1s, where 1 means there is garbage and 0 means there is no garbage.
 * Your job is to figure out how many garbage blocks RubbleRover will recycle while being most efficient.
 * Note: Every position is 1-based.
 * Input
 * The first line of the input contains P, representing RubbleRover's initial position.
 * The second line of the inpli contains N,
 * representing the number of positions where garbage blocks are located.
 * The third line of the input contains N integers a1, az ... an - containing only 0 and 1, which describes whether a garbage block is present in that position or not. O means there is no garbage block, while 1 means there is a garbage block.
 * Output
 * Determine how many garbage blocks RubbleRover will collect to recycle.
 * <p>
 * Constraints
 * 1 <= N, P <= 100
 * a; = {0,1}
 * Example #1
 * Input
 * 3
 * 5
 * 10 1 11
 * Output
 * 3
 * Explanation: RubbleRover is located in the 3rd position.
 * As the third line of the Input above shows, there is a garbage block at the 3rd position. RubbleRover is sure about it. So, RubbleRover will recycle it.
 * RubbleRover knows that there is one garbage block at a distance 1 from it, but it cannot determine whether it should go to the left or right. So it will not check these positions.
 * RubbleRover starts checking further and sees that there are two garbage blocks at a distance of 2 from it. Since one position can have only one garbage block, RubbleRover is sure that one is to its left and the other is to the right. So it will recycle both of these garbage blocks.
 * The answer is 1 + 2 = 3. RubbleRover will recycle 3
 * garbage blocks in this case.
 * <p>
 * Example #2
 * Input
 * 4
 * 4
 * 0110
 * Output
 * 2
 * Explanation: RubbleRover is located in the 4th position.
 * As the third line of the input above shows, there is
 * no garbage block in the 4th position.
 * There is one garbage block at a distance of 1 from RubbleRover, and RubbleRover is sure that it is located to its left (since there are only four garbage blocks). RubbleRover will recycle that garbage block.
 * The same is true for a distance of 2 from RubbleRover.
 * There is no garbage block at distance 3 from RubbleRover.
 * The answer is 1 + 1 = 2. RubbleRover will recycle 2
 * garbage blocks in this case.
 * <p>
 * <p>
 * import java.util.*; import java.io.*; import java.math.*;
 * class Outcome {
 * Implement method/function with name 'solve' below.
 * The function accepts following as parameters.
 * 1. P is of type int.
 * 2. a is of type List<Integer>.
 * return int.
 */

class Outcome {

    public static int solve(int P, List<Integer> a) {
        int collectedGarbage = 0;

        // Directly collect the garbage at the starting position if present
        if (a.get(P - 1) == 1) {
            collectedGarbage++;
            a.set(P - 1, 0); // Mark this garbage as collected
        }

        // Start from the immediate next positions and move outwards
        for (int dist = 1; dist < a.size(); dist++) {
            boolean left = P - dist - 1 >= 0 && a.get(P - dist - 1) == 1;
            boolean right = P + dist - 1 < a.size() && a.get(P + dist - 1) == 1;

            // If there is one garbage block on both sides at the same distance, it can collect both
            if (left && right) {
                collectedGarbage += 2; // Collect both
                a.set(P - dist - 1, 0); // Mark as collected
                a.set(P + dist - 1, 0); // Mark as collected
            }
            // If there is garbage only on one side, RubbleRover cannot determine its location and won't collect
        }

        return collectedGarbage;
    }


    public static void main(String[] args) {
        List<List<Integer>> examples = Arrays.asList(
                Arrays.asList(1, 0, 1, 1, 1), // Example #1
                Arrays.asList(0, 1, 1, 0)     // Example #2
        );
        List<Integer> starts = Arrays.asList(3, 4); // Starting positions for each example

        for (int i = 0; i < examples.size(); i++) {
            int result = solve(starts.get(i), examples.get(i));
            System.out.println("Example #" + (i + 1) + " Output: " + result);
        }
    }
}
