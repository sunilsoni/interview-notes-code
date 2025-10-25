package com.interview.notes.code.year.y2025.october.common.test1;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAccessor;
import java.time.temporal.TemporalQueries;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class DateRangeChecker {
    static final ZoneId ZONE = ZoneId.of("Asia/Kolkata");
    static final DateTimeFormatter[] FMT = new DateTimeFormatter[] {
        DateTimeFormatter.ISO_OFFSET_DATE_TIME,
        DateTimeFormatter.ISO_ZONED_DATE_TIME,
        DateTimeFormatter.ISO_LOCAL_DATE_TIME,
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"),
        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"),
        DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss"),
        DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"),
        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"),
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"),
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
    };

    static ZonedDateTime parse(String s) {
        if (s == null) throw new IllegalArgumentException("null datetime");
        String t = s.trim();
        for (DateTimeFormatter f : FMT) {
            try {
                TemporalAccessor ta = f.parse(t);
                if (ta.query(TemporalQueries.zone()) != null) {
                    return ZonedDateTime.from(ta).withZoneSameInstant(ZONE);
                }
                LocalDateTime ldt = LocalDateTime.from(ta);
                return ldt.atZone(ZONE);
            } catch (Exception e) {
            }
        }
        try {
            Instant inst = Instant.parse(t);
            return inst.atZone(ZONE);
        } catch (Exception e) {
        }
        try {
            return ZonedDateTime.parse(t).withZoneSameInstant(ZONE);
        } catch (DateTimeParseException ex) {
            throw new DateTimeParseException("Unsupported datetime format: " + s, s, 0);
        }
    }

    public static boolean isBetween(String input, String start, String end, boolean inclusive) {
        ZonedDateTime x = parse(input);
        ZonedDateTime a = parse(start);
        ZonedDateTime b = parse(end);
        if (inclusive) {
            return !x.isBefore(a) && !x.isAfter(b);
        } else {
            return x.isAfter(a) && x.isBefore(b);
        }
    }

    public static long durationSeconds(String start, String end) {
        ZonedDateTime a = parse(start);
        ZonedDateTime b = parse(end);
        return ChronoUnit.SECONDS.between(a, b);
    }

    public static void main(String[] args) {
        String start = "2025-10-23 23:30:00";
        String end = "2025-10-25 01:00:00";

        List<TestCase> tests = Arrays.asList(
            new TestCase("Normal-WithinRange", "2025-10-24 00:30:00", start, end, true),
            new TestCase("Normal-OutsideRange", "2025-10-22 23:45:00", start, end, false),
            new TestCase("Normal-ExactlyEnd", "2025-10-25 01:00:00", start, end, true),
            new TestCase("Edge-ExclusiveStartShouldReject", "2025-10-23 23:30:00", start, end, false, false),
            new TestCase("Large-Load-SameInputRepeated", "2025-10-24 00:00:00", start, end, true)
        );

        int passed = 0;
        for (TestCase tc : tests) {
            boolean result = isBetween(tc.input, tc.start, tc.end, tc.inclusive);
            String out = String.format("%s: expected=%s actual=%s -> %s", tc.name, tc.expected, result,
                    result == tc.expected ? "PASS" : "FAIL");
            System.out.println(out);
            if (result == tc.expected) passed++;
            if ("Large-Load-SameInputRepeated".equals(tc.name)) {
                int N = 200_000;
                boolean largePass = IntStream.range(0, N).parallel()
                        .allMatch(i -> isBetween(tc.input, tc.start, tc.end, tc.inclusive));
                System.out.println("Large test iterations: " + N + " -> " + (largePass ? "PASS" : "FAIL"));
                if (largePass) passed++;
            }
        }

        System.out.println("Duration (seconds) between start and end: " + durationSeconds(start, end));
        System.out.println("SUMMARY: Passed " + passed + " / " + (tests.size() + 1) + " (including large test)");
        if (passed == tests.size() + 1) {
            System.out.println("ALL TESTS PASS");
        } else {
            System.out.println("SOME TESTS FAILED");
        }
    }

    record TestCase(String name, String input, String start, String end, boolean expected, boolean inclusive) {
            TestCase(String name, String input, String start, String end, boolean expected) {
                this(name, input, start, end, expected, true);
            }

    }
}
