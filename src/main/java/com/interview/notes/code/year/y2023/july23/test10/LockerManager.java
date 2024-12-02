package com.interview.notes.code.year.y2023.july23.test10;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class is responsible for managing the lockers. It keeps track of the lockers and their availability.
 * It does this by storing lockers in a Map (HashMap in this case), where the keys are the sizes of the lockers (SMALL, MEDIUM, LARGE),
 * and the values are lists of Locker objects of that size. The addLocker method is used to add a new locker of a particular size to the corresponding list.
 * <p>
 * The getAvailableLocker method is used to find an available locker of a particular size.
 * It does this by iterating through the list of lockers of the given size and returning the first locker it finds that is available
 * (i.e., has no bag stored in it). If there are no available lockers of the required size, it returns null.
 */
public class LockerManager {
    private Map<Size, List<Locker>> lockers;

    /**
     * This is the constructor of the LockerManager class. It initializes a new LockerManager with an empty map of lockers.
     */
    public LockerManager() {
        lockers = new HashMap<>();
        for (Size size : Size.values()) {
            lockers.put(size, new ArrayList<>());
        }
    }

    /**
     * Adds a given locker to the map of lockers. The locker is added to the list corresponding to its size.
     */
    public void addLocker(Locker locker) {
        lockers.get(locker.getSize()).add(locker);
    }

    /**
     * Returns the first available locker of a given size. If no available locker of the given size is found, returns null.
     */
    public Locker getAvailableLocker(Size size) {
        for (Locker locker : lockers.get(size)) {
            if (locker.isAvailable()) {
                return locker;
            }
        }
        return null;
    }
}