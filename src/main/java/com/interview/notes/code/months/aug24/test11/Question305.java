package com.interview.notes.code.months.aug24.test11;

interface High {
    default void Method1() {
        System.out.println("Method 1");
    }

    public abstract void Method2();
}

class Middle implements High {
    protected static void Method3() {
        System.out.println("Method 3");
    }

    public void Method2() {
        System.out.println("Method 2");
    }

    private void Method4() {
        System.out.println("Method 4");
    }
}

public class Question305 extends Middle {
    public static void main(String[] args) {
        Middle test = new Middle();

        // Method calls here
        test.Method1();     // This will compile and run, printing "Method 1"
        test.Method2();     // This will compile and run, printing "Method 2"
        Middle.Method3();   // This will compile and run, printing "Method 3"

        // test.Method4();  // This will not compile as Method4 is private

        //  High.Method2();  // This will not compile as static reference is not allowed

        test.Method3();
        //High.Method2 () ;
        test.Method3();
        // High.Method2 () ;
//   test.Method4 () ;
        // Middle.Method2 () ;
        test.Method1();
    }
}
