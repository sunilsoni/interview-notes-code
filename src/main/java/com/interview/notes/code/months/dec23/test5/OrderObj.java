package com.interview.notes.code.months.dec23.test5;

class OrderObj {
    private String zipCode;
    private int orderAmount;

    public OrderObj(String zipCode, int orderAmount) {
        this.zipCode = zipCode;
        this.orderAmount = orderAmount;
    }

    public String getZipCode() {
        return zipCode;
    }

    public int getOrderAmount() {
        return orderAmount;
    }
}
