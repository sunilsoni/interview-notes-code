package com.interview.notes.code.year.y2025.november.Amazon.test4;

// Player.java
public class Player {
    private final String id;
    private final String name;
    private int currentPosition;
    
    public Player(String id, String name) {
        this.id = id;
        this.name = name;
        this.currentPosition = 0;
    }
    
    public String getId() { return id; }
    public String getName() { return name; }
    public int getCurrentPosition() { return currentPosition; }
    public void setCurrentPosition(int position) { this.currentPosition = position; }
}