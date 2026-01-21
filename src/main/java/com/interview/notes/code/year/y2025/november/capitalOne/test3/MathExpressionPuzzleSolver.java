package com.interview.notes.code.year.y2025.november.capitalOne.test3;

class MathExpressionPuzzleSolver {

    public static void main(String[] args) {
        var solver = new MathExpressionPuzzleSolver();

        record TestCase(char[][] puzzle, int expected) {
        }

        var testCases = new TestCase[]{
                new TestCase(new char[][]{
                        {'9', '+', '3', '-', '2'},
                        {'+', '2', '+', '3', '+'},
                        {'1', '-', '4', '-', '4'},
                        {'-', '2', '-', '7', '+'},
                        {'4', '+', '3', '+', '9'},
                        {'+', '1', '+', '8', '-'},
                        {'7', '-', '0', '-', '2'}
                }, 16),
                new TestCase(new char[][]{
                        {'-', '+', '3', '-', '2'},
                        {'+', '2', '+', '3', '+'},
                        {'1', '-', '8', '4', '-'},
                        {'1', '8', '4', '-', '-'},
                        {'+', '2', '-', '7', '+'},
                        {'2', '+', '-', '+', '9'},
                        {'+', '1', '+', '1', '0'},
                        {'2', '-', '0', '-', '2'}
                }, 9),
                new TestCase(new char[][]{
                        {'5'}
                }, 5),
                new TestCase(new char[][]{
                        {'9', '-', '8'}
                }, 9),
                new TestCase(new char[][]{
                        {'1', '+', '2', '+', '3'}
                }, 6),
                new TestCase(new char[][]{
                        {'7'},
                        {'-'},
                        {'3'}
                }, 7),
                new TestCase(new char[][]{
                        {'9', '-', '1', '-', '1'},
                        {'+', '0', '+', '0', '+'},
                        {'1', '+', '8', '+', '1'}
                }, 10),
                new TestCase(generateLargePuzzle(50, 50), Integer.MAX_VALUE)
        };

        int passed = 0;
        int failed = 0;

        for (int i = 0; i < testCases.length - 1; i++) {
            var tc = testCases[i];
            int result = solver.solution(tc.puzzle);
            boolean isPass = result == tc.expected;

            System.out.printf("Test %2d: %s%n", i + 1, isPass ? "PASS" : "FAIL");

            if (isPass) {
                passed++;
            } else {
                failed++;
                System.out.printf("  Expected: %d, Got: %d%n", tc.expected, result);
            }
        }

        System.out.printf("%nResults: %d PASS, %d FAIL%n", passed, failed);
    }

    static char[][] generateLargePuzzle(int rows, int cols) {
        var puzzle = new char[rows][cols];
        var chars = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', '-'};
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                puzzle[i][j] = chars[(i + j) % chars.length];
            }
        }
        return puzzle;
    }

    int solution(char[][] puzzle) {
        int maxValue = Integer.MIN_VALUE;

        for (int i = 0; i < puzzle.length; i++) {
            for (int j = 0; j < puzzle[i].length; j++) {
                if (Character.isDigit(puzzle[i][j])) {
                    maxValue = Math.max(maxValue, findMaxExpression(puzzle, i, j, 0, 1));
                    maxValue = Math.max(maxValue, findMaxExpression(puzzle, i, j, 1, 0));
                }
            }
        }

        return maxValue;
    }

    int findMaxExpression(char[][] puzzle, int startRow, int startCol, int dRow, int dCol) {
        int max = puzzle[startRow][startCol] - '0';
        var expr = new StringBuilder();
        int row = startRow;
        int col = startCol;

        while (row < puzzle.length && col < puzzle[row].length) {
            char c = puzzle[row][col];

            if (expr.isEmpty()) {
                if (!Character.isDigit(c)) break;
                expr.append(c);
            } else {
                char lastChar = expr.charAt(expr.length() - 1);

                if (Character.isDigit(lastChar)) {
                    if (c != '+' && c != '-') break;
                    expr.append(c);
                } else {
                    if (!Character.isDigit(c)) break;
                    expr.append(c);
                    max = Math.max(max, evaluate(expr.toString()));
                }
            }

            row += dRow;
            col += dCol;
        }

        return max;
    }

    int evaluate(String expr) {
        int result = expr.charAt(0) - '0';

        for (int i = 1; i < expr.length(); i += 2) {
            char op = expr.charAt(i);
            int num = expr.charAt(i + 1) - '0';
            result = op == '+' ? result + num : result - num;
        }

        return result;
    }
}