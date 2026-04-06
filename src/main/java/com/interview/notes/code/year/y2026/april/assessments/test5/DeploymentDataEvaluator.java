package com.interview.notes.code.year.y2026.april.assessments.test5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class DeploymentDataEvaluator {

    public static List<Integer> evaluate_deployments(List<String> deployments) {
        Pattern p1 = Pattern.compile("^\\{" + "\\s*\"deployment_id\"\\s*:\\s*\"(.*?)\"\\s*,\\s*\"status\"\\s*:\\s*\"(.*?)\"\\s*\\}$");
        Pattern p2 = Pattern.compile("^\\{" + "\\s*\"status\"\\s*:\\s*\"(.*?)\"\\s*,\\s*\"deployment_id\"\\s*:\\s*\"(.*?)\"\\s*\\}$");

        Map<Integer, Long> counts = deployments.stream().map(d -> {
            Matcher m1 = p1.matcher(d.trim());
            Matcher m2 = p2.matcher(d.trim());
            String id = null;
            String st = null;
            
            if (m1.matches()) {
                id = m1.group(1);
                st = m1.group(2);
            } else if (m2.matches()) {
                st = m2.group(1);
                id = m2.group(2);
            }
            
            if (id != null && id.matches("d-[a-z0-9]{10}") && st.matches("Success|Fail")) {
                return st.equals("Success") ? 0 : 1;
            }
            return 2;
        }).collect(Collectors.groupingBy(i -> i, Collectors.counting()));

        return Arrays.asList(
                counts.getOrDefault(0, 0L).intValue(),
                counts.getOrDefault(1, 0L).intValue(),
                counts.getOrDefault(2, 0L).intValue()
        );
    }

    public static void main(String[] args) {
        List<String> test1 = Arrays.asList(
                "{\"deployment_id\": \"d-12345678ab\", \"status\": \"Success\"}",
                "{\"deployment_id\": \"d-09876543cd\", \"status\": \"Fail\"}"
        );
        System.out.println(evaluate_deployments(test1).equals(Arrays.asList(1, 1, 0)) ? "PASS: Test 1" : "FAIL: Test 1");

        List<String> test2 = Arrays.asList(
                "{\"deployment_id\": \"d-12345678ab\", \"status\": \"Success\"}",
                "{\"deployment_id\": \"d-12345678cd\", \"status\": \"ABCDE\"}"
        );
        System.out.println(evaluate_deployments(test2).equals(Arrays.asList(1, 0, 1)) ? "PASS: Test 2" : "FAIL: Test 2");

        List<String> test3 = Arrays.asList(
                "{\"status\": \"Success\", \"deployment_id\": \"d-12345678ab\"}",
                "{\"deployment_id\": \"d-short\", \"status\": \"Fail\"}",
                "invalid_json_string"
        );
        System.out.println(evaluate_deployments(test3).equals(Arrays.asList(1, 0, 2)) ? "PASS: Test 3" : "FAIL: Test 3");

        List<String> largeTest = new ArrayList<>();
        IntStream.range(0, 500).forEach(i -> largeTest.add("{\"deployment_id\": \"d-12345678ab\", \"status\": \"Success\"}"));
        IntStream.range(0, 300).forEach(i -> largeTest.add("{\"deployment_id\": \"d-09876543cd\", \"status\": \"Fail\"}"));
        IntStream.range(0, 200).forEach(i -> largeTest.add("{\"deployment_id\": \"invalid_id\", \"status\": \"Fail\"}"));
        System.out.println(evaluate_deployments(largeTest).equals(Arrays.asList(500, 300, 200)) ? "PASS: Large Data Test" : "FAIL: Large Data Test");
    }
}