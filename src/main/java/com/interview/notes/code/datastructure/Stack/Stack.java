package com.interview.notes.code.datastructure.Stack;

import java.util.EmptyStackException;
import java.util.Objects;

public class Stack<T> {

    private StackNode<T> top;
    private int size;


    public void push(T val) {
        StackNode<T> cur = new StackNode<>(val);
        cur.setNext(top);
        top = cur;
        size++;
    }

    // peek() does not return the topmost node but rather its value:
    public T peek() {
        checkNotEmpty();
        return top.getValue();
    }

    public T pop() {
        checkNotEmpty();
        T poppedElement = top.getValue();
        top = top.getNext();
        size--;
        return poppedElement;
    }

    public void clear() {
        top = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[").append(top.getValue());
        StackNode<T> node = top.getNext();

        while (node != null) {
            sb.append(" -> ").append(node.getValue());
            node = node.getNext();
        }
        return sb.append("]").toString();
    }

    private void checkNotEmpty() {
        if (size == 0) {
            throw new EmptyStackException();
        }
    }

    private static class StackNode<T> {
        private final T value;
        private StackNode<T> next;

        StackNode(T val) {
            this.value = val;
        }

        T getValue() {
            return value;
        }

        StackNode<T> getNext() {
            return next;
        }

        void setNext(StackNode<T> nextNode) {
            this.next = nextNode;
        }

        @Override
        public String toString() {
            return Objects.toString(value);
        }
    }
}