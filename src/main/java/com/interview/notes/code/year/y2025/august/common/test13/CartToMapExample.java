package com.interview.notes.code.year.y2025.august.common.test13;

import java.util.*;
import java.util.stream.Collectors;

public class CartToMapExample {

    public static void main(String[] args) {
        // Example data
        Tray t1 = new Tray(3L, "T3");
        Tray t2 = new Tray(1L, "T1");
        Tray t3 = new Tray(2L, "T2");

        Cart c1 = new Cart(101L, "CartA", Arrays.asList(t1, t2, t3));
        Cart c2 = new Cart(102L, "CartB", null);   // null trays
        Cart c3 = new Cart(103L, "CartC", Arrays.asList(new Tray(5L, "T5")));

        List<Cart> carts = Arrays.asList(c1, c2, c3);

        // Convert List<Cart> -> Map<Long, List<Long>>
        Map<Long, List<Long>> cartTrayMap = carts.stream()
                .collect(Collectors.toMap(
                        Cart::getId, // key = cartId
                        cart -> Optional.ofNullable(cart.getTrays()) // handle null
                                .orElse(Collections.emptyList())
                                .stream()
                                .map(Tray::getId)   // take trayId
                                .sorted()           // sort trayIds
                                .collect(Collectors.toList())
                ));

        // Print result
        cartTrayMap.forEach((k, v) -> System.out.println(k + " -> " + v));
    }
}

class Cart {
    private Long id;
    private String name;
    private List<Tray> trays;

    public Cart(Long id, String name, List<Tray> trays) {
        this.id = id;
        this.name = name;
        this.trays = trays;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public List<Tray> getTrays() { return trays; }
}

class Tray {
    private Long id;
    private String name;

    public Tray(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
}