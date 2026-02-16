package com.interview.notes.code.year.y2026.feb.USTechSolutions.test1;

class Parent {
    static {
        System.out.println("4. Static ");
    }

    {
        System.out.println("5. Instance Initialization Block ");
    }
}

public class Main extends Parent {

    static {
        System.out.println("1. Static ");
    }

    {
        System.out.println("2. Instance Initialization Block");
    }

    public Main() {
        System.out.println("3. Constructor");
    }

    public static void main(String[] args) {
        System.out.println("--- Starting main method ---");
        Main obj1 = new Main();
        Main obj2 = new Main();
        System.out.println("--- Main method finished ---");
    }
}
