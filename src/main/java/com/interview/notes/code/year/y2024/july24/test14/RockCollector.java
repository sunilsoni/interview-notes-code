package com.interview.notes.code.year.y2024.july24.test14;

/*

‹ Instructions to candidate.
‹ • 1) • You are an avid rock collector who lives in southern California. Some rare-
• and desirable rocks just became available in New York, so you are planning•
•a cross-country• road trip. There are several other rare rocks that you could•
• pick up along the way.
You have been given a grid filled with numbers, representing the number of • rare rocks available in various cities across the country. • Your objective•
•• is to find the optimal path from So_Cal to New_York that would allow you to.
• accumulate the most rocks along the way."
Note: • You can only travel either north (up) or east • (right).
•2) • Consider adding some additional tests in doTestsPass ().
‹ 3) • Implement optimalPath() • correctly.
• 4) • Here is an example:
{<0, 0,0,0,5}, New_York (finish)
{0, 1, 1,1,0},
So_Cal (start) • {2,0,0,0,0}}
.....^
**••*N
•<-W•• • E•>
.... • S
• The total for this example would be 10• (2+0+1+1+1+0+5) .
 */
public class RockCollector {

    public static int optimalPath(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dp = new int[rows][cols];

        // Initialize the starting point
        dp[rows - 1][0] = grid[rows - 1][0];

        // Initialize the first column
        for (int i = rows - 2; i >= 0; i--) {
            dp[i][0] = dp[i + 1][0] + grid[i][0];
        }

        // Initialize the last row
        for (int j = 1; j < cols; j++) {
            dp[rows - 1][j] = dp[rows - 1][j - 1] + grid[rows - 1][j];
        }

        // Fill the dp table
        for (int i = rows - 2; i >= 0; i--) {
            for (int j = 1; j < cols; j++) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }

        return dp[0][cols - 1];
    }

    public static boolean doTestsPass() {
        boolean result = true;

        // Base test case
        result &= optimalPath(new int[][]{
                {0, 0, 0, 0, 5},
                {0, 1, 1, 1, 0},
                {2, 0, 0, 0, 0}
        }) == 10;

        // Test case 1: Empty grid
        result &= optimalPath(new int[][]{}) == 0;

        // Test case 2: Single cell grid
        result &= optimalPath(new int[][]{{5}}) == 5;

        // Test case 3: Single row grid
        result &= optimalPath(new int[][]{{1, 2, 3, 4}}) == 10;

        // Test case 4: Single column grid
        result &= optimalPath(new int[][]{{1}, {2}, {3}, {4}}) == 10;

        // Test case 5: Grid with negative numbers
        result &= optimalPath(new int[][]{
                {0, -1, 0},
                {-2, 3, 1}
        }) == 3;

        // Test case 6: Large grid
        int[][] largeGrid = new int[1000][1000];
        for (int i = 0; i < 1000; i++) {
            for (int j = 0; j < 1000; j++) {
                largeGrid[i][j] = 1;
            }
        }
        result &= optimalPath(largeGrid) == 1999;

        if (result) {
            System.out.println("All tests pass");
        } else {
            System.out.println("There are test failures");
        }

        return result;
    }

    public static void main(String[] args) {
        boolean result = optimalPath(new int[][]{
                {1, 3, 2, 0, 2, 1, 8},
                {3, 4, 1, 2, 0, 1, 1},
                {1, 1, 1, 2, 3, 2, 1},
                {1, 0, 1, 1, 4, 2, 1}}) == 25;
        System.out.println(result);
        // doTestsPass();
    }
}
