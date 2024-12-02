package com.interview.notes.code.year.y2024.oct24.wallmart.test1;

import java.util.*;

public class TicketPaymentMatcher {

    public static Map<Integer, String> matchPaymentsToUsers(List<User> users, List<Payment> payments, Map<String, Integer> prices) {
        Map<Integer, String> result = new HashMap<>();
        Map<String, User> emailToUser = new HashMap<>();
        Map<Integer, List<User>> costToUsers = new HashMap<>();

        // Build email to user map and calculate total cost for each user
        for (User user : users) {
            emailToUser.put(user.email, user);
            int totalCost = user.quantity * prices.get(user.purchase);
            costToUsers.computeIfAbsent(totalCost, k -> new ArrayList<>()).add(user);
        }

        // Match by email
        for (Payment payment : payments) {
            if (!payment.email.equals("email not found") && emailToUser.containsKey(payment.email)) {
                result.put(payment.id, emailToUser.get(payment.email).name);
                emailToUser.remove(payment.email);
            }
        }

        // Match by amount
        for (Payment payment : payments) {
            if (!result.containsKey(payment.id)) {
                List<User> matchingUsers = costToUsers.get(payment.amount);
                if (matchingUsers != null && matchingUsers.size() == 1) {
                    result.put(payment.id, matchingUsers.get(0).name);
                    costToUsers.remove(payment.amount);
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Sample data
        List<User> users = Arrays.asList(
                new User("John A.", "john.a@mail.com", "Top", 3),
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

        Map<String, Integer> prices = new HashMap<>();
        prices.put("Economy", 4);
        prices.put("Top", 11);
        prices.put("Medium", 6);
        prices.put("OneEco", 1);
        prices.put("TwoEco", 2);
        prices.put("ThreeEco", 3);
        prices.put("FourEco", 4);

        Map<Integer, String> result = matchPaymentsToUsers(users, payments, prices);

        // Print results
        for (Map.Entry<Integer, String> entry : result.entrySet()) {
            System.out.println(entry.getKey() + " -> " + entry.getValue());
        }
    }

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
}