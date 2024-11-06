package com.interview.notes.code.months.nov24.test4;

import java.util.*;

public final class ImmutablePerson {
    private final String name;
    private final int age;
    private final List<String> hobbies;
    private final Map<String, String> nameDepartmentMap;

    public ImmutablePerson(String name, int age, List<String> hobbies, Map<String, String> nameDepartmentMap) {
        this.name = name;
        this.age = age;
        // Create deep copies of mutable objects
        this.hobbies = new ArrayList<>(hobbies);
        this.nameDepartmentMap = new HashMap<>(nameDepartmentMap);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<String> getHobbies() {
        // Return an unmodifiable view of the list
        return Collections.unmodifiableList(hobbies);
    }

    public Map<String, String> getNameDepartmentMap() {
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
