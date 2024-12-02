package com.interview.notes.code.year.y2024.oct24.meta;

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