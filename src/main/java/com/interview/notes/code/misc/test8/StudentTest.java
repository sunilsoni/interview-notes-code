package com.interview.notes.code.misc.test8;

import java.util.HashMap;
import java.util.Map;

public class StudentTest {


    public static void main(String[] args) {

        Map<Student, String> map = new HashMap<>();

        Student s1 = new Student(1,"Ram");
        Student s2 = new Student(1,"Ram");

        map.put(s1,"Hello");
        s1.id=2;
        System.out.println(map.get(s1));


    }

}
