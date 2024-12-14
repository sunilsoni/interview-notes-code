package com.interview.notes.code.months.dec24.test6;

public interface CanHop {
}

class Frog implements CanHop {
}

class TurtleFrog extends Frog {
}

class BrazilianHornedFrog extends Frog {
}

class Main {
    public static void main(String[] args) {
        // Replace the blank with the correct types to test the code
        CanHop frog1 = new TurtleFrog();         // Valid, because TurtleFrog indirectly implements CanHop
        Frog frog2 = new TurtleFrog();          // Valid, because TurtleFrog extends Frog
        TurtleFrog frog3 = new TurtleFrog();    // Valid, because the object matches the type
        Object frog4 = new TurtleFrog();        // Valid, because all classes extend Object
        // BrazilianHornedFrog frog5 = new TurtleFrog(); // Invalid, unrelated in the hierarchy
        // Long frog6 = new TurtleFrog();              // Invalid, unrelated type
    }
}
