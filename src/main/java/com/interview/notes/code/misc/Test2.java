package com.interview.notes.code.misc;

import java.io.ByteArrayOutputStream;
import java.io.IOException;


public class Test2 {
    public static void main(String[] args) throws IOException {

        // ThreadLocal
        //List<Number> list = new LinkedList<>();
//list.removeIf(x ->x==1);
        // list.add((new BigDecimal((new Byte[]{0*1})));
        //list.add((1));
//list.add("12f".transform(Integer::parseInt));


//        List<String> list = Arrays.asList("dog", "over", "good");
//
//        System.out.print(list.stream().reduce(new String(), (x1, x2) -> {
//            if (x1.equals("dog")) return x1;
//            return x2;
//        }));

//        System.out.print(list.stream().reduce(new Character(),(Character x1,x2) -> x1+x2.charAt
//            if(x1.equals("dog")) return x1;return x2;}));

        // list.stream().reduce((x1,x2) ->  x1.length() ==3?(s1,s2).ifPresent
        // System.out.println(list.stream().reduce(new String(),(s1,s2) -> s1+s2.charAt(0),(c1,c2)) -> c1+=c2));
        //Object n Emp param


        byte c1[] = {10, 20, 30, 40, 50};
        byte c2[] = {10, 20, 30, 40, 50};

//        ByteArrayOutputStream b1=new ByteArrayOutputStream();
//        ByteArrayOutputStream b2=new ByteArrayOutputStream(10);
//
//        b2.write(100);;
//        System.out.println("out 1 : "+b2.size());


        ByteArrayOutputStream b1 = new ByteArrayOutputStream();
        ByteArrayOutputStream b2 = new ByteArrayOutputStream(10);
        b2.write(100);
        System.out.println("Out 1 : " + b2.size());
        b2.write(c1, 0, c2.length);
        System.out.println("Out 2 : " + b2.size());
        byte b[] = b2.toByteArray();
        System.out.println("Out 3 : " + b.length);
        b2.flush();
        System.out.println("Out 4 : " + b2.size());
        b2.reset();
        System.out.println("Out 5 : " + b2.size());
        b1.writeTo(b2);
        System.out.println("Out 6 : " + b1.size());


    }
}

