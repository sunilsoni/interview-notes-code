package com.interview.notes.code.months.july24.test5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

//WORKING FINAL
public class EmployeeSkillPairs3 {

    public static void main(String[] args) {
        // Example 1:
        List<Integer> skills1 = Arrays.asList(6, 2, 3);
        int minSkill1 = 7;
        int maxSkill1 = 10;
        System.out.println(getNumTeams(skills1, minSkill1, maxSkill1)); // Expected output: 2

        // Example 2:
        List<Integer> skills2 = Arrays.asList(100, 100);
        int minSkill2 = 200;
        int maxSkill2 = 200;
        System.out.println(getNumTeams(skills2, minSkill2, maxSkill2)); // Expected output: 1
    }

    public static long getNumTeams(List<Integer> skills, int minSkill, int maxSkill) {
        Collections.sort(skills);
        int n = skills.size();
        long count = 0;

        for (int i = 0; i < n; i++) {
            int low = i + 1, high = n - 1;
            int start = n, end = n;

            // Binary search for the first index where sum >= minSkill
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (skills.get(i) + skills.get(mid) >= minSkill) {
                    start = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            // Binary search for the first index where sum > maxSkill
            low = start;
            high = n - 1;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (skills.get(i) + skills.get(mid) > maxSkill) {
                    end = mid;
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            count += end - start;  // All indices from start to end-1 are valid pairs with i
        }

        return count;
    }
}
