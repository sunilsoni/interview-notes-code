package com.interview.notes.code.year.y2026.june.common.test2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public MinesweeperGame(int rows, int cols, List<int[]> mines) {
        this.rows = rows;
        this.cols = cols;
        this.board = new char[rows][cols];
        this.revealed = new boolean[rows][cols];

        for (char[] row : board) Arrays.fill(row, 'E');

        for (int[] mine : mines) {
            board[mine[0]][mine[1]] = 'M';
        }
    }

    static void test(String name, boolean condition) {
        System.out.println(name + " : " + (condition ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {

        List<int[]> mines = List.of(
                new int[]{1, 1},
                new int[]{1, 5},
                new int[]{3, 2},
                new int[]{4, 7}
        );

        MinesweeperGame game = new MinesweeperGame(5, 8, mines);

        MoveResult result1 = game.play(1, 1, Action.OPEN);
        test("Open mine should be game over",
                result1.status() == Status.GAME_OVER &&
                result1.changedCells().getFirst().value() == 'X');

        MinesweeperGame game2 = new MinesweeperGame(5, 8, mines);

        MoveResult result2 = game2.play(1, 2, Action.OPEN);
        test("Safe cell near mine should return number",
                result2.status() == Status.OK &&
                result2.changedCells().size() == 1 &&
                result2.changedCells().getFirst().value() == '1');

        MinesweeperGame game3 = new MinesweeperGame(5, 8, mines);

        MoveResult result3 = game3.play(4, 4, Action.OPEN);
        test("Blank cell should expand multiple cells",
                result3.status() == Status.OK &&
                result3.changedCells().size() > 1);

        MoveResult result4 = game3.play(4, 4, Action.OPEN);
        test("Already revealed cell should return no changes",
                result4.changedCells().isEmpty());

        MinesweeperGame game4 = new MinesweeperGame(5, 8, mines);

        MoveResult result5 = game4.play(0, 0, Action.MARK_MINE);
        test("Mark mine action should return flag",
                result5.status() == Status.OK &&
                result5.changedCells().getFirst().value() == 'F');

        List<int[]> largeMines = new ArrayList<>();
        for (int i = 0; i < 1000; i += 10) {
            largeMines.add(new int[]{i, i});
        }

        MinesweeperGame largeGame = new MinesweeperGame(1000, 1000, largeMines);
        MoveResult largeResult = largeGame.play(999, 0, Action.OPEN);

        test("Large board should handle safely",
                largeResult.status() == Status.OK);
    }

    public MoveResult play(int row, int col, Action action) {
        if (!valid(row, col)) {
            return new MoveResult(Status.OK, List.of());
        }

        if (action == Action.MARK_MINE) {
            return new MoveResult(Status.OK, List.of(new Cell(row, col, 'F')));
        }

        if (board[row][col] == 'M') {
            return new MoveResult(Status.GAME_OVER, List.of(new Cell(row, col, 'X')));
        }

        List<Cell> changed = new ArrayList<>();
        reveal(row, col, changed);

        return new MoveResult(Status.OK, changed);
    }

    private void reveal(int row, int col, List<Cell> changed) {
        if (!valid(row, col) || revealed[row][col] || board[row][col] == 'M') return;

        revealed[row][col] = true;

        int mines = countMines(row, col);

        if (mines > 0) {
            changed.add(new Cell(row, col, (char) ('0' + mines)));
            return;
        }

        changed.add(new Cell(row, col, 'B'));

        for (int[] d : DIRS) {
            reveal(row + d[0], col + d[1], changed);
        }
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

    enum Action { OPEN, MARK_MINE }

    enum Status { OK, GAME_OVER }

    record Cell(int row, int col, char value) {}

    record MoveResult(Status status, List<Cell> changedCells) {}
}