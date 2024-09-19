package com.interview.notes.code.months.sept24.wallmart.test3;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Test Case 1
        List<BadgeRecord> records1 = Arrays.asList(
            new BadgeRecord("Paul", "enter"),
            new BadgeRecord("Pauline", "exit"),
            new BadgeRecord("Paul", "enter"),
            new BadgeRecord("Paul", "exit"),
            new BadgeRecord("Martha", "exit"),
            new BadgeRecord("Joe", "enter"),
            new BadgeRecord("Martha", "enter"),
            new BadgeRecord("Steve", "enter"),
            new BadgeRecord("Martha", "exit"),
            new BadgeRecord("Jennifer", "enter"),
            new BadgeRecord("Joe", "enter"),
            new BadgeRecord("Curtis", "exit"),
            new BadgeRecord("Curtis", "enter"),
            new BadgeRecord("Joe", "exit"),
            new BadgeRecord("Martha", "enter"),
            new BadgeRecord("Martha", "exit"),
            new BadgeRecord("Jennifer", "exit"),
            new BadgeRecord("Joe", "enter"),
            new BadgeRecord("Joe", "enter"),
            new BadgeRecord("Martha", "exit"),
            new BadgeRecord("Joe", "exit"),
            new BadgeRecord("Joe", "exit")
        );

        runTestCase(records1, "Test Case 1");

        // Test Case 2
        List<BadgeRecord> records2 = Arrays.asList(
            new BadgeRecord("Paul", "enter"),
            new BadgeRecord("Paul", "exit")
        );

        runTestCase(records2, "Test Case 2");

        // Test Case 3
        List<BadgeRecord> records3 = Arrays.asList(
            new BadgeRecord("Paul", "enter"),
            new BadgeRecord("Paul", "enter"),
            new BadgeRecord("Paul", "exit"),
            new BadgeRecord("Paul", "exit")
        );

        runTestCase(records3, "Test Case 3");

        // Test Case 4
        List<BadgeRecord> records4 = Arrays.asList(
            new BadgeRecord("Raj", "enter"),
            new BadgeRecord("Paul", "enter"),
            new BadgeRecord("Paul", "exit"),
            new BadgeRecord("Paul", "exit"),
            new BadgeRecord("Paul", "enter"),
            new BadgeRecord("Raj", "enter")
        );

        runTestCase(records4, "Test Case 4");
    }

    private static void runTestCase(List<BadgeRecord> records, String testCaseName) {
        BadgeAccessSystem system = new BadgeAccessSystem();
        system.processRecords(records);

        List<String> didNotBadgeOut = system.getEmployeesWhoDidNotBadgeOut();
        List<String> didNotBadgeIn = system.getEmployeesWhoDidNotBadgeIn();

        Collections.sort(didNotBadgeOut);
        Collections.sort(didNotBadgeIn);

        System.out.println(testCaseName + ":");
        System.out.println("Did not badge out: " + didNotBadgeOut);
        System.out.println("Did not badge in: " + didNotBadgeIn);
        System.out.println();
    }
}