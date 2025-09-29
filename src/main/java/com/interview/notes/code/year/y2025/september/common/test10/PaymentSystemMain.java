package com.interview.notes.code.year.y2025.september.common.test10;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

interface PaymentMethod {
    void pay(double amount);
}

class Utils {
    public static String roundDouble(double d) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(d);
    }
}

// CREDIT CARD PAYMENT IMPLEMENTATION
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

    @Override
    public void pay(double amount) {
        // Print original amount
        System.out.println("Paying $" + Utils.roundDouble(amount) + " via Credit Card using Reward Points Redemption Feature.");

        // Calculate redeemable reward points (10% capped at $10)
        double redeem = Math.min(amount * 0.10, 10.0);
        System.out.println("Redeemed $" + Utils.roundDouble(redeem) + " using reward points.");

        // Pay remaining amount
        double remaining = amount - redeem;
        System.out.println("Paying remaining amount of $" + Utils.roundDouble(remaining) + " via credit card.");
    }
}

// PAYPAL PAYMENT IMPLEMENTATION
class PayPalPaymentMethod implements PaymentMethod {
    private final String email;
    private final String password;

    public PayPalPaymentMethod(String email, String password) {
        this.email = email;
        this.password = password;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paying $" + Utils.roundDouble(amount) + " via PayPal using Installment Payment Plan.");

        // First installment
        double installment1 = amount / 2;
        System.out.println("Paid $" + Utils.roundDouble(installment1) + " in first installment.");

        // Second installment with 5% interest
        double installment2 = installment1 * 1.05;
        System.out.println("Paid $" + Utils.roundDouble(installment2) + " in second installment with 5% interest.");
    }
}

// SERVICE CLASS TO CALL pay()
class PaymentService {
    public void processPayment(PaymentMethod method, double amount) {
        method.pay(amount);
    }
}

// MAIN METHOD FOR TESTING
public class PaymentSystemMain {
    public static void main(String[] args) {
        List<String> inputs = Arrays.asList(
                "Pay CreditCard 1234-5678-9101-1121 123 12/23 JohnDoe 119.858",
                "Pay PayPal amy.white@example.com password987 124.468",
                "Pay CreditCard 6666-7777-8888-9999 432 02/23 AmyWhite 60.1619",
                "Pay PayPal chris.wilson@example.com 12345678 145.737"
        );

        List<String> expectedOutputs = Arrays.asList(
                "Paying $119.86 via Credit Card using Reward Points Redemption Feature.",
                "Redeemed $10 using reward points.",
                "Paying remaining amount of $109.86 via credit card.",

                "Paying $124.47 via PayPal using Installment Payment Plan.",
                "Paid $62.23 in first installment.",
                "Paid $65.35 in second installment with 5% interest.",

                "Paying $60.16 via Credit Card using Reward Points Redemption Feature.",
                "Redeemed $6.02 using reward points.",
                "Paying remaining amount of $54.15 via credit card.",

                "Paying $145.74 via PayPal using Installment Payment Plan.",
                "Paid $72.87 in first installment.",
                "Paid $76.51 in second installment with 5% interest."
        );

        List<String> actualOutputs = new ArrayList<>();
        PaymentService service = new PaymentService();

        for (String line : inputs) {
            String[] parts = line.split(" ");
            String type = parts[1];

            if (type.equals("CreditCard")) {
                String cardNumber = parts[2];
                String cvv = parts[3];
                String expiry = parts[4];
                String name = parts[5];
                double amount = Double.parseDouble(parts[6]);

                CreditCardPaymentMethod cc = new CreditCardPaymentMethod(cardNumber, cvv, expiry, name);

                // Capture output using a PrintStream redirect
                captureOutput(() -> service.processPayment(cc, amount), actualOutputs);
            } else if (type.equals("PayPal")) {
                String email = parts[2];
                String password = parts[3];
                double amount = Double.parseDouble(parts[4]);

                PayPalPaymentMethod pp = new PayPalPaymentMethod(email, password);
                captureOutput(() -> service.processPayment(pp, amount), actualOutputs);
            }
        }

        // TEST VALIDATION
        boolean pass = true;
        for (int i = 0; i < expectedOutputs.size(); i++) {
            String expected = expectedOutputs.get(i);
            String actual = actualOutputs.get(i);
            if (!expected.equals(actual)) {
                System.out.println("FAIL at line " + (i + 1));
                System.out.println("Expected: " + expected);
                System.out.println("Actual  : " + actual);
                pass = false;
            }
        }

        if (pass) {
            System.out.println("✅ All test cases PASSED");
        } else {
            System.out.println("❌ Some test cases FAILED");
        }
    }

    // Utility method to capture System.out for validation
    private static void captureOutput(Runnable task, List<String> output) {
        PrintStream original = System.out;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        System.setOut(ps);

        task.run();

        System.out.flush();
        System.setOut(original);
        String[] lines = baos.toString().split("\\r?\\n");
        output.addAll(Arrays.asList(lines));
    }
}