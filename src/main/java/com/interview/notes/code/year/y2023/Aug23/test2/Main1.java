package com.interview.notes.code.year.y2023.Aug23.test2;


/**
 * In Java first no has 3 digits 123 so need to return next biggest int no which has all 3 no
 * <p>
 * 132  immediate biggest number
 */
class Main1 {
    public static int methodCall(int num) {
        // code goes here
        return num;
    }

    public static void main(String[] args) {
        // keep this function call here 
        System.out.println(methodCall(123));// Output should be which is immediate biggest number: // 132
        System.out.println(methodCall(12453)); //12534
        System.out.println(methodCall(11121)); //11211 
        System.out.println(methodCall(41352)); //41523 }
    }
}



