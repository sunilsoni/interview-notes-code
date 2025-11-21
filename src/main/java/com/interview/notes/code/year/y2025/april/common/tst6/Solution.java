package com.interview.notes.code.year.y2025.april.common.tst6;

import java.text.DecimalFormat;
import java.util.Scanner;

interface PaymentMethod {
    /**
     * Executes the payment logic (e.g., prints out the
     * details of how the amount was paid).
     */
    void pay(double amount);
}

class Utils {
    /**
     * Rounds a double to 2 decimal places and returns it as a String.
     * Example: 123.4567 -> "123.46"
     */
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

    public CreditCardPaymentMethod(String cardNumber, String cvv, String expiryDate, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
        this.cardHolderName = cardHolderName;
    }

    /**
     * Credit Card Feature:
     * - We apply a "Reward Points Redemption" such that up to 10% of the transaction can be redeemed,
     * if enough points exist. For simplicity, this example always shows 0 points redeemed unless
     * you maintain a real reward points balance somewhere.
     */
    @Override
    public void pay(double amount) {
        // Round the total payment amount first:
        double roundedAmount = Double.parseDouble(Utils.roundDouble(amount));

        // Suppose we check a reward points balance. For the sample output demonstration,
        // let's assume the user has 0 points left, so they redeem $0. 
        // If you wanted to do a “minimum of (10% of amount, availablePointsBalance)” you could.
        double maxRedeemable = roundedAmount * 0.10;
        double actualRedeemed = 0.00;  // here, we keep it at 0 for demonstration

        // Print the first line (the "Paying ..." message):
        System.out.println(
                "Paying $" + Utils.roundDouble(roundedAmount)
                        + " via Credit Card using Reward Points Redemption Feature."
        );

        // Print how much was redeemed:
        System.out.println(
                "Redeemed $" + Utils.roundDouble(actualRedeemed) + " using reward points."
        );

        // Subtract redeemed amount to get the "remaining" that is finally charged:
        double remaining = roundedAmount - actualRedeemed;

        // Print the line for "remaining amount" (though it’s effectively the final charge):
        System.out.println(
                "Remaining amount of $" + Utils.roundDouble(remaining) + " via Credit Card."
        );

        // In a real system, you might actually process the card here, etc.
    }
}

class PayPalPaymentMethod implements PaymentMethod {
    private final String email;
    private final String password;

    public PayPalPaymentMethod(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * PayPal Feature:
     * - For simplicity, we always apply an installment plan of 2 installments:
     * first is half of the base amount, second is half plus 5% interest on that half.
     * So if the total is T, first installment = T/2, second installment = (T/2)*1.05.
     */
    @Override
    public void pay(double amount) {
        // Round the original request first, if you like:
        double baseAmount = Double.parseDouble(Utils.roundDouble(amount));

        // First installment = half
        double firstInstallment = baseAmount / 2.0;
        // Second installment = half + 5% interest (on that half)
        double secondInstallment = firstInstallment * 1.05;
        double totalWithInterest = firstInstallment + secondInstallment;

        // Print overall message
        System.out.println(
                "Paying $" + Utils.roundDouble(totalWithInterest)
                        + " via PayPal using Installment Payment Plan."
        );

        // Print the breakdown of installments:
        System.out.println(
                "2 installments of $" + Utils.roundDouble(firstInstallment)
                        + " and $" + Utils.roundDouble(secondInstallment)
                        + " (5% interest on the second installment)."
        );

        // In a real system, you’d run these transactions separately, etc.
    }
}

class PaymentService {
    /**
     * Accepts a PaymentMethod (either CreditCard or PayPal) and an amount,
     * then calls the .pay(...) method.
     */
    public void processPayment(PaymentMethod paymentMethod, double amount) {
        paymentMethod.pay(amount);
    }
}

public class Solution {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // First line: number of commands
        int n = Integer.parseInt(sc.nextLine());
        PaymentService paymentService = new PaymentService();

        for (int i = 0; i < n; i++) {
            String line = sc.nextLine().trim();
            // Expecting: "Pay CreditCard ..." or "Pay PayPal ..."
            String[] parts = line.split("\\s+");

            // Basic validation:
            if (parts.length < 3) {
                // Not enough tokens to do anything
                continue;
            }

            // parts[0] = "Pay"
            // parts[1] = "CreditCard" or "PayPal"
            String methodType = parts[1];

            if (methodType.equalsIgnoreCase("CreditCard")) {
                // Expecting: Pay CreditCard cardNumber cvv expiryDate cardHolderName amount
                // That is 7 tokens total, indices 0..6
                if (parts.length < 7) {
                    continue; // skip if malformed
                }

                String cardNumber = parts[2];
                String cvv = parts[3];
                String expiryDate = parts[4];
                String cardHolderName = parts[5];
                double amount = Double.parseDouble(parts[6]);

                // Create a CreditCardPaymentMethod
                CreditCardPaymentMethod cpm = new CreditCardPaymentMethod(
                        cardNumber, cvv, expiryDate, cardHolderName
                );

                // Process
                paymentService.processPayment(cpm, amount);

            } else if (methodType.equalsIgnoreCase("PayPal")) {
                // Expecting: Pay PayPal email password amount
                // That is 5 tokens total, indices 0..4
                if (parts.length < 5) {
                    continue; // skip if malformed
                }

                String email = parts[2];
                String password = parts[3];
                double amount = Double.parseDouble(parts[4]);

                // Create a PayPalPaymentMethod
                PayPalPaymentMethod ppm = new PayPalPaymentMethod(email, password);

                // Process
                paymentService.processPayment(ppm, amount);

            } else {
                // Possibly an unsupported payment method
                System.out.println("Unknown payment method: " + methodType);
            }
        }

        sc.close();
    }
}