package com.interview.notes.code.year.y2026.march.common.test5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EmployeeStreamExercise {

    // method to return high paid engineers
    static List<String> getHighPaidEngineers(List<Employee> employees){

        return employees.stream()                               // convert employee list into stream for processing
                .filter(e->e.dept.equals("Engineering"))        // keep only employees whose department is Engineering
                .filter(e->e.salary>=100000)                    // keep only employees whose salary >= 100000
                .map(e->e.name)                                 // extract employee name from employee object
                .toList();                                      // convert final stream result into list
    }

    public static void main(String[] args){

        // sample test employee data
        List<Employee> employees = Arrays.asList(

                new Employee("Alice","Engineering",120000,30),  // high paid engineer
                new Employee("Bob","Engineering",95000,25),     // engineer but not high paid
                new Employee("Charlie","Sales",80000,35),       // sales employee
                new Employee("Diana","Engineering",110000,28),  // high paid engineer
                new Employee("Eve","Sales",75000,32),           // sales employee
                new Employee("Frank","HR",65000,40)             // HR employee
        );

        // expected output
        List<String> expected = Arrays.asList("Alice","Diana");

        // call method to compute result
        List<String> result = getHighPaidEngineers(employees);

        // check PASS or FAIL
        System.out.println("Test1 "+(result.equals(expected)?"PASS":"FAIL")+" -> "+result);

        // large dataset test
        List<Employee> big = new ArrayList<>();                  // create large list for performance test

        for(int i=0;i<1_000_000;i++)                             // simulate 1 million employees
            big.add(new Employee("Emp"+i,"Engineering",100000+i,30));

        // run method on large dataset
        System.out.println("Large Test "+(!getHighPaidEngineers(big).isEmpty()?"PASS":"FAIL"));
    }

    // Employee class represents employee object with properties
    static class Employee {

        String name;                    // employee name
        String dept;                    // employee department
        int salary;                     // employee salary
        int age;                        // employee age

        Employee(String name,String dept,int salary,int age){
            this.name=name;             // assign name value when object created
            this.dept=dept;             // assign department value
            this.salary=salary;         // assign salary value
            this.age=age;               // assign age value
        }
    }
}