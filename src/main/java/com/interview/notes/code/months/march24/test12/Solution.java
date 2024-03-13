package com.interview.notes.code.months.march24.test12;

/**
 * Java:
 * <p>
 * Groups of Ones
 * An airline has multiple hubs and typically the hub serves some cities around it. You are given a matrix that represents all the cities and the ones which the airline flies to, is marked with a "1" while the one that it does not fly to, is marked with a "0". A hub network can be identified by all the connected ones regardless of whether they are adjacent row-wise, column-wise or diagonally. Can you find the number of hub networks that exist in the matrix?
 * Input
 * The first line of the test case contains two integers N and M.
 * The next line contains NM inputs of the matrix, separated by space.
 * Output
 * An integer denoting the total no. of hub networks. If there is "1" that is isolated, consider that to be a network that is in-process of being built out and count it as a hub as well.
 * Constraints
 * 1 <= N, M <= 50
 * 0 <= A00 <= 1
 * <p>
 * <p>
 * Example #1
 * Input
 * 34
 * 100001010101
 * Output
 * 2
 * This is a [3x4] matrix:
 * Ones at
 * (0,0),(1,1) and (2,1) form one network.
 * Ones at (1,3) and (2,3) form second network.
 * Example #2
 * Input
 * 3 3
 * 100000010
 * Output
 * 2
 * This is a [3x3] matrix:
 * A = 0.
 * Lo
 * 1
 * '1' at (0,0) will form one isolated network that is being built out and the '1' at (2,1) will form the second isolated network.
 * <p>
 * <p>
 * class Solution {
 * public static void main(String[] args) {
 * int n, m;
 * Scanner in = new Scanner (System.in);
 * n = in.nextInto;
 * m = in.nextInt();
 * intl]l] array = new int[n][m];
 * for (int i=0;i<n;i++){
 * for (int j=0; j<m; j++){
 * array [1][jl=in.nextInt();
 * }
 * }
 * System.out.print(hubs(array));
 * public static int hubs(int[][] array){
 * // Write your code here
 * // Return the number of hubs
 * return 0;
 */

//WORKING
public class Solution {
    public static void main(String[] args) {
        // Example 1
        int[][] example1 = {
                {1, 0, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 1}
        };
        System.out.println("Example 1 Output: " + hubs(example1));

        // Example 2
        int[][] example2 = {
                {1, 0, 0},
                {0, 0, 0},
                {0, 1, 0}
        };
        System.out.println("Example 2 Output: " + hubs(example2));
    }

    public static int hubs(int[][] array) {
        int count = 0;
        boolean[][] visited = new boolean[array.length][array[0].length];

        // Traverse the matrix
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                // If cell contains a "1" and hasn't been visited yet
                if (array[i][j] == 1 && !visited[i][j]) {
                    // Increment the count and explore the connected component
                    count++;
                    explore(array, visited, i, j);
                }
            }
        }
        return count;
    }

    // DFS to explore connected component
    private static void explore(int[][] array, boolean[][] visited, int i, int j) {
        // Mark current cell as visited
        visited[i][j] = true;

        // Define the eight possible directions to explore
        int[][] directions = {
                {-1, -1}, {-1, 0}, {-1, 1},
                {0, -1}, {0, 1},
                {1, -1}, {1, 0}, {1, 1}
        };

        // Explore neighbors
        for (int[] dir : directions) {
            int newRow = i + dir[0];
            int newCol = j + dir[1];
            if (isValidCell(array, visited, newRow, newCol)) {
                explore(array, visited, newRow, newCol);
            }
        }
    }

    // Check if cell is valid and contains a "1"
    private static boolean isValidCell(int[][] array, boolean[][] visited, int i, int j) {
        return i >= 0 && i < array.length && j >= 0 && j < array[0].length && array[i][j] == 1 && !visited[i][j];
    }
}
