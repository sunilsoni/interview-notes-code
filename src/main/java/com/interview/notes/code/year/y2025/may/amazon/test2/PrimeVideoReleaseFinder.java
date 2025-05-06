package com.interview.notes.code.year.y2025.may.amazon.test2;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class PrimeVideoReleaseFinder {

    private final TreeMap<LocalDate, String> releaseDates;

    public PrimeVideoReleaseFinder() {
        this.releaseDates = new TreeMap<>();
    }

    public void addMovie(String date, String movieName) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        releaseDates.put(localDate, movieName);
    }

    public String findMovie(String date) {
        LocalDate targetDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        if (releaseDates.containsKey(targetDate)) {
            return releaseDates.get(targetDate);
        }

        LocalDate floor = releaseDates.floorKey(targetDate);
        LocalDate ceiling = releaseDates.ceilingKey(targetDate);

        if (floor == null) return releaseDates.get(ceiling);
        if (ceiling == null) return releaseDates.get(floor);

        if (Math.abs(floor.toEpochDay() - targetDate.toEpochDay()) <= 
            Math.abs(ceiling.toEpochDay() - targetDate.toEpochDay())) {
            return releaseDates.get(floor);
        } else {
            return releaseDates.get(ceiling);
        }
    }

    public static void main(String[] args) {
        PrimeVideoReleaseFinder finder = new PrimeVideoReleaseFinder();

        finder.addMovie("2025-05-01", "Movie A");
        finder.addMovie("2025-05-05", "Movie B");
        finder.addMovie("2025-05-10", "Movie C");

        runTest(finder, "2025-05-01", "Movie A");
        runTest(finder, "2025-05-03", "Movie A");
        runTest(finder, "2025-05-06", "Movie B");
        runTest(finder, "2025-05-11", "Movie C");

        // Edge Cases
        runTest(finder, "2025-04-01", "Movie A");
        runTest(finder, "2025-06-01", "Movie C");

        // Large data test
        for (int i = 1; i <= 100000; i++) {
            finder.addMovie("2020-01-" + String.format("%02d", i % 30 + 1), "LargeMovie" + i);
        }
        runTest(finder, "2020-01-15", "LargeMovie99974");
    }

    private static void runTest(PrimeVideoReleaseFinder finder, String testDate, String expected) {
        String result = finder.findMovie(testDate);
        if (result.equals(expected)) {
            System.out.println("Test PASS for date " + testDate);
        } else {
            System.out.println("Test FAIL for date " + testDate + ": expected " + expected + ", got " + result);
        }
    }
}
