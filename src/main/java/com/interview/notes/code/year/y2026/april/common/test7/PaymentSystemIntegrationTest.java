package com.interview.notes.code.year.y2026.april.common.test7;

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

record CreditCardPaymentProcessor(String cardNum, String cvv, String exp, String name) implements PaymentMethod {
    @Override
    public void pay(double amt) {
        double r = Math.min(10.0, amt * 0.1);
        System.out.println("Paying $" + Utils.roundDouble(amt) + " via Credit Card using Reward Points Redemption Feature.");
        System.out.println("Redeemed $" + Utils.roundDouble(r) + " using reward points.");
        System.out.println("Paying remaining amount of $" + Utils.roundDouble(amt - r) + " via credit card.");
    }
}

record PayPalPaymentProcessor(String email, String pass) implements PaymentMethod {
    @Override
    public void pay(double amt) {
        double h = amt / 2.0;
        System.out.println("Paying $" + Utils.roundDouble(amt) + " via PayPal using Installment Payment Plan.");
        System.out.println("Paid $" + Utils.roundDouble(h) + " in first installment.");
        System.out.println("Paid $" + Utils.roundDouble(h * 1.05) + " in second installment with 5% interest.");
    }
}

class TransactionDispatchService {
    public void process(PaymentMethod m, double amt) {
        m.pay(amt);
    }
}

public class PaymentSystemIntegrationTest {
    public static void main(String[] args) {
        validate("CreditCard 1234-5678-9101-1121 123 12/23 JohnDoe 119.858",
                 "Paying $119.86 via Credit Card using Reward Points Redemption Feature.\nRedeemed $10 using reward points.\nPaying remaining amount of $109.86 via credit card.\n");
        validate("PayPal amy.white@example.com password987 124.468",
                 "Paying $124.47 via PayPal using Installment Payment Plan.\nPaid $62.23 in first installment.\nPaid $65.35 in second installment with 5% interest.\n");
        validate("CreditCard 6666-7777-8888-9999 432 02/23 AmyWhite 60.1619",
                 "Paying $60.16 via Credit Card using Reward Points Redemption Feature.\nRedeemed $6.02 using reward points.\nPaying remaining amount of $54.15 via credit card.\n");
        validate("PayPal chris.wilson@example.com 12345678 145.737",
                 "Paying $145.74 via PayPal using Installment Payment Plan.\nPaid $72.87 in first installment.\nPaid $76.51 in second installment with 5% interest.\n");
        validate("CreditCard 1111-2222-3333-4444 999 12/99 BigSpender 9999999.99",
                 "Paying $9999999.99 via Credit Card using Reward Points Redemption Feature.\nRedeemed $10 using reward points.\nPaying remaining amount of $9999989.99 via credit card.\n");
    }

    private static void validate(String inStr, String exp) {
        var out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        
        String[] t = inStr.split(" ");
        var s = new TransactionDispatchService();
        if (t[0].equals("CreditCard")) {
            s.process(new CreditCardPaymentProcessor(t[1], t[2], t[3], t[4]), Double.parseDouble(t[5]));
        } else {
            s.process(new PayPalPaymentProcessor(t[1], t[2]), Double.parseDouble(t[3]));
        }
        
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));
        System.out.println(out.toString().replace("\r\n", "\n").equals(exp) ? "PASS" : "FAIL");
    }
}