package com.interview.notes.code.year.y2024.july24.test15;

public class MainClass {

    public static void main(String[] args) {
        MainClass obj = new MainClass();

        // Calling testMethod with null
        //    obj.testMethod(null);
    }

    // Method accepting String parameter
    public void testMethod(String str) {
        System.out.println("String method called with: " + str);
    }

    // Method accepting StringBuilder parameter
    public void testMethod(StringBuilder sb) {
        System.out.println("StringBuilder method called with: " + sb);
    }
}
