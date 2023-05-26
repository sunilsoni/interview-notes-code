package com.interview.notes.code.array;

public interface I1 {
    default String getGreeting() {
        return "Good Morning!";
    }

    //default String getGreeting1();
}

interface I2 {
    default String getGreeting() {
        return "Good Night!";
    }
}

class C1 implements I1, I2 {
    public static void main(String[] args) {
        C1 c = new C1();
        c.getGreeting();
    }

    //  @Override
    public String getGreeting1() {

        return I1.super.getGreeting();
    }

    @Override
    public String getGreeting() {
        return I1.super.getGreeting();
    }
}