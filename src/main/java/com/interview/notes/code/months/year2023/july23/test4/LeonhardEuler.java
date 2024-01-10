package com.interview.notes.code.months.year2023.july23.test4;

/*



In Java:

Leonhard Euler (15 April 1707 - 18 September 1783) was a Swiss
mathematician, physicist, astronomer, geographer, logician and
engineer who founded the studies of graph theory and topology and
made pioneering and influential discoveries in many other branches of
mathematics such as analytic number theory, complex analysis, and
infinitesimal calculus.
Euler provided a proof to calculate value of pi using (yet another)
infinite sum. expressed as:
Your job is to write a program to calculate value of pi using first N
steps using above formula.
To tost the code, give value of N - 1000 or 10.000 or 100.000 or
1,000.000 and see how accuracy increases. How many digits after the
decimal point are you getting accurate results?


It appears you have not provided the specific infinite sum formula that Euler used to calculate the value of pi. Euler is known for several different mathematical formulas related to pi, so please specify the exact formula you're referring to.

However, one of the most famous ones Euler proposed involves an infinite series and is linked to the value of pi squared over 6. Please confirm if this is the series you want the Java code for:

π²/6 = 1/1² + 1/2² + 1/3² + 1/4² + ...

If you're looking for a different Euler series to calculate pi, please provide more information.

 */
public class LeonhardEuler {
    public static void main(String[] args) {
        System.out.println(calculatePi(1000));      // 1000 iterations
        System.out.println(calculatePi(10000));     // 10,000 iterations
        System.out.println(calculatePi(100000));    // 100,000 iterations
        System.out.println(calculatePi(1000000));   // 1,000,000 iterations
    }

    public static double calculatePi1(int n) {
        double sum = 0.0;
        for (int i = 1; i <= n; i++) {
            sum += 1.0 / (i * i);
        }
        return Math.sqrt(sum * 6);
    }

    public static double calculatePi(int n) {
        double pi = 0.0;
        for (int i = 1; i <= n; i++) {
            pi += 1.0 / (i * i);
        }

        pi *= 6.0;

        return pi;
    }
}
