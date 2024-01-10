package com.interview.notes.code.months.year2023.july23.test10;

public class Locker {
    private Size size;
    private Bag storedBag;

    public Locker(Size size) {
        this.size = size;
    }

    public Size getSize() {
        return size;
    }

    public boolean isAvailable() {
        return storedBag == null;
    }

    public void storeBag(Bag bag) {
        this.storedBag = bag;
    }

    public Bag retrieveBag() {
        Bag bag = this.storedBag;
        this.storedBag = null;
        return bag;
    }
}