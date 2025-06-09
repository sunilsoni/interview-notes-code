package com.interview.notes.code.year.y2025.may.common.test15;

class T {
    int t = 20;

    T() {
        t = 40; // Constructor sets t to 40
    }
}

public class Main {
    public static void main(String[] args) {
        T t1 = new T(); // Creates object, invokes constructor
        System.out.println(t1.t); // Output will be 40
    }
}
