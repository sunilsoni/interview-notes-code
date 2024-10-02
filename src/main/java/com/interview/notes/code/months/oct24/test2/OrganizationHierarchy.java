package com.interview.notes.code.months.oct24.test2;

import java.util.*;

class Contact {
    String SID;
    String Manager_SID;

    Contact(String SID, String Manager_SID) {
        this.SID = SID;
        this.Manager_SID = Manager_SID;
    }
}

public class OrganizationHierarchy {

    public static List<String> getSidsInHierarchy(List<Contact> allOrganizationContacts, String sid) {
        // Step 1: Build a map where each manager SID maps to their direct reports
        Map<String, List<String>> managerToSubordinatesMap = new HashMap<>();
        for (Contact contact : allOrganizationContacts) {
            managerToSubordinatesMap
                .computeIfAbsent(contact.Manager_SID, k -> new ArrayList<>())
                .add(contact.SID);
        }

        // Step 2: Perform DFS to gather all SIDs in the hierarchy
        List<String> sidsInHierarchy = new ArrayList<>();
        dfs(sid, managerToSubordinatesMap, sidsInHierarchy);
        return sidsInHierarchy;
    }

    private static void dfs(String sid, Map<String, List<String>> managerToSubordinatesMap, List<String> sidsInHierarchy) {
        // Add the current SID to the hierarchy
        sidsInHierarchy.add(sid);

        // Get the subordinates of the current SID
        List<String> subordinates = managerToSubordinatesMap.get(sid);

        // If the current SID has subordinates, visit each one recursively
        if (subordinates != null) {
            for (String subordinateSid : subordinates) {
                dfs(subordinateSid, managerToSubordinatesMap, sidsInHierarchy);
            }
        }
    }

    public static void main(String[] args) {
        // Example contacts list
        List<Contact> allOrganizationContacts = Arrays.asList(
            new Contact("g705208", "a705208"),
            new Contact("a705208", null),
            new Contact("d705208", "g705208"),
            new Contact("e705208", "b705208"),
            new Contact("f705208", "b705208"),
            new Contact("c705208", "g705208"),
            new Contact("b705208", "a705208")
        );

        // Test the hierarchy retrieval
        String sid = "a705208";
        List<String> hierarchy = getSidsInHierarchy(allOrganizationContacts, sid);
        System.out.println("Hierarchy under " + sid + ": " + hierarchy);
    }
}
//OUTPUT: Hierarchy under a705208: [a705208, g705208, d705208, c705208, b705208, e705208, f705208]