package com.interview.notes.code.months.year2023.Aug23.test6;

import java.util.List;

class InternationalShipping implements Shipping {
    private static final double FLAT_RATE = 2.5;
    private static final double TAX_RATE = 0.2;

    @Override
    public double calculateShippingCost(List<Product> products) {
        double total = FLAT_RATE * products.size();
        total += total * TAX_RATE;
        return total;
    }
}