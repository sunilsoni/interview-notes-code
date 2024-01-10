package com.interview.notes.code.months.year2023.june23.test10;

import java.util.HashSet;

public class UpdateHashSet {


    public static void main(String[] args) {
        HashSet<Student> studentList = new HashSet<>();
        Student st1 = new Student("Nimit", 1);//3
        Student st2 = new Student("Rahul", 3);
        Student st3 = new Student("Nimit", 2);

        studentList.add(st1);

        studentList.add(st2);

        studentList.add(st3);


        System.out.println(studentList.size());//3


        st1.id = 3;

        System.out.println(studentList.size());//2

    }

} 