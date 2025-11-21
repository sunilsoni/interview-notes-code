package com.interview.notes.code.year.y2025.july.apple.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ChecksumTest {

    /**
     * Given a list of bytes (0–255) organized in chunks, returns the checksum
     * of each chunk: sum of its data bytes modulo 256.
     *
     * @param fileBytes a list of bytes organized in chunks.
     * @return the checksums of all the chunks.
     */
    public static List<Integer> computeChecksums(List<Integer> fileBytes) {
        List<Integer> checksums = new ArrayList<>();
        int i = 0, n = fileBytes.size();

        while (i < n) {
            int size = fileBytes.get(i);   // header: number of data bytes
            i++;
            if (size > 0) {
                // sum the next 'size' bytes, then mod 256
                int sum = fileBytes.subList(i, i + size)
                        .stream()
                        .mapToInt(Integer::intValue)
                        .sum();
                checksums.add(sum & 0xFF);
            } else {
                // zero-sized chunk → checksum 0
                checksums.add(0);
            }
            i += size;
        }

        return checksums;
    }

    /**
     * Simple main method to run multiple test cases and print PASS/FAIL.
     */
    public static void main(String[] args) {
        class TestCase {
            final List<Integer> input;
            final List<Integer> expected;

            TestCase(List<Integer> in, List<Integer> exp) {
                input = in;
                expected = exp;
            }
        }

        List<TestCase> tests = new ArrayList<>();

        // example from prompt
        tests.add(new TestCase(
                Arrays.asList(3, 44, 55, 66, 2, 110, 220),
                Arrays.asList(165, 74)
        ));

        // single zero-sized chunk
        tests.add(new TestCase(
                List.of(0),
                List.of(0)
        ));

        // mix of zero and non-zero chunks: [0] then [3,1,2,3]
        tests.add(new TestCase(
                Arrays.asList(0, 3, 1, 2, 3),
                Arrays.asList(0, 6)   // 1+2+3 = 6
        ));

        // two small chunks
        tests.add(new TestCase(
                Arrays.asList(2, 255, 255, 1, 128),
                Arrays.asList(254, 128)  // 510%256=254, 128%256=128
        ));

        // large chunk test: header=999, then 999 ones → sum=999%256=231
        List<Integer> big = new ArrayList<>(1000);
        big.add(999);
        for (int k = 0; k < 999; k++) big.add(1);
        tests.add(new TestCase(
                big,
                Collections.singletonList(999 % 256)
        ));

        // run all tests
        int passCount = 0;
        for (int t = 0; t < tests.size(); t++) {
            TestCase tc = tests.get(t);
            List<Integer> result = computeChecksums(tc.input);
            boolean pass = result.equals(tc.expected);

            System.out.printf(
                    "Test %d: %s\n  input: %s\n  expected: %s\n  got: %s\n\n",
                    t + 1,
                    pass ? "PASS" : "FAIL",
                    tc.input,
                    tc.expected,
                    result
            );
            if (pass) passCount++;
        }

        System.out.printf("Summary: %d/%d tests passed.\n",
                passCount, tests.size());
    }
}