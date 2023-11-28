package com.interview.notes.code.months.nov23.test6;

import java.util.*;

public class UniqueNumbersPerName1 {

    // Main method for example execution
    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Jim", "111"),
                // ... (other Person objects as per example input)
                new Person("Dan", "555")
        );

        printUniqueNumbers(people);
    }

    // Method to print unique phone numbers per name
    public static void printUniqueNumbers(List<Person> people) {
        Map<String, Set<String>> nameToPhonesMap = new HashMap<>();

        for (Person person : people) {
            nameToPhonesMap.putIfAbsent(person.name, new HashSet<>());
            nameToPhonesMap.get(person.name).add(person.phone);
        }

        for (Map.Entry<String, Set<String>> entry : nameToPhonesMap.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().size());
        }
    }

    // Define the Person class
    static class Person {
        String name;
        String phone;

        Person(String name, String phone) {
            this.name = name;
            this.phone = phone;
        }
    }
}
