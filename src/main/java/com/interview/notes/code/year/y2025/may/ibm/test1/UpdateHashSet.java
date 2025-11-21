package com.interview.notes.code.year.y2025.may.ibm.test1;

import java.util.HashSet;

class Student {

    public int id;
    public String name;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Student s) {
            return s.id == this.id;
        }
        return false;
    }

    @Override
    public String toString() {
        return "Student: " + this.name + "@" + Integer.toHexString(hashCode());
    }
}

public class UpdateHashSet {

    public static void main(String[] args) {
        HashSet<Student> studentList = new HashSet<>();

        Student st1 = new Student("Nimit", 1);
        Student st2 = new Student("Rahul", 3);
        Student st3 = new Student("Nimit", 2);

        studentList.add(st1);
        studentList.add(st2);
        studentList.add(st3);

        System.out.println("Size before mutation: " + studentList.size());

        // Mutate st1's id which affects hashCode
        st1.id = 3;

        System.out.println("Size after mutation: " + studentList.size());
    }
}
