package com.interview.notes.code.months.nov23.MorganStanley;

import java.util.HashSet;

class Employee1 {
    private final int id;

    public Employee1(int id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        // Return a random hash code (not recommended)
        return (int) (Math.random() * 1000);
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee1 employee = (Employee1) obj;
        return id == employee.id;
    }
}

public class Example {
    public static void main(String[] args) {
        HashSet<Employee1> employeeSet = new HashSet<>();

        Employee1 obj1 = new Employee1(1);
        Employee1 obj2 = new Employee1(1);

        employeeSet.add(obj1);
        employeeSet.add(obj2);

        System.out.println("Size of HashSet: " + employeeSet.size());
    }
}
