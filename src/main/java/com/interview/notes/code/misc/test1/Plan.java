package com.interview.notes.code.misc.test1;

abstract class Plan {
    double rate;

    abstract double getRate();

    public void caluclateBill(int unit) {

        System.out.println(unit * rate);
    }
}