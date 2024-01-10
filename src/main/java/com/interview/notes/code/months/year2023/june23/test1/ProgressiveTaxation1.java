package com.interview.notes.code.months.year2023.june23.test1;

/* * Rate - Bracket
 * 10% - $0.00 - $10,000
 * 12% - $10,000 - $40,000
 * 22% - $40,000 - $100,000
 * 24% - $100,000 or more
 */
class ProgressiveTaxation1 {

    public static void main(String[] args) {
        assertResult(calculateNetIncome(5000.50), 4500.45);
        assertResult(calculateNetIncome(10000.00), 9000.00);
        assertResult(calculateNetIncome(10000.99), 9000.87);
        assertResult(calculateNetIncome(40000.00), 35400.00);
        assertResult(calculateNetIncome(40000.99), 35400.77);
        assertResult(calculateNetIncome(100000.00), 82200.00);
        assertResult(calculateNetIncome(100000.99), 82200.75);
        assertResult(calculateNetIncome(123456.78), 100027.15);
    }

    public static void assertResult(Double actuaL, Double expected) {

        System.out.println(expected.equals(actuaL) ? "PASS" : "FAIL - Actual[" + actuaL + "] Expected[" + expected + "]");
    }

    public static double calculateNetIncome(Double grossIncome) {
// your code here
        return 0.0;
    }

    public static double round(Double amount) {
        return Math.round(amount * 100.0) / 100.0;
    }


}



