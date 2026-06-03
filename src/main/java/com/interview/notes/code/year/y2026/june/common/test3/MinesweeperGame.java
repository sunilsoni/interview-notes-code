package com.interview.notes.code.year.y2026.june.common.test3;

import java.util.*;

public class MinesweeperGame {

    private static final int[][] DIRS = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
    };
    private final int rows;
    private final int cols;
    private final char[][] board;
    private final boolean[][] revealed;
    private final Random random = new Random(1);

    public MinesweeperGame(int rows, int cols, int mineCount) {
        this.rows = rows;
        this.cols = cols;
        this.board = new char[rows][cols];
        this.revealed = new boolean[rows][cols];

        for (char[] row : board) Arrays.fill(row, 'E');

        placeMines(mineCount);
    }

    static void test(String name, boolean condition) {
        System.out.println(name + " : " + (condition ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {

        MinesweeperGame game1 = new MinesweeperGame(3, 3, 0);
        game1.clearBoardForTest();
        game1.putMineForTest(1, 1);

        List<Cell> r1 = game1.open(0, 0);
        test("Safe cell near mine returns number",
                r1.size() == 1 && r1.getFirst().value() == '1');

        MinesweeperGame game2 = new MinesweeperGame(4, 4, 0);
        game2.clearBoardForTest();
        game2.putMineForTest(3, 3);

        List<Cell> r2 = game2.open(0, 0);
        test("Blank cell opens multiple cells",
                r2.size() > 1 && r2.getFirst().value() == 'B');

        MinesweeperGame game3 = new MinesweeperGame(3, 3, 0);
        game3.clearBoardForTest();
        game3.putMineForTest(1, 1);

        List<Cell> r3 = game3.open(1, 1);
        test("Opening mine returns X",
                r3.size() == 1 && r3.getFirst().value() == 'X');

        List<Cell> r4 = game3.open(1, 1);
        test("Already revealed cell returns empty",
                r4.isEmpty());

        MinesweeperGame game4 = new MinesweeperGame(1000, 1000, 100);
        List<Cell> r5 = game4.open(999, 999);
        test("Large board handled safely",
                r5 != null);
    }

    private void placeMines(int mineCount) {
        int placed = 0;

        while (placed < mineCount) {
            int r = random.nextInt(rows);
            int c = random.nextInt(cols);

            if (board[r][c] != 'M') {
                board[r][c] = 'M';
                placed++;
            }
        }
    }

    public List<Cell> open(int row, int col) {
        if (!valid(row, col) || revealed[row][col]) return List.of();

        if (board[row][col] == 'M') {
            revealed[row][col] = true;
            return List.of(new Cell(row, col, 'X'));
        }

        int mines = countMines(row, col);

        if (mines > 0) {
            revealed[row][col] = true;
            return List.of(new Cell(row, col, (char) ('0' + mines)));
        }

        return revealBlankArea(row, col);
    }

    private List<Cell> revealBlankArea(int row, int col) {
        List<Cell> result = new ArrayList<>();
        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{row, col});
        revealed[row][col] = true;

        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int r = cell[0], c = cell[1];

            int mines = countMines(r, c);

            if (mines > 0) {
                result.add(new Cell(r, c, (char) ('0' + mines)));
                continue;
            }

            result.add(new Cell(r, c, 'B'));

            for (int[] d : DIRS) {
                int nr = r + d[0];
                int nc = c + d[1];

                if (valid(nr, nc) && !revealed[nr][nc] && board[nr][nc] != 'M') {
                    revealed[nr][nc] = true;
                    queue.offer(new int[]{nr, nc});
                }
            }
        }

        return result;
    }

    private int countMines(int row, int col) {
        int count = 0;

        for (int[] d : DIRS) {
            int r = row + d[0];
            int c = col + d[1];

            if (valid(r, c) && board[r][c] == 'M') {
                count++;
            }
        }

        return count;
    }

    private boolean valid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public void putMineForTest(int row, int col) {
        board[row][col] = 'M';
    }

    public void clearBoardForTest() {
        for (char[] row : board) Arrays.fill(row, 'E');
        for (boolean[] row : revealed) Arrays.fill(row, false);
    }

    record Cell(int row, int col, char value) {}
}