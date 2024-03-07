package com.interview.notes.code.months.march24.test6;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Result1 {

    public static List<String> getPrintedStrings(List<List<String>> commands) {
        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<>();
        int cursor = 0;

        for (List<String> command : commands) {
            String cmdType = command.get(0);
            String argument = command.get(1);
            int x;

            switch (cmdType) {
                case "Insert":
                    sb.insert(cursor, argument);
                    cursor += argument.length();
                    break;
                case "Print":
                    x = Integer.parseInt(argument);
                    int start = Math.max(cursor - x, 0);
                    int end = Math.min(cursor + x, sb.length());
                    result.add(sb.substring(start, end));
                    break;
                case "Left":
                    x = Integer.parseInt(argument);
                    cursor = Math.max(cursor - x, 0);
                    break;
                case "Right":
                    x = Integer.parseInt(argument);
                    cursor = Math.min(cursor + x, sb.length());
                    break;
                case "Backspace":
                    x = Integer.parseInt(argument);
                    int startDelete = Math.max(cursor - x, 0);
                    if (startDelete < cursor) {
                        sb.delete(startDelete, cursor);
                        cursor = startDelete;
                    }
                    break;
                case "Delete":
                    x = Integer.parseInt(argument);
                    int endDelete = Math.min(cursor + x, sb.length());
                    if (cursor < endDelete) {
                        sb.delete(cursor, endDelete);
                    }
                    break;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        List<List<String>> commandsExample1 = Arrays.asList(
                Arrays.asList("Insert", "hello"),
                Arrays.asList("Print", "3"),
                Arrays.asList("Delete", "1"),
                Arrays.asList("Right", "2"),
                Arrays.asList("Backspace", "1"),
                Arrays.asList("Print", "4")
        );

        List<List<String>> commandsExample2 = Arrays.asList(
                Arrays.asList("Insert", "addthis"),
                Arrays.asList("Print", "5"),
                Arrays.asList("Left", "4"),
                Arrays.asList("Right", "2"),
                Arrays.asList("Backspace", "1"),
                Arrays.asList("Delete", "1"),
                Arrays.asList("Print", "10")
        );

        List<List<String>> commandsExample3 = Arrays.asList(
                Arrays.asList("Insert", "present"),
                Arrays.asList("Print", "5"),
                Arrays.asList("Left", "4"),
                Arrays.asList("Insert", "test"),
                Arrays.asList("Print", "8"),
                Arrays.asList("Delete", "4"),
                Arrays.asList("Print", "7")
        );

        System.out.println("Example 1 Results: " + getPrintedStrings(commandsExample1));
        System.out.println("Example 2 Results: " + getPrintedStrings(commandsExample2));
        System.out.println("Example 3 Results: " + getPrintedStrings(commandsExample3));
    }
}
