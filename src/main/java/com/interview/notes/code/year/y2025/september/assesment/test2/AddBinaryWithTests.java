package com.interview.notes.code.year.y2025.september.assesment.test2;

import java.util.*;
import java.util.stream.*;
import java.math.BigInteger;

public class AddBinaryWithTests {

    public static String addBinary(String a, String b) {
        int i = a.length() - 1;
        int j = b.length() - 1;
        StringBuilder sb = new StringBuilder();
        int carry = 0;
        while (i >= 0 || j >= 0 || carry != 0) {
            int sum = carry;
            if (i >= 0) sum += a.charAt(i--) - '0';
            if (j >= 0) sum += b.charAt(j--) - '0';
            sb.append((char)('0' + (sum & 1)));
            carry = sum >> 1;
        }
        return sb.reverse().toString();
    }

    static class TestCase {
        final String name;
        final String a;
        final String b;
        final String expected;
        TestCase(String name, String a, String b, String expected) {
            this.name = name;
            this.a = a;
            this.b = b;
            this.expected = expected;
        }
    }

    private static void assertEqual(String actual, String expected) {
        if (!Objects.equals(actual, expected)) throw new AssertionError("Expected " + expected + " but got " + actual);
    }

    public static void main(String[] args) {
        List<TestCase> tests = new ArrayList<>();

        tests.add(new TestCase("example1", "11", "1", "100"));
        tests.add(new TestCase("example2", "1010", "1011", "10101"));
        tests.add(new TestCase("zero+zero", "0", "0", "0"));
        tests.add(new TestCase("zero+one", "0", "1", "1"));
        tests.add(new TestCase("different-length", "1", "111", "1000"));
        tests.add(new TestCase("carry-all", "1111", "1", "10000"));

        Random rnd = new Random(123456);
        IntStream.range(0, 5).forEach(k -> {
            int la = 2000 + rnd.nextInt(3000);
            int lb = 2000 + rnd.nextInt(3000);
            String a = IntStream.range(0, la).mapToObj(i -> rnd.nextBoolean() ? "1" : "0").collect(Collectors.joining());
            String b = IntStream.range(0, lb).mapToObj(i -> rnd.nextBoolean() ? "1" : "0").collect(Collectors.joining());
            String ea = new BigInteger(a, 2).add(new BigInteger(b, 2)).toString(2);
            tests.add(new TestCase("random-big-" + k, a, b, ea));
        });

        String longOnesA = IntStream.range(0, 30000).mapToObj(i -> "1").collect(Collectors.joining());
        String longOnesB = IntStream.range(0, 30000).mapToObj(i -> "1").collect(Collectors.joining());
        tests.add(new TestCase("very-large-ones", longOnesA, longOnesB, null));

        List<String> results = tests.stream().map(tc -> {
            long start = System.currentTimeMillis();
            try {
                String res = addBinary(tc.a, tc.b);
                long time = System.currentTimeMillis() - start;
                if (tc.expected != null) {
                    assertEqual(res, tc.expected);
                    return String.format("%s: PASS (lenA=%d,lenB=%d,resultLen=%d,timeMs=%d)", tc.name, tc.a.length(), tc.b.length(), res.length(), time);
                } else {
                    int la = tc.a.length();
                    int lb = tc.b.length();
                    int expectedMaxLen = Math.max(la, lb);
                    if (!(res.length() == expectedMaxLen || res.length() == expectedMaxLen + 1)) {
                        return String.format("%s: FAIL (lenA=%d,lenB=%d,resultLen=%d,timeMs=%d) - unexpected length", tc.name, la, lb, res.length(), time);
                    }
                    if (Math.max(la, lb) <= 20000) {
                        String ea = new BigInteger(tc.a, 2).add(new BigInteger(tc.b, 2)).toString(2);
                        if (!ea.equals(res)) return String.format("%s: FAIL (lenA=%d,lenB=%d,timeMs=%d) - mismatch with BigInteger", tc.name, la, lb, time);
                    }
                    return String.format("%s: PASS (lenA=%d,lenB=%d,resultLen=%d,timeMs=%d)", tc.name, la, lb, res.length(), time);
                }
            } catch (Throwable t) {
                long time = System.currentTimeMillis() - start;
                return String.format("%s: FAIL (lenA=%d,lenB=%d,timeMs=%d) - %s", tc.name, tc.a.length(), tc.b.length(), time, t.getMessage());
            }
        }).collect(Collectors.toList());

        results.forEach(System.out::println);
        long passCount = results.stream().filter(s -> s.contains("PASS")).count();
        System.out.println(String.format("Summary: Passed %d out of %d tests", passCount, tests.size()));
    }
}