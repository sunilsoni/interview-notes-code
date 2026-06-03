package com.interview.notes.code.year.y2026.june.common.test5;

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
    public MinesweeperGame(int rows, int cols, int mineCount) {
        this.rows = rows;
        this.cols = cols;
        this.board = new char[rows][cols];
        this.revealed = new boolean[rows][cols];

        for (char[] row : board) {
            Arrays.fill(row, 'E');
        }

        placeMines(mineCount);
    }

    static void test(String name, boolean condition) {
        System.out.println(name + " : " + (condition ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {

        MinesweeperGame game1 = new MinesweeperGame(5, 5, 0);
        game1.clearBoardForTest();
        game1.putMineForTest(1, 1);

        MoveResult r1 = game1.play(1, 1, Action.MINE_PRESENT);
        test("Mine present and actual mine",
                r1.correct() && r1.changedCells().isEmpty());

        MinesweeperGame game2 = new MinesweeperGame(5, 5, 0);
        game2.clearBoardForTest();
        game2.putMineForTest(1, 1);

        MoveResult r2 = game2.play(0, 0, Action.MINE_ABSENT);
        test("Mine absent and safe numbered cell",
                r2.correct()
                        && r2.changedCells().size() == 1
                        && r2.changedCells().getFirst().value() == '1');

        MinesweeperGame game3 = new MinesweeperGame(5, 5, 0);
        game3.clearBoardForTest();
        game3.putMineForTest(4, 4);

        MoveResult r3 = game3.play(0, 0, Action.MINE_ABSENT);
        test("Blank cell should expand",
                r3.correct()
                        && r3.changedCells().size() > 1
                        && r3.changedCells().getFirst().value() == 'B');

        MinesweeperGame game4 = new MinesweeperGame(5, 5, 0);
        game4.clearBoardForTest();
        game4.putMineForTest(2, 2);

        MoveResult r4 = game4.play(2, 2, Action.MINE_ABSENT);
        test("Mine absent but actual mine",
                !r4.correct()
                        && r4.changedCells().size() == 1
                        && r4.changedCells().getFirst().value() == 'X');

        MinesweeperGame game5 = new MinesweeperGame(5, 5, 0);
        game5.clearBoardForTest();
        game5.putMineForTest(2, 2);

        MoveResult r5 = game5.play(0, 0, Action.MINE_PRESENT);
        test("Mine present but actual safe cell",
                !r5.correct() && r5.changedCells().isEmpty());

        MoveResult r6 = game5.play(-1, 20, Action.MINE_ABSENT);
        test("Invalid input returns no changes",
                !r6.correct() && r6.changedCells().isEmpty());

        MinesweeperGame largeGame = new MinesweeperGame(1000, 1000, 500);

        MoveResult r7 = largeGame.play(999, 999, Action.MINE_ABSENT);
        test("Large board handled",
                r7.changedCells() != null);
    }

    private void placeMines(int mineCount) {
        Random random = new Random(1);
        int placed = 0;

        while (placed < mineCount) {
            int row = random.nextInt(rows);
            int col = random.nextInt(cols);

            if (board[row][col] != 'M') {
                board[row][col] = 'M';
                placed++;
            }
        }
    }

    public MoveResult play(int row, int col, Action action) {
        if (!valid(row, col) || revealed[row][col]) {
            return new MoveResult(false, List.of());
        }

        if (action == Action.MINE_PRESENT) {
            boolean correct = board[row][col] == 'M';
            return new MoveResult(correct, List.of());
        }

        if (board[row][col] == 'M') {
            revealed[row][col] = true;
            return new MoveResult(false, List.of(new Cell(row, col, 'X')));
        }

        return new MoveResult(true, reveal(row, col));
    }

    private List<Cell> reveal(int row, int col) {
        List<Cell> result = new ArrayList<>();
        Queue<int[]> queue = new ArrayDeque<>();

        queue.add(new int[]{row, col});
        revealed[row][col] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int r = current[0];
            int c = current[1];

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
                    queue.add(new int[]{nr, nc});
                }
            }
        }

        return result;
    }

    private int countMines(int row, int col) {
        int count = 0;

        for (int[] d : DIRS) {
            int nr = row + d[0];
            int nc = col + d[1];

            if (valid(nr, nc) && board[nr][nc] == 'M') {
                count++;
            }
        }

        return count;
    }

    private boolean valid(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    // Only for testing
    void clearBoardForTest() {
        for (char[] row : board) {
            Arrays.fill(row, 'E');
        }

        for (boolean[] row : revealed) {
            Arrays.fill(row, false);
        }
    }

    void putMineForTest(int row, int col) {
        board[row][col] = 'M';
    }

    enum Action {
        MINE_PRESENT,
        MINE_ABSENT
    }

    record Cell(int row, int col, char value) {}

    record MoveResult(boolean correct, List<Cell> changedCells) {}
}