package com.interview.notes.code.misc.test9;

import java.util.HashMap;

class Employee {

    private String name;

    public Employee(String name) { // constructor
        this.name = name;
    }

    @Override
    public int hashCode() {
        return 1;
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

    @Override
    public String toString() {
        return "Employee[ name=" + name + "] ";
    }

}

public class First {
    public static void main(String[] arg) {

        HashMap<Employee, String> hm = new HashMap<Employee, String>();
        hm.put(new Employee("a"), "emp1");
        hm.put(new Employee("b"), "emp2");

        System.out.println("HashMap's data> " + hm);
        System.out.println("HashMap's size> " + hm.size());
        System.out.println(hm.get(new Employee("a")));

    }
}
