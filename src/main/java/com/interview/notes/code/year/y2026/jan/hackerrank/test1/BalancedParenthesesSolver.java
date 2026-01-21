package com.interview.notes.code.year.y2026.jan.hackerrank.test1;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BalancedParenthesesSolver {
    public static int getMin(String s) {
        int bal = 0, ins = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') bal++;
            else if (bal > 0) bal--;
            else ins++;
        }
        return ins + bal;
    }

    public static void main(String[] args) {
        record Case(String s, int expect) {
        }
        List<Case> cases = List.of(
                new Case("())", 1),
                new Case("))((", 4),
                new Case("()", 0),
                new Case("(((", 3),
                new Case(")))", 3),
                new Case("", 0),
                new Case("()()()", 0),
                new Case("(()))(", 2),
                new Case(")(", 2),
                new Case("((((()))", 1)
        );
        cases.stream()
                .map(c -> Map.entry(c, getMin(c.s())))
                .forEach(e -> System.out.println(
                        (e.getValue() == e.getKey().expect() ? "PASS " : "FAIL ") +
                                e.getKey().s() + " -> " + e.getValue() + " expect " + e.getKey().expect()
                ));

        int n = 100_000;
        String large = IntStream.range(0, n)
                .map(i -> i % 3 == 0 ? ')' : (i % 2 == 0 ? '(' : ')'))
                .mapToObj(i -> String.valueOf((char) i))
                .collect(Collectors.joining());
        long t = System.nanoTime();
        int ans = getMin(large);
        long d = System.nanoTime() - t;
        System.out.println("LARGE PASS len " + large.length() + " result " + ans + " timeNs " + d);
    }
}
