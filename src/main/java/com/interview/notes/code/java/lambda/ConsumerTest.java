package com.interview.notes.code.java.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class ConsumerTest {

    public static void main(String[] args) {
        Consumer<Employee> printOnConsole = (e -> System.out.print(e));
        Consumer<Employee> storeInDB = (e -> DaoUtil.save(e));

        List<Employee> empList = new ArrayList();
        forEach(empList, printOnConsole);
        forEach(empList, storeInDB);
        forEach(empList, printOnConsole.andThen(storeInDB));
    }

    static <T> void forEach(List<T> list, Consumer<T> consumer) {
        int nullCount = 0;
        for (T t : list) {
            if (t != null) {
                consumer.accept(t);
            } else {
                nullCount++;
            }
        }
        System.out.printf("%d null entries found in the list.\n", nullCount);
    }
}