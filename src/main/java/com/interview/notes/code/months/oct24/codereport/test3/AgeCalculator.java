package com.interview.notes.code.months.oct24.codereport.test3;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

public class AgeCalculator {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Alice", LocalDate.of(1990, 5, 15)),
                new Person("Bob", LocalDate.of(1985, 8, 20)),
                new Person("Charlie", LocalDate.of(2000, 3, 10))
        );

        LocalDate currentDate = LocalDate.now();

        people.stream()
                .forEach(person -> {
                    int age = Period.between(person.getDob(), currentDate).getYears();
                    System.out.println(person.getName() + " is " + age + " years old.");
                });
    }
}

class Person {
    private String name;
    private LocalDate dob;

    public Person(String name, LocalDate dob) {
        this.name = name;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public LocalDate getDob() {
        return dob;
    }
}
