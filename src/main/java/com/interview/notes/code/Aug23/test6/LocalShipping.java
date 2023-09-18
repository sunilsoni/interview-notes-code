package com.interview.notes.code.Aug23.test6;

import java.util.List;

class LocalShipping implements Shipping {
    private static final double SMALL_COST = 1.5;
    private static final double MEDIUM_COST = 3.2;
    private static final double LARGE_COST = 5.4;

    @Override
    public double calculateShippingCost(List<Product> products) {
        double total = 0;
        for (Product product : products) {
           /* switch(product.getSize()) {
                case SMALL_COST: total += SMALL_COST; break;
                case MEDIUM_COST: total += MEDIUM_COST; break;
                case LARGE_COST: total += LARGE_COST; break;
            }*/
        }
        return total;
    }
}