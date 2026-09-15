package com.interview.notes.code.year.y2026.august.common.test6;

import java.text.DecimalFormat;
import java.util.Scanner;

interface PaymentMethod {
    void pay(double amount);
}

class Utils {
    public static String roundDouble(double d) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(d);
    }
}

class CreditCardPaymentMethod implements PaymentMethod {
    private final String cardNumber;
    private final String cvv;
    private final String expiryDate;
    private final String cardHolderName;

    public CreditCardPaymentMethod(
            String cardNumber,
            String cvv,
            String expiryDate,
            String cardHolderName) {

        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public void pay(double amount) {

        // TODO 1: Redeem 10% of amount, but maximum reward is $10
        var redeemed = Math.min(amount * 0.10, 10);

        // TODO 2: Calculate remaining amount using actual double value
        var remaining = amount - redeemed;

        // TODO 3: Round only while printing
        System.out.println(
                "Paying $" + Utils.roundDouble(amount)
                        + " via Credit Card using Reward Points Redemption Feature.");

        System.out.println(
                "Redeemed $" + Utils.roundDouble(redeemed)
                        + " using reward points.");

        System.out.println(
                "Paying remaining amount of $" + Utils.roundDouble(remaining)
                        + " via credit card.");
    }
}

class PayPalPaymentMethod implements PaymentMethod {
    private final String email;
    private final String password;

    public PayPalPaymentMethod(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void pay(double amount) {

        // TODO 1: Split payment into two equal installments
        var installment1 = amount / 2;

        // TODO 2: Add 5% interest only to second installment
        var installment2 = installment1 * 1.05;

        // TODO 3: Round only while printing
        System.out.println(
                "Paying $" + Utils.roundDouble(amount)
                        + " via PayPal using Installment Payment Plan.");

        System.out.println(
                "Paid $" + Utils.roundDouble(installment1)
                        + " in first installment.");

        System.out.println(
                "Paid $" + Utils.roundDouble(installment2)
                        + " in second installment with 5% interest.");
    }
}

class PaymentService {
    public void processPayment(
            PaymentMethod paymentMethod,
            double amount) {

        paymentMethod.pay(amount);
    }
}

public class Solution {

    public static void main(String[] args) {

        PaymentService paymentService = new PaymentService();

        int n;
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        sc.nextLine();

        for (int i = 0; i < n; i++) {

            String[] input = sc.nextLine().split(" ");

            if (input[1].equals("CreditCard")) {

                PaymentMethod paymentMethod =
                        new CreditCardPaymentMethod(
                                input[2],
                                input[3],
                                input[4],
                                input[5]);

                paymentService.processPayment(
                        paymentMethod,
                        Double.parseDouble(input[6]));

            } else if (input[1].equals("PayPal")) {

                PaymentMethod paymentMethod =
                        new PayPalPaymentMethod(
                                input[2],
                                input[3]);

                paymentService.processPayment(
                        paymentMethod,
                        Double.parseDouble(input[4]));
            }
        }

        sc.close();
    }
}