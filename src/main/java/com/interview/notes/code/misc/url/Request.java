package com.interview.notes.code.misc.url;

import java.lang.reflect.Method;
public class Request implements GetInterface {
    public static final String BASEURL = "http://www.foo.com";
    @Override
    public void getUser(String id) {
        String baseUrl = BASEURL;
        try {
            //getting the path
            Method m = GetInterface.class.getMethod("getUser", String.class);
            Get getAnnotation = m.getAnnotation(Get.class);
            String path = getAnnotation.value();

            //getting the method parameter attribute
            Field fieldAnnotation = m.getParameters()[0].getAnnotation(Field.class);
            String fieldName =  fieldAnnotation.value();

            //Construct the final url
            String endpoint = baseUrl + path + "?" + fieldName + "=" + id;
            System.out.println(endpoint);
            System.out.println(" --------------------------- ");
        } catch (Exception e) {
            System.out.println("getUser " + e);
        }

    }

    @Override
    public void getBook(String isbn) {
        String baseUrl = BASEURL;
        try {
            //getting the path
            Method m = GetInterface.class.getMethod("getBook", String.class);
            Get getAnnotation = m.getAnnotation(Get.class);
            String path = getAnnotation.value();

            //getting the method parameter attribute
            Field fieldAnnotation = m.getParameters()[0].getAnnotation(Field.class);
            String fieldName =  fieldAnnotation.value();

            //Construct the final url
            String endpoint = baseUrl + path + "?" + fieldName + "=" + isbn;
            System.out.println(endpoint);
            System.out.println(" --------------------------- ");
        } catch (Exception e) {
            System.out.println("getUser " + e);
        }
    }

    private static void printAnnotationValues() {
        String baseUrl = BASEURL;
        String endpoint = "";
        Class getInterface = GetInterface.class;
        for (Method method : getInterface.getMethods()) {
            endpoint = "";
            Get getAnnotation = (Get) method.getAnnotation(Get.class);
            if (getAnnotation != null) {
                System.out.println(" Method Name : " + method.getName());
                System.out.println(" Get : " + getAnnotation.value());
                endpoint = baseUrl + getAnnotation.value();
            }

            Field fieldAnnotation = (Field) method.getParameters()[0].getAnnotation(Field.class);
            if (fieldAnnotation != null) {
                System.out.println(" Field : " + fieldAnnotation.value());
                endpoint = baseUrl + getAnnotation.value() + "?" + fieldAnnotation.value() + "=";
            }
            System.out.println(" endpoint : " + endpoint);
            System.out.println(" --------------------------- ");
        }        
    }

    public static void main(String[] args) {
        printAnnotationValues();
        Request request = new Request();
        request.getUser("111");
        request.getBook("789");
    }
}