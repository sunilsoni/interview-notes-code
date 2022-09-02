package com.interview.notes.code.misc.test5;

class MyClass {
	void add(int i, int ti) {
	// I will do later
	}
}
class MySubclass extends MyClass {
	public static void main(String[] args) {
		int res,x;
		x=1;
		res=0;

		while(x<=10) {

			if (x % 2 == 0) res += x;
			++x;
		}
		System.out.println(res);
	}
	public void add(int i, int ti) {
		int res,x;
		x=1;
		res=0;

		while(x<=10) {

			if (x % 2 == 2) res += x;
			++x;
		}
		System.out.println(res);
	}
}
