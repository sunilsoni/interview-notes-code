package com.interview.notes.code.year.y2023.june23.test10;

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

        if (o instanceof Student s) {

            return s.id == this.id;

        }

        return false;

    }

} 