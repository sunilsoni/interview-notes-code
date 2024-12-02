package com.interview.notes.code.year.y2024.aug24.test9;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestPerson {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Bob", 1),
                new Person(null, 2),
                new Person("Jane", 3)
        );

        people.stream()
                .reduce((e1, e2) -> {
                    System.out.println(e1);  // Print the person being discarded in each reduction step
                    return e2;               // Always retain the second person
                })
                .flatMap(e -> Optional.ofNullable(e.name)  // Work with the final reduced Person's name
                        .map(name -> new Person(name, e.id)))
                .ifPresent(System.out::println);  // Print the final Person if present
    }

    static class Person {
        String name;
        Integer id;

        Person(String n, Integer i) {
            name = n;
            id = i;
        }

        @Override
        public String toString() {
            return name + " " + id;
        }
    }
}
