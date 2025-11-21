package com.interview.notes.code.year.y2024.oct24.test19;

import java.util.*;
import java.util.stream.Collectors;

public class StudentAnalyzer {
    public static void main(String[] args) {
        List<Student> students = createStudentList();

        // Test cases
        testFindStudentsWith900Score(students);
        testFindSecondHighestScoreStudent(students);
        testFindUniqueScoreStudents(students);
        testLargeDataSet();
    }

    public static List<Student> createStudentList() {
        return Arrays.asList(
                new Student("Alice", 1, 950),
                new Student("Bob", 2, 900),
                new Student("Charlie", 3, 850),
                new Student("David", 4, 900),
                new Student("Eve", 5, 925),
                new Student("Frank", 6, 850)
        );
    }

    public static List<Student> findStudentsWith900Score(List<Student> students) {
        return students.stream()
                .filter(s -> s.getScore() == 900)
                .collect(Collectors.toList());
    }

    public static Optional<Student> findSecondHighestScoreStudent(List<Student> students) {
        return students.stream()
                .sorted(Comparator.comparingInt(Student::getScore).reversed())
                .skip(1)
                .findFirst();
    }

    public static List<Student> findUniqueScoreStudents(List<Student> students) {
        return students.stream()
                .collect(Collectors.groupingBy(Student::getScore))
                .values().stream()
                .filter(list -> list.size() == 1)
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    // Test methods
    public static void testFindStudentsWith900Score(List<Student> students) {
        List<Student> result = findStudentsWith900Score(students);
        System.out.println("Students with 900 score: " + result);
        assert result.size() == 2 : "Failed: Expected 2 students with 900 score";
        assert result.stream().allMatch(s -> s.getScore() == 900) : "Failed: Not all students have 900 score";
        System.out.println("PASS: findStudentsWith900Score test");
    }

    public static void testFindSecondHighestScoreStudent(List<Student> students) {
        Optional<Student> result = findSecondHighestScoreStudent(students);
        System.out.println("Second highest score student: " + result.orElse(null));
        assert result.isPresent() : "Failed: No second highest score student found";
        assert result.get().getScore() == 925 : "Failed: Incorrect second highest score";
        System.out.println("PASS: findSecondHighestScoreStudent test");
    }

    public static void testFindUniqueScoreStudents(List<Student> students) {
        List<Student> uniqueScoreStudents = findUniqueScoreStudents(students);
        System.out.println("Students with unique scores: " + uniqueScoreStudents);
        assert uniqueScoreStudents.size() == 3 : "Failed: Expected 3 students with unique scores";
        assert uniqueScoreStudents.stream().map(Student::getScore).distinct().count() == uniqueScoreStudents.size() : "Failed: Not all scores are unique";
        System.out.println("PASS: findUniqueScoreStudents test");
    }

    public static void testLargeDataSet() {
        List<Student> largeDataSet = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 1000000; i++) {
            largeDataSet.add(new Student("Student" + i, i, random.nextInt(1000)));
        }

        long startTime = System.currentTimeMillis();
        List<Student> studentsWithMaxScore = findStudentsWith900Score(largeDataSet);
        Optional<Student> secondHighest = findSecondHighestScoreStudent(largeDataSet);
        List<Student> uniqueScoreStudents = findUniqueScoreStudents(largeDataSet);
        long endTime = System.currentTimeMillis();

        System.out.println("Large dataset processing time: " + (endTime - startTime) + "ms");
        System.out.println("Students with max score: " + studentsWithMaxScore.size());
        System.out.println("Second highest score student: " + secondHighest.orElse(null));
        System.out.println("Students with unique scores: " + uniqueScoreStudents.size());
        System.out.println("PASS: Large dataset test");
    }

    static class Student {
        private final String name;
        private final int id;
        private final int score;

        public Student(String name, int id, int score) {
            this.name = name;
            this.id = id;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }

        public int getScore() {
            return score;
        }

        @Override
        public String toString() {
            return "Student{name='" + name + "', id=" + id + ", score=" + score + '}';
        }
    }
}
