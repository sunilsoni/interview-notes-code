package com.interview.notes.code.year.y2026.july.amazon.test2;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ShopkeeperSaleDiscount {

    public static void finalPrice(List<Integer> prices) {
        var costs = prices.stream().mapToInt(i -> i).toArray();
        var stack = new ArrayDeque<Integer>();
        
        for (var i = 0; i < costs.length; i++) {
            while (!stack.isEmpty() && costs[i] <= costs[stack.peek()]) {
                costs[stack.pop()] -= costs[i];
            }
            stack.push(i);
        }
        
        var total = 0L;
        for (var cost : costs) {
            total += cost;
        }
        
        System.out.println(total);
        System.out.println(stack.stream().sorted().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    public static void main(String[] args) {
        runTest(List.of(1, 3, 3, 2, 5), "9\n0 3 4");
        runTest(List.of(5, 1, 3, 4, 6, 2), "14\n1 5");
        runTest(List.of(2, 3, 1, 2, 4, 2), "8\n2 5");
        
        var largeInput = new ArrayList<Integer>(Collections.nCopies(100000, 10));
        runTest(largeInput, "10\n99999");
        
        runTest(List.of(1, 2, 3), "6\n0 1 2");
    }

    private static void runTest(List<Integer> input, String expected) {
        var outputStream = new ByteArrayOutputStream();
        var originalOut = System.out;
        
        System.setOut(new PrintStream(outputStream));
        finalPrice(input);
        System.setOut(originalOut);
        
        var actualOutput = outputStream.toString().trim().replace("\r", "");
        
        if (actualOutput.equals(expected)) {
            System.out.println("PASS");
        } else {
            System.out.println("FAIL");
        }
    }
}