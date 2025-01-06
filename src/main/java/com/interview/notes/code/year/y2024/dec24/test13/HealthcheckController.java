package com.interview.notes.code.year.y2024.dec24.test13;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.Map;

/*

**Problem Statement:**

You are given a part of a Spring MVC application and your task is to build a new RESTful web controller.

You should implement two variants of reading the `/healthcheck` resource using JSON as the response data format:

**Method to read simple healthcheck:**
A request to `GET /healthcheck?format=short` should return a simple message:
```json
{ "status": "OK" }
```

**Method to read detailed healthcheck:**
A request to `GET /healthcheck?format=full` should return the application status and current time (formatted as an ISO 8601 string):
```json
{ "currentTime": "2018-06-06T21:59:36Z", "status": "OK" }
```

**Other requirements and hints:**
1. Performance is not assessed; focus on correctness and code quality.
2. Your service is expected to handle only the GET method. Other methods should return 405 status.
3. If parameters' values do not match the specified possible values or if no parameter is present, you should return HTTP status code 400 ("Bad Request").
4. Responses should have `Content-Type` header with the appropriate value (`application/json`).
5. If you need to create multiple classes, you can use nested classes or define multiple classes in one source file.
6. You can use only the following libraries (and their dependencies):
   - Spring Web MVC (v5.0.7.RELEASE)
   - FasterXML Jackson, Jackson Datatype JSR310 (v2.9.6)

 */
@RestController
class HealthcheckController {

    @GetMapping(value = "/healthcheck", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> healthcheck(@RequestParam(required = false) String format) {
        if (format == null) {
            return ResponseEntity.badRequest().build();
        }

        Map<String, Object> response = new HashMap<>();

        switch (format.toLowerCase()) {
            case "short":
                response.put("status", "OK");
                return ResponseEntity.ok(response);

            case "full":
                response.put("status", "OK");
                response.put("currentTime", ZonedDateTime.now().toString());
                return ResponseEntity.ok(response);

            default:
                return ResponseEntity.badRequest().build();
        }
    }

    @RequestMapping(value = "/healthcheck", method = {
            RequestMethod.PUT,
            RequestMethod.POST,
            RequestMethod.DELETE
    })
    public ResponseEntity<?> unsupportedMethods() {
        return ResponseEntity.status(405).build();
    }
}
