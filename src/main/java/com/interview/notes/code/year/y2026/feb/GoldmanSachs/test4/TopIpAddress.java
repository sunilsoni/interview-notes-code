package com.interview.notes.code.year.y2026.feb.GoldmanSachs.test4;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

public class TopIpAddress {

    /**
     * Given Apache log lines, returns the IP address(es) that appear most often.
     * IP is the first token in each line (before the first space).
     */
    public static String findTopIpaddress(String[] lines) {
        // Count how many times each IP appears using stream groupingBy
        Map<String, Long> ipCounts = Arrays.stream(lines)
                // extract IP: it's everything before the first whitespace
                .map(line -> line.split(" ")[0])
                // group IPs and count occurrences of each
                .collect(Collectors.groupingBy(ip -> ip, Collectors.counting()));

        // find the maximum count value across all IPs
        long maxCount = ipCounts.values().stream()
                .mapToLong(Long::longValue)
                .max()
                // if input is empty, default max to 0
                .orElse(0);

        // collect all IPs that share the maximum count (handles ties)
        // join them with comma so caller can see all top IPs
        return ipCounts.entrySet().stream()
                .filter(e -> e.getValue() == maxCount)  // keep only entries matching max
                .map(Map.Entry::getKey)                  // extract the IP string
                .sorted()                                // deterministic order for ties
                .collect(Collectors.joining(","));        // combine into single result
    }

    /**
     * Runs all test cases and prints PASS/FAIL for each.
     */
    public static void main(String[] args) {
        int pass = 0, fail = 0;

        // --- Test 1: basic case, one clear winner ---
        {
            String[] lines = {
                    "10.0.0.1 - frank [10/Dec/2000:12:34:56 -0500] \"GET /a.gif HTTP/1.0\" 200 234",
                    "10.0.0.1 - frank [10/Dec/2000:12:34:57 -0500] \"GET /b.gif HTTP/1.0\" 200 234",
                    "10.0.0.2 - nancy [10/Dec/2000:12:34:58 -0500] \"GET /c.gif HTTP/1.0\" 200 234"
            };
            String result = findTopIpaddress(lines);
            boolean ok = result.equals("10.0.0.1");
            System.out.println("Test 1 (basic winner): " + (ok ? "PASS" : "FAIL") + " got=" + result);
            if (ok) pass++;
            else fail++;
        }

        // --- Test 2: tie between two IPs (both appear twice) ---
        {
            String[] lines = {
                    "10.0.0.1 - frank [10/Dec/2000:12:34:56 -0500] \"GET /a.gif HTTP/1.0\" 200 234",
                    "10.0.0.2 - nancy [10/Dec/2000:12:34:57 -0500] \"GET /b.gif HTTP/1.0\" 200 234",
                    "10.0.0.1 - frank [10/Dec/2000:12:34:58 -0500] \"GET /c.gif HTTP/1.0\" 200 234",
                    "10.0.0.2 - nancy [10/Dec/2000:12:34:59 -0500] \"GET /d.gif HTTP/1.0\" 200 234"
            };
            String result = findTopIpaddress(lines);
            // sorted alphabetically: 10.0.0.1 comes before 10.0.0.2
            boolean ok = result.equals("10.0.0.1,10.0.0.2");
            System.out.println("Test 2 (tie two IPs): " + (ok ? "PASS" : "FAIL") + " got=" + result);
            if (ok) pass++;
            else fail++;
        }

        // --- Test 3: single line input ---
        {
            String[] lines = {
                    "192.168.1.1 - alice [10/Dec/2000:12:34:56 -0500] \"GET /x.gif HTTP/1.0\" 200 100"
            };
            String result = findTopIpaddress(lines);
            boolean ok = result.equals("192.168.1.1");
            System.out.println("Test 3 (single line):  " + (ok ? "PASS" : "FAIL") + " got=" + result);
            if (ok) pass++;
            else fail++;
        }

        // --- Test 4: all same IP ---
        {
            String[] lines = {
                    "5.5.5.5 - a [date] \"GET / HTTP/1.0\" 200 1",
                    "5.5.5.5 - b [date] \"GET / HTTP/1.0\" 200 1",
                    "5.5.5.5 - c [date] \"GET / HTTP/1.0\" 200 1"
            };
            String result = findTopIpaddress(lines);
            boolean ok = result.equals("5.5.5.5");
            System.out.println("Test 4 (all same IP):  " + (ok ? "PASS" : "FAIL") + " got=" + result);
            if (ok) pass++;
            else fail++;
        }

        // --- Test 5: large data (1 million lines) ---
        {
            int n = 1_000_000;
            String[] lines = new String[n];
            // fill most lines with rotating IPs 1.0.0.0 through 1.0.0.9
            for (int i = 0; i < n; i++) {
                // IP suffix cycles 0-9; suffix 0 gets one extra hit at the end
                String ip = "1.0.0." + (i % 10);
                lines[i] = ip + " - user [date] \"GET / HTTP/1.0\" 200 1";
            }
            // give 1.0.0.0 one extra entry to make it the clear winner
            lines[n - 1] = "1.0.0.0 - user [date] \"GET / HTTP/1.0\" 200 1";

            long start = System.currentTimeMillis();
            String result = findTopIpaddress(lines);
            long elapsed = System.currentTimeMillis() - start;

            boolean ok = result.equals("1.0.0.0");
            System.out.println("Test 5 (1M lines):     " + (ok ? "PASS" : "FAIL")
                    + " got=" + result + " time=" + elapsed + "ms");
            if (ok) pass++;
            else fail++;
        }

        // --- Test 6: three-way tie ---
        {
            String[] lines = {
                    "A - x [d] \"G\" 200 1",
                    "B - x [d] \"G\" 200 1",
                    "C - x [d] \"G\" 200 1"
            };
            String result = findTopIpaddress(lines);
            boolean ok = result.equals("A,B,C");
            System.out.println("Test 6 (3-way tie):    " + (ok ? "PASS" : "FAIL") + " got=" + result);
            if (ok) pass++;
            else fail++;
        }

        // --- Summary ---
        System.out.println("\nResults: " + pass + " PASS, " + fail + " FAIL out of " + (pass + fail));
    }
}