package com.interview.notes.code.datastructure.Stack;

/**
 * Exception to indicate that LinkedList is empty.
 */

class LinkedListEmptyException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public LinkedListEmptyException() {
        super();
    }

    public LinkedListEmptyException(String message) {
        super(message);
    }
}