package com.interview.notes.code.months.oct24.wallmart.test1;

import java.util.*;

public class TicketingSystem {

    // Class to represent a payment
    static class Payment {
        int id;
        String email;
        int amount;
        
        Payment(int id, String email, int amount) {
            this.id = id;
            this.email = email;
            this.amount = amount;
        }
    }

    // Class to represent a user
    static class User {
        String name;
        String email;
        String purchase;
        int quantity;
        
        User(String name, String email, String purchase, int quantity) {
            this.name = name;
            this.email = email;
            this.purchase = purchase;
            this.quantity = quantity;
        }
    }

    // Ticket prices map
    static Map<String, Integer> ticketPrices = new HashMap<>();
    
    static {
        ticketPrices.put("Economy", 4);
        ticketPrices.put("Top", 11);
        ticketPrices.put("Medium", 6);
        ticketPrices.put("OneEco", 1);
        ticketPrices.put("TwoEco", 2);
        ticketPrices.put("ThreeEco", 3);
        ticketPrices.put("FourEco", 4);
    }

    // Method to match users with payments
    public static Map<Integer, String> matchUsersToPayments(List<User> users, List<Payment> payments) {
        Map<Integer, String> result = new HashMap<>();
        Map<String, Payment> emailToPaymentMap = new HashMap<>();
        Map<Integer, Payment> amountToPaymentMap = new HashMap<>();
        Set<Integer> matchedPaymentIds = new HashSet<>();
        
        // First, map payments by email
        for (Payment payment : payments) {
            if (!payment.email.equals("email not found")) {
                emailToPaymentMap.put(payment.email, payment);
            } else {
                amountToPaymentMap.put(payment.amount, payment);
            }
        }

        // Step 1: Match users by email
        for (User user : users) {
            if (emailToPaymentMap.containsKey(user.email)) {
                Payment matchedPayment = emailToPaymentMap.get(user.email);
                result.put(matchedPayment.id, user.name);
                matchedPaymentIds.add(matchedPayment.id);
            }
        }

        // Step 2: Match remaining users by amount
        for (User user : users) {
            if (!result.containsValue(user.name)) {
                int totalAmount = ticketPrices.get(user.purchase) * user.quantity;
                if (amountToPaymentMap.containsKey(totalAmount)) {
                    Payment matchedPayment = amountToPaymentMap.get(totalAmount);
                    if (!matchedPaymentIds.contains(matchedPayment.id)) {
                        result.put(matchedPayment.id, user.name);
                        matchedPaymentIds.add(matchedPayment.id);
                    }
                }
            }
        }

        return result;
    }

    // Test Method to check if all test cases pass
    public static void testMatchUsersToPayments() {
        // Test data for users
        List<User> users = Arrays.asList(
            new User("John A.", "john.@mail.com", "Top", 3),
            new User("James S.", "j.s@mail.com", "Economy", 2),
            new User("Stacy C.", "stacy.c@test.com", "Economy", 2),
            new User("Bobby B.", "bob@mail.com", "Medium", 10),
            new User("Michelle X.", "mix@test.com", "Medium", 10),
            new User("Linda F.", "l.f@mail.com", "Top", 10),
            new User("Betty T.", "b.t@mail.com", "ThreeEco", 1),
            new User("Nancy L.", "n.l@test.com", "TwoEco", 1),
            new User("Daniel O.", "d.o@mail.com", "OneEco", 1),
            new User("Mike E.", "m.e@mail.com", "FourEco", 1),
            new User("Matthew R.", "mr@test.com", "OneEco", 5),
            new User("Albert K.", "albert@test.com", "OneEco", 5)
        );

        // Test data for payments
        List<Payment> payments = Arrays.asList(
            new Payment(1, "john2@mail.com", 33),
            new Payment(2, "michelle@mail.com", 60),
            new Payment(4, "james@mail.com", 8),
            new Payment(3, "stacy.c@test.com", 8),
            new Payment(5, "bob@mail.com", 60),
            new Payment(6, "email not found", 110),
            new Payment(7, "email not found", 1),
            new Payment(8, "email not found", 2),
            new Payment(9, "email not found", 3),
            new Payment(99, "email not found", 4),
            new Payment(10, "mr@test.com", 5),
            new Payment(11, "a@mail.com", 5)
        );

        // Expected results
        Map<Integer, String> expectedResults = new HashMap<>();
        expectedResults.put(5, "Bobby B.");
        expectedResults.put(3, "Stacy C.");
        expectedResults.put(10, "Matthew R.");
        expectedResults.put(6, "Linda F.");
        expectedResults.put(7, "Daniel O.");
        expectedResults.put(8, "Nancy L.");
        expectedResults.put(9, "Betty T.");
        expectedResults.put(99, "Mike E.");
        expectedResults.put(1, "John A.");
        expectedResults.put(2, "Michelle X.");
        expectedResults.put(4, "James S.");
        expectedResults.put(11, "Albert K.");

        // Run the method
        Map<Integer, String> actualResults = matchUsersToPayments(users, payments);
        System.out.println();
        // Check if the results match
        if (actualResults.equals(expectedResults)) {
            System.out.println("All test cases passed!");
        } else {
            System.out.println("Test cases failed. Expected: " + expectedResults + ", but got: " + actualResults);
        }
    }

    public static void main(String[] args) {
        testMatchUsersToPayments();
    }
}
