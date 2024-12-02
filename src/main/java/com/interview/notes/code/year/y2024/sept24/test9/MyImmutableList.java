package com.interview.notes.code.year.y2024.sept24.test9;

public final class MyImmutableList<T> implements CustomImmutableList<T> {
    private final T[] elements;

    @SuppressWarnings("unchecked")
    public MyImmutableList() {
        this.elements = (T[]) new Object[0];
    }

    private MyImmutableList(T[] elements) {
        this.elements = elements;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= elements.length) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + elements.length);
        }
        return elements[index];
    }

    @Override
    public int size() {
        return elements.length;
    }

    @Override
    public CustomImmutableList<T> add(T element) {
        @SuppressWarnings("unchecked")
        T[] newElements = (T[]) new Object[elements.length + 1];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        newElements[elements.length] = element;
        return new MyImmutableList<>(newElements);
    }
}
