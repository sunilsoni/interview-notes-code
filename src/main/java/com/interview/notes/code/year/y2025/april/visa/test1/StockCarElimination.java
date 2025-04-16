package com.interview.notes.code.year.y2025.april.visa.test1;

import java.util.*;
import java.util.stream.Collectors;

public class StockCarElimination {

    /**
     * -----------------------------------------------------------
     * Returns the drivers in the exact order they are eliminated.
     * ----------------------------------------------------------
     */
    public static String[] solution(String[][] laps) {

        if (laps == null || laps.length == 0 || laps[0].length == 0)
            return new String[0];

        // 1. collect the list of drivers that appear in lap 0
        Set<String> remaining = Arrays.stream(laps[0])
                .map(s -> s.split("\\s+")[0])
                .collect(Collectors.toCollection(LinkedHashSet::new));

        // 2. best time seen so far for every driver
        Map<String, Integer> best = new HashMap<>();
        remaining.forEach(d -> best.put(d, Integer.MAX_VALUE));

        List<String> eliminated = new ArrayList<>();

        // 3. process every lap
        for (String[] lap : laps) {
            // 3a. update best time for each driver this lap
            for (String record : lap) {
                String[] parts = record.split("\\s+");
                String name = parts[0];
                int time = Integer.parseInt(parts[1]);
                best.put(name, Math.min(best.get(name), time));
            }

            // 3b. find the slowest best time among those still racing
            int slowestBest = remaining.stream()
                    .mapToInt(best::get)
                    .max()
                    .orElse(Integer.MIN_VALUE);

            // 3c. everyone with that slowest time is out (alphabetically)
            List<String> toRemove = remaining.stream()
                    .filter(d -> best.get(d) == slowestBest)
                    .sorted()
                    .collect(Collectors.toList());

            eliminated.addAll(toRemove);
            remaining.removeAll(toRemove);

            if (remaining.isEmpty()) break;   // finished early
        }
        return eliminated.toArray(new String[0]);
    }

    /* ------------------------------------------------------------
       Simple "framework‑less" test harness – prints PASS / FAIL.
       -----------------------------------------------------------*/
    public static void main(String[] args) {
        int passed = 0, total = 0;

        // ------------ provided example 1 -------------
        total++;
        String[][] ex1 = {
                {"Harold 154", "Gina 155", "Juan 160"},
                {"Juan 152", "Gina 153", "Harold 160"},
                {"Harold 148", "Gina 150", "Juan 151"}
        };
        passed += check(ex1, new String[]{"Juan", "Harold", "Gina"});

        // ------------ provided example 2 -------------
        total++;
        String[][] ex2 = {
                {"Gina 155", "Eddie 160", "Joy 161", "Harold 163"},
                {"Harold 151", "Gina 153", "Joy 160", "Eddie 160"},
                {"Harold 149", "Joy 150", "Gina 152", "Eddie 154"},
                {"Harold 148", "Gina 150", "Eddie 151", "Joy 155"}
        };
        passed += check(ex2, new String[]{"Harold", "Eddie", "Joy", "Gina"});

        // ------------ all drivers tie every lap -------------
        total++;
        String[][] tie = {
                {"A 100", "B 100"},
                {"A 100", "B 100"}
        };
        passed += check(tie, new String[]{"A", "B"});  // alphabetic

        // ------------ one lap only -------------
        total++;
        String[][] oneLap = {{"X 120", "Y 110", "Z 115"}};
        passed += check(oneLap, new String[]{"X"}); // X is slowest best (120)

        // ------------ large random test -------------
        total++;
        passed += largeRandomTest(1000, 100); // 1000 laps × 100 drivers

        System.out.printf("%nSummary: %d / %d tests passed%n", passed, total);
    }

    /* helper: run solution and print PASS/FAIL */
    private static int check(String[][] laps, String[] expected) {
        String[] got = solution(laps);
        boolean ok = Arrays.equals(got, expected);
        System.out.printf("%s – expected %s, got %s%n",
                ok ? "PASS" : "FAIL",
                Arrays.toString(expected),
                Arrays.toString(got));
        return ok ? 1 : 0;
    }

    /* helper: generate a big random test (returns 1 if passes) */
    private static int largeRandomTest(int lapsCount, int driversCount) {
        Random rnd = new Random(0);
        String[] names = new String[driversCount];
        for (int i = 0; i < driversCount; i++)
            names[i] = "D" + i;

        String[][] laps = new String[lapsCount][driversCount];
        for (int i = 0; i < lapsCount; i++) {
            for (int j = 0; j < driversCount; j++) {
                int time = 50 + rnd.nextInt(1000);   // 50‑1049
                laps[i][j] = names[j] + " " + time;
            }
        }

        // run – we don’t know the true answer easily, but we just make
        // sure the code finishes and returns exactly driversCount names.
        String[] out = solution(laps);
        boolean ok = out.length == driversCount;
        System.out.println(ok ? "PASS" : "FAIL" + " – large random test");
        return ok ? 1 : 0;
    }
}
