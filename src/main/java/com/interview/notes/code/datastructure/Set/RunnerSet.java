package com.interview.notes.code.datastructure.Set;

import java.util.Random;

public class RunnerSet {
    public static void main(String[] args) {
        CustomHashSet<Integer> set = new CustomHashSet<>();

        for (int i = 0; i < 50; i++) {
            if (i == 25) {
                set.add(null);
            }
            set.add(new Random().nextInt(50));
        }
        set.add(null);
        System.out.println(set);
        System.out.println("Length: " + set.length());
        System.out.println("Contains result: " + set.contains(23));
        set.delete(23);
        System.out.println(set);
        System.out.println("Length: " + set.length());
        System.out.println("Contains result: " + set.contains(23));
    }
}