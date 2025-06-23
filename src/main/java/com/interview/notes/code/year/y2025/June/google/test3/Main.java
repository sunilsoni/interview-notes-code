package com.interview.notes.code.year.y2025.June.google.test3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Example question implementation
        Question q1 = new Question() {
            public boolean ask(Person p) {
                return p.ask(this);
            }
        };
        Question q2 = new Question() {
            public boolean ask(Person p) {
                return p.ask(this);
            }
        };

        // Create test data
        Map<Question, Boolean> answers1 = new HashMap<>();
        answers1.put(q1, true);
        answers1.put(q2, false);
        Person p1 = new Person("John", answers1);

        Map<Question, Boolean> answers2 = new HashMap<>();
        answers2.put(q1, false);
        answers2.put(q2, true);
        Person p2 = new Person("Mike", answers2);

        List<Person> persons = Arrays.asList(p1, p2);
        List<Question> questions = Arrays.asList(q1, q2);

        // Test opponent who chose p1
        Answerer opponent = new Answerer() {
            @Override
            public boolean ask(Question q) {
                return p1.ask(q);
            }
        };

        PersonGuesser guesser = new PersonGuesser(persons, questions);
        Person guessed = guesser.guessPerson(opponent);

        System.out.println("Test result: " +
                (guessed == p1 ? "PASS" : "FAIL"));
    }
}
