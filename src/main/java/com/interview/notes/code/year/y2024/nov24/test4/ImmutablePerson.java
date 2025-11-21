package com.interview.notes.code.year.y2024.nov24.test4;

import java.util.*;

public record ImmutablePerson(String name, int age, List<String> hobbies, Map<String, String> nameDepartmentMap) {
    public ImmutablePerson(String name, int age, List<String> hobbies, Map<String, String> nameDepartmentMap) {
        this.name = name;
        this.age = age;
        // Create deep copies of mutable objects
        this.hobbies = new ArrayList<>(hobbies);
        this.nameDepartmentMap = new HashMap<>(nameDepartmentMap);
    }

    @Override
    public List<String> hobbies() {
        // Return an unmodifiable view of the list
        return Collections.unmodifiableList(hobbies);
    }

    @Override
    public Map<String, String> nameDepartmentMap() {
        // Return an unmodifiable view of the map
        return Collections.unmodifiableMap(nameDepartmentMap);
    }

    // Method to get a specific department by name
    public String getDepartmentByName(String name) {
        return nameDepartmentMap.get(name);
    }

    @Override
    public String toString() {
        return "ImmutablePerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hobbies=" + hobbies +
                ", nameDepartmentMap=" + nameDepartmentMap +
                '}';
    }
}
