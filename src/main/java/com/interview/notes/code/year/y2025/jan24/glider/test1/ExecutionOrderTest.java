package com.interview.notes.code.year.y2025.jan24.glider.test1;

public class ExecutionOrderTest {
    static {
        System.out.println("Static block executed");
    }

    {
        System.out.println("Instance block executed");
    }

    public static void display() {
        System.out.println("Static method executed");
    }

    public ExecutionOrderTest() {
        System.out.println("Constructor executed");
    }

    public static void main(String[] args) {
        System.out.println("Main method starts");
        display();
        new ExecutionOrderTest();
        System.out.println("Main method ends");
    }
}
