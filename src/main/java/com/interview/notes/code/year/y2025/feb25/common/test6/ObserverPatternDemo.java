package com.interview.notes.code.year.y2025.feb25.common.test6;

import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Observer {
    void update(String message);
}

// Subject interface
interface Subject {
    void registerObserver(Observer observer);

    void removeObserver(Observer observer);

    void notifyObservers();
}

// Concrete Subject
class NewsAgency implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String news;

    @Override
    public void registerObserver(Observer observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(news);
        }
    }

    // Method to update the news
    public void setNews(String news) {
        this.news = news;
        notifyObservers();
    }
}

// Concrete Observer
class NewsChannel implements Observer {
    private String name;

    public NewsChannel(String name) {
        this.name = name;
    }

    @Override
    public void update(String news) {
        System.out.println(name + " received news: " + news);
    }
}

// Demo class to test the implementation
public class ObserverPatternDemo {
    public static void main(String[] args) {
        // Create the subject
        NewsAgency newsAgency = new NewsAgency();

        // Create observers
        NewsChannel channel1 = new NewsChannel("Channel 1");
        NewsChannel channel2 = new NewsChannel("Channel 2");
        NewsChannel channel3 = new NewsChannel("Channel 3");

        // Register observers
        newsAgency.registerObserver(channel1);
        newsAgency.registerObserver(channel2);
        newsAgency.registerObserver(channel3);

        // Set news
        newsAgency.setNews("Breaking: Major technology breakthrough!");

        // Remove one observer
        System.out.println("\nRemoving Channel 2...\n");
        newsAgency.removeObserver(channel2);

        // Set another news
        newsAgency.setNews("Weather: Sunny day ahead!");
    }
}
