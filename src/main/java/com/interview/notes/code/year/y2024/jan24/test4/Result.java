package com.interview.notes.code.year.y2024.jan24.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Result {

    public static List<Integer> getTotalExecutionTime(int n, List<String> logs) {
        int[] execTime = new int[n];
        Stack<Integer> stack = new Stack<>();

        String[] firstLog = logs.get(0).split(":");
        stack.push(Integer.parseInt(firstLog[0]));
        int i = 1, prevTime = Integer.parseInt(firstLog[2]);

        while (i < logs.size()) {
            String[] log = logs.get(i).split(":");
            if (log[1].equals("start")) {
                if (!stack.isEmpty()) {
                    execTime[stack.peek()] += Integer.parseInt(log[2]) - prevTime;
                }
                stack.push(Integer.parseInt(log[0]));
                prevTime = Integer.parseInt(log[2]);
            } else {
                execTime[stack.peek()] += Integer.parseInt(log[2]) - prevTime + 1;
                stack.pop();
                prevTime = Integer.parseInt(log[2]) + 1;
            }
            i++;
        }

        List<Integer> result = new ArrayList<>();
        for (int time : execTime) {
            result.add(time);
        }

        return result;
    }

    public static void main(String[] args) {
        List<String> logs = Arrays.asList("0:start:0", "1:start:2", "1:end:5", "2:start:6", "2:end:9", "0:end:12");
        List<Integer> totalExecutionTime = Result1.getTotalExecutionTime(3, logs);
        System.out.println(totalExecutionTime);
    }
}
