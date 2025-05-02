package com.interview.notes.code.year.y2025.may.photon.test3;

import java.util.*;

interface PaymentMethod {
    void pay(double amount);
}

class Utils {
    public static String roundDouble(double d) {
        return String.format("%.2f", d);
    }
}

class CreditCardPaymentMethod implements PaymentMethod {
    private String cardNumber;
    private String cvv;
    private String expiryDate;
    private String cardHolderName;

    public CreditCardPaymentMethod(String cardNumber, String cvv, String expiryDate, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public void pay(double amount) {
        double redeemableAmt = Math.min(0.10 * amount, 10);
        double remainingAmt = amount - redeemableAmt;
        System.out.println("Paying $" + Utils.roundDouble(amount) + " via Credit Card using Reward Points Redemption Feature.");
        System.out.println("Redeemed $" + Utils.roundDouble(redeemableAmt) + " using reward points.");
        System.out.println("Paying remaining amount of $" + Utils.roundDouble(remainingAmt) + " via credit card.");
    }
}

class PayPalPaymentMethod implements PaymentMethod {
    private String email;
    private String password;

    public PayPalPaymentMethod(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void pay(double amount) {
        double firstInstallment = amount / 2;
        double secondInstallment = firstInstallment * 1.05;

        System.out.println("Paying $" + Utils.roundDouble(amount) + " via PayPal using Installment Payment Plan.");
        System.out.println("Paid $" + Utils.roundDouble(firstInstallment) + " in first installment.");
        System.out.println("Paid $" + Utils.roundDouble(secondInstallment) + " in second installment with 5% interest.");
    }
}

public class PaymentService {

    public static void main(String[] args) {
        List<PaymentMethod> paymentMethods = new ArrayList<>();

        // Example 1: Credit Card
        paymentMethods.add(new CreditCardPaymentMethod("1234-5678-9101-1121", "123", "12/23", "JohnDoe"));
        paymentMethods.get(0).pay(119.858);

        // Example 2: PayPal
        paymentMethods.add(new PayPalPaymentMethod("amy.white@example.com", "password987"));
        paymentMethods.get(1).pay(124.468);

        // Add more examples as needed...

        // Example 3: Another Credit Card
        paymentMethods.add(new CreditCardPaymentMethod("6666-7777-8888-9999", "432", "02/23", "AmyWhite"));
        paymentMethods.get(2).pay(60.1619);

        // Example 4: Another PayPal
        paymentMethods.add(new PayPalPaymentMethod("chris.wilson@example.com", "12345678"));
        paymentMethods.get(3).pay(145.737);
    }
}
