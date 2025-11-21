package com.interview.notes.code.year.y2024.may24.test4;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class HelloWorld {
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("John", 31),
                new Person("David", 19),
                new Person("Jerry", 25),
                new Person("Peter", 30),
                new Person("Jane", 28)
        );

        // Extract all names that start with 'J' into a collection of Strings
        List<String> namesStartingWithJ = people.stream()
                .map(Person::getName)
                .filter(name -> name.startsWith("J"))
                .collect(Collectors.toList());

        System.out.println("Names starting with 'J': " + namesStartingWithJ);

        // Find the min, max, and average age
        IntSummaryStatistics ageStats = people.stream()
                .mapToInt(Person::getAge)
                .summaryStatistics();

        System.out.println("Min age: " + ageStats.getMin());
        System.out.println("Max age: " + ageStats.getMax());
        System.out.println("Average age: " + ageStats.getAverage());
    }

    public static class Person {
        private final String name;
        private final Integer age;

        public Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public Integer getAge() {
            return age;
        }
    }
}
