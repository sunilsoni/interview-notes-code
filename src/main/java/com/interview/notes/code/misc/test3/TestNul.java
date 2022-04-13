package com.interview.notes.code.misc.test3;

import com.interview.notes.code.java.lambda.Employee;

public class TestNul {
    public static void main(String[] args) {
        Object s = new Employee();
        //test(s);
        //test(null);

        test(5);
    }

    public static void test(Integer o){
        System.out.println(" Input 1"+o + "type is Object" );
    }
    public static void test(int o){
        System.out.println(" Input 2"+o + "type is Object" );
    }
    public static void test(long o){
        System.out.println(" Input 3"+o + "type is Object" );
    }

    public static void test1(Object o){
        System.out.println(" Input "+o + "type is Object" );
    }

    public static void test1(String o){
        System.out.println(" Input "+o + "type is String" );
    }
    public static void test1(Employee o){
        System.out.println(" Input "+o + "type is Employee" );
    }

}
