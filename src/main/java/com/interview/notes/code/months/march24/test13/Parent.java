package com.interview.notes.code.months.march24.test13;

class Parent {
    static {
        System.out.println("Parent static block");
    }

    {
        System.out.println("Parent initialization block");
    }

    public Parent() {
        System.out.println("Parent Constructor");
    }
}

class Child extends Parent {
    {
        System.out.println("Child initialization block");
    }

    static {
        System.out.println("Child static block");
    }

    public Child() {
        System.out.println("Child Constructor");
    }

    public static void main(String[] args) {
        new Child();
    }
}
