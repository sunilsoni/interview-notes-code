package com.interview.notes.code.year.y2025.november.Amazon.test4;// Board.java

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final int size;
    private final Map<Integer, Jump> jumps;
    
    public Board(int size) {
        this.size = size;
        this.jumps = new HashMap<>();
    }
    
    public void addSnake(int start, int end) {
        validatePosition(start);
        validatePosition(end);
        
        if (jumps.containsKey(start)) {
            throw new IllegalArgumentException("Jump already exists at position " + start);
        }
        
        Snake snake = new Snake(start, end);
        jumps.put(start, snake);
    }
    
    public void addLadder(int start, int end) {
        validatePosition(start);
        validatePosition(end);
        
        if (jumps.containsKey(start)) {
            throw new IllegalArgumentException("Jump already exists at position " + start);
        }
        
        Ladder ladder = new Ladder(start, end);
        jumps.put(start, ladder);
    }
    
    private void validatePosition(int position) {
        if (position < 1 || position > size) {
            throw new IllegalArgumentException("Position must be between 1 and " + size);
        }
    }
    
    public int getSize() { return size; }
    
    public int getNextPosition(int currentPosition, int diceValue) {
        int nextPosition = currentPosition + diceValue;
        
        // Check if exceeds board size
        if (nextPosition > size) {
            return currentPosition; // Stay in same position
        }
        
        // Check for snake or ladder
        if (jumps.containsKey(nextPosition)) {
            Jump jump = jumps.get(nextPosition);
            System.out.println("  Found " + jump.getJumpType() + " at " + nextPosition + 
                             " -> Moving to " + jump.getEnd());
            return jump.getEnd();
        }
        
        return nextPosition;
    }
    
    public boolean hasWon(int position) {
        return position == size;
    }
}