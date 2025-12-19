package com.interview.notes.code.year.y2025.december.capitalOne.test4;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class VirtualCardValidator {

    public static void main(String[] args) {

        // Run all test cases and print results
        System.out.println("=== Virtual Card Validation Test Results ===\n");

        // Store test results
        var results = new ArrayList<String>();
        int passed = 0, failed = 0;

        // Test Case 1: Visa, merchant-bound, not multi-use -> VALID
        var t1 = validate("1234567890123010", "12345600", 50.0);
        results.add(check("TC1: Visa merchant-bound not-multiuse", t1, true));
        if (t1) passed++; else failed++;

        // Test Case 2: Visa, not merchant-bound, online, charge, amt<100 -> VALID
        var t2 = validate("1234567890123000", "12345611", 99.0);
        results.add(check("TC2: Visa online charge <$100", t2, true));
        if (t2) passed++; else failed++;

        // Test Case 3: Visa, online, authorization, amt<100 -> INVALID (authorization fails)
        var t3 = validate("1234567890123000", "12345601", 50.0);
        results.add(check("TC3: Visa online authorization", t3, false));
        if (!t3) passed++; else failed++;

        // Test Case 4: Mastercard, not merchant-bound, online, amt<100 -> VALID
        var t4 = validate("1234567890123100", "12345611", 50.0);
        results.add(check("TC4: MC not-merchant-bound <$100", t4, true));
        if (t4) passed++; else failed++;

        // Test Case 5: Mastercard, merchant-bound, amt>100 -> VALID
        var t5 = validate("1234567890123101", "12345611", 150.0);
        results.add(check("TC5: MC merchant-bound >$100", t5, true));
        if (t5) passed++; else failed++;

        // Test Case 6: Mastercard, not merchant-bound, amt>=100 -> INVALID
        var t6 = validate("1234567890123100", "12345611", 100.0);
        results.add(check("TC6: MC not-merchant-bound =$100", t6, false));
        if (!t6) passed++; else failed++;

        // Test Case 7: Mastercard, merchant-bound, amt<=100 -> INVALID
        var t7 = validate("1234567890123101", "12345611", 100.0);
        results.add(check("TC7: MC merchant-bound =$100", t7, false));
        if (!t7) passed++; else failed++;

        // Test Case 8: Visa, offline, not merchant-bound -> INVALID
        var t8 = validate("1234567890123000", "12345610", 50.0);
        results.add(check("TC8: Visa offline not-merchant-bound", t8, false));
        if (!t8) passed++; else failed++;

        // Test Case 9: Visa, merchant-bound, multi-use, offline -> INVALID
        var t9 = validate("1234567890123011", "12345610", 50.0);
        results.add(check("TC9: Visa merchant-bound multi-use", t9, false));
        if (!t9) passed++; else failed++;

        // Test Case 10: Large data - process 10000 transactions
        var t10 = testLargeData();
        results.add(check("TC10: Large data 10000 txns", t10, true));
        if (t10) passed++; else failed++;

        // Print all results
        results.forEach(System.out::println);

        // Summary
        System.out.println("\n=== Summary ===");
        System.out.println("Passed: " + passed + " | Failed: " + failed);
    }

    // Main validation method - parses inputs and applies business rules
    static boolean validate(String vcn, String txnId, double amt) {

        // Parse the transaction data from card number and transaction id
        var data = parse(vcn, txnId, amt);

        // Apply card-specific rules using pattern matching
        return data.visa() ? visaRules(data) : mcRules(data);
    }

    // Parse method extracts meaningful digits from VCN and Transaction ID
    static TxnData parse(String vcn, String txnId, double amt) {

        // Position 7 of txnId: 1=online, 0=offline
        var online = txnId.charAt(7) == '1';

        // Position 6 of txnId: 0=authorization, 1=charge
        var charge = txnId.charAt(6) == '1';

        // Position 15 of VCN (last digit): 1=merchant-bound, 0=not
        var merchantBound = vcn.charAt(15) == '1';

        // Position 14 of VCN: 0=Visa, 1=Mastercard
        var visa = vcn.charAt(14) == '0';

        // Position 13 of VCN: 1=multi-use, 0=single-use
        var multiUse = vcn.charAt(13) == '1';

        // Return parsed data as record
        return new TxnData(online, charge, merchantBound, visa, multiUse, amt);
    }

    // Visa validation rules from line 27 of requirements
    static boolean visaRules(TxnData d) {

        // Rule 1: Valid when merchant-bound AND not multi-use
        if (d.merchantBound() && !d.multiUse()) return true;

        // Rule 2: Valid when online AND amount<$100 AND is a charge (not authorization)
        return d.online() && d.amt() < 100 && d.charge();

        // All other cases invalid for Visa
    }

    // Mastercard validation rules from line 29 of requirements
    static boolean mcRules(TxnData d) {

        // Rule 1: Valid when amount<$100 AND not merchant-bound (online/offline ok)
        if (d.amt() < 100 && !d.merchantBound()) return true;

        // Rule 2: Valid when merchant-bound AND amount>$100
        return d.merchantBound() && d.amt() > 100;

        // All other cases invalid for Mastercard
    }

    // Helper to format test result
    static String check(String name, boolean actual, boolean expected) {
        var status = actual == expected ? "PASS ✓" : "FAIL ✗";
        return String.format("%-40s | Expected: %-5s | Actual: %-5s | %s",
            name, expected, actual, status);
    }

    // Large data test - validates 10000 transactions
    static boolean testLargeData() {

        // Generate and validate 10000 random transactions
        var count = IntStream.range(0, 10000)
            .mapToObj(i -> {
                // Create valid Visa merchant-bound transaction
                var vcn = "1234567890123010";
                var txn = "12345611";
                return validate(vcn, txn, 50.0);
            })
            .filter(b -> b) // count valid ones
            .count();

        // All should be valid
        return count == 10000;
    }

    // Record to hold parsed transaction data - Java 21 feature for compact data classes
    record TxnData(boolean online, boolean charge, boolean merchantBound, boolean visa, boolean multiUse, double amt) {}
}