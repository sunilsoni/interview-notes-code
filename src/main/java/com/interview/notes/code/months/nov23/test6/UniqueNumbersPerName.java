package com.interview.notes.code.months.nov23.test6;

import java.util.*;
import java.util.stream.Collectors;

public class UniqueNumbersPerName {
    // Define the Person class

/*
    public static void main(String[] args) {
        List<UniqueNumbersPerName1.Person> people = new ArrayList<>();
        people.add(new Person("Jim", "111"));
        people.add(new Person("Bob", "222"));
        people.add(new Person("Dan", "333"));
        people.add(new Person("Jim", "222"));
        people.add(new Person("Jim", "333"));
        people.add(new Person("Dan", "111"));
        people.add(new Person("Jim", "222"));
        people.add(new Person("Jim", "111"));
        people.add(new Person("Bob", "111"));
        people.add(new Person("Jim", "111"));
        people.add(new Person("Dan", "444"));
        people.add(new Person("Dan", "555"));

        printUniqueNumbersPerName(people);
    }
*/
    public static void printUniqueNumbersPerName(List<Person> people) {
        Map<String, Set<String>> nameToPhones = new HashMap<>();

        // Iterate through the list of Person objects and populate the HashMap
        for (Person person : people) {
            String name = person.name;
            String phone = person.phone;

            // If the name is not in the map, create a new set for it
            nameToPhones.putIfAbsent(name, new HashSet<>());

            // Add the phone number to the set associated with the name
            nameToPhones.get(name).add(phone);
        }

        // Iterate through the HashMap and print name along with the number of unique phone numbers
        for (Map.Entry<String, Set<String>> entry : nameToPhones.entrySet()) {
            String name = entry.getKey();
            int uniqueNumbers = entry.getValue().size();
            System.out.println(name + ":" + uniqueNumbers);
        }
    }

    // Method to return a TreeMap sorted by person name with count of unique phone numbers
    public static Map<String, Integer> getUniqueNumbersSortedByName(List<Person> people) {
        return people.stream()
                .collect(
                        Collectors.groupingBy(
                                Person::getName,
                                TreeMap::new,
                                Collectors.collectingAndThen(
                                        Collectors.mapping(
                                                Person::getPhone,
                                                Collectors.toSet()
                                        ),
                                        Set::size
                                )
                        )
                );
    }
}