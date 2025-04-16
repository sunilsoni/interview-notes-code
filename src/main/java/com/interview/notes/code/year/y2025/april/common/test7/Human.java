package com.interview.notes.code.year.y2025.april.common.test7;


//Package A
class Main {
    public static void main(String[] args) {
        India ind = new India();
        // ind.str1="";
        ind.str2 = "str2";
        ind.str3 = "str3";
        ind.str4 = "str4";

        // System.out.println("str1: "+str1);
        //  System.out.println("str2: "+str2);
        // System.out.println("str3: "+str3);
        // System.out.println("str4: "+str4);

    }
}


//Package B
class Human {
    public String str2;
    protected String str3;
    String str4;
    private String str1;

    public String add() {
        return "123";
    }
}

//Package C
class India extends Human {
    public String add() {
        return "234";
    }
}
