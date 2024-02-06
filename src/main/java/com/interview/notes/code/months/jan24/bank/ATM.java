package com.interview.notes.code.months.jan24.bank;

import java.util.Scanner;

// ATM class with Singleton pattern
class ATM {
    private static ATM instance = new ATM();
    private BankService bankService;
    private Account currentAccount;
    private Scanner scanner;

    private ATM() {
        AccountFactory accountFactory = new AccountFactory();
        this.bankService = new BankService(accountFactory);
        this.scanner = new Scanner(System.in);
    }

    public static ATM getInstance() {
        return instance;
    }

    public void start1() {
        try {
            System.out.println("Please enter your account number:");
            String accountNumber = scanner.nextLine();
            System.out.println("Enter your PIN:");
            int pin = scanner.nextInt();
            scanner.nextLine(); // Consume the newline

            currentAccount = bankService.getAccount(accountNumber, pin);
            System.out.println("Authentication successful.");
            performTransaction();
        } catch (ATMException e) {
            System.out.println(e.getMessage());
        }
    }

    public void start() {
        while (true) {
            try {
                System.out.println("Please enter your account number or 'exit' to quit:");
                String accountInput = scanner.nextLine();

                // User can type 'exit' to quit the application
                if ("exit".equalsIgnoreCase(accountInput)) {
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    break;
                }

                System.out.println("Enter your PIN:");
                int pin = scanner.nextInt();
                scanner.nextLine(); // Consume the newline

                currentAccount = bankService.getAccount(accountInput, pin);
                System.out.println("Authentication successful.");

                boolean sessionActive = true;
                while (sessionActive) {
                    sessionActive = performTransaction();
                }
            } catch (ATMException e) {
                System.out.println(e.getMessage());
            } finally {
                // Clear any session data for security
                currentAccount = null;
            }
        }
    }

    private boolean performTransaction() {
        System.out.println("Enter the amount to withdraw or '0' to logout:");
        double amount = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline

        if (amount == 0) {
            System.out.println("Logging out...");
            return false; // End the session
        }

        try {
            currentAccount.withdraw(amount);
            System.out.println("Please take your cash.");
            System.out.println("Remaining balance: " + currentAccount.getBalance());
        } catch (ATMException e) {
            System.out.println(e.getMessage());
        }
        return true; // Continue the session
    }
    private void performTransaction1() {
        try {
            System.out.println("Enter the amount to withdraw:");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume the newline

            currentAccount.withdraw(amount);
            System.out.println("Please take your cash.");
            System.out.println("Remaining balance: " + currentAccount.getBalance());
        } catch (ATMException e) {
            System.out.println(e.getMessage());
        }
    }
}