package com.interview.notes.code.year.y2026.april.assessments.test1;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class UrlBuilder {
    private String scheme = "http";
    private String host = "";
    private Integer port = null;
    private String path = "";
    private Map<String, String> params = new HashMap<>();

    public static void main(String[] args) {
        String res1 = new UrlBuilder()
                .port(8080)
                .host("codility.com")
                .https()
                .build();
        check("Test 1", "https://codility.com:8080".equals(res1));

        Map<String, String> map1 = new HashMap<>();
        map1.put("key1", "value1");
        map1.put("key2", "value2");
        String res2 = new UrlBuilder()
                .host("codility.com")
                .path("/test/hello/world")
                .queryParams(map1)
                .build();
        boolean pass2 = "http://codility.com/test/hello/world?key1=value1&key2=value2".equals(res2) ||
                        "http://codility.com/test/hello/world?key2=value2&key1=value1".equals(res2);
        check("Test 2", pass2);

        Map<String, String> map2 = new HashMap<>();
        map2.put("single", "val");
        String res3 = new UrlBuilder()
                .host("test.com")
                .queryParams(map2)
                .build();
        check("Test 3", "http://test.com?single=val".equals(res3));

        Map<String, String> largeMap = new HashMap<>();
        for (int i = 0; i < 100000; i++) {
            largeMap.put("k" + i, "v" + i);
        }
        String res4 = new UrlBuilder()
                .https()
                .host("large.com")
                .queryParams(largeMap)
                .build();
        check("Large Data Test", res4.startsWith("https://large.com?") && res4.contains("k99999=v99999"));
    }

    private static void check(String name, boolean passed) {
        System.out.println(name + ": " + (passed ? "PASS" : "FAIL"));
    }

    public UrlBuilder https() {
        this.scheme = "https";
        return this;
    }

    public UrlBuilder host(String host) {
        this.host = host;
        return this;
    }

    public UrlBuilder port(int port) {
        this.port = port;
        return this;
    }

    public UrlBuilder path(String path) {
        this.path = path;
        return this;
    }

    public UrlBuilder queryParams(Map<String, String> params) {
        this.params = params != null ? params : new HashMap<>();
        return this;
    }

    public String build() {
        String url = scheme + "://" + host + (port != null ? ":" + port : "") + path;
        if (!params.isEmpty()) {
            url += "?" + params.entrySet().stream()
                    .map(e -> e.getKey() + "=" + e.getValue())
                    .collect(Collectors.joining("&"));
        }
        return url;
    }
}