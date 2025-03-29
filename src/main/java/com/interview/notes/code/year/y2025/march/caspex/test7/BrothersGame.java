package com.interview.notes.code.year.y2025.march.caspex.test7;

import java.util.*;

public class BrothersGame {
    public static int solve(List<Integer> arr) {
        int totalOnes = 0;
        for (int num : arr) {
            if (num == 1) totalOnes++;
        }
        int bestFlip = Integer.MIN_VALUE, current = 0;
        for (int num : arr) {
            int val = (num == 0) ? 1 : -1;
            current = Math.max(val, current + val);
            bestFlip = Math.max(bestFlip, current);
        }
        return totalOnes + bestFlip;
    }

    public static void main(String[] args) {
        System.out.println(solve(Arrays.asList(5, 0, 1, 0, 1))); 
        System.out.println(solve(Arrays.asList(6, 1, 0, 1, 0))); 
        System.out.println(solve(Arrays.asList(1, 1, 1, 1, 1))); 
        System.out.println(solve(Arrays.asList(0, 0, 0, 0)));     
    }
}