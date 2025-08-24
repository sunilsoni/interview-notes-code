package com.interview.notes.code.year.y2025.august.HackerRank.test2;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class AdditionMagic {

    private static String trimDouble(double v) {
        return BigDecimal.valueOf(v).stripTrailingZeros().toPlainString();
    }

    // double + String => String ("1.0" -> "1", "10.50" -> "10.5")
    public String add(double a, String b) {
        return trimDouble(a) + String.valueOf(b);
    }

    // double + double => String
    // Round HALF_UP to 2 decimals, then strip trailing zeros but keep at least one decimal digit.
    public String add(double a, double b) {
        BigDecimal s = BigDecimal.valueOf(a).add(BigDecimal.valueOf(b))
                .setScale(2, RoundingMode.HALF_UP);
        String out = s.stripTrailingZeros().toPlainString();
        if (!out.contains(".")) out += ".0";
        return out;
    }

    // String + String => String
    public String add(String a, String b) {
        return String.valueOf(a) + String.valueOf(b);
    }
}

public class Main {

    private static String assertEq(String name, String expected, Supplier<String> actual) {
        String got;
        try {
            got = actual.get();
        } catch (Throwable t) {
            return "FAIL: " + name + " (threw " + t.getClass().getSimpleName() + ": " + t.getMessage() + ")";
        }
        return (Objects.equals(expected, got) ? "PASS: " : "FAIL: ") +
                name + " | expected=[" + expected + "] actual=[" + got + "]";
    }

    public static void main(String[] args) {
        AdditionMagic am = new AdditionMagic();

        List<Supplier<String>> tests = new ArrayList<>();

        // ---- Site's Sample Case 0 (numbers only) ----
        tests.add(() -> assertEq("Sample0-1: 1 2", "3.0", () -> am.add(1, 2)));
        tests.add(() -> assertEq("Sample0-2: 3 4", "7.0", () -> am.add(3, 4)));

        // ---- Site's Sample Case 1 (mixed + strings) ----
        tests.add(() -> assertEq("Sample1-1: 1 hello", "1hello", () -> am.add(1, "hello")));
        tests.add(() -> assertEq("Sample1-2: hello, world!", "hello,world!", () -> am.add("hello,", "world!")));
        tests.add(() -> assertEq("Sample1-3: 5 7", "12.0", () -> am.add(5, 7)));

        // ---- Rounding behaviour (HALF_UP, 2dp; then trim keeping ≥1 dp) ----
        tests.add(() -> assertEq("Round: 1 + 2.2", "3.2", () -> am.add(1, 2.2)));
        tests.add(() -> assertEq("Round: 3.765 + 0", "3.77", () -> am.add(3.765, 0)));
        tests.add(() -> assertEq("Round: 3.7649 + 0", "3.76", () -> am.add(3.7649, 0)));
        tests.add(() -> assertEq("Round: 3.770 + 0", "3.77", () -> am.add(3.77, 0)));
        tests.add(() -> assertEq("Round: 12 + 0", "12.0", () -> am.add(12, 0)));
        tests.add(() -> assertEq("Round: -1.235 + 0", "-1.24", () -> am.add(-1.235, 0)));
        tests.add(() -> assertEq("Round: 1.105 + 0", "1.11", () -> am.add(1.105, 0)));

        // ---- double + String formatting (trim trailing zeros) ----
        tests.add(() -> assertEq("Trim: 10.0 + x", "10x", () -> am.add(10.0, "x")));
        tests.add(() -> assertEq("Trim: 10.5000 + y", "10.5y", () -> am.add(10.5, "y")));
        tests.add(() -> assertEq("Trim: 0.0 + hello", "0hello", () -> am.add(0.0, "hello")));

        // ---- String + String ----
        tests.add(() -> assertEq("Concat: hello + hello", "hellohello", () -> am.add("hello", "hello")));
        tests.add(() -> assertEq("Concat: null + a", "nulla", () -> am.add((String) null, "a")));

        // ---- Edge numbers ----
        tests.add(() -> assertEq("Edge: big sum", "20000000000.0", () -> am.add(1e10, 1e10)));
        tests.add(() -> assertEq("Edge: negatives", "-3.3", () -> am.add(-1.1, -2.2)));
        tests.add(() -> assertEq("Edge: mixed sign", "0.0", () -> am.add(1.11, -1.11)));

        // Print small test results
        String summary = tests.stream().map(Supplier::get).collect(Collectors.joining("\n"));
        System.out.println(summary);

        // ---- Large data test to ensure stability/performance (100k ops) ----
        System.out.println(runLargeDataTest(am, 100_000));
    }

    private static String runLargeDataTest(AdditionMagic am, int n) {
        long start = System.currentTimeMillis();
        long mismatches = IntStream.range(0, n).parallel().filter(i -> {
            double a = rnd();
            double b = rnd();
            String got = am.add(a, b);
            String ref = refSum(a, b);
            return !got.equals(ref);
        }).count();
        long ms = System.currentTimeMillis() - start;
        return (mismatches == 0)
                ? "PASS: Large data test (" + n + " ops) in " + ms + " ms"
                : "FAIL: Large data test — mismatches=" + mismatches + " in " + ms + " ms";
    }

    private static String refSum(double a, double b) {
        BigDecimal s = BigDecimal.valueOf(a).add(BigDecimal.valueOf(b)).setScale(2, RoundingMode.HALF_UP);
        String out = s.stripTrailingZeros().toPlainString();
        if (!out.contains(".")) out += ".0";
        return out;
    }

    private static double rnd() {
        ThreadLocalRandom r = ThreadLocalRandom.current();
        double base = r.nextDouble(-1e6, 1e6);
        return base + r.nextLong(-999_999, 1_000_000) / 1_000_000.0;
    }
}