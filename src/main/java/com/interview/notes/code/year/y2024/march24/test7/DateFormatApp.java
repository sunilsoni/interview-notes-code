package com.interview.notes.code.year.y2024.march24.test7;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DateFormatApp {
    public static void main(String[] args) {

        LocalDate localDate = LocalDate.of(2015, 4, 4);

        System.out.println(localDate.format(DateTimeFormatter.ofPattern("MMM dd, YYYY")));

        System.out.println(localDate.format(DateTimeFormatter.ofPattern("E, MMM dd, YYYY")));

        System.out.println(localDate.format(DateTimeFormatter.ofPattern("MM/dd/yy")));

    }
}
