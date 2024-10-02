package com.interview.notes.code.months.oct24.meta;

import java.util.*;
import java.util.concurrent.*;

class Order {
    int orderId;
    String restaurant;
    String customerAddress;
    long preparationTime; // in milliseconds
    boolean isPrepared;

    public Order(int orderId, String restaurant, String customerAddress, long preparationTime) {
        this.orderId = orderId;
        this.restaurant = restaurant;
        this.customerAddress = customerAddress;
        this.preparationTime = preparationTime;
        this.isPrepared = false;
    }

    public void setPrepared(boolean prepared) {
        this.isPrepared = prepared;
    }
}