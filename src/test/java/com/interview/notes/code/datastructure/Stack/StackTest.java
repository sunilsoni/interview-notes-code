package com.interview.notes.code.datastructure.Stack;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.junit.jupiter.api.Assertions.*;


class StackTest {

    static Stack<Integer> st;

    @BeforeAll
    public static void setUp() {
        st = new Stack<>();
        System.out.println(st.size());
        st.push(1);
        st.push(10);
        st.push(100);
        st.push(null);
        System.out.println(st);
    }

    @AfterAll
    static void done() {
        st = null;
    }

    @Test
    void stackTest() {
        System.out.println(st.size());
        System.out.println(st.peek());
        System.out.println(st.pop());
        System.out.println(st.peek());
        System.out.println(st.pop());
        System.out.println(st.isEmpty());

        st.clear();
        System.out.println(st.isEmpty());
        System.out.println(st.size());
    }

    @Test
    void shouldThrowEmptyStackException() {
        Throwable exception = assertThrows(EmptyStackException.class, () -> {
            throw new EmptyStackException();
        });

        st.peek(); // Exception!
        assertNull(exception.getMessage());
    }
}