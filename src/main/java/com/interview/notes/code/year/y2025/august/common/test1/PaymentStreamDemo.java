package com.interview.notes.code.year.y2025.august.common.test1;

import java.util.*;
import java.util.stream.Collectors;

class Address {
    private String name;
    private String mobile;
    private String type;

    public Address(String name, String mobile, String type) {
        this.name = name;
        this.mobile = mobile;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getMobile() {
        return mobile;
    }

    public String getType() {
        return type;
    }
}

class Payment {
    private String fromAccount;
    private String toAccount;
    private String amount;
    private String paymentDate;
    private List<Address> addresses;

    public Payment(String fromAccount, String toAccount, String amount,
                   String paymentDate, List<Address> addresses) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.addresses = addresses;
    }

    public List<Address> getAddresses() {
        return addresses;
    }
}

public class PaymentStreamDemo {

    // Extract Name -> Mobile for type "P" without merge function
    public static Map<String, String> getPrimaryAddressMap(List<Payment> payments) {
        // Step 1: Stream over all addresses
        // Step 2: Filter only type = "P"
        // Step 3: Use groupingBy first (so no duplicate key issue)
        // Step 4: Pick first mobile for each name
        return payments.stream()
                .flatMap(payment -> payment.getAddresses().stream())
                .filter(address -> "P".equalsIgnoreCase(address.getType()))
                .collect(Collectors.groupingBy(
                        Address::getName,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.get(0).getMobile()
                        )
                ));
    }

    // Test method
    public static void runTests() {
        // Test Case 1
        List<Payment> testPayments1 = Arrays.asList(
                new Payment("1234", "5678", "10", "2025-08-20",
                        Arrays.asList(new Address("Vijay", "123456", "P"),
                                new Address("Ajay", "999999", "S"))),
                new Payment("1111", "2222", "20", "2025-08-21",
                        Arrays.asList(new Address("Sunil", "888888", "P")))
        );
        Map<String, String> expected1 = new HashMap<>();
        expected1.put("Vijay", "123456");
        expected1.put("Sunil", "888888");
        System.out.println("Test Case 1: " + (getPrimaryAddressMap(testPayments1).equals(expected1) ? "PASS" : "FAIL"));

        // Test Case 2
        List<Payment> testPayments2 = Arrays.asList(
                new Payment("3333", "4444", "30", "2025-08-22",
                        Arrays.asList(new Address("Ram", "777777", "S")))
        );
        System.out.println("Test Case 2: " + (getPrimaryAddressMap(testPayments2).isEmpty() ? "PASS" : "FAIL"));

        // Test Case 3: Duplicate name
        List<Payment> testPayments3 = Arrays.asList(
                new Payment("5555", "6666", "40", "2025-08-23",
                        Arrays.asList(new Address("Vijay", "123456", "P"))),
                new Payment("7777", "8888", "50", "2025-08-24",
                        Arrays.asList(new Address("Vijay", "654321", "P")))
        );
        Map<String, String> expected3 = new HashMap<>();
        expected3.put("Vijay", "123456"); // keeps first one
        System.out.println("Test Case 3: " + (getPrimaryAddressMap(testPayments3).equals(expected3) ? "PASS" : "FAIL"));

        // Test Case 4: Large Data
        List<Payment> largePayments = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            Address primary = new Address("Name" + i, "Mobile" + i, "P");
            Address secondary = new Address("Alt" + i, "AltMobile" + i, "S");
            largePayments.add(new Payment("A" + i, "B" + i, String.valueOf(i),
                    "2025-08-25", Arrays.asList(primary, secondary)));
        }
        Map<String, String> largeResult = getPrimaryAddressMap(largePayments);
        boolean largePass = largeResult.size() == 1000 && largeResult.get("Name500").equals("Mobile500");
        System.out.println("Test Case 4 (Large Data): " + (largePass ? "PASS" : "FAIL"));
    }

    public static void main(String[] args) {
        runTests();
    }
}