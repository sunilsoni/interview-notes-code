package com.interview.notes.code.year.y2025.september.common.test4;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    static List<String> processRequests(List<String> input) {
        int n = Integer.parseInt(input.get(0));
        return input.stream().skip(1).limit(n)
                .flatMap(line -> {
                    String[] p = line.split(" ");
                    if (p[1].equals("CreditCard")) {
                        double amt = Double.parseDouble(p[6]);
                        return new CreditCardPaymentMethod().pay(amt).stream();
                    } else {
                        double amt = Double.parseDouble(p[4]);
                        return new PayPalPaymentMethod().pay(amt).stream();
                    }
                }).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<TestCase> cases = List.of(
                new TestCase(
                        Arrays.asList(
                                "4",
                                "Pay CreditCard 1234-5678-9101-1121 123 12/23 JohnDoe 119.858",
                                "Pay PayPal amy.white@example.com password987 124.468",
                                "Pay CreditCard 6666-7777-8888-9999 432 02/23 AmyWhite 60.1619",
                                "Pay PayPal chris.wilson@example.com 12345678 145.737"
                        ),
                        Arrays.asList(
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
                        )
                )
        );

        int passed = 0;
        for (int i = 0; i < cases.size(); i++) {
            List<String> actual = processRequests(cases.get(i).in);
            boolean ok = actual.equals(cases.get(i).exp);
            System.out.println("Test case " + (i + 1) + ": " + (ok ? "PASS" : "FAIL"));
            if (ok) passed++;
        }
        System.out.println("Passed " + passed + " out of " + cases.size());

        int size = 100000;
        List<String> large = new ArrayList<>();
        large.add(String.valueOf(size));
        for (int i = 0; i < size; i++) {
            large.add("Pay PayPal user" + i + "@example.com pass" + i + " 100.0");
        }
        long start = System.currentTimeMillis();
        processRequests(large);
        long end = System.currentTimeMillis();
        System.out.println("Large test time: " + (end - start) + "ms");
    }

    interface PaymentMethod {
        List<String> pay(double amount);
    }

    static class CreditCardPaymentMethod implements PaymentMethod {
        public List<String> pay(double amount) {
            double redeem = Math.min(amount * 0.1, 10);
            double remaining = amount - redeem;
            return Arrays.asList(
                    "Paying $" + Utils.roundDouble(amount) + " via Credit Card using Reward Points Redemption Feature.",
                    "Redeemed $" + Utils.roundDouble(redeem) + " using reward points.",
                    "Paying remaining amount of $" + Utils.roundDouble(remaining) + " via credit card."
            );
        }
    }

    static class PayPalPaymentMethod implements PaymentMethod {
        public List<String> pay(double amount) {
            double first = amount / 2;
            double second = first * 1.05;
            return Arrays.asList(
                    "Paying $" + Utils.roundDouble(amount) + " via PayPal using Installment Payment Plan.",
                    "Paid $" + Utils.roundDouble(first) + " in first installment.",
                    "Paid $" + Utils.roundDouble(second) + " in second installment with 5% interest."
            );
        }
    }

    static class Utils {
        public static String roundDouble(double d) {
            return new java.text.DecimalFormat("#.##").format(d);
        }
    }

    static class TestCase {
        List<String> in, exp;

        TestCase(List<String> i, List<String> e) {
            in = i;
            exp = e;
        }
    }
}