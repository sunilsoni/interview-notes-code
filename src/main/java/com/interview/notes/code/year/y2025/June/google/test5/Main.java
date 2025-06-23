package com.interview.notes.code.year.y2025.June.google.test5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Create test questions
        Question q1 = new Question("Is male?");
        Question q2 = new Question("Has glasses?");
        Question q3 = new Question("Has beard?");

        // Create test persons with their answers
        Map<Question, Boolean> answers1 = new HashMap<>();
        answers1.put(q1, true);
        answers1.put(q2, false);
        answers1.put(q3, true);
        Person p1 = new Person("John", answers1);

        Map<Question, Boolean> answers2 = new HashMap<>();
        answers2.put(q1, true);
        answers2.put(q2, true);
        answers2.put(q3, false);
        Person p2 = new Person("Mike", answers2);

        // Create test data
        List<Person> persons = Arrays.asList(p1, p2);
        List<Question> questions = Arrays.asList(q1, q2, q3);

        // Create a test opponent who chose p1
        Answerer opponent = new Answerer() {
            @Override
            public boolean ask(Question q) {
                return p1.ask(q);
            }
        };

        // Test the guesser
        PersonGuesser guesser = new PersonGuesser(persons, questions);
        Person guessed = guesser.guessPerson(opponent);

        // Verify result
        System.out.println("Test case 1: " +
                (guessed == p1 ? "PASS" : "FAIL"));

        // Add more test cases here for edge cases and larger datasets
    }
}
