package com.interview.notes.code.spring;

import java.util.Scanner;

public class CoinVendingMachine {
  public static void main(String[] args) {
    int[] coinValues = {25, 10, 5, 1};
    int[] coinCount = new int[4];
    String[] coinNames = {"quarter", "dime", "nickel", "penny"};

    Scanner sc = new Scanner(System.in);
    System.out.print("Enter cost of item: ");
    int cost = sc.nextInt();
    System.out.print("Enter amount given: ");
    int amount = sc.nextInt();
    sc.close();

    int change = amount - cost;
    System.out.println("Change to return: " + change + " cents");

    for (int i = 0; i < 4; i++) {
      coinCount[i] = change / coinValues[i];
      change = change % coinValues[i];
    }

    System.out.println("Coins to return: ");
    for (int i = 0; i < 4; i++) {
      if (coinCount[i] > 0) {
        System.out.println(coinCount[i] + " " + coinNames[i]);
      }
    }
  }
}
