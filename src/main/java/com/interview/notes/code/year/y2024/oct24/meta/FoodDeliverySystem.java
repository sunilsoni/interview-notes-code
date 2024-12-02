package com.interview.notes.code.year.y2024.oct24.meta;

import java.util.concurrent.TimeUnit;

/*

System Design architecture

Food delivery :
| Chinese | Thai | Japanese |
|R1 |R2 |R3 |R4
| R5
M1
P1
M2
P2
M3
P3
Home--<-
Restaurant
[dasher is approaching]
 */
public class FoodDeliverySystem {
    public static void main(String[] args) {
        DeliveryService deliveryService = new DeliveryService();

        // Place an order with a 15-minute preparation time (in milliseconds)
        Order order = new Order(101, "Best Pizza", "123 Main St", TimeUnit.MINUTES.toMillis(15));
        deliveryService.placeOrder(order);
    }
}