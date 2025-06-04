package com.interview.notes.code.year.y2025.may.common.test1;

class Test {
    void method(String s) {
        System.out.println("String method called");
    }

    void method(Object o) {
        System.out.println("Object method called");
    }

    public static void main(String[] args) {
        Test test = new Test();
        test.method(null); // Calls the String method
    }
}
