package com.interview.notes.code.year.y2025.november.capitalOne.test3;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

//FInal working
public class ExpressionPuzzleMaximizer {

    public static void main(String[] args) {
        ExpressionPuzzleMaximizer solver = new ExpressionPuzzleMaximizer();
        List<TestCase> tests = new ArrayList<>();

        char[][] puzzle1 = {
                {'9', '+', '3', '-', '2'},
                {'+', '2', '+', '3', '+'},
                {'1', '-', '4', '-', '4'},
                {'-', '2', '-', '7', '+'},
                {'4', '+', '3', '+', '9'},
                {'+', '1', '+', '8', '-'},
                {'7', '-', '0', '-', '2'}
        };
        tests.add(new TestCase("ExampleMax16", puzzle1, 16));

        char[][] puzzle2 = {
                {'-', '+', '3', '-', '2'},
                {'-', '-', '2', '3', '+'},
                {'1', '8', '4', '-', '-'},
                {'+', '2', '-', '7', '+'},
                {'2', '+', '-', '+', '9'},
                {'+', '1', '+', '1', '0'},
                {'2', '-', '0', '-', '2'}
        };
        tests.add(new TestCase("SingleDigitMax9", puzzle2, 9));

        char[][] puzzle3 = {
                {'5'}
        };
        tests.add(new TestCase("SingleCell", puzzle3, 5));

        char[][] puzzle4 = {
                {'1', '+', '2'},
                {'+', '+', '+'},
                {'3', '-', '4'}
        };
        tests.add(new TestCase("Mixed", puzzle4, 6));

        int n = 50;
        int m = 50;
        char[][] big = new char[n][m];
        IntStream.range(0, n).forEach(i ->
                IntStream.range(0, m).forEach(j ->
                        big[i][j] = (j % 2 == 0) ? '9' : '+'
                )
        );
        int digitsCount = (m + 1) / 2;
        int expectedBig = 9 * digitsCount;
        tests.add(new TestCase("LargeGrid", big, expectedBig));

        int idx = 1;
        for (TestCase t : tests) {
            int res = solver.solution(t.input());
            boolean ok = res == t.expected();
            System.out.println("Case " + idx++ + " (" + t.name() + "): " + (ok ? "PASS" : "FAIL expected=" + t.expected() + " got=" + res + ")"));
        }
    }

    public int solution(char[][] puzzle) {
        int rows = puzzle.length;
        int cols = puzzle[0].length;
        int best = Integer.MIN_VALUE;

        for (int r = 0; r < rows; r++) {
            best = Math.max(best, lineMax(puzzle[r]));
        }

        for (int c = 0; c < cols; c++) {
            char[] col = new char[rows];
            for (int r = 0; r < rows; r++) {
                col[r] = puzzle[r][c];
            }
            best = Math.max(best, lineMax(col));
        }

        return best;
    }

    private int lineMax(char[] line) {
        int n = line.length;
        int best = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (!isDigit(line[i])) {
                continue;
            }
            int val = digit(line[i]);
            if (val > best) {
                best = val;
            }
            for (int pos = i + 1; pos + 1 < n; pos += 2) {
                char op = line[pos];
                char d = line[pos + 1];
                if (!isOp(op) || !isDigit(d)) {
                    break;
                }
                int num = digit(d);
                val = op == '+' ? val + num : val - num;
                if (val > best) {
                    best = val;
                }
            }
        }
        return best;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private boolean isOp(char c) {
        return c == '+' || c == '-';
    }

    private int digit(char c) {
        return c - '0';
    }

    private record TestCase(String name, char[][] input, int expected) {
    }
}
