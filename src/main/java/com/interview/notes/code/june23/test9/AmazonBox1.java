package com.interview.notes.code.june23.test9;

/**
 * In Java solve below problem with good space and time complexity:
 * <p>
 * <p>
 * Items can ship as-is (no Amazon box).
 * In some scenarios. Customers want Amazon to hide their items in an Amazon box.
 * The problem: Amazon only has a finite set of box types.
 * We want to warn the customer if an item they are about to purchase is too big to fit in any box type.
 * <p>
 * // example box types:
 * // interior Length, width. Height
 * 24 x 20 x 16
 * 20 x 16 x 12
 * 50 x 40 x 2
 * 40 x 30 x 2
 * 60 x 3 x 3
 * 15 x 3 x 3
 * <p>
 * // example input item
 * 10 x 10 x 10 => yes, can be hidden
 * 60 x 5 x 5 => no, cannot be hidden
 * T
 */
public class AmazonBox1 {

    private static final Boxx[] boxTypes = {
            new Boxx(24, 20, 16),
            new Boxx(20, 16, 12),
            new Boxx(50, 40, 2),
            new Boxx(40, 30, 2),
            new Boxx(60, 3, 3),
            new Boxx(15, 3, 3)
    };

    public static boolean canHideItem(int length, int width, int height) {
        for (Boxx box : boxTypes) {
            if (box.canFit(length, width, height)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(canHideItem(10, 10, 10)); // true
        System.out.println(canHideItem(60, 5, 5)); // false
    }
}

class Boxx {

    private int length;
    private int width;
    private int height;

    public Boxx(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public boolean canFit(int length, int width, int height) {
        return this.length >= length && this.width >= width && this.height >= height;
    }
}
