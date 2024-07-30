package com.interview.notes.code.months.july24.test13;

import java.util.*;
import java.util.stream.Collectors;

class PaymentException extends Exception {
    static final long serialVersionUID = 50L;

    public PaymentException() {
        super("Payment Exception");
    }

    public PaymentException(String msg) {
        super(msg);
    }
}

class UsernameException extends Exception {
    static final long serialVersionUID = 51L;

    public UsernameException() {
        super("Username Exception");
    }

    public UsernameException(String msg) {
        super(msg);
    }
}

class CreditCardException extends Exception {
    static final long serialVersionUID = 52L;

    public CreditCardException() {
        super("CreditCard Exception");
    }

    public CreditCardException(String msg) {
        super(msg);
    }
}

class Payment {
    String paymentId;
    double amount;
    String actorUsername;
    String targetUsername;
    String note;

    public Payment(double amount, String actorUsername, String targetUsername, String note) {
        this.paymentId = UUID.randomUUID().toString();
        this.amount = amount;
        this.actorUsername = actorUsername;
        this.targetUsername = targetUsername;
        this.note = note;
    }

    @Override
    public String toString() {
        return actorUsername + " paid " + targetUsername + " $" + String.format("%.2f", amount) + " for " + note;
    }
}

class User {
    private String username;
    private double balance;
    private List<String> creditCards;
    private Set<String> friends;
    private List<Payment> payments;

    public User(String username) throws UsernameException {
        if (validateUsername(username)) {
            this.username = username;
        } else {
            throw new UsernameException("Username not valid");
        }
        this.balance = 0.0;
        this.creditCards = new ArrayList<>();
        this.friends = new HashSet<>();
        this.payments = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public List<String> retrieveFeed() {
        return payments.stream().map(Payment::toString).collect(Collectors.toList());
    }

    public void addFriend(User newFriend) {
        friends.add(newFriend.getUsername());
    }

    public void addToBalance(double amount) {
        this.balance += amount;
    }

    public void addCreditCard(String creditCardNumber) throws CreditCardException {
        if (isValidCCNumber(creditCardNumber)) {
            this.creditCards.add(creditCardNumber);
        } else {
            throw new CreditCardException("Invalid credit card number");
        }
    }

    public boolean isValidCCNumber(String creditCardNumber) {
        List<String> validCardNumbers = Arrays.asList("4111111111111111", "4242424242424242");
        return validCardNumbers.contains(creditCardNumber);
    }

    private boolean validateUsername(String username) {
        return username.matches("^[A-Za-z0-9_\\-]{4,15}$");
    }

    private void chargeCreditCard(String creditCardNumber) {
        // magic method that charges a credit card thru the card processor
    }

    public Payment pay(User target, double amount, String note, String creditCardNumber) throws PaymentException {
        if (creditCardNumber != null) {
            return payWithCard(target, amount, note, creditCardNumber);
        } else {
            return payWithBalance(target, amount, note);
        }
    }

    public Payment payWithCard(User target, double amount, String note, String creditCardNumber) throws PaymentException {
        if (this.username.equals(target.getUsername())) {
            throw new PaymentException("User cannot pay themselves");
        } else if (amount <= 0.0) {
            throw new PaymentException("Amount must be a non-negative number");
        } else if (!this.creditCards.contains(creditCardNumber)) {
            throw new PaymentException("Invalid credit card number");
        }
        chargeCreditCard(creditCardNumber);
        final Payment payment = new Payment(amount, this.username, target.getUsername(), note);
        target.addToBalance(amount);
        this.payments.add(payment);
        target.payments.add(payment);
        return payment;
    }

    public Payment payWithBalance(User target, double amount, String note) throws PaymentException {
        if (this.username.equals(target.getUsername())) {
            throw new PaymentException("User cannot pay themselves");
        } else if (amount <= 0.0) {
            throw new PaymentException("Amount must be a non-negative number");
        } else if (this.balance < amount) {
            throw new PaymentException("Insufficient balance");
        }
        this.balance -= amount;
        final Payment payment = new Payment(amount, this.username, target.getUsername(), note);
        target.addToBalance(amount);
        this.payments.add(payment);
        target.payments.add(payment);
        return payment;
    }
}

class MiniVenmo {
    private Map<String, User> users;
    private List<Payment> allPayments;

    public MiniVenmo() {
        this.users = new HashMap<>();
        this.allPayments = new ArrayList<>();
    }

    public static void main(String args[]) {
        MiniVenmo venmo = new MiniVenmo();
        try {
            User bobby = venmo.createUser("Bobby", 5.00, "4111111111111111");
            User carol = venmo.createUser("Carol", 10.00, "4242424242424242");

            // should complete using balance
            Payment payment1 = bobby.pay(carol, 5.00, "Coffee", null);
            venmo.addPaymentToFeed(payment1);

            // should complete using card
            Payment payment2 = carol.pay(bobby, 15.00, "Lunch", "4242424242424242");
            venmo.addPaymentToFeed(payment2);

            List<String> bobbyFeed = bobby.retrieveFeed();
            System.out.println("Bobby's feed:");
            venmo.renderFeed(bobbyFeed);

            System.out.println("\nPublic feed:");
            List<String> publicFeed = venmo.getPublicFeed();
            venmo.renderFeed(publicFeed);

            bobby.addFriend(carol);
            System.out.println("\nBobby added Carol as a friend");

        } catch (PaymentException | UsernameException | CreditCardException e) {
            System.out.println(e.getMessage());
        }
    }

    public User createUser(String username, double balance, String creditCardNumber) throws UsernameException, CreditCardException {
        User user = new User(username);
        user.addToBalance(balance);
        user.addCreditCard(creditCardNumber);
        users.put(username, user);
        return user;
    }

    public void renderFeed(List<String> feed) {
        for (String entry : feed) {
            System.out.println(entry);
        }
    }

    public List<String> getPublicFeed() {
        return allPayments.stream().map(Payment::toString).collect(Collectors.toList());
    }

    public void addPaymentToFeed(Payment payment) {
        allPayments.add(payment);
    }
}
