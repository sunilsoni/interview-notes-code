package com.interview.notes.code.misc.test4;

import com.interview.notes.code.datastructure.Map.ConcurrentHashMap.ConcurrentHashMap1;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;

class Test {
    public static void main(String args[]) {
        int x = 2;
        System.out.println(x);//2
        update(x);
        System.out.println(x);//3

       // HashMap  map=new ;
    }

    private static void update(int x) {
        x = 3;
        System.out.println(x);//2
    }
}

@Service
class Test1 {
    public static void main(String args[]) {

        String word = "SreejaSrinivas";
        Map<String, Long> charCount = word.codePoints().mapToObj(Character::toString)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(charCount);

        System.out.println('a'+'c');
        List<Integer> list1 = new ArrayList<Integer>();
        list1.add(10);
        list1.add(20);
        System.out.println(list1.size());
        update(list1);
        System.out.println(list1.size());
    }

    private static void update(List<Integer> list1) {
        list1.add(30);
        System.out.println(list1.size());
    }
}
