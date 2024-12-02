package com.interview.notes.code.year.y2024.june24.test13;

import java.util.ArrayList;
import java.util.List;

public class Main3 {
    private List<Integer> nums;

    public Main3() {
        nums = new ArrayList<>();
        nums.add(0);
        nums.add(0);
        nums.add(4);
        nums.add(2);
        nums.add(5);
        nums.add(0);
        nums.add(3);
        nums.add(0);
    }

    public static void main(String[] args) {
        Main3 main = new Main3();
        main.numQuest();
        main.printNums();
    }

    public void numQuest() {
        int k = 0;
        Integer zero = new Integer(0);
        while (k < nums.size()) {
            if (nums.get(k).equals(zero)) {
                nums.remove(k);
            }
            k++;
        }
    }

    public void printNums() {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
