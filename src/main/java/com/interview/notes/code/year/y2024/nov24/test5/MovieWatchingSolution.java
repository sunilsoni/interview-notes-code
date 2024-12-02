package com.interview.notes.code.year.y2024.nov24.test5;

import java.util.*;

/*

WORKING SLOW
**A friend of Alex has gifted him a movie collection, and Alex is excited to watch them all as quickly as possible.**

The duration of the movies is given in an array called `durations`. Each movie's duration is between `1.01` and `3.00` hours (up to two decimal places).

Every day, Alex wants to spend no more than `3.00` hours watching the movies and to complete the movies in the minimum number of days possible. If a movie is started, Alex watches the complete movie on the same day.

**Your task**: Find the minimum number of days needed to watch all the movies.

Example:
* durations = [1.90, 1.04, 1.25, 2.5, 1.75]

Alex can watch the movies in a minimum of `3` days as:
* **Day 1**: first and second movies: 1.90 + 1.04 = 2.94 ≤ 3.00
* **Day 2**: fourth movie: 2.5 ≤ 3.00
* **Day 3**: third and fifth movies: 1.25 + 1.75 = 3.00 ≤ 3.00

Returned answer: `3`

**NOTE**: Since the minimum movie duration is greater than 1 hour, it's not possible to watch more than 2 movies in one day.



Here's the extracted text from the image:

```java
public static int minimumDays(List<double> duration) {
}
```

 ---

 */
public class MovieWatchingSolution {

    public static int minimumDays(List<Double> durations) {
        Collections.sort(durations, Collections.reverseOrder());
        int days = 0;
        double dailyDuration = 0;
        int i = 0;

        while (i < durations.size()) {
            if (dailyDuration + durations.get(i) <= 3.00) {
                dailyDuration += durations.get(i);
                i++;
            } else {
                // Try to find a smaller movie that fits
                int j = i + 1;
                while (j < durations.size() && dailyDuration + durations.get(j) > 3.00) {
                    j++;
                }
                if (j < durations.size()) {
                    dailyDuration += durations.get(j);
                    durations.remove(j);
                } else {
                    days++;
                    dailyDuration = 0;
                }
            }
        }

        if (dailyDuration > 0) {
            days++;
        }

        return days;
    }

    public static void main(String[] args) {
        // Test cases
        List<List<Double>> testCases = new ArrayList<>();
        testCases.add(Arrays.asList(1.90, 1.04, 1.25, 2.5, 1.75));
        testCases.add(Arrays.asList(1.01, 2.99, 1.32, 1.50));
        testCases.add(Arrays.asList(3.00, 2.50, 1.50, 1.25, 1.75));
        testCases.add(Arrays.asList(1.50, 1.50, 1.50, 1.50, 1.50, 1.50));

        // Large data input
        List<Double> largeInput = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 10000; i++) {
            largeInput.add(1.01 + rand.nextDouble() * 1.99);
        }
        testCases.add(largeInput);

        // Expected results
        int[] expectedResults = {3, 3, 4, 3, 5000};

        // Run tests
        for (int i = 0; i < testCases.size(); i++) {
            List<Double> testCase = new ArrayList<>(testCases.get(i)); // Create a copy to avoid modifying original
            int result = minimumDays(testCase);
            boolean passed = result == expectedResults[i];

            System.out.println("Test Case " + (i + 1) + ": " + (passed ? "PASS" : "FAIL"));
            System.out.println("Input: " + (testCase.size() > 10 ? "Large input with " + testCase.size() + " elements" : testCases.get(i)));
            System.out.println("Expected: " + expectedResults[i]);
            System.out.println("Actual: " + result);
            System.out.println();
        }
    }
}
