package com.interview.notes.code.misc.test5;

public class TestOverriding {
    public static void main(String str[]) {
        TestT1 t = new Fest();
        t.tests();
    }
}

class TestT1 {
    void tests() {
        System.out.println("TestT1 class : tests");
    }
}

class Fest extends TestT1 {
	/*static void tests(){
		System.out.println("Fest class : tests");
	}*/
}
 