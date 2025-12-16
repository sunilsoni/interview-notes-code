package com.interview.notes.code.year.y2025.december.hackerank.test4;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StringConversionOps {
    public static int getMinimumOperations(String s) {
        long ones = s.chars().filter(c -> c == '1').count();
        if ((ones & 1) == 1) return -1;
        if (ones == 0) return 0;
        int[] pos = IntStream.range(0, s.length()).filter(i -> s.charAt(i) == '1').toArray();
        long ops = 0;
        for (int i = 0; i < pos.length; i += 2) {
            int gap = pos[i + 1] - pos[i] - 1;
            ops += 2L * gap + 2;
        }
        return (int) ops;
    }

    public static void main(String[] args) {
        List<String> tests = List.of(
            "1111",
            "0000",
            "101",
            "1",
            "01",
            "10",
            "1100",
            "10001",
            "111111",
            "0101010101",
            "0".repeat(100000),
            IntStream.range(0,100000).mapToObj(i->(i%2==0)?"1":"0").collect(Collectors.joining())
        );
        List<Integer> expected = List.of(
            2,
            0,
            4,
            -1,
            2,
            2,
            2,
            8,
            6,
            10,
            0,
            -1
        );

        int pass = 0, fail = 0;
        for (int i = 0; i < tests.size(); i++) {
            String s = tests.get(i);
            int got = getMinimumOperations(s);
            int exp = expected.get(i);
            boolean ok = got == exp;
            System.out.println("Case " + i + ": " + (ok ? "PASS" : "FAIL") + " | got=" + got + " exp=" + exp + " | len=" + s.length());
            if (ok) pass++; else fail++;
        }
        System.out.println("Summary: PASS=" + pass + " FAIL=" + fail);
    }
}
