package com.interview.notes.code.year.y2025.June.common.test17;

import java.util.Arrays;
import java.util.stream.IntStream;

public class ReplaceXandRemoveY {       // main class

    /**
     * Replace 'x' with two 'a's and remove 'y' from the input array.
     */
    public static char[] replaceAndRemove(char[] input) { // method signature
        if (input == null) {             // check for null input
            return new char[0];          // return empty array if input is null
        }
        String str = new String(input);   // convert char[] to String for streaming
        int[] codePoints = str.chars()    // get an IntStream of Unicode code points
                .flatMap(c -> {               // for each code point...
                    if (c == 'x') {           // if it's 'x'...
                        return IntStream.of('a', 'a'); // emit two 'a's
                    } else if (c == 'y') {    // if it's 'y'...
                        return IntStream.empty();      // emit nothing (delete it)
                    } else {                  // all other characters...
                        return IntStream.of(c);        // keep the same character
                    }
                })
                .toArray();                   // collect the stream into an int[]

        char[] result = new char[codePoints.length]; // allocate result char[]
        for (int i = 0; i < codePoints.length; i++) { // fill result array
            result[i] = (char) codePoints[i];  // cast each code point back to char
        }
        return result;                   // return the transformed array
    }

    public static void main(String[] args) { // simple main method for testing
        char[][] testInputs = { // test inputs to validate functionality
                {'x', 'x', 'y', 'z', 'x'}, // example 1
                {'x', 'y', 'y', 'x', 'y'}, // example 2
                {},                        // empty array
                null                       // null input
        };
        char[][] expectedOutputs = { // expected results for each test
                {'a', 'a', 'a', 'a', 'z', 'a', 'a'},
                {'a', 'a', 'a', 'a'},
                {},
                {}
        };

        // run each small test and print PASS/FAIL
        for (int i = 0; i < testInputs.length; i++) {
            char[] output = replaceAndRemove(testInputs[i]);            // apply transform
            boolean pass = Arrays.equals(output, expectedOutputs[i]);   // compare with expected
            System.out.println("Test case " + (i + 1) + ": "
                    + (pass ? "PASS" : "FAIL"));            // print result
        }

        // large-input test to ensure scalability
        int largeSize = 100_000;               // define large array size
        char[] largeInput = new char[largeSize]; // allocate
        for (int i = 0; i < largeSize; i++) {  // fill with a repeating pattern
            largeInput[i] = (i % 3 == 0) ? 'x'
                    : (i % 3 == 1) ? 'y'
                    : 'z';
        }
        int countX = 0, countY = 0;            // counters for expected length calc
        for (char c : largeInput) {            // count 'x' and 'y'
            if (c == 'x') countX++;
            if (c == 'y') countY++;
        }
        char[] largeOutput = replaceAndRemove(largeInput); // transform it
        int expectedLength = largeSize - countY + countX;  // compute expected length
        System.out.println("Large input test: "
                + (largeOutput.length == expectedLength ? "PASS" : "FAIL")); // report
    }
}