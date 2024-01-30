package com.interview.notes.code.months.jan24.test6;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//NO
public class RiceBags {

    public static int maxSetSize(int[] riceBags) {
        // Sort the rice bags in ascending order
        Arrays.sort(riceBags);

        int maxLength = -1;
        Set<Integer> seen = new HashSet<>();

        // Iterate through the bags, trying to extend existing sets or start new ones
        for (int bag : riceBags) {
            if (!seen.contains(bag)) {
                int nextBag = bag * 2;
                int currentLength = 1;  // Start with the current bag

                // Extend the set as long as the next bag is found
                while (seen.contains(nextBag)) {
                    currentLength++;
                    seen.remove(nextBag);  // Remove to avoid revisiting later
                    nextBag *= 2;
                }

                maxLength = Math.max(maxLength, currentLength);
            }

            seen.add(bag);
        }

        return maxLength;
    }

    public static void main(String[] args) {
        int[] riceBags = {625, 4, 2, 5, 25};
        int maxSize = maxSetSize(riceBags);
        System.out.println("The maximum set size is: " + maxSize);

      /*  List<Integer> riceBags1 = Arrays.asList(625, 4, 2, 5, 25);
        System.out.println(maxSetSize(riceBags1)); // Should return 3

        List<Integer> riceBags2 = Arrays.asList(7, 4, 8, 9);
        System.out.println(maxSetSize(riceBags2)); // Should return -1

        List<Integer> riceBags3 = Arrays.asList(3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);
        System.out.println(maxSetSize(riceBags3)); // Should return 3*/
    }
}
