package com.interview.notes.code.test.test4;

/*

In Java provide code

Calculate the Net Income for someone given the Gross Income under a progressive taxation system
using the following brackets

Rate - Bracket
10% - $0.00-$10,000
12% - $10,001 -$40,000
22% - $40,001 -$100,000
24% - $100,001 or more

Examples
$5,000 gross income would only fall under the first bracket thus
5000*0.1 =500

$15,000 gross income would fall under the first two brackets thus
10000*0.1 = 1000
5000*0.12 = 600
1000 + 600 = 1600
 */
public class NetIncomeCalculatorWorking {
    public static void main(String[] args) {
        double grossIncome = 15000.0; // Example gross income


        double netIncome = calculateNetIncome(grossIncome);
        System.out.println("Net Income: $" + netIncome);
    }


    public static double calculateNetIncome(double grossIncome) {
        double netIncome = 0.0;

        if (grossIncome <= 10000) {
            netIncome = grossIncome - grossIncome * 0.10;
        } else if (grossIncome <= 40000) {
            netIncome = grossIncome - (10000 * 0.10 + (grossIncome - 10000) * 0.12);
        } else if (grossIncome <= 100000) {
            netIncome = grossIncome - (10000 * 0.10 + 30000 * 0.12 + (grossIncome - 40000) * 0.22);
        } else {
            netIncome = grossIncome - (10000 * 0.10 + 30000 * 0.12 + 60000 * 0.22 + (grossIncome - 100000) * 0.24);
        }


        return netIncome;
    }
}


