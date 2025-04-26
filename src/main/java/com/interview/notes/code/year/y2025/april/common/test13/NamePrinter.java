package com.interview.notes.code.year.y2025.april.common.test13;

public class NamePrinter {
    public static void main(String[] args) {
        // Create a shared object to synchronize the threads
        SharedPrinter printer = new SharedPrinter();

        // Create two threads
        Thread firstNameThread = new Thread(new FirstNamePrinter(printer));
        Thread lastNameThread = new Thread(new LastNamePrinter(printer));

        // Start both threads
        firstNameThread.start();
        lastNameThread.start();

        // Wait for both threads to complete
        try {
            firstNameThread.join();
            lastNameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Print full name after both threads complete
        System.out.println("Full Name: " + printer.getFullName());
    }
}

class SharedPrinter {
    private String firstName = "";
    private String lastName = "";
    private boolean isFirstNamePrinted = false;
    private boolean isLastNamePrinted = false;

    synchronized void printFirstName(String name) {
        firstName = name;
        System.out.println("First Name: " + firstName);
        isFirstNamePrinted = true;
        notify();
    }

    synchronized void printLastName(String name) {
        lastName = name;
        System.out.println("Last Name: " + lastName);
        isLastNamePrinted = true;
        notify();
    }

    String getFullName() {
        return firstName + " " + lastName;
    }
}

class FirstNamePrinter implements Runnable {
    private SharedPrinter printer;

    FirstNamePrinter(SharedPrinter printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        printer.printFirstName("John");
    }
}

class LastNamePrinter implements Runnable {
    private SharedPrinter printer;

    LastNamePrinter(SharedPrinter printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        printer.printLastName("Doe");
    }
}
