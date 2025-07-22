package com.interview.notes.code.year.y2025.july.hackerank.test1;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PaymentTest {
    public static void main(String[] args) throws Exception {
        List<TestCase> tests = new ArrayList<>();

        // --- Sample test from prompt ---
        tests.add(new TestCase(
                "Sample",
                new String[]{
                        "4",
                        "makePayment 0 2 100 P2P",
                        "makePayment 1 4 18 P2M",
                        "makePayment 2 2 50 P2M",
                        "getNumberOfTransactions 2 P2P"
                },
                new String[]{
                        "0 false",
                        "1 true",
                        "2 true",
                        "1"
                }
        ));

        // --- Large-scale performance & correctness test ---
        // 200,000 P2M ops, senderId cycles 0..999 ⇒ each of 1000 IDs appears 200 times.
        int N = 200_000;
        String[] largeInput = new String[N + 2];
        largeInput[0] = String.valueOf(N + 1);
        for (int i = 0; i < N; i++) {
            // txnId=i, senderId=i%1000, amount=1, type=P2M
            largeInput[i + 1] = "makePayment " + i + " " + (i % 1000) + " 1 P2M";
        }
        // One final query: how many P2M did sender 42 make? → should be 200
        largeInput[N + 1] = "getNumberOfTransactions 42 P2M";
        tests.add(new TestCase(
                "LargePerformance",
                largeInput,
                new String[]{"200"}
        ));

        // --- run all tests ---
        for (TestCase tc : tests) {
            System.out.print("Running " + tc.name + "... ");
            String input = String.join("\n", tc.input) + "\n";

            // swap in our test input / capture output
            InputStream oldIn = System.in;
            PrintStream oldOut = System.out;
            ByteArrayInputStream inBuf = new ByteArrayInputStream(input.getBytes());
            ByteArrayOutputStream outBuf = new ByteArrayOutputStream();
            System.setIn(inBuf);
            System.setOut(new PrintStream(outBuf));

            long start = System.currentTimeMillis();
            Payment.main(new String[0]);
            long duration = System.currentTimeMillis() - start;

            // restore
            System.setIn(oldIn);
            System.setOut(oldOut);

            // compare
            String[] actual = outBuf.toString().trim().split("\\r?\\n");
            boolean pass = Arrays.equals(actual, tc.expected);

            System.out.println(pass ? "PASS" : "FAIL");
            if (!pass) {
                System.out.println("  Expected: " + Arrays.toString(tc.expected));
                System.out.println("  Actual:   " + Arrays.toString(actual));
            }
            System.out.println("  Time: " + duration + " ms\n");
        }
    }

    static class TestCase {
        final String name;
        final String[] input;
        final String[] expected;

        TestCase(String name, String[] input, String[] expected) {
            this.name = name;
            this.input = input;
            this.expected = expected;
        }
    }
}