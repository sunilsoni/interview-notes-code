package com.interview.notes.code.year.y2023.dec23.test1;

import java.util.List;

public class LegoBrickPainter {

    public static int solve(List<Integer> colors) {
        int maxColor = 0;
        int[] colorCount = new int[1001]; // Since the maximum possible color value is 1000

        // Count the frequency of each color and find the maximum color value
        for (int color : colors) {
            colorCount[color]++;
            maxColor = Math.max(maxColor, color);
        }

        int repaintCost = 0;
        for (int color = 1; color <= maxColor; color++) {
            if (colorCount[color] > 1) {
                repaintCost += colorCount[color] - 1;
                // Distributing the excess colors to the next color
                colorCount[color + 1] += colorCount[color] - 1;
            }
        }

        return repaintCost;
    }

    public static void main(String[] args) {
        // Example usage
        List<Integer> colors = List.of(1, 2, 2);
        System.out.println("Minimum repaint cost: " + solve(colors));
    }
}
