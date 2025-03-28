package com.interview.notes.code.year.y2024.march24.test14;

import java.util.*;

/**
 * Java
 * <p>
 * Russian Dolls
 * Oleg has N dolls of various sizes. He can place the smaller dolls inside the larger ones, but he cannot place the same-sized dolls inside each other. He needs to find the minimum number of dolls that remain when the maximum number of dolls have been packed.
 * Input
 * The first line of input contains an integer N, representing the number of dolls initially.
 * The second line consists of N space-separated integers representing the size of dolls.
 * Constraints
 * 1 ≤ N≤ 105
 * 1 ≤ size of doll ≤ 105
 * <p>
 * <p>
 * Output
 * Print an integer representing the minimum number of dolls Oleg has after placing all smaller dolls inside the larger dolls.
 * Example #1
 * Input
 * 4
 * 2233
 * Output
 * 2
 * Explanation: In order to be left with the minimum number of dolls, Oleg will do the following:
 * • Put the doll at index 1 inside the doll at index 3, i.e., the doll of size two in size three.
 * • Put the doll at index 2 inside the box at index 4, i.e., the doll of size two in size three.
 * Now he is left with two dolls of size three which cannot be further placed inside each other. So, the output is 2.
 * <p>
 * <p>
 * Example #2
 * Input
 * 6
 * 1 2 2 3 45
 * Output
 * 2
 * Explanation: Oleg will place the dolls at index (1, 2, 4, 5) in the doll at index 6. So, he will be left with two dolls of sizes two and five.
 * <p>
 * <p>
 * class Outcome 1
 * Implement method/function with name 'solve' below.
 * The function accepts following as parameters.
 * 1. doll is of type List‹Integer>.
 * return int.
 * public static int solve(List<Integer> doll){
 * //Write your code here return; //return type "int"
 * }
 * }
 */
public class RussianDolls {
    public static int solve(List<Integer> dolls) {
        Collections.sort(dolls, Collections.reverseOrder());
        Set<Integer> set = new HashSet<>();
        for (int doll : dolls) {
            if (!set.contains(doll)) {
                int smallerDoll = doll / 2;
                while (smallerDoll > 0 && set.contains(smallerDoll)) {
                    smallerDoll /= 2;
                }
                if (smallerDoll != 0) {
                    set.add(doll);
                }
            }
        }
        return dolls.size() - set.size();
    }

    public static void main(String[] args) {
        List<Integer> dolls1 = Arrays.asList(2, 2, 3, 3);
        System.out.println(solve(dolls1)); // Output: 2

        List<Integer> dolls2 = Arrays.asList(1, 2, 2, 3, 4, 5);
        System.out.println(solve(dolls2)); // Output: 2
    }
}
