package com.interview.notes.code.year.y2024.march24.test7;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) {
        Integer x = 3;
        Integer y = null;
        try {
            System.out.println(Integer.compareUnsigned(x, 3) == 0 || Integer.compareUnsigned(y, 0) == 0);
        } catch (Exception ex) {
            System.out.println(ex.getClass().toString());
        }
        try {
            System.out.println(y.compareTo(null) == 0 || true);
        } catch (Exception ex) {
            System.out.println(ex.getClass().toString());
        }
        System.out.println(Stream.of("green", "yellow", "blue")
                .max((s1, s2) -> s1.compareTo(s2))
                .filter(s -> s.endsWith("n"))
                .orElse("yellow"));

        LocalDate localDate = LocalDate.now(); // Get the current date

        System.out.println(localDate.format(DateTimeFormatter.ofPattern("MMM dd, yyyy")));

    }
}
