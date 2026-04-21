package com.interview.notes.code.year.y2026.april.common.test8;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.text.DecimalFormat;

interface PaymentMethod {
    void pay(double amount);
}

class Utils {
    public static String roundDouble(double d) {
        return new DecimalFormat("#.##").format(d);
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

    @Override
    public void pay(double amount) {
        double r = Math.min(10.0, amount * 0.1);
        System.out.println("Paying $" + Utils.roundDouble(amount) + " via Credit Card using Reward Points Redemption Feature.");
        System.out.println("Redeemed $" + Utils.roundDouble(r) + " using reward points.");
        System.out.println("Paying remaining amount of $" + Utils.roundDouble(amount - r) + " via credit card.");
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
        double i = amount / 2.0;
        System.out.println("Paying $" + Utils.roundDouble(amount) + " via PayPal using Installment Payment Plan.");
        System.out.println("Paid $" + Utils.roundDouble(i) + " in first installment.");
        System.out.println("Paid $" + Utils.roundDouble(i * 1.05) + " in second installment with 5% interest.");
    }
}

class PaymentService {
    public void processPayment(PaymentMethod paymentMethod, double amount) {
        paymentMethod.pay(amount);
    }
}

public class Solution {
    public static void main(String[] args) {
        test("CreditCard 1234-5678-9101-1121 123 12/23 JohnDoe 119.858", "Paying $119.86 via Credit Card using Reward Points Redemption Feature.\nRedeemed $10 using reward points.\nPaying remaining amount of $109.86 via credit card.\n");
        test("PayPal amy.white@example.com password987 124.468", "Paying $124.47 via PayPal using Installment Payment Plan.\nPaid $62.23 in first installment.\nPaid $65.35 in second installment with 5% interest.\n");
        test("CreditCard 6666-7777-8888-9999 432 02/23 AmyWhite 60.1619", "Paying $60.16 via Credit Card using Reward Points Redemption Feature.\nRedeemed $6.02 using reward points.\nPaying remaining amount of $54.15 via credit card.\n");
        test("PayPal chris.wilson@example.com 12345678 145.737", "Paying $145.74 via PayPal using Installment Payment Plan.\nPaid $72.87 in first installment.\nPaid $76.51 in second installment with 5% interest.\n");
        test("CreditCard 1111-2222-3333-4444 999 12/99 BigSpender 9999999.99", "Paying $9999999.99 via Credit Card using Reward Points Redemption Feature.\nRedeemed $10 using reward points.\nPaying remaining amount of $9999989.99 via credit card.\n");
    }

    private static void test(String input, String expected) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));

        PaymentService ps = new PaymentService();
        String[] in = input.split(" ");
        
        if (in[0].equals("CreditCard")) {
            ps.processPayment(new CreditCardPaymentMethod(in[1], in[2], in[3], in[4]), Double.parseDouble(in[5]));
        } else if (in[0].equals("PayPal")) {
            ps.processPayment(new PayPalPaymentMethod(in[1], in[2]), Double.parseDouble(in[3]));
        }

        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(out.toString().replace("\r\n", "\n").equals(expected) ? "PASS" : "FAIL");
    }
}