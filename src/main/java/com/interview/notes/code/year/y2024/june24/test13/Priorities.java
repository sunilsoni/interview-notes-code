package com.interview.notes.code.year.y2024.june24.test13;

import java.util.PriorityQueue;

class Priorities {
    public static void main(String[] args) {
        PriorityQueue<String> toDo = new PriorityQueue<>();
        toDo.add("dishes");
        toDo.add("laundry");
        toDo.add("bills");
        toDo.offer("bills");
        System.out.print(toDo.size() + " " + toDo.poll());
        System.out.print(" " + toDo.peek() + " " + toDo.poll());
        System.out.println(" " + toDo.poll() + " " + toDo.poll());
    }
}
