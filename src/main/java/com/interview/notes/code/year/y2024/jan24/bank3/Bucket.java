package com.interview.notes.code.year.y2024.jan24.bank3;

// Represents a single bucket for a denomination
class Bucket {
    private final int denomination;
    private int noteCount;

    public Bucket(int denomination, int noteCount) {
        this.denomination = denomination;
        this.noteCount = noteCount;
    }

    // Getter and Setter
    public int getDenomination() {
        return denomination;
    }

    public int getNoteCount() {
        return noteCount;
    }

    public void addNotes(int count) {
        this.noteCount += count;
    }

    public boolean removeNotes(int count) {
        if (count <= noteCount) {
            noteCount -= count;
            return true;
        }
        return false;
    }
}