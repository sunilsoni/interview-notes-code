package com.interview.notes.code.year.y2025.august.common.test1;

public class Test2 {
    public static void main(String[] args) {
        System.out.println("hello world");

        // Create object of Test3
        Test3 test3 = new Test3();

        // Call void display()
        test3.display();

        // Call int display() explicitly and capture return value
        int result = test3.display(1); // overloaded version
        System.out.println("Returned value: " + result);
    }
}

class Test3 {
    // First method: void return type
    public void display() {
        System.out.println("display world");
    }

    // Overloaded method: takes int parameter (to differentiate)
    public int display(int dummy) {
        System.out.println("display universe");
        return 1;
    }
}
