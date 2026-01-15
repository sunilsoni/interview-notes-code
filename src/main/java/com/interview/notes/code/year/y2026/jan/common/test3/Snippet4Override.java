package com.interview.notes.code.year.y2026.jan.common.test3;

public class Snippet4Override {

    public static void main(String[] args) {

        MyStringBuffer message = new MyStringBuffer("HELLO WORLD");
        MyStringBuffer result = modifyStringBuffer(message);

        if (message == result) {
            System.out.println("1 = " + result);
        }

        if (message.equals(result)) {
            System.out.println("2 = " + result);
        }
    }

    private static MyStringBuffer modifyStringBuffer(MyStringBuffer strBuffer) {
        strBuffer.reverse();
        return strBuffer;
    }
}

class MyStringBuffer {
    private final StringBuffer buffer;

    MyStringBuffer(String value) {
        this.buffer = new StringBuffer(value);
    }

    void reverse() {
        buffer.reverse();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof MyStringBuffer other)) return false;
        return buffer.toString().contentEquals(other.buffer);
    }

    @Override
    public String toString() {
        return buffer.toString();
    }
}
