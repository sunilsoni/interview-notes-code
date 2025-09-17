package com.interview.notes.code.year.y2025.september.common.test6;

class Parent {
    private void secretMethod() {
        System.out.println("Secret stuff");
    }

    public void publicMethod() {
        System.out.println("Public stuff");

    }
}

public class Test {
    public static void main(String[] args) {
        Parent p = new Parent();
        p.publicMethod();      // ✅ Works
        // p.secretMethod();      // ❌ Compile-time error
    }
}
