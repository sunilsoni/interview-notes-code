package com.interview.notes.code.months.sept24.test9;

import java.util.*;

public class BadgeSystem {

    public static void main1(String[] args) {
        List<String[]> records1 = Arrays.asList(
                new String[]{"Paul", "enter"},
                new String[]{"Pauline", "exit"},
                new String[]{"Paul", "enter"},
                new String[]{"Paul", "exit"},
                new String[]{"Martha", "exit"},
                new String[]{"Joe", "enter"},
                new String[]{"Martha", "enter"},
                new String[]{"Steve", "enter"},
                new String[]{"Martha", "exit"},
                new String[]{"Jennifer", "enter"},
                new String[]{"Joe", "enter"},
                new String[]{"Curtis", "exit"},
                new String[]{"Curtis", "enter"},
                new String[]{"Joe", "exit"},
                new String[]{"Martha", "enter"},
                new String[]{"Martha", "exit"},
                new String[]{"Jennifer", "exit"},
                new String[]{"Joe", "enter"},
                new String[]{"Joe", "enter"},
                new String[]{"Martha", "exit"},
                new String[]{"Joe", "exit"},
                new String[]{"Joe", "exit"}
        );

        System.out.println(mismatches(records1));
    }

    public static Map<String, List<String>> mismatches(List<String[]> records) {
        Set<String> enteredWithoutExit = new HashSet<>();
        Set<String> exitedWithoutEnter = new HashSet<>();
        Map<String, Boolean> currentStatus = new HashMap<>();

        for (String[] record : records) {
            String name = record[0];
            String action = record[1];

            if (action.equals("enter")) {
                if (currentStatus.getOrDefault(name, false)) {
                    enteredWithoutExit.add(name);
                }
                currentStatus.put(name, true);
            } else if (action.equals("exit")) {
                if (!currentStatus.getOrDefault(name, false)) {
                    exitedWithoutEnter.add(name);
                }
                currentStatus.put(name, false);
            }
        }

        for (Map.Entry<String, Boolean> entry : currentStatus.entrySet()) {
            if (entry.getValue()) {
                enteredWithoutExit.add(entry.getKey());
            }
        }

        List<String> enteredList = new ArrayList<>(enteredWithoutExit);
        List<String> exitedList = new ArrayList<>(exitedWithoutEnter);
        Collections.sort(enteredList);
        Collections.sort(exitedList);

        Map<String, List<String>> result = new HashMap<>();
        result.put("enteredWithoutExit", enteredList);
        result.put("exitedWithoutEnter", exitedList);

        return result;
    }
}
