package com.interview.notes.code.year.y2025.feb.common.test7;

import java.util.ArrayList;
import java.util.List;

// Observer Interface
interface Observer {
    void update(String state);
}

// Subject (Observable)
class Subject {
    private List<Observer> observers = new ArrayList<>();
    private String state;

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    public void setState(String state) {
        this.state = state;
        notifyObservers();
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(state);
        }
    }
}

// Concrete Observer 1
class ConcreteObserverA implements Observer {
    @Override
    public void update(String state) {
        System.out.println("Observer A received update: " + state);
    }
}

// Concrete Observer 2
class ConcreteObserverB implements Observer {
    @Override
    public void update(String state) {
        System.out.println("Observer B received update: " + state);
    }
}

// Main Class to Test
public class ObserverPatternExample {
    public static void main(String[] args) {
        Subject subject = new Subject();

        Observer observer1 = new ConcreteObserverA();
        Observer observer2 = new ConcreteObserverB();

        subject.addObserver(observer1);
        subject.addObserver(observer2);

        System.out.println("Setting state to 'NEW UPDATE'");
        subject.setState("NEW UPDATE");

        System.out.println("Removing Observer A and updating state");
        subject.removeObserver(observer1);
        subject.setState("SECOND UPDATE");
    }
}
