package com.interview.notes.code.year.y2023.july23.test7;

import java.util.ArrayList;
import java.util.List;

enum Size {
    S, M, L
}

interface Shipping {
    double calculateShippingCost(List<Product> products);
}

class Product {
    private int id;
    private String title;
    private Size size;

    public Product(int id, String title, Size size) {
        this.id = id;
        this.title = title;
        this.size = size;
    }

    // getters and setters
}

class LocalShipping implements Shipping {
    @Override
    public double calculateShippingCost(List<Product> products) {
        return 0;
    }

    /*
    public double calculateShippingCost(List<Product> products) {
        double total = 0;
        for (Product product : products) {
            switch (product.getSize()) {
                case S:
                    total += 1.5;
                    break;
                case M:
                    total += 3.2;
                    break;
                case L:
                    total += 5.4;
                    break;
            }
        }
         return null;//total;
        */


}

class InternationalShipping implements Shipping {
    public double calculateShippingCost(List<Product> products) {
        double total = 0;
        total += 2.5 * products.size();
        total += total * 0.2;  // adding 20% taxes
        return total;
    }
}

class Cart {
    private List<Product> products = new ArrayList<>();
    private Shipping shipping;

    public Cart(Shipping shipping) {
        this.shipping = shipping;
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
