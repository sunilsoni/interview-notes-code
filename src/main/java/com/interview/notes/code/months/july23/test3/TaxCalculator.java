package com.interview.notes.code.months.july23.test3;

public class TaxCalculator {
    public static float taxRate = 10;
    public float grossincome;
    public float deduction;

    public TaxCalculator(float grossincome, float deduction) {
        this.grossincome = grossincome;
        this.deduction = deduction;
    }

    public static void main(String[] args) {
        TaxCalculator taxCalculator = new TaxCalculator(1000, 100);
        float payableTax = taxCalculator.getPayableTax();
        System.out.println("Payable tax: " + payableTax);
    }

    public float getPayableTax() {
        return (TaxCalculator.taxRate / 100) * (this.grossincome - this.deduction);
    }
}

