package com.interview.notes.code.year.y2026.may.cognizant.test2;

import java.util.Arrays;
import java.util.stream.Collectors;

class Solution {

    /*
     * Find the best average grade among all students.
     */
    public static int bestAverageGrade(String[][] scores) {
        // Check if the input array is null or completely empty.
        // We need this to satisfy the instruction: "Return 0 for an empty input."
        if (scores == null || scores.length == 0) {
            // If the array is empty, we immediately exit and return 0.
            return 0;
        }

        // Open a stream on the 2D array to process the data functionally.
        return Arrays.stream(scores)
            // Collect the data into a Map to group scores by student name.
            .collect(Collectors.groupingBy(
                // The first element (index 0) of each row is the student's name, used as the Map key.
                scoreRow -> scoreRow[0],
                // The second element (index 1) is the score. We parse it to an Integer and collect into a List.
                Collectors.mapping(scoreRow -> Integer.parseInt(scoreRow[1]), Collectors.toList())
            ))
            // Extract just the values (the Lists of scores) from the Map, ignoring the names now.
            .values().stream()
            // Map each List of integer scores into a single floored average integer.
            .mapToInt(scoreList -> 
                // Cast the final floored double value back into an integer as required by the return type.
                (int) Math.floor(
                    // Open a stream on the current list of integer scores.
                    scoreList.stream()
                    // Convert the Int stream to a Double stream to prevent integer division truncation.
                    .mapToDouble(Integer::doubleValue)
                    // Calculate the precise mathematical average of the doubles.
                    .average()
                    // Provide a fallback of 0.0 just in case the list is empty (avoids Optional exceptions).
                    .orElse(0.0)
                )
            )
            // Find the highest integer value among all the calculated averages.
            .max()
            // Provide a final fallback of 0 if for some reason the stream had no elements to maximize.
            .orElse(0);
    }

    /*
     * Returns true if all the tests pass. Otherwise, returns false.
     */
    public static boolean doTestsPass() {
        // Test Case 1: Standard input provided in the problem description.
        // Bobby: 87. Charles: (100+22)/2 = 61. Eric: 64. Best is 87.
        String[][] tc1 = {
            {"Bobby", "87"},
            {"Charles", "100"},
            {"Eric", "64"},
            {"Charles", "22"}
        };
        // Verify Test Case 1 evaluates to 87.
        boolean pass1 = bestAverageGrade(tc1) == 87;

        // Test Case 2: Negative numbers to strictly test the floor function.
        // Alice: (-5 + 0) / 2 = -2.5. The floor of -2.5 is -3.
        String[][] tc2 = {
            {"Alice", "-5"},
            {"Alice", "0"}
        };
        // Verify Test Case 2 properly floors to -3.
        boolean pass2 = bestAverageGrade(tc2) == -3;

        // Test Case 3: Empty input array to test the edge case rule.
        String[][] tc3 = {};
        // Verify Test Case 3 returns 0 as requested by the instructions.
        boolean pass3 = bestAverageGrade(tc3) == 0;

        // Test Case 4: Large data input to verify performance and prevent memory overflow.
        // We use 'var' (Java 10+ feature) for concise local variable typing.
        var largeDataSize = 100000;
        // Initialize a large 2D array.
        String[][] tc4 = new String[largeDataSize][2];
        // Populate the array with 100 unique students, each having 1000 scores.
        for (int i = 0; i < largeDataSize; i++) {
            // Assign a recurring student name.
            tc4[i][0] = "Student" + (i % 100);
            // Assign a score based on the loop index.
            tc4[i][1] = String.valueOf(i % 100);
        }
        // Run the large dataset. We just need to ensure it processes without timing out or crashing.
        // The highest average should be 99 (since scores are 0 to 99).
        boolean pass4 = bestAverageGrade(tc4) == 99;

        // Return true only if ALL individual test cases evaluate to true.
        return pass1 && pass2 && pass3 && pass4;
    }

    /*
     * Execution entry point.
     */
    public static void main(String[] args) {
        // Run the tests and check the boolean result.
        if (doTestsPass()) {
            // Print success message if the boolean is true.
            System.out.println("All tests pass");
        } else {
            // Print failure message if the boolean is false.
            System.out.println("Tests fail.");
        }
    }
}