package com.interview.notes.code.year.y2025.july.apple.test3;

import java.util.*;

public class Main {

    /**
     * Returns a new array containing the values of `data`
     * in their original order but with duplicates removed.
     */
    public static int[] filterDuplicates(int[] data) {
        Set<Integer> seen = new HashSet<>();
        List<Integer> output = new ArrayList<>();

        for (int x : data) {
            if (seen.add(x)) {
                output.add(x);
            }
        }

        // convert back to int[]
        int[] result = new int[output.size()];
        for (int i = 0; i < output.size(); i++) {
            result[i] = output.get(i);
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // read n, then the n numbers
        int n = sc.nextInt();
        int[] data = new int[n];
        for (int i = 0; i < n; i++) {
            data[i] = sc.nextInt();
        }

        // filter and print
        int[] filtered = filterDuplicates(data);
        for (int i = 0; i < filtered.length; i++) {
            System.out.print(filtered[i]);
            if (i + 1 < filtered.length) System.out.print(" ");
        }
        System.out.println();
    }
}