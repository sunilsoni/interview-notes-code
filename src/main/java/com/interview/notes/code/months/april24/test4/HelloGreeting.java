package com.interview.notes.code.months.april24.test4;

class HelloGreeting implements Greeting {
  @Override
  public String greet(String name) {
    return "Hello, " + name + "!";
  }
}
