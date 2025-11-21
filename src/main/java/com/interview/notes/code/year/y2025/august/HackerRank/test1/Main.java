package com.interview.notes.code.year.y2025.august.HackerRank.test1;

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

    // helper: “1.0” -> “1”, “10.5000” -> “10.5”
    private static String formatDoubleTrim(double v) {
        // BigDecimal handles proper trimming for printable form
        return BigDecimal.valueOf(v).stripTrailingZeros().toPlainString();
    }

    // 1) double + String -> String, with trimmed double representation
    public String add(double a, String b) {
        return formatDoubleTrim(a) + b;
    }

    // 2) double + double -> String, rounded HALF_UP to 2 decimals
    public String add(double a, double b) {
        BigDecimal x = BigDecimal.valueOf(a);
        BigDecimal y = BigDecimal.valueOf(b);
        BigDecimal sum = x.add(y).setScale(2, RoundingMode.HALF_UP);
        return sum.toPlainString();
    }

    // 3) String + String -> String
    public String add(String a, String b) {
        return a + b;
    }
}

public class Main {
    // small utility for asserting equality
    private static String assertEquals(String name, String expected, Supplier<String> actualSupplier) {
        try {
            String actual = actualSupplier.get();
            boolean pass = Objects.equals(expected, actual);
            return (pass ? "PASS: " : "FAIL: ") + name + " | expected=[" + expected + "] actual=[" + actual + "]";
        } catch (Throwable t) {
            return "FAIL: " + name + " | threw " + t.getClass().getSimpleName() + ": " + t.getMessage();
        }
    }

    public static void main(String[] args) {
        AdditionMagic am = new AdditionMagic();

        List<Supplier<String>> printers = new ArrayList<>();

        // Sample Case 0
        printers.add(() -> assertEquals("Sample0: 1 + 2",
                "3.00", () -> am.add(1, 2)));
        printers.add(() -> assertEquals("Sample0: 3 + 4",
                "7.00", () -> am.add(3, 4)));

        // Sample Case 1
        printers.add(() -> assertEquals("Sample1: 1 + \"hello\"",
                "1hello", () -> am.add(1, "hello")));
        printers.add(() -> assertEquals("Sample1: \"hello,\" + \"world!\"",
                "hello,world!", () -> am.add("hello,", "world!")));
        printers.add(() -> assertEquals("Sample1: 5 + 7",
                "12.00", () -> am.add(5, 7)));

        // Rounding checks (HALF_UP, 2 dp)
        printers.add(() -> assertEquals("Round: 1 + 2.2", "3.20", () -> am.add(1, 2.2)));
        printers.add(() -> assertEquals("Round: 3.765 + 0", "3.77", () -> am.add(3.765, 0)));
        printers.add(() -> assertEquals("Round: 3.7649 + 0", "3.76", () -> am.add(3.7649, 0)));
        printers.add(() -> assertEquals("Round: 3.778 + 0", "3.78", () -> am.add(3.778, 0)));
        printers.add(() -> assertEquals("Round: -1.235 + 0", "-1.24", () -> am.add(-1.235, 0)));

        // String-format trimming checks
        printers.add(() -> assertEquals("Trim: 10.0 + \"x\"", "10x", () -> am.add(10.0, "x")));
        printers.add(() -> assertEquals("Trim: 10.5000 + \"y\"", "10.5y", () -> am.add(10.5, "y")));
        printers.add(() -> assertEquals("Concat: null + \"a\"", "nulla", () -> am.add(null, "a")));

        // Edge numbers
        printers.add(() -> assertEquals("Edge: big double sum", "20000000000.00", () -> am.add(1e10, 1e10)));
        printers.add(() -> assertEquals("Edge: negatives", "-3.30", () -> am.add(-1.1, -2.2)));
        printers.add(() -> assertEquals("Edge: mix sign", "0.00", () -> am.add(1.11, -1.11)));

        // Print all small tests using streams
        String results = printers.stream().map(Supplier::get).collect(Collectors.joining("\n"));
        System.out.println(results);

        // Large data test (100k random operations) — validates rounding vs BigDecimal
        String largeRun = largeDataTest(am, 100_000);
        System.out.println(largeRun);
    }

    private static String largeDataTest(AdditionMagic am, int n) {
        long start = System.currentTimeMillis();

        // verify many sums match BigDecimal HALF_UP(2) reference
        long failures = IntStream.range(0, n).parallel().filter(i -> {
            double a = randDouble();
            double b = randDouble();
            String got = am.add(a, b);
            String ref = BigDecimal.valueOf(a).add(BigDecimal.valueOf(b))
                    .setScale(2, RoundingMode.HALF_UP).toPlainString();
            return !got.equals(ref);
        }).count();

        long elapsed = System.currentTimeMillis() - start;
        return (failures == 0
                ? "PASS: Large data test (" + n + " ops) in " + elapsed + " ms"
                : "FAIL: Large data test — mismatches=" + failures + " in " + elapsed + " ms");
    }

    private static double randDouble() {
        // bounded randoms to avoid Infinity/NaN and too many extreme exponents
        ThreadLocalRandom r = ThreadLocalRandom.current();
        double v = r.nextDouble(-1e6, 1e6);
        // add a fractional part with up to 6 decimals
        double frac = r.nextLong(0, 1_000_000) / 1_000_000.0;
        return (r.nextBoolean() ? v + frac : v - frac);
    }
}