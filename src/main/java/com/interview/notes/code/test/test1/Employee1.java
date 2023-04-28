package com.interview.notes.code.test.test1;

class Person {
    public Person() {
        System.out.println("Person class constructor called");
    }
}

public class Employee1 extends Person {
    public Employee1() {
        System.out.println("Employee class constructor called");
    }

    public static void main(String args[]) {
        Employee1 e = new Employee1();
    }
}  
