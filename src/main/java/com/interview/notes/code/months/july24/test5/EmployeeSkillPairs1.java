package com.interview.notes.code.months.july24.test5;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class EmployeeSkillPairs1 {

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
            int j = i + 1;  // Start the second pointer right after i to form pairs
            while (j < n && skills.get(i) + skills.get(j) < minSkill) {
                j++;  // Move the second pointer to the right to increase the sum
            }
            int k = j;  // Start k from where j stopped
            while (k < n && skills.get(i) + skills.get(k) <= maxSkill) {
                k++;  // Move k to the right as long as the sum is within the range
            }
            count += k - j;  // All indices from j to k-1 are valid pairs with i
        }

        return count;
    }
}
