package com.interview.notes.code.year.y2024.july24.test15;

public class AccountDetails {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    // Constructor
    public AccountDetails(String accountNumber, String accountHolderName, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = initialBalance;
    }

    // Main method to test AccountDetails
    public static void main(String[] args) {
        AccountDetails account = new AccountDetails("123456789", "John Doe", 1000.0);
        // Display account details
        account.displayAccountDetails();
        // Update and display details
        account.setAccountHolderName("Jane Doe");
        account.setBalance(1500.0);
        account.displayAccountDetails();
    }

    // Getters and Setters
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public void setAccountHolderName(String accountHolderName) {
        this.accountHolderName = accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Method to display account details
    public void displayAccountDetails() {
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder Name: " + accountHolderName);
        System.out.println("Balance: " + balance);
    }
}
