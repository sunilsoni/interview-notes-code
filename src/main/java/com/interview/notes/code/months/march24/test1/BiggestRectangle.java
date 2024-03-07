package com.interview.notes.code.months.march24.test1;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

/**
 * Biggest Rectangle
 * You are given data about a bar graph as an array of integers in which each element represents the height of a bar from left to right. You depict this data as a bar graph and then realize that you can form rectangles by taking the filled areas of a bar or by combining a number of contiguous bars. Assuming that all bars have equal width, you are curious to find the area of the largest rectangle that can be thus formed.
 * For example, the largest rectangle that can be formed out of the bars in the bar graph represented as 7 3 6 5 6 0 7 is marked in red in the image given below.
 * MAX AREA = 5 X 3 = 15
 */
public class BiggestRectangle {

    public static void main(String[] args) {
        List<Integer> example1 = Arrays.asList(7, 3, 6, 5, 6, 0, 7);
        List<Integer> example2 = Arrays.asList(9, 1, 1, 9);

        System.out.println("Max Area for example1: " + maxArea(example1));
        System.out.println("Max Area for example2: " + maxArea(example2));
    }

    public static int maxArea(List<Integer> B) {
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;
        int index = 0;

        while (index < B.size()) {
            if (stack.isEmpty() || B.get(stack.peek()) <= B.get(index)) {
                stack.push(index++);
            } else {
                int height = B.get(stack.pop());
                int width = stack.isEmpty() ? index : index - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
        }
        while (!stack.isEmpty()) {
            int height = B.get(stack.pop());
            int width = stack.isEmpty() ? index : index - stack.peek() - 1;
            maxArea = Math.max(maxArea, height * width);
        }

        return maxArea;
    }


}


