package com.interview.notes.code.year.y2024.aug24.test31;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CombinedQuestions {

    public static void testQuestion30() {
        Base obj1 = new Derived();
        Base.display(); // Static or class method from Base
        obj1.print();   // Non-static or Instance method from Derived
    }

    public static void testQuestion29_27() {
        InterfaceExample obj = new InterfaceExample();
        obj.display(); // display method of MyInterface1, display method of MyInterface2
    }

    public static void testQuestion28_26() {
        // This would create a cyclic dependency problem in a real Spring context
        BeanA beanA = new BeanA();
        BeanB beanB = new BeanB();
        beanA.setBeanB(beanB);
        beanB.setBeanA(beanA);
    }

    // Question 25
    public static void testQuestion25() {
        int[] numbers = {11, 9, 5, 8, 21, 6, 3, 10};
        List<Integer> result = Arrays.stream(numbers)
                .boxed()
                .sorted(Comparator.reverseOrder())
                .skip(1).limit(1)
                .collect(Collectors.toList());
        System.out.println("result: " + result); // result: [11]
    }

    public static void testQuestion24() {
        // Compilation error would occur due to having both default and static methods
        // in a @FunctionalInterface
    }

    // Question 23
    public static void testQuestion23() {
        List<Integer> integerList = Arrays.asList(4, 5, 6, 7, 1, 2, 3);
        integerList.stream()
                .filter(i -> i % 2 == 0)
                .map(i -> i * i * i)
                .filter(i -> i > 50)
                .forEach(System.out::println); // 64, 216
    }

    // Question 22
    public static void testQuestion22() {
        String input = "Welcome to Java World";
        List<Character> result = input.chars()
                .mapToObj(s -> Character.toLowerCase((char) s))
                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
                .entrySet()
                .stream()
                .filter(entry -> entry.getValue() > 1L)
                .map(entry -> entry.getKey())
                .collect(Collectors.toList());
        System.out.println(result); // [e, o, l, a]
    }

    // Question 21
    public static void testQuestion21() {
        List<Integer> integerList = Arrays.asList(1, 2, 3, 4, 5);
        Set<Integer> integerSet = Set.of(5, 6, 7, 7);
        Set<Integer> resultSet = Stream.concat(integerList.stream(), integerSet.stream())
                .collect(Collectors.toSet());
        resultSet.forEach(System.out::print); // 1,2,3,4,5,6,7
    }

    public static void main(String[] args) {
        // Run all test cases
        testQuestion30();
        testQuestion29_27();
        testQuestion28_26();
        testQuestion25();
        // Uncomment the following line if you want to see the compile-time error for question 24
        // testQuestion24();
        testQuestion23();
        testQuestion22();
        testQuestion21();
    }

    // Question 29 and 27
    interface MyInterface1 {
        int num = 100;

        default void display() {
            System.out.println("display method of MyInterface1");
        }
    }

    interface MyInterface2 {
        int num = 1000;

        default void display() {
            System.out.println("display method of MyInterface2");
        }
    }

    // Question 24
    @FunctionalInterface
    interface Tester<A, B, C> {
        static void printBase() {
            System.out.println("I'm Base Tester");
        }

        C apply(A a, B b);

        default void print() {
            System.out.println("I'm Tester");
        }
    }

    // Question 30
    static class Base {
        public static void display() {
            System.out.println("Static or class method from Base");
        }

        public void print() {
            System.out.println("Non-static or Instance method from Base");
        }
    }

    static class Derived extends Base {
        public static void display() {
            System.out.println("Static or class method from Derived");
        }

        public void print() {
            System.out.println("Non-static or Instance method from Derived");
        }
    }

    static class InterfaceExample implements MyInterface1, MyInterface2 {
        public void display() {
            MyInterface1.super.display();
            MyInterface2.super.display();
        }
    }

    // Question 28 and 26
    @Component
    static class BeanA {
        private BeanB beanB;

        public BeanA() {
            System.out.println("BeanA constructor called ");
        }

        @Autowired
        public void setBeanB(BeanB beanB) {
            System.out.println("Setting property beanB of BeanA instance");
            this.beanB = beanB;
        }
    }

    @Component
    static class BeanB {
        private BeanA beanA;

        public BeanB() {
            System.out.println("BeanB constructor called ");
        }

        @Autowired
        public void setBeanA(BeanA beanA) {
            System.out.println("Setting property beanA of BeanB instance");
            this.beanA = beanA;
        }
    }
}
