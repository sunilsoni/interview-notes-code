package com.interview.notes.code.year.y2025.may.common.test4;

import java.util.*;
import java.util.stream.Collectors;

public class OrgChart {
    // ---------- simple POJO ----------
    static class Emp {
        final int id;
        final String name;
        final Integer mid;        // may be null

        Emp(int id, String name, Integer mid) {
            this.id = id;
            this.name = name;
            this.mid = mid;
        }
    }

    // ---------- printing ----------
    private static void printChart(List<Emp> emps) {
        // group children by manager
        Map<Integer, List<Emp>> byManager =
                emps.stream()
                    .collect(Collectors.groupingBy(e -> e.mid, LinkedHashMap::new, Collectors.toList()));

        // roots = employees whose manager id is null OR missing from list
        List<Emp> roots = emps.stream()
                              .filter(e -> e.mid == null || byManager.get(e.mid) == null)
                              .collect(Collectors.toList());

        // avoid printing the same employee twice (protects against cycles)
        Set<Integer> visited = new HashSet<>();

        for (Iterator<Emp> it = roots.iterator(); it.hasNext(); ) {
            Emp root = it.next();
            dfs(root, byManager, "", !it.hasNext(), visited);
        }
    }

    private static void dfs(Emp e,
                            Map<Integer, List<Emp>> children,
                            String indent,
                            boolean isLast,
                            Set<Integer> visited) {
        if (!visited.add(e.id)) return;            // duplicate / cycle guard

        // print this employee
        System.out.println(indent + (indent.isEmpty() ? "" : "|__ ") + e.name);

        // prepare indent for children
        String childIndent = indent + (indent.isEmpty() ? "" : "    ");

        List<Emp> subs = children.get(e.id);
        if (subs == null) return;

        for (Iterator<Emp> it = subs.iterator(); it.hasNext(); ) {
            dfs(it.next(), children, childIndent, !it.hasNext(), visited);
        }
    }

    // ---------- demo + quick tests ----------
    public static void main(String[] args) {
        // basic sample employees
        List<Emp> sample = Arrays.asList(
                new Emp(1, "Eric",    null),
                new Emp(2, "Jack",    1),
                new Emp(3, "Viral",   2),
                new Emp(4, "Michael", 2),
                new Emp(5, "Nitesh",  1),
                new Emp(6, "George",  4),
                new Emp(7, "Ryan",    2)
        );

        // ---- tiny PASS / FAIL check ----
        System.out.println("=== SAMPLE OUTPUT ===");
        printChart(sample);
        System.out.println("=====================");

        /* Manual expected string (trimmed left for clarity):
        Eric
        |__ Jack
            |__ Viral
            |__ Michael
                |__ George
            |__ Ryan
        |__ Nitesh
        */

        // Large-data sanity test (100 000 employees, depth ≤ 10)
        int n = 100_000;
        List<Emp> big = new ArrayList<>(n);
        big.add(new Emp(1, "Root", null));
        Random rnd = new Random(42);
        for (int i = 2; i <= n; i++) {
            int mid = rnd.nextInt(i - 1) + 1;              // always a valid manager id
            big.add(new Emp(i, "E" + i, mid));
        }

        long t0 = System.nanoTime();
        printChart(big);           // comment out the line if you don’t want to flood the console!
        long t1 = System.nanoTime();
        System.out.println("\nLarge test finished in " + ((t1 - t0) / 1_000_000) + " ms");
    }
}
