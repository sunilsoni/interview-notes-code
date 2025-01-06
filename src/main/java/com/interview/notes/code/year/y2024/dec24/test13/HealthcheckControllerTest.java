package com.interview.notes.code.year.y2024.dec24.test13;

import org.springframework.http.ResponseEntity;

import java.util.Map;

public class HealthcheckControllerTest {

    private static final HealthcheckController controller = new HealthcheckController();

    public static void main(String[] args) {
        // Setup mock request context
        //MockHttpServletRequest request = new MockHttpServletRequest();
        //RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        // Run all tests
        testShortFormat();
        testFullFormat();
        testInvalidFormat();
        testMissingFormat();
        testUnsupportedMethods();
    }

    private static void testShortFormat() {
        ResponseEntity<?> response = controller.healthcheck("short");
        Map<String, Object> body = (Map<String, Object>) response.getBody();

        boolean passed = response.getStatusCodeValue() == 200
                && body != null
                && "OK".equals(body.get("status"))
                && body.size() == 1;

        System.out.println("Short Format Test: " + (passed ? "PASS" : "FAIL"));
    }

    private static void testFullFormat() {
        ResponseEntity<?> response = controller.healthcheck("full");
        Map<String, Object> body = (Map<String, Object>) response.getBody();

        boolean passed = response.getStatusCodeValue() == 200
                && body != null
                && "OK".equals(body.get("status"))
                && body.get("currentTime") != null
                && body.size() == 2;

        System.out.println("Full Format Test: " + (passed ? "PASS" : "FAIL"));
    }

    private static void testInvalidFormat() {
        ResponseEntity<?> response = controller.healthcheck("invalid");
        boolean passed = response.getStatusCodeValue() == 400;
        System.out.println("Invalid Format Test: " + (passed ? "PASS" : "FAIL"));
    }

    private static void testMissingFormat() {
        ResponseEntity<?> response = controller.healthcheck(null);
        boolean passed = response.getStatusCodeValue() == 400;
        System.out.println("Missing Format Test: " + (passed ? "PASS" : "FAIL"));
    }

    private static void testUnsupportedMethods() {
        ResponseEntity<?> response = controller.unsupportedMethods();
        boolean passed = response.getStatusCodeValue() == 405;
        System.out.println("Unsupported Methods Test: " + (passed ? "PASS" : "FAIL"));
    }
}
