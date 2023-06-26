package com.interview.notes.code.june23.test4;

public class Demo {
    static int x = 1111;

    static {
        x = x-- - --x;
        System.out.println("x: " + x);
    }

    {
        x = x++ + ++x;
        //  System.out.println("x1 "+x);
    }

    public static void main(String args[]) {

        System.out.println(x);

        //  System.out.println("x2: "+x);
    }
}  