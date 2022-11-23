package com.interview.notes.code.misc;

import java.util.HashSet;
import java.util.Set;

public class Test {

    public static void main(String[] args) {


        int i = 1;
        System.out.println(--i + i++);

        Set<Employee> set = new HashSet<Employee>();
        set.add(new Employee(100, "Anil"));
        set.add(new Employee(100, "Anil"));
        System.out.println(set.size());

        String s = null;

        if (s instanceof String) {
            System.out.println(set.size());
        }

    }


}
