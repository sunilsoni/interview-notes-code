package com.interview.notes.code.year.y2024.july24.test11;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Create the Student class here as provided in the question.
class Student {
    private final String id;
    private final String name;

    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }
}

// Create the RegistrationPortal class here.
class RegistrationPortal {
    private static RegistrationPortal instance;
    private List<Student> registeredStudents = Collections.synchronizedList(new ArrayList<>());

    private RegistrationPortal() {
        // Private constructor to prevent instantiation.
    }

    public static synchronized RegistrationPortal getRegistrationPortal() {
        if (instance == null) {
            instance = new RegistrationPortal();
        }
        return instance;
    }

    public synchronized void register(Student student) {
        registeredStudents.add(student);
    }

    public List<Student> getRegisteredStudents() {
        return registeredStudents;
    }
}

// The rest of the classes are as provided in the question.
class RegistrationHelper {
    private final RegistrationPortal registrationPortal;

    public RegistrationHelper(RegistrationPortal registrationPortal) {
        this.registrationPortal = registrationPortal;
    }

    public void register(Student student) {
        if (this.registrationPortal != null) {
            this.registrationPortal.register(student);
        }
    }
}

class RegistrationRunnable implements Runnable {
    private final RegistrationPortal registration;
    private final int studentsCount;
    private final String studentsIdPrefix;

    public RegistrationRunnable(RegistrationPortal registration, int studentsCount, String studentsIdPrefix) {
        this.registration = registration;
        this.studentsCount = studentsCount;
        this.studentsIdPrefix = studentsIdPrefix;
    }

    @Override
    public void run() {
        RegistrationHelper registrationHelper = new RegistrationHelper(registration);
        for (int i = 1; i <= studentsCount; i++) {
            String studentId = "id-" + studentsIdPrefix + "-" + i;
            String studentName = "name-" + i;
            Student student = new Student(studentId, studentName);
            registrationHelper.register(student);
        }
    }
}
