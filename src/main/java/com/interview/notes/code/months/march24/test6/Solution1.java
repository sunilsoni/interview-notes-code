package com.interview.notes.code.months.march24.test6;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Solution1 {
    public static int getMinLength(List<Integer> a, int k) {
        int i = 0;
        while (i < a.size() - 1) {
            if ((long) a.get(i) * a.get(i + 1) <= k) {
                a.set(i, a.get(i) * a.get(i + 1));
                a.remove(i + 1);
            } else {
                i++;
            }
        }
        return a.size();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // The first line contains an integer, n, the number of elements in a.
        List<Integer> a = Arrays.asList(new Integer[n]);

        for (int i = 0; i < n; i++) {
            a.set(i, scanner.nextInt()); // Each line i of the n subsequent lines (where 0 ≤ i < n) contains an integer describing ai.
        }

        int k = scanner.nextInt(); // The last line contains an integer, k, the constraint of the operation.

        System.out.println(getMinLength(a, k));
        scanner.close();
    }
}
