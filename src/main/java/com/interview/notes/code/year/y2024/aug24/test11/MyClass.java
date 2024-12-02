package com.interview.notes.code.year.y2024.aug24.test11;

public class MyClass {
    static MyOtherClass otherClass;
    String Howdy = "Hello There!";

    public MyClass() {
        otherClass = new MyOtherClass();
    }

    public static void main(String[] args) {
        System.out.println(MyOtherClass.Goodbye);
    }
}

class MyOtherClass {
    public static String Goodbye = "So Long!";

    public MyOtherClass() {
    }
}
