package com.interview.notes.code.months.march24.test6;

import java.util.ArrayList;
import java.util.List;

class Result2 {
    public static List<String> getPrintedStrings(List<List<String>> commands) {
        StringBuilder sb = new StringBuilder();
        List<String> result = new ArrayList<>();
        int cursor = 0;

        for (List<String> command : commands) {
            String cmdType = command.get(0);
            String argument = command.get(1);
            int x;

            try {
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
            } catch (Exception e) {
                // In case of an exceptional case, we just ignore the faulty command
            }
        }
        return result;
    }
}