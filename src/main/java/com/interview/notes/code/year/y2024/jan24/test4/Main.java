package com.interview.notes.code.year.y2024.jan24.test4;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


class Result1 {
    public static void main(String[] args) {
        List<String> logs = Arrays.asList("0:start:0", "1:start:2", "1:end:5", "2:start:6", "2:end:9", "0:end:12");
        List<Integer> totalExecutionTime = Result1.getTotalExecutionTime(3, logs);
        System.out.println(totalExecutionTime);
    }

    public static List<Integer> getTotalExecutionTime(int n, List<String> logs) {
        int[] execTime = new int[n];
        Stack<Integer> stack = new Stack<>();

        String[] firstLog = logs.get(0).split(":");
        stack.push(Integer.parseInt(firstLog[0]));
        int prevTime = Integer.parseInt(firstLog[2]);

        logs.stream().skip(1).forEach(log -> {
            String[] parts = log.split(":");
            int time = Integer.parseInt(parts[2]);
            if (parts[1].equals("start")) {
                if (!stack.isEmpty()) {
                    //execTime[stack.peek()] += time - prevTime;
                }
                stack.push(Integer.parseInt(parts[0]));
                //   prevTime = time;
            } else {
                //    execTime[stack.peek()] += time - prevTime + 1;
                stack.pop();
                //  prevTime = time + 1;
            }
        });

        return IntStream.of(execTime).boxed().collect(Collectors.toList());
    }
}
