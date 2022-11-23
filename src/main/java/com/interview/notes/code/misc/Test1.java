package com.interview.notes.code.misc;

public class Test1 {
    public static void main(String[] args) {

        int x = 3;
        int y = 5;
        int z = 12;

        int r = ++z + y - y + z + x++;

//13
        System.out.println(r);


        Test1 obj = new Test1();
        // printing the hashcode
        System.out.println("Hashcode is: " + obj.hashCode());
        obj = null;
        // calling the garbage collector using gc()
        System.gc();
        System.out.println("End of the garbage collection");

        try {
            System.out.println("Inside try block");
            // below code throws divide by zero exception
            int data = 25 / 0;
            System.out.println(data);
        }
        // handles the Arithmetic Exception / Divide by zero exception
        catch (ArithmeticException e) {
            System.out.println("Exception handled");
            System.out.println(e);
        }
        // executes regardless of exception occurred or not
        finally {
            System.out.println("finally block is always executed");
        }
    }

    // defining the finalize method
    protected void finalize() {
        System.out.println("Called the finalize() method");
    }
}
