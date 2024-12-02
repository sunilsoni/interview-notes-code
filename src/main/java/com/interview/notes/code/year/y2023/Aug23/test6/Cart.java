package com.interview.notes.code.year.y2023.Aug23.test6;

import java.util.ArrayList;
import java.util.List;

class Cart {
    private List<Product> products = new ArrayList<>();
    private Shipping shipping;

    public Cart(boolean isInternational) {
        this.shipping = isInternational ? new InternationalShipping() : new LocalShipping();
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public double calculateShippingCost() {
        return shipping.calculateShippingCost(products);
    }
}