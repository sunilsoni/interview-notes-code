package com.interview.notes.code.months.jan24.bank;

class Bucket {
    private int denomination;
    private int noteCount;

    public Bucket(int denomination, int noteCount) {
        this.denomination = denomination;
        this.noteCount = noteCount;
    }

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
        if (this.noteCount >= count) {
            this.noteCount -= count;
            return true;
        } else {
            return false;
        }
    }
}