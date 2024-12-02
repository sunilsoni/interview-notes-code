package com.interview.notes.code.year.y2024.jan24.test5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;


/**
 * ---
 * <p>
 * In a game, there is an array of cells, each with an integer value. In one move, you can merge any two cells to obtain a new cell that contains the sum of the two cells. The power needed in each move is the sum of the values of the two merged cells. The goal is to merge the cells until only one cell remains. Find the minimum possible power required to do so.
 * <p>
 * **Example:**
 * <p>
 * `cells = [20, 30, 40]`
 * <p>
 * 1. Select cells with values 20 and 30 and merge them to obtain [50, 40]. The power needed for this move is 20+30=50.
 * 2. Select cells with values 50 and 40 and merge them to obtain [90]. The power needed for this move is 50+40=90.
 * <p>
 * The total power required is 50+90 = 140. This is the minimum possible power.
 * <p>
 * **Function Description:**
 * <p>
 * Complete the function `minPower` in the editor.
 * <p>
 * `minPower` has the following parameter:
 * <p>
 * - `int cells[n]`: the values of each cell
 * <p>
 * **Returns:**
 * <p>
 * - `int`: the minimum power required to finish the game
 * <p>
 * **Constraints:**
 * <p>
 * - \(2 \leq n \leq 10^5\)
 * - \(1 \leq cells[i] \leq 100\)
 * <p>
 * **Input Format For Custom Testing:**
 * <p>
 * The first line contains an integer, n, the number of elements in cells.
 * <p>
 * Each line i of the n subsequent lines (where \(0 \leq i < n\)) contains an integer, `cells[i]`.
 * <p>
 * **Sample Case 0:**
 * <p>
 * - Input: `cells = [30, 10, 20]`
 * - Output: 90
 * <p>
 * **Explanation:**
 * <p>
 * - Merge 10 and 20, power needed = 10+20 = 30, cells = [30,30].
 * - Merge 30 and 30, power needed = 30+30 = 60, cells = [60].
 * <p>
 * The total power needed is 30+60 = 90.
 * <p>
 * **Sample Case 1:**
 * <p>
 * - Input: `cells = [100, 1]`
 * - Output: 101
 * <p>
 * **Explanation:**
 * <p>
 * Only one move is made, with 100 and 1, and the power needed is 100+1 = 101.
 * <p>
 * ---
 */
class MinimumPowerCell {

    public static int minPower(List<Integer> cells) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.addAll(cells);
        int totalPower = 0;

        while (pq.size() > 1) {
            int mergedPower = pq.poll() + pq.poll();
            totalPower += mergedPower;
            pq.add(mergedPower);
        }

        return totalPower;
    }

    public static void main(String[] args) {
        List<Integer> cells = Arrays.asList(20, 30, 40);
        System.out.println(MinimumPowerCell.minPower(cells)); // Output will be the total minimum power required

        // Sample Case 0
        List<Integer> cellsCase0 = Arrays.asList(30, 10, 20);
        System.out.println("Sample Case 0 Total Power: " + MinimumPowerCell.minPower(cellsCase0)); // Output: 90

        // Sample Case 1
        List<Integer> cellsCase1 = Arrays.asList(100, 1);
        System.out.println("Sample Case 1 Total Power: " + MinimumPowerCell.minPower(cellsCase1)); // Output: 101

    }

    /*
     * Complete the 'minPower' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts INTEGER_ARRAY cells as parameter.
     */

    public static int minPower1(List<Integer> cells) {
        // Write your code here

        // Sort the cells array in ascending order
        Collections.sort(cells);

        // Initialize the power and the current cell value
        int power = 0;
        int current = 0;

        // Loop through the cells array
        for (int cell : cells) {
            // Merge the current cell with the next cell
            current += cell;
            // Add the power needed for this move
            power += current;
        }

        // Return the minimum power
        return power;
    }
}
