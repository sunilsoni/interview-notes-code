package com.interview.notes.code.year.y2024.sept24.wallmart.test3;

class Employee {
    private String name;
    private int badgeBalance; // Positive: inside, Zero: outside
    private boolean badgedInWithoutExit;
    private boolean badgedOutWithoutEnter;

    public Employee(String name) {
        this.name = name;
        this.badgeBalance = 0;
        this.badgedInWithoutExit = false;
        this.badgedOutWithoutEnter = false;
    }

    public String getName() {
        return name;
    }

    public void enter() {
        if (badgeBalance > 0) {
            // Entering again without exiting
            badgedInWithoutExit = true;
        }
        badgeBalance++;
    }

    public void exit() {
        if (badgeBalance == 0) {
            // Exiting without entering
            badgedOutWithoutEnter = true;
        } else {
            badgeBalance--;
        }
    }

    public boolean didNotBadgeOut() {
        return badgeBalance > 0 || badgedInWithoutExit;
    }

    public boolean didNotBadgeIn() {
        return badgedOutWithoutEnter;
    }
}