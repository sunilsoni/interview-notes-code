package com.interview.notes.code.year.y2026.april.assessments.test8;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DeploymentHealthAnalyzer {
    private static final Pattern ID = Pattern.compile("d-[a-z0-9]{10}");
    private static final Pattern PAIR = Pattern.compile("\\s*\"([^\"]+)\"\\s*:\\s*\"([^\"]*)\"\\s*(,|$)");

    public static List<Integer> evaluate_deployments(List<String> deployments) {
        int success = 0, fail = 0, error = 0;
        for (String s : deployments) {
            Map<String, String> m = parseObject(s);
            String id = m == null ? null : m.get("deployment_id");
            String status = m == null ? null : m.get("status");
            if (m == null || id == null || status == null || !ID.matcher(id).matches()) {
                error++;
            } else if ("Success".equals(status)) {
                success++;
            } else if ("Fail".equals(status)) {
                fail++;
            } else {
                error++;
            }
        }
        return Arrays.asList(success, fail, error);
    }

    private static Map<String, String> parseObject(String s) {
        if (s == null) {
            return null;
        }
        s = s.trim();
        if (s.length() < 2 || s.charAt(0) != '{' || s.charAt(s.length() - 1) != '}') {
            return null;
        }
        String body = s.substring(1, s.length() - 1).trim();
        Map<String, String> map = new HashMap<>();
        if (body.isEmpty()) {
            return map;
        }
        int i = 0;
        while (i < body.length()) {
            Matcher m = PAIR.matcher(body);
            m.region(i, body.length());
            if (!m.lookingAt() || map.put(m.group(1), m.group(2)) != null) {
                return null;
            }
            i = m.end();
        }
        return map;
    }

    private static String json(String id, String status) {
        return "{\"deployment_id\":\"" + id + "\",\"status\":\"" + status + "\"}";
    }

    private static void test(int n, List<String> in, List<Integer> exp) {
        List<Integer> got = evaluate_deployments(in);
        System.out.println("Case " + n + ": " + (got.equals(exp) ? "PASS" : "FAIL") + " | got=" + got + " expected=" + exp);
    }

    public static void main(String[] args) {
        test(0,
                Arrays.asList(
                        json("d-12345678ab", "Success"),
                        json("d-09876543cd", "Fail")
                ),
                Arrays.asList(1, 1, 0)
        );

        test(1,
                Arrays.asList(
                        json("d-12345678ab", "Success"),
                        json("d-12345678cd", "ABCDE")
                ),
                Arrays.asList(1, 0, 1)
        );

        test(2,
                Arrays.asList(
                        json("d-12345678ab", "Success"),
                        json("d-12345ABCcd", "Fail"),
                        json("d-aaaaaaaaaa", "Fail")
                ),
                Arrays.asList(1, 1, 1)
        );

        test(3,
                Arrays.asList(
                        "{\"status\":\"Success\",\"deployment_id\":\"d-12345678ab\"}",
                        "{\"deployment_id\":\"d-09876543cd\",\"status\":\"Fail\",\"x\":\"1\"}"
                ),
                Arrays.asList(1, 1, 0)
        );

        test(4,
                Arrays.asList(
                        "{\"deployment_id\":\"d-12345678ab\",\"status\":\"Success\"",
                        "{\"deployment_id\":\"d-09876543cd\"}",
                        "{}"
                ),
                Arrays.asList(0, 0, 3)
        );

        List<String> large = IntStream.range(0, 1000)
                .mapToObj(i -> i % 3 == 0
                        ? json("d-" + String.format("%010d", i), "Success")
                        : i % 3 == 1
                        ? json("d-" + String.format("%010d", i), "Fail")
                        : json("d-" + String.format("%010d", i), "Bad"))
                .collect(Collectors.toList());

        test(5, large, Arrays.asList(334, 333, 333));
    }
}