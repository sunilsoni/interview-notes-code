package com.interview.notes.code.year.y2025.august.task;

import java.util.LinkedHashMap;
import java.util.Map;

public class HtmlToJsonCodeClient {

    public static void main(String[] args) throws Exception {

        Map<String, Object> currentResponse = new LinkedHashMap<>();
        currentResponse.put("carrierId", "72375");
        currentResponse.put("planName", "United Healthcare<br/>UHC Bronze Value Plan");
        currentResponse.put("disclaimerText", java.util.List.of(
                "Before enrolling, confirm that your provider accepts the plan.<br><br> Click <a href=\"https://www.healthcare.gov/quality-ratings/\" target=\"_blank\">here</a>"
        ));

        Map<String, Object> planSections = new LinkedHashMap<>();
        planSections.put("Your Total Yearly Cost Estimate",
                Map.of("YOUR TOTAL YEARLY COST ESTIMATE", "<p><b>^¤978</b></p>"));
        currentResponse.put("planSections", planSections);

        currentResponse.put("serviceProvider", "MHBE");
        currentResponse.put("staticFilesVersion", "49.0");

        Map<String, Object> expected = PlanResponseTransformer.transform(currentResponse);

        String expectedOutput = PlanResponseTransformer.toJson(expected);

        System.out.println(expectedOutput);
    }
}
