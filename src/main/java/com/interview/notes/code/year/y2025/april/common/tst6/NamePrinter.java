package com.interview.notes.code.year.y2025.april.common.tst6;

public class NamePrinter {
    static volatile boolean running = true;  // Flag to control continuous execution

    public static void main(String[] args) {
        SharedPrinter printer = new SharedPrinter();

        // Create and name the threads
        Thread firstNameThread = new Thread(new FirstNamePrinter(printer), "FirstNameThread");
        Thread lastNameThread = new Thread(new LastNamePrinter(printer), "LastNameThread");

        System.out.println("Starting continuous printing... (Press Ctrl+C to stop)");

        // Start both threads
        firstNameThread.start();
        lastNameThread.start();

        // Add shutdown hook
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("\nShutting down...");
            running = false;
        }));
    }
}

class SharedPrinter {
    private String firstName = "";
    private String lastName = "";

    synchronized void printFirstName(String name) {
        firstName = name;
        System.out.println(Thread.currentThread().getName() +
                " is printing First Name: " + firstName);
        try {
            Thread.sleep(1000); // Add delay for better visualization
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    synchronized void printLastName(String name) {
        lastName = name;
        System.out.println(Thread.currentThread().getName() +
                " is printing Last Name: " + lastName);
        try {
            Thread.sleep(1000); // Add delay for better visualization
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
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
        while (NamePrinter.running) {
            printer.printFirstName("John");
            try {
                Thread.sleep(500); // Small delay between prints
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}

class LastNamePrinter implements Runnable {
    private SharedPrinter printer;

    LastNamePrinter(SharedPrinter printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        while (NamePrinter.running) {
            printer.printLastName("Doe");
            try {
                Thread.sleep(500); // Small delay between prints
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}
