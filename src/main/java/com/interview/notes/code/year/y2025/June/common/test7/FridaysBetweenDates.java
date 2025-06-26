package com.interview.notes.code.year.y2025.June.common.test7;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class FridaysBetweenDates {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.print("Enter start date (yyyy-MM-dd): ");
        String startInput = scanner.nextLine();
        System.out.print("Enter end date (yyyy-MM-dd): ");
        String endInput = scanner.nextLine();

        LocalDate startDate = LocalDate.parse(startInput, formatter);
        LocalDate endDate = LocalDate.parse(endInput, formatter);

        System.out.println("\nFridays between " + startDate + " and " + endDate + ":");

        LocalDate current = startDate;
        while (!current.isAfter(endDate)) {
            if (current.getDayOfWeek() == DayOfWeek.FRIDAY) {
                System.out.println(current);
            }
            current = current.plusDays(1);
        }

        scanner.close();
    }
}
