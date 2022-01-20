package com.interview.notes.code.misc;

import java.util.HashSet;
import java.util.Set;

public class Test {
    public static void main(String[] args) {
        Set<Employee> set = new HashSet<Employee>();
        set.add(new Employee(100, "Anil"));
        set.add(new Employee(100, "Anil"));
        System.out.println(set.size());
    }




}
