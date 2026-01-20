package com.interview.notes.code.year.y2026.jan.hackerrank.test1;

public class BracketStabilizer {

    public static int getMin(String s) {
        int bal = 0, req = 0;
        for (var c : s.toCharArray()) {
            if (c == '(') bal++;
            else if (bal > 0) bal--;
            else req++;
        }
        return bal + req;
    }

    public static void main(String[] args) {
        var tests = java.util.Map.of(
            "(()))", 1,
            "))((", 4,
            "((", 2,
            "))", 2
        );

        tests.forEach((in, exp) -> {
            int res = getMin(in);
            System.out.println(in + " -> " + (res == exp ? "PASS" : "FAIL"));
        });

        var large = "(".repeat(50000) + ")".repeat(50000);
        long start = System.currentTimeMillis();
        boolean pass = getMin(large) == 0;
        System.out.println("Large Input (10^5) -> " + (pass ? "PASS" : "FAIL") + " (" + (System.currentTimeMillis() - start) + "ms)");
    }
}