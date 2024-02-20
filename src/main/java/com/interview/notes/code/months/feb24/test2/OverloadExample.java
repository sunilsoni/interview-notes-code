package com.interview.notes.code.months.feb24.test2;

public class OverloadExample {

    public int add(int a, int b) {
        System.out.println("inside int add");
        return a + b;
    }

    public float add(float a, float b) {
        System.out.println("inside float add");
        return a + b;
    }

    public double add(double a, double b) {
        System.out.println("inside double add");
        return a + b;
    }

    public static void main(String[] args) {
        OverloadExample example = new OverloadExample();

        // Calling the add method with int arguments
        int result1 = example.add(1, 2);
        System.out.println("Result (int): " + result1);

        // Calling the add method with float arguments
        float result2 = example.add(1.0f, 2.0f); // Use float literals
        System.out.println("Result (float): " + result2);

        // Calling the add method with double arguments
        double result3 = example.add(1.0, 2.0);
        System.out.println("Result (double): " + result3);
    }
}
