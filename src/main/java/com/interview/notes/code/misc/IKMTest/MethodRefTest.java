package com.interview.notes.code.misc.IKMTest;

import java.util.Arrays;
import java.util.List;

class Name {
    private String first, last;

    Name(String f, String l) {
        first = f;
        last = l;
    }

    Name() {
    }

    @Override
    public String toString() {
        return first + " " + last;
    }

    public static int compareByLastName(Name nl, Name n2) {
        return nl.last.compareTo(n2.last);
    }

}



public class MethodRefTest {
    public static void main(String[] args) {
        Name n1 = new Name("Harry", "Homeowner");
        Name n2 = new Name("Paul", "Painter");
        Name n3 = new Name("Jane", "Doe");
        List<Name> nameList = Arrays.asList(n1, n2, n3);

        //   nameList.sort(Name.compareByLastName); nameList.forEach(System.out.println);
        // nameList.sort(Name.compareByLastName()); nameList.forEach(System.out.printin());
        //   nameList.sort(n1 :: compareByLastName); nameList.forEach(System.out::println);
        nameList.sort(Name::compareByLastName); nameList.forEach(System.out::println);
        //  nameList.sort(Name::compareBylastName()); nameList.forEach(System.out::pr=ltln());
    }
}
