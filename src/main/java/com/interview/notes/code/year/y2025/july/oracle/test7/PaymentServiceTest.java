package com.interview.notes.code.year.y2025.july.oracle.test7;

import java.util.*;                         // for List, Arrays, etc.
import java.util.stream.*;                  // for streams and IntStream
import java.io.*;                           // for PrintStream, ByteArrayOutputStream
import java.text.DecimalFormat;             // for DecimalFormat in rounding
/*


## ✅ Problem Statement:

Design a **payment service** that supports two different payment methods: **PayPal** and **Credit Card**, each with **unique features**.

### 1. **PayPal**:

* Use an **Installment Payment Plan**.
* The total amount is split into **two payments**:

  * The **first installment** is 50% of the amount.
  * The **second installment** is the remaining amount with a **5% interest** applied.
* Output should be:

  ```text
  Paying $<amount> via PayPal using Installment Payment Plan.
  Paid $<installment1> in first installment.
  Paid $<installment2> in second installment with 5% interest.
  ```

### 2. **Credit Card**:

* Use **Reward Points Redemption Feature**:

  * Redeem **10%** of the amount using reward points, with a **maximum of \$10**.
* Output should be:

  ```text
  Paying $<amount> via Credit Card using Reward Points Redemption Feature.
  Redeemed $<redeemableAmount> using reward points.
  Paying remaining amount of $<remainingAmount> via credit card.
  ```

---

## 📘 Interface Specification

```java
interface PaymentMethod {
    void pay(double amount);
}
```

---

## 🔧 Classes to Implement

### 1. `CreditCardPaymentMethod implements PaymentMethod`

```java
public CreditCardPaymentMethod(String cardNumber, String cvv, String expiryDate, String cardHolderName) { }
@Override
public void pay(double amount) { }
```

### 2. `PayPalPaymentMethod implements PaymentMethod`

```java
public PayPalPaymentMethod(String email, String password) { }
@Override
public void pay(double amount) { }
```

### 3. `PaymentService`

```java
public void processPayment(PaymentMethod paymentMethod, double amount) {
    paymentMethod.pay(amount);
}
```

---

## 🧪 Utility Method for Output Formatting

```java
class Utils {
    public static String roundDouble(double d) {
        DecimalFormat df = new DecimalFormat("#.##");
        return df.format(d);
    }
}
```

Use `Utils.roundDouble(double)` to round and format all monetary values for output.

---

## 📥 Input Format

```
n (number of requests)

Then n lines follow, each line in one of the following formats:
1- Pay CreditCard {cardNumber} {cvv} {expiryDate} {cardHolderName} {amount}
2- Pay PayPal {email} {password} {amount}
```

* `cardNumber`, `cvv`, `expiryDate`, `cardHolderName`, `email`, `password` → Strings with no spaces.
* `amount` → A **real non-negative** number.

---

## 📤 Output Format

Follow the message formats exactly as described above.

---

## 📌 Sample Input

```
4
Pay CreditCard 1234-5678-9101-1121 123 12/23 JohnDoe 119.858
Pay PayPal amy.white@example.com password987 124.468
Pay CreditCard 6666-7777-8888-9999 432 02/23 AmyWhite 60.1619
Pay PayPal chris.wilson@example.com 12345678 145.737
```

---

## ✅ Sample Output

```
Paying $119.86 via Credit Card using Reward Points Redemption Feature.
Redeemed $10 using reward points.
Paying remaining amount of $109.86 via credit card.

Paying $124.47 via PayPal using Installment Payment Plan.
Paid $62.23 in first installment.
Paid $65.35 in second installment with 5% interest.

Paying $60.16 via Credit Card using Reward Points Redemption Feature.
Redeemed $6.02 using reward points.
Paying remaining amount of $54.15 via credit card.

Paying $145.74 via PayPal using Installment Payment Plan.
Paid $72.87 in first installment.
Paid $76.51 in second installment with 5% interest.
```

---


 */
// Utility class for rounding doubles when printing
class Utils {
    public static String roundDouble(double d) {                                 // take a double
        DecimalFormat df = new DecimalFormat("#.##");                           // create formatter with up to 2 decimals
        return df.format(d);                                                    // return formatted string
    }
}

// Common interface for all payment methods
interface PaymentMethod {
    void pay(double amount);                                                     // process a payment of given amount
}

// Credit‐card payment, with reward‐points redemption
class CreditCardPaymentMethod implements PaymentMethod {
    private String cardNumber;                                                   // card number (not used in logic)
    private String cvv;                                                          // CVV code
    private String expiryDate;                                                   // expiry date
    private String cardHolderName;                                               // name on card

    // Constructor: store credentials
    public CreditCardPaymentMethod(String cardNumber, String cvv, String expiryDate, String cardHolderName) {
        this.cardNumber = cardNumber;                                            // save card number
        this.cvv = cvv;                                                          // save CVV
        this.expiryDate = expiryDate;                                            // save expiry
        this.cardHolderName = cardHolderName;                                    // save holder name
    }

    @Override
    public void pay(double amount) {
        // calculate 10% of amount as redeemable points, capped at $10
        double redeemable = Math.min(amount * 0.10, 10.0);
        // remaining amount after redemption
        double remaining = amount - redeemable;
        // print main payment line
        System.out.println("Paying " + Utils.roundDouble(amount)
            + " via Credit Card using Reward Points Redemption Feature.");
        // print redeemed‐points line
        System.out.println("Redeemed " + Utils.roundDouble(redeemable)
            + " using reward points.");
        // print remaining‐amount line
        System.out.println("Paying remaining amount of " + Utils.roundDouble(remaining)
            + " via credit card.");
    }
}

// PayPal payment, with two‐installment plan and 5% interest on second
class PayPalPaymentMethod implements PaymentMethod {
    private String email;                                                        // PayPal email
    private String password;                                                     // PayPal password

    // Constructor: store credentials
    public PayPalPaymentMethod(String email, String password) {
        this.email = email;                                                      // save email
        this.password = password;                                                // save password
    }

    @Override
    public void pay(double amount) {
        // split into two equal installments
        double first = amount / 2.0;
        double second = amount - first;
        // add 5% interest on the second installment
        double secondWithInterest = second * 1.05;
        // print main payment line
        System.out.println("Paying " + Utils.roundDouble(amount)
            + " via PayPal using Installment Payment Plan.");
        // print first‐installment line
        System.out.println("Paid " + Utils.roundDouble(first)
            + " in first installment.");
        // print second‐installment line
        System.out.println("Paid " + Utils.roundDouble(secondWithInterest)
            + " in second installment with 5% interest.");
    }
}

// Service that delegates to whatever PaymentMethod you give it
class PaymentService {
    public void processPayment(PaymentMethod pm, double amount) {
        pm.pay(amount);                                                           // call the pay(...) method
    }
}

// Simple holder for a single test case
class TestCase {
    String name;                                                                // name of the test
    PaymentMethod method;                                                       // which payment method to use
    double amount;                                                              // input amount
    List<String> expected;                                                      // expected console lines

    public TestCase(String name, PaymentMethod method, double amount, List<String> expected) {
        this.name = name;                                                       // save test name
        this.method = method;                                                   // save method
        this.amount = amount;                                                   // save amount
        this.expected = expected;                                               // save expected lines
    }
}

// Main class with tests
public class PaymentServiceTest {
    public static void main(String[] args) {
        PaymentService service = new PaymentService();                          // create our payment service

        // define a few small, deterministic test cases
        List<TestCase> testCases = Arrays.asList(
            new TestCase(
                "CC small",                                                     // test name
                new CreditCardPaymentMethod("1234", "000", "12/25", "John Doe"),
                50.0,                                                            // amount
                Arrays.asList(                                                  // expected output lines
                    "Paying 50 via Credit Card using Reward Points Redemption Feature.",
                    "Redeemed 5 using reward points.",
                    "Paying remaining amount of 45 via credit card."
                )
            ),
            new TestCase(
                "CC cap",                                                       // test reward‐cap
                new CreditCardPaymentMethod("1234", "000", "12/25", "John Doe"),
                200.0,                                                           // amount
                Arrays.asList(
                    "Paying 200 via Credit Card using Reward Points Redemption Feature.",
                    "Redeemed 10 using reward points.",
                    "Paying remaining amount of 190 via credit card."
                )
            ),
            new TestCase(
                "PP standard",                                                  // PayPal standard
                new PayPalPaymentMethod("user@example.com", "pass"),
                100.0,                                                           // amount
                Arrays.asList(
                    "Paying 100 via PayPal using Installment Payment Plan.",
                    "Paid 50 in first installment.",
                    "Paid 52.5 in second installment with 5% interest."
                )
            )
        );

        // run all small test cases and print PASS/FAIL
        testCases.stream().forEach(tc -> {                                       // use Java 8 stream
            // capture System.out
            ByteArrayOutputStream baos = new ByteArrayOutputStream();            // buffer for output
            PrintStream oldOut = System.out;                                     // remember old out
            System.setOut(new PrintStream(baos));                                // redirect to buffer

            service.processPayment(tc.method, tc.amount);                       // run the payment

            System.out.flush();                                                  // flush buffer
            System.setOut(oldOut);                                               // restore console

            // split captured output into lines
            String[] actual = baos.toString().trim().split("\\r?\\n");           
            // compare actual vs expected exactly
            boolean pass = Arrays.asList(actual).equals(tc.expected);           
            // print result line
            System.out.println(tc.name + ": " + (pass ? "PASS" : "FAIL"));       
        });

        // large‐data test: run 10 000 payments to check for exceptions/performance
        try {
            IntStream.range(0, 10_000).forEach(i -> {                            // loop 0..9999
                double amt = i * 1.11;                                           // generate a test amount
                service.processPayment(                                         
                    new CreditCardPaymentMethod("0000","111","01/30","Test"),    
                    amt                                                         
                );
            });
            System.out.println("Large data test: PASS");                        // no exceptions = pass
        } catch (Exception e) {                                                  
            System.out.println("Large data test: FAIL – " + e.getMessage());     // report error
        }
    }
}