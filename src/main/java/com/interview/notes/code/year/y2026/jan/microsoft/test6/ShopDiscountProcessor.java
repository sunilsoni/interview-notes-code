package com.interview.notes.code.year.y2026.jan.microsoft.test6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShopDiscountProcessor {

    public static void finalPrice(List<Integer> prices) {
        int n = prices.size();
        long totalCost = 0;
        List<Integer> fullPriceIndices = new ArrayList<>();
        int[] nextSmallerOrEqual = new int[n];
        Arrays.fill(nextSmallerOrEqual, -1);
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && prices.get(stack.peek()) >= prices.get(i)) {
                nextSmallerOrEqual[stack.pop()] = i;
            }
            stack.push(i);
        }

        for (int i = 0; i < n; i++) {
            if (nextSmallerOrEqual[i] != -1) {
                totalCost += (prices.get(i) - prices.get(nextSmallerOrEqual[i]));
            } else {
                totalCost += prices.get(i);
                fullPriceIndices.add(i);
            }
        }

        System.out.println(totalCost);
        System.out.println(fullPriceIndices.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(" ")));
    }

    public static void main(String[] args) {
        test("Sample 0", List.of(5, 1, 3, 4, 6, 2), "14\n1 5");
        test("Sample 1", List.of(1, 3, 3, 2, 5), "9\n0 3 4");
        test("Example 3", List.of(2, 3, 1, 2, 4, 2), "8\n2 5");
        
        List<Integer> largeData = IntStream.range(0, 100000)
                .mapToObj(i -> 1000000)
                .toList();
        System.out.println("Running Large Data Test...");
        finalPrice(largeData);
    }

    private static void test(String name, List<Integer> input, String expected) {
        System.out.println("--- " + name + " ---");
        System.out.println("Expected:\n" + expected);
        System.out.println("Actual:");
        finalPrice(input);
        System.out.println();
    }
}