package com.interview.notes.code.year.y2025.may.photon.test1;

import java.text.DecimalFormat;

class Utils {
    public static String roundDouble(double d) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(d);
    }
}

interface PaymentMethod {
    void pay(double amount);
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
        double redeemableAmount = Math.min(10.0, amount * 0.1);
        double remainingAmount = amount - redeemableAmount;
        System.out.println("Paying $" + Utils.roundDouble(amount) + " via Credit Card using Reward Points Redemption Feature.");
        System.out.println("Redeemed $" + Utils.roundDouble(redeemableAmount) + " using reward points.");
        System.out.println("Paying remaining amount of $" + Utils.roundDouble(remainingAmount) + " via credit card.");
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
        double installment1 = amount / 2.0;
        double installment2 = installment1 * 1.05;
        System.out.println("Paying $" + Utils.roundDouble(amount) + " via PayPal using Installment Payment Plan.");
        System.out.println("Paid $" + Utils.roundDouble(installment1) + " in first installment.");
        System.out.println("Paid $" + Utils.roundDouble(installment2) + " in second installment with 5% interest.");
    }
}

class PaymentService {
    public void processPayment(PaymentMethod paymentMethod, double amount) {
        paymentMethod.pay(amount);
    }
}

public class Main {
    public static void main(String[] args) {
        PaymentService service = new PaymentService();

        System.out.println("=== Test Case 1 ===");
        service.processPayment(
            new CreditCardPaymentMethod("1234-5678-9101-1121", "123", "12/23", "JohnDoe"),
            119.858
        );
        System.out.println("Expected: PASS\n");

        System.out.println("=== Test Case 2 ===");
        service.processPayment(
            new PayPalPaymentMethod("amy.white@example.com", "password987"),
            124.468
        );
        System.out.println("Expected: PASS\n");

        System.out.println("=== Test Case 3 ===");
        service.processPayment(
            new CreditCardPaymentMethod("6666-7777-8888-9999", "432", "02/23", "AmyWhite"),
            60.1619
        );
        System.out.println("Expected: PASS\n");

        System.out.println("=== Test Case 4 ===");
        service.processPayment(
            new PayPalPaymentMethod("chris.wilson@example.com", "12345678"),
            145.737
        );
        System.out.println("Expected: PASS\n");
    }
}