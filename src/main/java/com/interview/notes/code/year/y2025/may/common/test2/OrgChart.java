package com.interview.notes.code.year.y2025.may.common.test2;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Emp {
    private final int id;
    private final String name;
    private final Integer mid; // null means top-level

    public Emp(int id, String name, Integer mid) {
        this.id = id;
        this.name = name;
        this.mid = mid;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getMid() {
        return mid;
    }
}

public class OrgChart {
    // Build the grouping once
    private final Map<Integer, List<Emp>> byManager;

    public OrgChart(List<Emp> emps) {
        this.byManager = emps.stream()
                .collect(Collectors.groupingBy(Emp::getMid));
    }

    // Simple main-method tests
    public static void main(String[] args) {
        // Sample data
        List<Emp> sample = Arrays.asList(
                new Emp(1, "Eric", null),
                new Emp(2, "Jack", 1),
                new Emp(3, "Viral", 2),
                new Emp(4, "Michael", 2),
                new Emp(5, "Nitesh", 1),
                new Emp(6, "George", 4),
                new Emp(7, "Ryan", 2)
        );
        OrgChart org = new OrgChart(sample);
        String actual = org.printChart();
        String expected =
                "Eric\n" +
                        "|__ Jack\n" +
                        "    |__ Viral\n" +
                        "    |__ Michael\n" +
                        "        |__ George\n" +
                        "    |__ Ryan\n" +
                        "|__ Nitesh\n";
        System.out.println("Sample Test: " + (actual.equals(expected) ? "PASS" : "FAIL"));
        if (!actual.equals(expected)) {
            System.out.println("Expected:\n" + expected);
            System.out.println("Actual:\n" + actual);
        }

        // Large-data smoke test
        try {
            int N = 50_000;
            List<Emp> chain = IntStream.rangeClosed(1, N)
                    .mapToObj(i -> new Emp(i, "E" + i, i == 1 ? null : i - 1))
                    .collect(Collectors.toList());
            new OrgChart(chain).printChart();
            System.out.println("Large Input Test: PASS");
        } catch (Throwable t) {
            System.out.println("Large Input Test: FAIL");
            t.printStackTrace();
        }
    }

    // Recursively build the lines
    private void build(Integer managerId, int level, StringBuilder out, Set<Integer> seen) {
        List<Emp> subs = byManager.get(managerId);
        if (subs == null) return;
        for (Emp e : subs) {
            if (!seen.add(e.getId())) {
                out.append("    ".repeat(level))
                        .append("[cycle with ")
                        .append(e.getName())
                        .append("]\n");
                continue;
            }
            String indent = level == 0 ? "" : "    ".repeat(level - 1) + "|__ ";
            out.append(indent).append(e.getName()).append("\n");
            build(e.getId(), level + 1, out, seen);
        }
    }

    // Public printer
    public String printChart() {
        StringBuilder sb = new StringBuilder();
        build(null, 0, sb, new HashSet<>());
        return sb.toString();
    }
}