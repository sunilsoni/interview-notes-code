package com.interview.notes.code.months.year2023.july23.test10;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * The BagManager class is responsible for checking in and retrieving bags. It maintains a Map (again, a HashMap) called storage, where the keys are integers (bag IDs) and the values are the Locker objects where the bags are stored. It also maintains a reference to a LockerManager object, which it uses to manage the lockers.
 * <p>
 * The checkIn method checks a bag into an available locker. It does this by calling the getAvailableLocker method of LockerManager to find an available locker of the same size as the bag. If no available locker is found, it throws an exception. Otherwise, it stores the bag in the locker by calling the storeBag method of the Locker class, generates a unique ID for the bag by incrementing an AtomicInteger, and stores the ID and the locker in the storage map. It then returns the unique ID.
 * <p>
 * The retrieve method retrieves a bag given its unique ID. It does this by looking up the locker in the storage map using the ID, removing the entry from the map, and retrieving the bag from the locker by calling the retrieveBag method of the Locker class. If the ID is not found in the map, it throws an exception.
 * <p>
 * The bag ID serves as a ticket or receipt that the user must provide in order to retrieve their bag. This ID-treatment system allows the bag check-in and retrieval process to be efficient and secure.
 */
public class BagManager {

    private Map<Integer, Locker> storage;
    private LockerManager lockerManager;
    private AtomicInteger idGenerator;

    //This is the constructor of the
    public BagManager(LockerManager lockerManager) {
        this.lockerManager = lockerManager;
        storage = new HashMap<>();
        idGenerator = new AtomicInteger(1);
    }

    /**
     * Checks in a given bag. Finds an available locker of the same size as the bag, stores the bag in the locker,
     * generates a unique ID for the bag, and stores the ID and the locker in the storage map. Returns the unique ID.
     */
    public int checkIn(Bag bag) {
        Locker locker = lockerManager.getAvailableLocker(bag.getSize());
        if (locker == null) {
            throw new RuntimeException("No available locker for bag size " + bag.getSize());
        }
        locker.storeBag(bag);
        int id = idGenerator.getAndIncrement();
        storage.put(id, locker);
        return id;
    }

    /*
    Retrieves the bag with a given ID. Looks up the locker in the storage map using the ID,
    removes the entry from the map, and retrieves the bag from the locker. Returns the retrieved bag.
     */
    public Bag retrieve(int id) {
        if (!storage.containsKey(id)) {
            throw new RuntimeException("Invalid ID");
        }
        Locker locker = storage.remove(id);
        return locker.retrieveBag();
    }

    /**
     * Here is an updated BagManager.checkIn() method that stores the bag in a larger locker if a locker of the bag's size is not available:
     */
    public int checkIn1(Bag bag) {
        Locker locker = lockerManager.getAvailableLocker(bag.getSize());

        if (locker == null) {
            // Try to find a larger locker
            if (bag.getSize() == Size.SMALL) {
                locker = lockerManager.getAvailableLocker(Size.MEDIUM);
                if (locker == null) {
                    locker = lockerManager.getAvailableLocker(Size.LARGE);
                }
            } else if (bag.getSize() == Size.MEDIUM) {
                locker = lockerManager.getAvailableLocker(Size.LARGE);
            }
        }

        if (locker == null) {
            throw new IllegalArgumentException("No available locker found");
        }

        locker.storeBag(bag);
        int id = idGenerator.incrementAndGet();
        storage.put(id, locker);
        return id;
    }

}