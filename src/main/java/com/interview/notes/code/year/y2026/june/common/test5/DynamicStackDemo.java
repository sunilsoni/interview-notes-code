package com.interview.notes.code.year.y2026.june.common.test5;

import java.util.Arrays;

public class DynamicStackDemo {

    public static void main(String[] args) {

        DynamicStack<Integer> stack = new DynamicStack<>();

        stack.push(10);
        stack.push(20);

        System.out.println("Capacity after 2 values: " + stack.capacity()); // 2

        stack.push(30); // resize happens here

        System.out.println("Capacity after 3 values: " + stack.capacity()); // 4

        stack.push(40);
        stack.push(50); // resize happens again

        System.out.println("Capacity after 5 values: " + stack.capacity()); // 6

        System.out.println(stack.pop()); // 50
        System.out.println(stack.pop()); // 40
        System.out.println(stack.pop()); // 30
        System.out.println(stack.pop()); // 20
        System.out.println(stack.pop()); // 10
    }

    static class DynamicStack<T> {

        private Object[] data = new Object[2]; // starting capacity is 2
        private int size = 0; // current number of elements

        public void push(T value) {
            if (size == data.length) { // if stack is full
                resize(); // increase capacity
            }

            data[size++] = value; // add value and increase size
        }

        private void resize() {
            data = Arrays.copyOf(data, data.length + 2); // grow by 2 more spaces
        }

        @SuppressWarnings("unchecked")
        public T pop() {
            if (size == 0) {
                throw new RuntimeException("Stack is empty");
            }

            T value = (T) data[--size]; // get top value
            data[size] = null; // avoid memory leak
            return value;
        }

        public int size() {
            return size;
        }

        public int capacity() {
            return data.length;
        }
    }
}