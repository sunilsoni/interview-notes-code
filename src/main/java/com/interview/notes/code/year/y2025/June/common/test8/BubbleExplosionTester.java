package com.interview.notes.code.year.y2025.June.common.test8;

import java.util.*;

public class BubbleExplosionTester {

    /**
     * Performs one round of “bubble explosion”:
     * 1) Finds each connected component of same-colored bubbles (adjacent up/down/left/right).
     * 2) Marks any component of size ≥ 3 for removal.
     * 3) Removes them (sets to 0) and lets remaining bubbles “fall” down each column.
     */
    public static int[][] solution(int[][] bubbles) {
        int R = bubbles.length;
        int C = bubbles[0].length;

        boolean[][] removed = new boolean[R][C];
        boolean[][] seen    = new boolean[R][C];
        int[] dr = { -1, 1, 0, 0 };
        int[] dc = {  0, 0,-1, 1 };

        // 1) Find & mark every component of size >= 3
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (!seen[i][j]) {
                    int color = bubbles[i][j];
                    List<int[]> comp = new ArrayList<>();
                    Queue<int[]> q = new ArrayDeque<>();
                    seen[i][j] = true;
                    q.add(new int[]{i, j});

                    // BFS for this component
                    while (!q.isEmpty()) {
                        int[] p = q.poll();
                        comp.add(p);
                        for (int d = 0; d < 4; d++) {
                            int ni = p[0] + dr[d], nj = p[1] + dc[d];
                            // **correct bounds check** allows row/col == 0
                            if (ni >= 0 && ni < R
                             && nj >= 0 && nj < C
                             && !seen[ni][nj]
                             && bubbles[ni][nj] == color) {
                                seen[ni][nj] = true;
                                q.add(new int[]{ni, nj});
                            }
                        }
                    }

                    // mark for removal if size >= 3
                    if (comp.size() >= 3) {
                        for (int[] p : comp) {
                            removed[p[0]][p[1]] = true;
                        }
                    }
                }
            }
        }

        // 2) Build the result grid with “gravity” (bubbles fall down)
        int[][] res = new int[R][C];
        for (int col = 0; col < C; col++) {
            List<Integer> keep = new ArrayList<>();
            // from bottom→top, collect any bubble not removed
            for (int row = R - 1; row >= 0; row--) {
                if (!removed[row][col]) {
                    keep.add(bubbles[row][col]);
                }
            }
            // drop them into res at the bottom
            int r = R - 1;
            for (int v : keep) {
                res[r--][col] = v;
            }
            // all other cells in this column remain 0
        }

        return res;
    }

    /** Utility to return a string representation of a 2D grid. */
    private static String gridToString(int[][] g) {
        StringBuilder sb = new StringBuilder();
        for (int[] row : g) {
            sb.append(Arrays.toString(row)).append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        // define all test cases (input → expected)
        List<int[][]> inputs = Arrays.asList(
            // original example
            new int[][] {
                {3,1,2,1},
                {1,1,1,4},
                {3,1,2,2},
                {3,3,3,4}
            },
            // Test 2: all same color → remove all
            new int[][] {
                {1,1},
                {1,1}
            },
            // Test 7: single long row with big groups
            new int[][] {
                {1,2,2,2,2,2,2,2,3,3,3,2,1,1,2}
            }
        );

        List<int[][]> expects = Arrays.asList(
            // original expected
            new int[][] {
                {0,0,0,1},
                {0,0,0,4},
                {0,0,2,2},
                {3,0,2,4}
            },
            // Test 2 expected: all zeros
            new int[][] {
                {0,0},
                {0,0}
            },
            // Test 7 expected
            new int[][] {
                {1,0,0,0,0,0,0,0,0,0,0,2,1,1,2}
            }
        );

        int passed = 0;
        for (int t = 0; t < inputs.size(); t++) {
            int[][] in   = inputs.get(t);
            int[][] exp  = expects.get(t);
            int[][] got  = solution(in);
            boolean ok   = Arrays.deepEquals(got, exp);

            System.out.println("==== Test " + (t+1) + " ====");
            System.out.println("Input:");
            System.out.print(gridToString(in));
            System.out.println("Expected:");
            System.out.print(gridToString(exp));
            System.out.println("Got:");
            System.out.print(gridToString(got));
            System.out.println(ok ? "PASS\n" : "FAIL\n");

            if (ok) passed++;
        }
        System.out.printf("Summary: passed %d/%d tests%n",
                          passed, inputs.size());
    }
}