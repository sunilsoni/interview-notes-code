package com.interview.notes.code.year.y2024.july24.test4;

import java.util.Arrays;
import java.util.List;

public class EmployeeSkillPairs12 {

    public static void main(String[] args) {
        // Example 1:
        List<Integer> skills1 = Arrays.asList(6, 2, 3);
        int minSkill1 = 7;
        int maxSkill1 = 10;
        System.out.println(getNumTeams(skills1, minSkill1, maxSkill1)); // Output: 2

        // Example 2:
        List<Integer> skills2 = Arrays.asList(100, 100);
        int minSkill2 = 200;
        int maxSkill2 = 200;
        System.out.println(getNumTeams(skills2, minSkill2, maxSkill2)); // Output: 1
    }

    public static long getNumTeams(List<Integer> skill, int minSkill, int maxSkill) {
        long count = 0;
        int n = skill.size();

        // Sort the skills list to optimize the search
        Integer[] skillsArray = skill.toArray(new Integer[n]);
        Arrays.sort(skillsArray);

        // Use two pointers to efficiently find valid pairs
        int left = 0;
        int right = n - 1;
        while (left < right) {
            int sum = skillsArray[left] + skillsArray[right];
            if (sum >= minSkill && sum <= maxSkill) {
                // If a valid pair is found, count it and move both pointers
                count++;
                left++;
                right--;
            } else if (sum < minSkill) {
                // If the sum is too small, move the left pointer to increase the sum
                left++;
            } else {
                // If the sum is too large, move the right pointer to decrease the sum
                right--;
            }
        }

        return count;
    }
}