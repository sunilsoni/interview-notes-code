package com.interview.notes.code.year.y2024.july24.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*

Painting Cells
Harper loves two-dimensional matrix and enjoys painting its cells with white and black colors.
Harper has a matrix of N rows and M columns. But, some matrix cells are already painted with white or black and hence cannot be repainted. The rest of the cells can be painted either white or black color. But, Harper wants no two adjacent cells to have the same color. Two cells are said to be adjacent if they share a common edge.
Your task is to find a suitable placement of colors on the given matrix.
Note: For every test case, at least one matrix cell is painted white or black, and the answer always exists.
Input
The first line of input contains an integer N, representing the number of rows.
The second line of input contains an integer M, representing the number of columns.
The next N lines of input contain M space-separated characters, representing row elements.
The jth character of the ith line is either
".", "W" or "B":
• "B": represents an already painted black color cell.
• "W": represents an already painted white color cell.
• " "(dot): represents the paintable cells.
Constraints
1 ≤ N, M ≤ 10-
value of the matrix is either ".", "W" or "B"

Output
The output must contain N lines, and each line must contain a string of M space-separated characters.
The jth character of the ith string should be either
"W", or "B". Character "W" means the color of the cell is white, "B" means it is black.
It is guaranteed that the answer always exists.
Example #1
Input
2
2
• В
B .
Output
W B
B W
Explanation: The above output follows the condition that no two adjacent cells have the same color.
Example #2
Input
5
.... W
• • • • •
•• • • •
Output
WB W BW
B WB WB
WB WBW
Explanation: The above output follows the condition
that no two adiaront colle havo the camo color

 */
public class PaintingCells {
    public static List<List<Character>> solve(List<List<Character>> A) {
        int n = A.size();
        int m = A.get(0).size();
        List<List<Character>> result = new ArrayList<>();

        // Initialize the result matrix with a default pattern
        for (int i = 0; i < n; i++) {
            List<Character> row = new ArrayList<>();
            for (int j = 0; j < m; j++) {
                if (A.get(i).get(j) == '.') {
                    // Apply checkerboard pattern
                    row.add((i + j) % 2 == 0 ? 'W' : 'B');
                } else {
                    row.add(A.get(i).get(j));
                }
            }
            result.add(row);
        }

        // Adjust colors based on pre-painted cells
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (result.get(i).get(j) != '.') {
                    // Check and adjust neighboring cells
                    if (i > 0 && result.get(i - 1).get(j) == result.get(i).get(j)) {
                        result.get(i - 1).set(j, result.get(i).get(j) == 'W' ? 'B' : 'W');
                    }
                    if (j > 0 && result.get(i).get(j - 1) == result.get(i).get(j)) {
                        result.get(i).set(j - 1, result.get(i).get(j) == 'W' ? 'B' : 'W');
                    }
                    if (i < n - 1 && result.get(i + 1).get(j) == result.get(i).get(j)) {
                        result.get(i + 1).set(j, result.get(i).get(j) == 'W' ? 'B' : 'W');
                    }
                    if (j < m - 1 && result.get(i).get(j + 1) == result.get(i).get(j)) {
                        result.get(i).set(j + 1, result.get(i).get(j) == 'W' ? 'B' : 'W');
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Example 1
        List<List<Character>> input1 = Arrays.asList(
                Arrays.asList('.', 'B'),
                Arrays.asList('B', '.')
        );
        List<List<Character>> output1 = solve(input1);
        for (List<Character> row : output1) {
            for (Character c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }

        // Example 2
        System.out.println();
        List<List<Character>> input2 = Arrays.asList(
                Arrays.asList('.', '.', '.', '.', 'W'),
                Arrays.asList('.', '.', '.', '.', '.'),
                Arrays.asList('.', '.', '.', '.', '.')
        );
        List<List<Character>> output2 = solve(input2);
        for (List<Character> row : output2) {
            for (Character c : row) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
