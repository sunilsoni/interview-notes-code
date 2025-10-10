package com.interview.notes.code.year.y2025.october.common.test1;

@FunctionalInterface
interface University {
    static void universityInfo() {
        System.out.println("Welcome to Global University");
    }

    void offerCourse(String courseName);

    default void addNewStream(String streamName) {
        System.out.println("New stream added: " + streamName);
    }
}

class ComputerScience implements University {
    public void offerCourse(String courseName) {
        System.out.println("Computer Science course: " + courseName);
    }
}

class DataScience implements University {
    public void offerCourse(String courseName) {
        System.out.println("Data Science course: " + courseName);
    }
}

class Electronics implements University {
    public void offerCourse(String courseName) {
        System.out.println("Electronics course: " + courseName);
    }
}

public class Main {
    public static void main(String[] args) {
        University.universityInfo();

        University cs = new ComputerScience();
        cs.offerCourse("Java Programming");
        cs.addNewStream("Artificial Intelligence");

        University ds = new DataScience();
        ds.offerCourse("Machine Learning");
        ds.addNewStream("Quantum Computing");

        University el = new Electronics();
        el.offerCourse("Digital Circuits");
        el.addNewStream("Embedded Systems");
    }
}