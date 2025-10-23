package com.interview.notes.code.year.y2025.october.jpmc.test3;

import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public class URLParser {
    private static String parseURL(String url) {
        String protocol = "", domain = "", query = "";
        int protoEnd = url.indexOf("://");
        if (protoEnd != -1) {
            protocol = url.substring(0, protoEnd);
            url = url.substring(protoEnd + 3);
        }
        int queryStart = url.indexOf("?");
        if (queryStart != -1) {
            query = url.substring(queryStart + 1);
            url = url.substring(0, queryStart);
        }
        int slash = url.indexOf("/");
        domain = slash == -1 ? url : url.substring(0, slash);
        return String.join(",", protocol, domain, query);
    }

    public static void main(String[] args) {
        List<String> inputs = Arrays.asList(
            "https://example.com/path?query=123",
            "ftp://ftp.example.org/files",
            "http://google.com",
            "https://openai.com/docs?lang=en&v=5",
            "invalidurl"
        );
        List<String> expected = Arrays.asList(
            "https,example.com,query=123",
            "ftp,ftp.example.org,",
            "http,google.com,",
            "https,openai.com,lang=en&v=5",
            ",invalidurl,"
        );
        IntStream.range(0, inputs.size()).forEach(i -> {
            String result = parseURL(inputs.get(i));
            String status = result.equals(expected.get(i)) ? "PASS" : "FAIL";
            System.out.println("Input=" + inputs.get(i) + " | Output=" + result + " | Expected=" + expected.get(i) + " | Result=" + status);
        });
        String largeInput = "https://domain.com/path?param=" + "a".repeat(1000000);
        long start = System.currentTimeMillis();
        parseURL(largeInput);
        System.out.println("Large data test executed in " + (System.currentTimeMillis() - start) + " ms");
    }
}