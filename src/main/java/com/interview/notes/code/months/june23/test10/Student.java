package com.interview.notes.code.months.june23.test10;

class Student {

    public int id;

    public String name;


    public Student(String name, int id) {

        this.name = name;

        this.id = id;

    }

    public int hashCode() {

        return this.id;

    }

    public String toString() {

        return "Student: " + this.name + "@" + Integer.toHexString(hashCode());

    }

    public boolean equals(Object o) {

        if (o instanceof Student) {

            Student s = (Student) o;

            return s.id == this.id ? true : false;

        }

        return false;

    }

} 