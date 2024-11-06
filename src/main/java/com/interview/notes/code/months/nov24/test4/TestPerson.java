package com.interview.notes.code.months.nov24.test4;

import java.util.*;

public class TestPerson {

    public static void main(String[] args) {
        List<String> hobbies = new ArrayList<>(Arrays.asList("Reading", "Swimming"));
        Map<String, String> nameDepartmentMap = new HashMap<>();
        nameDepartmentMap.put("John Doe", "Engineering");
        nameDepartmentMap.put("Jane Smith", "Marketing");

        ImmutablePerson person = new ImmutablePerson("John Doe", 30, hobbies, nameDepartmentMap);

        System.out.println(person);
        System.out.println("John's Department: " + person.getDepartmentByName("John Doe"));

// Attempting to modify the map will throw an UnsupportedOperationException
  person.getNameDepartmentMap().put("New Person", "HR");

    }
}
