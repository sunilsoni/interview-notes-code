package com.interview.notes.code.year.y2024.sept24.amazon.test1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/* WORKING
Amazon games have introduced a new mathematical game for kids. You will be given n sticks and the player is required to form rectangles from those sticks.
Formally, given an array of n integers representing the lengths of the sticks, you are required to create rectangles using those sticks. Note that a particular stick can be used in at most one rectangle and in order to create a rectangle we must have exactly two pairs of sticks with the same lengths. For example, you can create a rectangle using sticks of lengths [2, 2, 5, 5] and [4, 4, 4, 4] but not with [3, 3, 5, 8]. The goal of the game is to maximize the total sum of areas of all the rectangles formed.
In order to make the game more interesting, we are allowed to reduce any integer by at most 1.
Given the array sideLengths, representing the length of the sticks, find the maximum sum of areas of rectangles that can be formed such that each element of the array can be used as length or breadth of at most one rectangle and you are allowed to decrease any integer by at most 1. Since this number can be quite large, return the answer modulo 109+7.
Note: It is not a requirement that all side lengths be used. Also, a modulo b here represents the remainder obtained when an integer a is divided by an integer b.
Example
The side lengths are given as sideLengths = [2, 6, 6, 2, 3, 5].
The lengths 2, 2, 6, and 6 can be used to form a rectangle of area 2*6=12. No other rectangles can
be formed with the remaining lengths. The answer is 12 modulo (109+7) = 12.
Function Description
Complete the function getMaxTotalArea in the editor below. getMaxTotalArea has the following parameters):
int sideLengths[n]: the side lengths that can be used to form rectangles
Returns
int: the maximum total area of the rectangles that can be formed, modulo (10° +7).
 */
public class AmazonGamesWorking {

    public static void main(String[] args) {
        // Test cases
        List<Integer> sideLengths1 = Arrays.asList(3, 4, 5, 5, 6);
        List<Integer> sideLengths2 = Arrays.asList(2, 3, 3, 4, 6, 8, 8, 6);

        System.out.println(getMaxTotalArea(sideLengths1)); // Output: 20
        System.out.println(getMaxTotalArea(sideLengths2)); // Output: 54
    }

    public static int getMaxTotalArea(List<Integer> sideLengths) {
        // Sort the list of side lengths in descending order
        Collections.sort(sideLengths, Collections.reverseOrder());

        // List to store pairs that can form rectangles
        List<Integer> pairs = new ArrayList<>();

        int n = sideLengths.size();
        int i = 0;

        // Traverse the sorted list to find the largest pairs of same or reduced lengths
        while (i < n - 1) {
            if (sideLengths.get(i).equals(sideLengths.get(i + 1))) {
                pairs.add(sideLengths.get(i)); // Add the pair to the list
                i += 2; // Skip the next element
            } else if (sideLengths.get(i) - 1 == sideLengths.get(i + 1)) {
                pairs.add(sideLengths.get(i + 1)); // Reduced to match
                i += 2; // Skip the next element
            } else {
                i++; // Move to the next element
            }
        }

        // Calculate the total maximum area using the pairs found
        long totalArea = 0;
        for (int j = 0; j < pairs.size() - 1; j += 2) {
            totalArea += (long) pairs.get(j) * pairs.get(j + 1); // Area = side1 * side2
        }

        // Return the total area modulo 10^9 + 7
        return (int) (totalArea % (1_000_000_007));
    }
}
