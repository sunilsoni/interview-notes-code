package com.interview.notes.code.year.y2025.June.amazon.test15;

import java.util.*;
import java.util.stream.*;

// Represents a single action rule under a jurisdiction
class Rule {
    private final String action;            // the action name
    private final boolean requiresConsent;  // whether consent is required

    public Rule(String action, boolean requiresConsent) {
        this.action = action;
        this.requiresConsent = requiresConsent;
    }
    public String getAction() { return action; }
    public boolean isRequiresConsent() { return requiresConsent; }
}

// Holds all rules for a given jurisdiction
class JurisdictionRules {
    private final String jurisdiction;  // e.g. "US-CA"
    private final List<Rule> rules;     // list of action rules

    public JurisdictionRules(String jurisdiction, List<Rule> rules) {
        this.jurisdiction = jurisdiction;
        this.rules = rules;
    }
    public String getJurisdiction() { return jurisdiction; }
    public List<Rule> getRules() { return rules; }
}

public class PrivacyEnforcer {
    // Top-level map: jurisdiction → (action → requiresConsent)
    private final Map<String, Map<String, Boolean>> rulesByJurisdiction;

    /**
     * Build the lookup map from a list of JurisdictionRules.
     */
    public PrivacyEnforcer(List<JurisdictionRules> config) {
        // Stream over each jurisdiction block
        this.rulesByJurisdiction = config.stream()
            // key = jurisdiction code; value = map of its rules
            .collect(Collectors.toMap(
                JurisdictionRules::getJurisdiction,
                jr -> jr.getRules().stream()
                         // for each Rule, map action→requiresConsent
                         .collect(Collectors.toMap(
                             Rule::getAction,
                             Rule::isRequiresConsent
                         ))
            ));
    }

    /**
     * Returns true if 'action' is allowed for a user in 'userLocation'
     * given the set of actions they've consented to.
     */
    public boolean isActionPermitted(String userLocation,
                                     String action,
                                     Set<String> userConsents) {
        // 1) Look up rules for this jurisdiction
        Map<String, Boolean> actionMap = rulesByJurisdiction.get(userLocation);
        if (actionMap == null) {
            // If jurisdiction not found → deny by default
            return false;
        }

        // 2) Look up whether that specific action requires consent
        Boolean requiresConsent = actionMap.get(action);
        if (requiresConsent == null) {
            // If action not listed under this jurisdiction → deny
            return false;
        }

        // 3) If consent is NOT required, allow immediately
        if (!requiresConsent) {
            return true;
        }

        // 4) Otherwise require that the user has explicitly consented
        return userConsents.contains(action);
    }

    /**
     * Simple main() to run test cases and report PASS/FAIL.
     */
    public static void main(String[] args) {
        // --- 1) Build a small sample config manually ---
        List<JurisdictionRules> config = Arrays.asList(
            new JurisdictionRules("US-CA", Arrays.asList(
                new Rule("combine_data", true),
                new Rule("target_ads",   true),
                new Rule("generate_analytics", false)
            )),
            new JurisdictionRules("US-NY", Arrays.asList(
                new Rule("combine_data", false),
                new Rule("target_ads",   true)
            )),
            new JurisdictionRules("DE", Arrays.asList(
                new Rule("combine_data", true),
                new Rule("target_ads",   true),
                new Rule("generate_analytics", true)
            ))
        );

        PrivacyEnforcer enforcer = new PrivacyEnforcer(config);

        // --- 2) Define test cases ---
        class Test {
            String loc, action;
            Set<String> consents;
            boolean expected;
            Test(String loc, String action, Set<String> consents, boolean expected) {
                this.loc = loc;
                this.action = action;
                this.consents = consents;
                this.expected = expected;
            }
        }
        List<Test> tests = Arrays.asList(
            // Consent not required → always allowed
            new Test("US-CA", "generate_analytics", Collections.emptySet(), true),
            // Consent required but provided → allowed
            new Test("US-CA", "combine_data", Set.of("combine_data"), true),
            // Consent required but missing → denied
            new Test("US-CA", "combine_data", Collections.emptySet(), false),
            // Unknown jurisdiction → denied
            new Test("US-XX", "combine_data", Set.of("combine_data"), false),
            // Consent required in DE → with and without
            new Test("DE", "generate_analytics", Set.of("generate_analytics"), true),
            new Test("DE", "generate_analytics", Collections.emptySet(), false),
            // Action not listed under US-NY → denied
            new Test("US-NY", "generate_analytics", Collections.emptySet(), false)
        );

        // --- 3) Execute & report ---
        int passed = 0;
        for (Test t : tests) {
            boolean got = enforcer.isActionPermitted(t.loc, t.action, t.consents);
            boolean ok  = got == t.expected;
            System.out.printf(
                "Test(%s, %s, %s) => expected %b, got %b: %s%n",
                t.loc, t.action, t.consents, t.expected, got, ok ? "PASS" : "FAIL"
            );
            if (ok) passed++;
        }
        System.out.printf("Passed %d/%d tests%n", passed, tests.size());
    }
}