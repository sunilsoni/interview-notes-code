package com.interview.notes.code.months.june23.test9;

import java.util.Arrays;
import java.util.List;

public class ThirdHighestSalary {
    public static void main(String[] args) {
        List<Integer> salaries = Arrays.asList(10000, 5000, 15000, 8000, 12000, 10000, 15000);

        int thirdHighestSalary = salaries.stream()
                .sorted((s1, s2) -> s2.compareTo(s1)) // Sort in descending order
                .distinct() // Remove duplicates
                .skip(2) // Skip first two highest salaries
                .limit(1) // Limit the result to the first element
                .findFirst() // Retrieve the third-highest salary
                .orElse(-1); // Default value if no third-highest salary found

        System.out.println("Third highest salary: " + thirdHighestSalary);
    }
}