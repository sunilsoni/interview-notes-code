package com.interview.notes.code.months.oct24.wallmart.test2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
You are working on an accounting program for an event's ticketing system.
At the end of the day, all the payments received from the payment gateway have to be matched with the users who bought the tickets. There is always a 1-to-1 match between the users and the payments.
Write a function that, given the payment, pricing, and user data, returns a data structure that links the names of the users to their payment IDs, based on the rules described below.
First, orders can be match by the users' emails. If the emails don't match, use the payment amounts. For each payment amount, there will be at most one payment that cannot be matched via the email.
For this problem, we can assume the names are unique.
Users:
.........------------
| Name
| Email
1 Purchase | Quantity I

John A.
john.a@mail.com | Top

3 11
James S.
j.s@mail.com / Economy

2 |
Stacy C.
stacy.c@test.com
Economy
2|
Bobby B.
bob@mail.com / Medium

10 |
Michelle X. |
mix@test.com / Medium

10 |
Linda F.
1.f@mail.com / Top

10 |
Betty T.
b.t@mail.com / ThreeEco

1
Nancy L.
n.1@test.com / TwoEco

11
Daniel 0.
d.o@mail.com / OneEco

11
Mike E.
m.e@mail.com | FourEco

1
Matthew R.
mr@test.com | OneEco

5 |
Albert K.
albert@test.com l OneEco

5 |
 */
class User {
    private String name;
    private String email;
    private String purchaseType;
    private int quantity;

    public User(String name, String email, String purchaseType, int quantity) {
        this.name = name;
        this.email = email;
        this.purchaseType = purchaseType;
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPurchaseType() {
        return purchaseType;
    }

    public int getQuantity() {
        return quantity;
    }
}

class Payment {
    private int id;
    private String email;
    private int amount;

    public Payment(int id, String email, int amount) {
        this.id = id;
        this.email = email;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public int getAmount() {
        return amount;
    }
}

public class PaymentMatcher {

    public static Map<Integer, String> matchUsersToPayments(List<User> users, List<Payment> payments, Map<String, Integer> ticketPrices) {
        Map<String, Payment> emailToPaymentMap = new HashMap<>();
        Map<Integer, Payment> amountToPaymentMap = new HashMap<>();
        Map<Integer, String> result = new HashMap<>();

        // Step 1: Populate emailToPaymentMap and amountToPaymentMap
        for (Payment payment : payments) {
            if (!payment.getEmail().equals("email not found")) {
                emailToPaymentMap.put(payment.getEmail().trim().toLowerCase(), payment);
            } else {
                amountToPaymentMap.put(payment.getAmount(), payment);
            }
        }

        // Step 2: Match users by email
        for (User user : users) {
            String email = user.getEmail().trim().toLowerCase();
            if (emailToPaymentMap.containsKey(email)) {
                Payment payment = emailToPaymentMap.get(email);
                result.put(payment.getId(), user.getName());
                emailToPaymentMap.remove(email);  // Ensure this payment is not reused
            }
        }

        // Step 3: Match remaining users by amount
        for (User user : users) {
            if (!result.containsValue(user.getName())) {
                int ticketPrice = ticketPrices.get(user.getPurchaseType());
                int totalAmount = ticketPrice * user.getQuantity();
                if (amountToPaymentMap.containsKey(totalAmount)) {
                    Payment payment = amountToPaymentMap.get(totalAmount);
                    result.put(payment.getId(), user.getName());
                    amountToPaymentMap.remove(totalAmount);  // Ensure this payment is not reused
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        // Prices array
        String[][] pricesArray = {
                {"Economy", "4"},
                {"Top", "11"},
                {"Medium", "6"},
                {"OneEco", "1"},
                {"TwoEco", "2"},
                {"ThreeEco", "3"},
                {"FourEco", "4"}
        };

        // Users array
        String[][] usersArray = {
                {"John A.", "john.a@mail.com", "Top", "3"},
                {"James S.", "j.s@mail.com", "Economy", "2"},
                {"Stacy C.", "stacy.c@test.com", "Economy", "2"},
                {"Bobby B.", "bob@mail.com", "Medium", "10"},
                {"Michelle X.", "mix@test.com", "Medium", "10"},
                {"Linda F.", "l.f@mail.com", "Top", "10"},
                {"Betty T.", "b.t@mail.com", "ThreeEco", "1"},
                {"Nancy L.", "n.l@test.com", "TwoEco", "1"},
                {"Daniel O.", "d.o@mail.com", "OneEco", "1"},
                {"Mike E.", "m.e@mail.com", "FourEco", "1"},
                {"Matthew R.", "mr@test.com", "OneEco", "5"},
                {"Albert K.", "albert@test.com", "OneEco", "5"}
        };

        // Payments array
        String[][] paymentsArray = {
                {"1", "john2@mail.com", "33"},
                {"2", "michelle@mail.com", "60"},
                {"4", "james@mail.com", "8"},
                {"3", "stacy.c@test.com", "8"},
                {"5", "bob@mail.com", "60"},
                {"6", "email not found", "110"},
                {"7", "email not found", "1"},
                {"8", "email not found", "2"},
                {"9", "email not found", "3"},
                {"99", "email not found", "4"},
                {"10", "mr@test.com", "5"},
                {"11", "a@mail.com", "5"}
        };

        // Convert pricesArray to a Map<String, Integer>
        Map<String, Integer> ticketPrices = new HashMap<>();
        for (String[] price : pricesArray) {
            ticketPrices.put(price[0], Integer.parseInt(price[1]));
        }

        // Convert usersArray to a List<User>
        List<User> users = new ArrayList<>();
        for (String[] user : usersArray) {
            users.add(new User(user[0], user[1], user[2], Integer.parseInt(user[3])));
        }

        // Convert paymentsArray to a List<Payment>
        List<Payment> payments = new ArrayList<>();
        for (String[] payment : paymentsArray) {
            payments.add(new Payment(Integer.parseInt(payment[0]), payment[1], Integer.parseInt(payment[2])));
        }

        // Call the matching function
        Map<Integer, String> result = matchUsersToPayments(users, payments, ticketPrices);

        // Display the result
        for (Map.Entry<Integer, String> entry : result.entrySet()) {
            System.out.println("Payment ID " + entry.getKey() + " is matched with User: " + entry.getValue());
        }
    }
}
