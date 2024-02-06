package com.interview.notes.code.months.jan24.bank3;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Represents a single bucket for a denomination
class Bucket {
    private int denomination;
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