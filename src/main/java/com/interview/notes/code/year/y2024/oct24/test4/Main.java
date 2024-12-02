package com.interview.notes.code.year.y2024.oct24.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Customer {
    private String name;
    private List<Account> accounts;

    public Customer(String name, List<Account> accounts) {
        this.name = name;
        this.accounts = accounts;
    }

    public String getName() {
        return name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}

class Account {
    private String accountNumber;

    public Account(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

public class Main {

    // Method to get account numbers for customer named "Raj"
    public static List<String> getAccountNumbersOfRaj(List<Customer> customers) {
        return customers.stream()
                .filter(customer -> "Raj".equals(customer.getName()))  // Filter customers named "Raj"
                .flatMap(customer -> customer.getAccounts().stream())  // Get the list of accounts
                .map(Account::getAccountNumber)                        // Map to account numbers
                .collect(Collectors.toList());                         // Collect to a List
    }

    // Test method to check if PASS/FAIL all test cases
    public static void testGetAccountNumbersOfRaj() {
        // Test case 1: Customer "Raj" with 2 accounts
        List<Account> rajAccounts = Arrays.asList(new Account("12345"), new Account("67890"));
        Customer raj = new Customer("Raj", rajAccounts);
        Customer john = new Customer("John", Arrays.asList(new Account("54321")));
        List<Customer> customers1 = Arrays.asList(raj, john);

        List<String> result1 = getAccountNumbersOfRaj(customers1);
        assert result1.equals(Arrays.asList("12345", "67890")) : "Test Case 1 Failed";

        // Test case 2: No customer named "Raj"
        Customer alice = new Customer("Alice", Arrays.asList(new Account("11223")));
        List<Customer> customers2 = Arrays.asList(alice, john);

        List<String> result2 = getAccountNumbersOfRaj(customers2);
        assert result2.isEmpty() : "Test Case 2 Failed";

        // Test case 3: Customer "Raj" with no accounts
        Customer rajNoAccounts = new Customer("Raj", new ArrayList<>());
        List<Customer> customers3 = Arrays.asList(rajNoAccounts, john);

        List<String> result3 = getAccountNumbersOfRaj(customers3);
        assert result3.isEmpty() : "Test Case 3 Failed";

        // Test case 4: Multiple customers named "Raj"
        Customer raj2 = new Customer("Raj", Arrays.asList(new Account("98765")));
        List<Customer> customers4 = Arrays.asList(raj, raj2, john);

        List<String> result4 = getAccountNumbersOfRaj(customers4);
        assert result4.equals(Arrays.asList("12345", "67890", "98765")) : "Test Case 4 Failed";

        System.out.println("All test cases passed!");
    }

    public static void main(String[] args) {
        // Run the test cases
        testGetAccountNumbersOfRaj();
    }
}
