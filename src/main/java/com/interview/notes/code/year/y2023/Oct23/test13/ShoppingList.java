package com.interview.notes.code.year.y2023.Oct23.test13;

public class ShoppingList {

    public static void main(String[] args) {
        ShoppingList list = new ShoppingList();
        int[] prices = {2, 7, 11, 15};
        int money = 9;
        int[] answer = list.findItems(prices, money);
        System.out.println("You can buy items at indices: " + answer[0] + " and " + answer[1]);
    }

    public int[] findItems(int[] nums, int target) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int sum = nums[left] + nums[right];

            if (sum == target) {
                return new int[]{left, right};
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return null; // Return null if no solution is found.
    }
}
