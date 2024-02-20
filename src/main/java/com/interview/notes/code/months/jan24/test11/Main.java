package com.interview.notes.code.months.jan24.test11;

public class Main {
    public static void main(String[] args) {
        // Example usage
        Food egg = new Egg(6.3, 5.3, 0.6);
        egg.getMacroNutrients();
        System.out.println("Taste: " + egg.getTaste());
        System.out.println("Type: " + ((Egg) egg).getType());
        
        Food bread = new Bread(4.0, 1.1, 13.8);
        bread.getMacroNutrients();
        System.out.println("Taste: " + bread.getTaste());
        System.out.println("Type: " + ((Bread) bread).getType());
    }
}