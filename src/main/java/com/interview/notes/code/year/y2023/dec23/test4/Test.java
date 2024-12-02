package com.interview.notes.code.year.y2023.dec23.test4;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@FunctionalInterface
interface FunctionalInterface1 {
    void method1();
}

//@FunctionalInterface
interface FunctionalInterface2 extends FunctionalInterface1 {
    void method2();
}

public class Test {

    public static void main(String[] args) {
        Derived e = new Derived();
        e.display();//Static or class method from Base
        e.print();//Non-static or Instance method from Derived

        // e.print();

        List<String> x = Arrays.asList("a", "b", "c");
        List<String> y = Arrays.asList("1", "2", "3");
        List<String> z = Arrays.asList("1", "2", "3", "4", "5");

        Collections.copy(y, x);
        Collections.copy(z, x);

        System.out.println("x->" + x);//[a, b, c]
        System.out.println("y->" + y);//[a, b, c]
        System.out.println("z->" + z);//[a, b, c, 4, 5
    }


}

