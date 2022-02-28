package com.interview.notes.code.misc.IKMTest;

public class XyzTest1 {
    private int firstNumber;
    private int secondNumber;

    public XyzTest1(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = -- secondNumber;
    }

    public void display() {
        class Sum {
            public void sum() {
                secondNumber = firstNumber +++ secondNumber;
            }
        }
                Sum d = new Sum();
                d.sum();
                System.out.println("firstNumber = " + firstNumber + ", secondNumber=" + secondNumber);
            }

            public static void main(String[] args) {
                XyzTest1 t = new XyzTest1(1, 2);
                t.display();
                System.out.println("firstNumber = " + t.firstNumber);
                System.out.println("secondNumber = " + t.secondNumber);

            }
 }
