package com.interview.notes.code.year.y2025.august.common.test13;

class Test {
    void print(String s) { System.out.println("String version"); }
    void print(Object o) { System.out.println("Object version"); }
}

public class Main {
    public static void main(String[] args) {
        Test t = new Test();
        t.print("Hello");   // String version
        t.print(new Object()); // Object version
        t.print(null);      // ???
    }
}
