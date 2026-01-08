package com.interview.notes.code.year.y2026.jan.common.test2;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.IntStream;

public class SecondHighestFinder {
    
    // method to get second highest number
    static Optional<Integer> getSecondHighest(List<Integer> nums) {
        // stream the list
        return nums.stream()
                   .distinct() // remove duplicates
                   .sorted(Comparator.reverseOrder()) // sort descending
                   .skip(1) // skip first (highest)
                   .findFirst(); // get second highest
    }

    // main method for testing
    public static void main(String[] args) {
        // test cases
        var tests = Map.of(
            "Case1", List.of(5,13,1,13,1,8,3,9,4), // normal case
            "Case2", List.of(1,1,1),               // all same
            "Case3", List.of(10),                  // single element
            "Case4", List.of(),                    // empty list
            "Case5", IntStream.range(1,1000000).boxed().toList() // large data
        );

        // expected outputs
        var expected = Map.of(
            "Case1", Optional.of(9),
            "Case2", Optional.empty(),
            "Case3", Optional.empty(),
            "Case4", Optional.empty(),
            "Case5", Optional.of(999998) // second highest in 1..999999
        );

        // run tests
       /* tests.forEach((name, input) -> {
            var result = getSecondHighest(input); // call method
            if(result.equals(expected.get(name))) // compare with expected
                System.out.println(name+" : PASS"); // print PASS
            else
                System.out.println(name+" : FAIL, got "+result); // print FAIL
        });*/
    }
}
