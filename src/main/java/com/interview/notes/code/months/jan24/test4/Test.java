package com.interview.notes.code.months.jan24.test4;

public class Test {
    private int age;
    private int counter;

    public void methodA() {
        System.out.println("Hello, world!");
    }

    public String methodB() {
        if (this.age >= 18) {
            System.out.println("I can Vote!!!");
        } else {
            System.out.println("I am not an adult!");
        }
        return "SomeString"; // Return a value to match the method's return type
    }

    public String methodC(String orig) {
        String newStr = orig + "A";
        this.counter++;
        return newStr;
    }

    public String methodD() {
        if (this.age <= 18) {
            return "MSN";
        } else if (this.age > 19 && this.age <= 30) {
            return "ICQ";
        }
        return "Default"; // Add a default return statement
    }
}
