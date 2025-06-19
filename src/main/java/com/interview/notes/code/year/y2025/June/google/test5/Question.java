package com.interview.notes.code.year.y2025.June.google.test5;

import java.util.*;

// Represents a question that can be asked with a yes/no answer
class Question {
    private String text;
    
    public Question(String text) {
        this.text = text;
    }
    
    @Override
    public String toString() {
        return text;
    }
}

// Interface for entities that can answer questions
interface Answerer {
    boolean ask(Question q);
}

// Represents a person with known answers to questions
class Person implements Answerer {
    private String name;
    private Map<Question, Boolean> answers;
    
    public Person(String name, Map<Question, Boolean> answers) {
        this.name = name;
        this.answers = answers;
    }
    
    @Override
    public boolean ask(Question q) {
        return answers.get(q);
    }
    
    @Override
    public String toString() {
        return name;
    }
}

// Main solution class that implements the guessing logic
class PersonGuesser {
    private List<Person> persons;
    private List<Question> questions;
    
    public PersonGuesser(List<Person> persons, List<Question> questions) {
        this.persons = new ArrayList<>(persons);
        this.questions = new ArrayList<>(questions);
    }
    
    // Main method to guess the person chosen by opponent
    public Person guessPerson(Answerer opponent) {
        // Start with all persons as candidates
        List<Person> candidates = new ArrayList<>(persons);
        
        // Continue asking questions until only one person remains
        while (candidates.size() > 1 && !questions.isEmpty()) {
            // Find the most effective question to ask next
            Question bestQuestion = findBestQuestion(candidates);
            
            // Ask the opponent the chosen question
            boolean answer = opponent.ask(bestQuestion);
            
            // Filter candidates based on the answer
            candidates.removeIf(p -> p.ask(bestQuestion) != answer);
            questions.remove(bestQuestion);
        }
        
        return candidates.isEmpty() ? null : candidates.get(0);
    }
    
    // Helper method to find the question that best splits remaining candidates
    private Question findBestQuestion(List<Person> candidates) {
        return questions.stream()
            .min(Comparator.comparingDouble(q -> 
                Math.abs(candidates.stream().filter(p -> p.ask(q)).count() 
                - candidates.size() / 2.0)))
            .orElse(questions.get(0));
    }
}
