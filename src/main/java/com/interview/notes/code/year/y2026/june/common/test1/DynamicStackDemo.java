package com.interview.notes.code.year.y2026.june.common.test1;

import java.util.Arrays;

public class DynamicStackDemo {

    public static void main(String[] args) throws InterruptedException {

        DynamicStack<Integer> stack = new DynamicStack<>();

        Thread t1 = new Thread(() -> {
            stack.push(10);
            stack.push(20);
            stack.push(30);
        });

        Thread t2 = new Thread(() -> {
            stack.push(40);
            stack.push(50);
            stack.push(60);
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("Size: " + stack.size());       // 6
        System.out.println("Capacity: " + stack.capacity()); // grows dynamically

        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

    static class DynamicStack<T> {

        private Object[] data = new Object[2]; // starting capacity
        private int size = 0; // current stack size

        public synchronized void push(T value) {
            if (size == data.length) {
                resize();
            }

            data[size++] = value;
        }

        private void resize() {
            data = Arrays.copyOf(data, data.length + 2); // grow by 2
        }

        @SuppressWarnings("unchecked")
        public synchronized T pop() {
            if (size == 0) {
                throw new RuntimeException("Stack is empty");
            }

            T value = (T) data[--size];
            data[size] = null;
            return value;
        }

        public synchronized int size() {
            return size;
        }

        public synchronized int capacity() {
            return data.length;
        }
    }
}