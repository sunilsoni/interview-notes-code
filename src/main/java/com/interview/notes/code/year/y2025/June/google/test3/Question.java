package com.interview.notes.code.year.y2025.June.google.test3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

interface Answerer {
    boolean ask(Question q);
}

/*

The problem is clear - it's essentially implementing a decision tree or binary search algorithm to identify a person by asking optimal yes/no questions. Here's my understanding:

Key points:
1. We have a list of Person objects
2. We have a list of Questions
3. We can ask questions to an Opponent who has secretly picked a Person
4. We need to minimize the number of questions to identify the chosen Person

No doubts on the basic setup, but I would clarify a few things:

1. Do we need to optimize for the minimum number of questions, or just find a working solution?
2. Can we assume all Person objects have consistent answers to questions? (no randomness)
3. Should we handle edge cases like:
   - Empty list of persons/questions
   - Multiple persons having identical answers to all questions
   - Invalid questions

The core problem seems like a classic information theory/decision tree problem where each question should ideally eliminate roughly half of the remaining candidates to achieve O(log n) complexity.



 Thank you. No worries. Okay, let's get started. So let's assume you're given a list of possible persons, okay? Okay. And a list of possible yes and no questions. And you know the answer for each question for each person.

Okay. Yeah. So let's assume I already picked opponents. Already picked a person secretly. So can you write me a function or a method or const. Whatever that by asking me questions. Guess which person I pick so we can assume we have an interface.

Like this. Or actually. We have an interface like this. So. For. Person and opponents already implements this interface, so we already have this. Okay. Let's go back to the question. So you have a list of person.

A list of questions like this. And you know the answer for each question of each person. So basically, for each question for each person. You can ask him the questions and he'll give you answers. Okay? Yeah.

Need already picked a person. You can also ask me questions. And you need to write a function. That by asking me questions. Guess which person I pick.

**Problem Statement:**

You are given:

* A list of possible `Person`
* A list of possible yes/no `Question`s
* You also know the answers (true/false) for each `Person` to each `Question`

An opponent **secretly picks one `Person`**. Your goal is to find out who it is by asking yes/no questions.

---

**Interface and Classes:**

```java
interface Answerer {
    boolean ask(Question q);
}

class Person implements Answerer {
    // Implementation not shown
}

class Opponent implements Answerer {
    // Implementation not shown
}
```
 */
// Abstract class for questions - implementation details not important
abstract class Question {
    public abstract boolean ask(Person p);
}

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

class PersonGuesser {
    private List<Person> persons;
    private List<Question> questions;

    public PersonGuesser(List<Person> persons, List<Question> questions) {
        this.persons = new ArrayList<>(persons);
        this.questions = new ArrayList<>(questions);
    }

    public Person guessPerson(Answerer opponent) {
        List<Person> candidates = new ArrayList<>(persons);

        while (candidates.size() > 1 && !questions.isEmpty()) {
            Question bestQuestion = findBestQuestion(candidates);
            boolean answer = opponent.ask(bestQuestion);
            candidates.removeIf(p -> p.ask(bestQuestion) != answer);
            questions.remove(bestQuestion);
        }

        return candidates.isEmpty() ? null : candidates.get(0);
    }

    private Question findBestQuestion(List<Person> candidates) {
        return questions.stream()
                .min(Comparator.comparingDouble(q ->
                        Math.abs(candidates.stream()
                                .filter(p -> p.ask(q)).count()
                                - candidates.size() / 2.0)))
                .orElse(questions.get(0));
    }
}
