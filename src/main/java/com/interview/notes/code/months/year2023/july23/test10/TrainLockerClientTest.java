package com.interview.notes.code.months.year2023.july23.test10;

/**
 * Design and implement some classes to manage lockers in a train station.
 * There are three sizes of lockers: small, medium, and large.
 * They will be interacting with bags that can be equivalently bucketed into small, medium, and large.
 * I'd like the classes to support two logical operations:
 * •  Check in - Given a bag, the classes should be able to find a best fit available locker and logically check the bag into it. It'll
 * need to return some piece of data (I'll leave it a little loose as to what that piece of data). But that piece of data will be used in
 * the second operation...
 * <p>
 * •  Retrieve - The client/caller will pass back that piece of data. Your classes should be able to logically retrieve the bag from the
 * locker, making that locker available for use by other clients/callers, and passing the bag back to the client/caller.
 * I'm looking for classes, interfaces, etc and their supporting data structures. Eventually I'd like to implement the checkin/retrieve
 * functions (don't need to worry about constructors, getters/setters, etc) so it's up to you if you'd like approach the problem top-
 * down or bottom-up.
 */

/**
 * designed to test the functionality of the other classes. The main method demonstrates the process of checking a bag in and retrieving it.
 */
public class TrainLockerClientTest {
    public static void main(String[] args) {

        //Creation of LockerManager instance: creates a new instance of LockerManager, which is responsible for managing all lockers at the train station.
        LockerManager lockerManager = new LockerManager();

        //these three lines create and add a locker of each size to the lockerManager.
        lockerManager.addLocker(new Locker(Size.SMALL));
        lockerManager.addLocker(new Locker(Size.MEDIUM));
        lockerManager.addLocker(new Locker(Size.LARGE));

        // creates a new instance of BagManager, providing it with the lockerManager instance.
        BagManager bagManager = new BagManager(lockerManager);

        //creates a new bag of size small. int id = bagManager.checkIn(smallBag); checks the bag into an available locker of the appropriate size,
        // and saves the ID (which is returned by the checkIn method) for later use.
        Bag bag = new Bag(Size.SMALL);

        int id = bagManager.checkIn(bag);
        System.out.println("Bag checked in with ID: " + id);

        // retrieves the bag that was checked in earlier using its ID. The retrieve method of BagManager takes the ID returned by checkIn, looks up the corresponding locker in its storage map,
        // retrieves the bag from the locker, and removes the entry from the storage map.
        Bag retrievedBag = bagManager.retrieve(id);
        System.out.println("Bag retrieved with size: " + retrievedBag.getSize());

        //This test class demonstrates the functionality of the system, showing how bags can be checked in and retrieved using unique IDs.
        // It's a simple use case for the system, and further tests could be added to fully verify the functionality and robustness of the system,
        // such as handling cases where the check-in or retrieval operations fail.
    }
}