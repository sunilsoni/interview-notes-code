package com.interview.notes.code.months.june24.test3;

import java.util.Optional;

class lester {
    public static void main(String args[]) {

        Integer width = null;
        Integer height = new Integer(8);

        Optional<Integer> rWidth = Optional.of(width);
        Optional<Integer> rHeight = Optional.ofNullable(height);

        System.out.println("Width of the rectangle is: " + rWidth.isPresent());
        System.out.println("Height of the rectangle is: " + rHeight.isPresent());

        try {
            int c[] = { 1 };
            System.out.println(c.length);
            c[1] = 142;
            System.out.println("c = " + c[1]);
            int a = args.length;
            System.out.println("a = " + a);
            int b = 8 / a;
        } catch(ArithmeticException e) {
            System.out.println("Divide by 0: " + e);
        } catch(ArrayIndexOutOfBoundsException e) {
            System.out.println("Array index oob: " + e);
        }
        System.out.println("After try/catch blocks.");
    }
}
