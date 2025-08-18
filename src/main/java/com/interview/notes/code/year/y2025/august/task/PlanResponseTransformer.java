package com.interview.notes.code.year.y2025.august.task;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.UUID;

public class PlanResponseTransformer {

    private static final ObjectMapper mapper = new ObjectMapper();

    /**
     * Escapes HTML so that <, >, &, quotes become \u003c, \u003e, \u0026, etc.
     */
    private static String escapeHtml(String html) {
        if (html == null) return null;
        return html.replace("<", "\\u003c")
                .replace(">", "\\u003e")
                .replace("&", "\\u0026")
                .replace("\"", "\\u0022")
                .replace("'", "\\u0027");
    }

    /**
     * Recursively escape all HTML string values inside a nested map.
     */
    @SuppressWarnings("unchecked")
    private static Map<String, Object> escapeAllValues(Map<String, Object> input) {

        Map<String, Object> escaped = new LinkedHashMap<>();

        input.forEach((key, value) -> {
            if (value instanceof String) {
                escaped.put(key, escapeHtml((String) value));
            } else if (value instanceof Map) {
                escaped.put(key, escapeAllValues((Map<String, Object>) value));
            } else if (value instanceof Iterable) {

                // handle arrays/lists
                var list = new java.util.ArrayList<>();
                for (Object v : (Iterable<?>) value) {
                    list.add(v instanceof String ? escapeHtml((String) v) : v);
                }
                escaped.put(key, list);
            } else {
                escaped.put(key, value);
            }
        });
        return escaped;
    }

    /**
     * Take input as map of current API response and then covert it into escapes HTML content of expected map
     */
    public static Map<String, Object> transform(Map<String, Object> currentResponse) {
        Map<String, Object> serviceModel = new LinkedHashMap<>();

        Map<String, Object> escaped = escapeAllValues(currentResponse);

        escaped.put("carrierId", "90296");

        Map<String, Object> clientState = new LinkedHashMap<>();
        clientState.put("data", "");
        clientState.put("lastIncrement", 0);
        clientState.put("nonce", UUID.randomUUID().toString());
        clientState.put("ts", System.currentTimeMillis());
        escaped.put("clientState", clientState);

        serviceModel.put("serviceModel", escaped);

        return serviceModel;
    }

    /**
     * This method will take map as input with all required details and covert it into JSON String
     */
    public static String toJson(Map<String, Object> serviceModel) {
        try {
            return mapper.writeValueAsString(serviceModel);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error converting to JSON", e);
        }
    }


}