package com.interview.notes.code.year.y2023.july23.test3;

/**
 * Account Comparisons in java
 * Given an interface named "OnlineAccounf that models the account of popular online video streaming platforms,
 * perform the operations listed below. The interface "OnlineAccounf consists of the basePrice, regularMoviePrice, and
 * exclusiveMoviePrice.
 * In order to complete this challenge, you need to implement an incomplete class named "Account' which implements
 * the "OnlineAccounf interface as well as the "Comparable<Account>" interface.
 * Class Account has two attributes to keep track of the number of movies watched:
 * 1. Integer noOfRegularMovies
 * 2. Integer noOfExclusiveMovies
 * 3. String ownerName
 * Methods to complete for class Account:
 * 1. Add a parameterized constructor t hat initializes the attributes ownerName, numberOfRegularMovies,
 * and numberOfExclusiveMovies.
 * 2. Int monthlyCostQ => This method returns the monthly cost for the account. [Monthly Cost = base price
 * + noOfRegularMovies*regularMoviePrice + noOfExclusiveMovies*exclusiveMoviePrice]
 * 3. Override the compareTo method of the Comparable interface such that two accounts can be compared based on their
 * monthly cost.
 * 4. String toStringf) which returns => "Owner is [ownerName] and monthly cost is [monthlyCost] USD."
 */
public class Account implements OnlineAccount, Comparable<Account> {
    private final int noOfRegularMovies;
    private final int noOfExclusiveMovies;
    private final String ownerName;

    // Parameterized constructor
    public Account(String ownerName, int noOfRegularMovies, int noOfExclusiveMovies) {
        this.noOfRegularMovies = noOfRegularMovies;
        this.noOfExclusiveMovies = noOfExclusiveMovies;
        this.ownerName = ownerName;
    }

    // Method to calculate monthly cost
    public int monthlyCost() {
        return basePrice + noOfRegularMovies * regularMoviePrice + noOfExclusiveMovies * exclusiveMoviePrice;
    }

    // Overridden compareTo method
    @Override
    public int compareTo(Account other) {
        return Integer.compare(this.monthlyCost(), other.monthlyCost());
    }

    // Overridden toString method
    @Override
    public String toString() {
        return "Owner is " + this.ownerName + " and monthly cost is " + this.monthlyCost() + " USD.";
    }
}
