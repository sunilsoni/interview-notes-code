package com.interview.notes.code.months.year2023.june23.test10;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ArrayFlattener {
    public static void main(String[] args) {
        Object[] array = {1, 2, new Object[]{3, 4, new Object[]{5}, 6, 7}, 8, 9, 10};

        List<Object> flattened = flattenArray(array);
        System.out.println(flattened);
    }

    private static List<Object> flattenArray(Object[] array) {
        return Stream.of(array)
                .flatMap(ArrayFlattener::flatten)
                .collect(Collectors.toList());
    }

    private static Stream<Object> flatten(Object element) {
        if (element instanceof Object[]) {
            Object[] subArray = (Object[]) element;
            return Stream.of(subArray)
                    .flatMap(ArrayFlattener::flatten);
        } else {
            return Stream.of(element);
        }
    }
}
