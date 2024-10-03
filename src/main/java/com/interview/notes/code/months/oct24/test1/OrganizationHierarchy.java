package com.interview.notes.code.months.oct24.test1;

import java.util.*;

/*


 Inputs:  List<Contact> allOrganizationContacts - a list of all Contacts at the Organization
      String sid - the SID of the Contact that we want to print the org hierarchy of

 Outputs: List<String> sidsInHierarchy = ['a705208', 'b705208' ..., 'g705208'] - list of the SIDs of the Contacts in the org hierarchy
      of the passed in sid

 Assumptions: Every link exists, there are no Contacts who have a Manager_SID that doesn't also exist as a Contact
          SID is a unique identifier that each contact at the organization is assigned

 Example contact
 Contact: {
  SID: va05208,
  Manager_SID: v704299
 }

  Given an SID and a list of every Contact at the organization:
 Output a list of all the SIDs of the Contacts that are under the given SID. Also include the given SID in the output list
public List<String> getSidsInHierarchy(List<Contact> allOrganizationContacts, String sid) {
    List<String> sidsInHierarchy = new List<String>();

    // complete the function here

    return sidsInHierarchy;
}

// example of allOrganizationContacts
allOrganizationContacts = [
    Contact: {
        SID: g705208,
        Manager_SID: a705208
    },
    Contact: {
        SID: a705208,
        Manager_SID: null
    },
    Contact: {
        SID: d705208,
        Manager_SID: g705208
    },
    Contact: {
        SID: e705208,
        Manager_SID: b705208
    },
    Contact: {
        SID: f705208,
        Manager_SID: b705208
    },
    Contact: {
        SID: c705208,
        Manager_SID: g705208
    },
    Contact: {
        SID: b705208,
        Manager_SID: a705208
    }
]
 */

class Contact {
    String SID;
    String Manager_SID;

    public Contact(String SID, String Manager_SID) {
        this.SID = SID;
        this.Manager_SID = Manager_SID;
    }
}

public class OrganizationHierarchy {

    public static List<String> getSidsInHierarchy(List<Contact> allOrganizationContacts, String sid) {
        List<String> sidsInHierarchy = new ArrayList<>();

        // Step 1: Build a manager-to-subordinates map
        Map<String, List<String>> managerToSubordinates = new HashMap<>();

        for (Contact contact : allOrganizationContacts) {
            if (contact.Manager_SID != null) {
                managerToSubordinates
                        .computeIfAbsent(contact.Manager_SID, k -> new ArrayList<>())
                        .add(contact.SID);
            }
        }

        // Step 2: Perform DFS to gather all SIDs in the hierarchy
        Deque<String> stack = new ArrayDeque<>();
        stack.push(sid);

        while (!stack.isEmpty()) {
            String currentSid = stack.pop();
            sidsInHierarchy.add(currentSid);

            // Get the subordinates of the current SID and add them to the stack
            List<String> subordinates = managerToSubordinates.get(currentSid);
            if (subordinates != null) {
                for (String subordinate : subordinates) {
                    stack.push(subordinate);
                }
            }
        }

        return sidsInHierarchy;
    }

    public static void main(String[] args) {
        List<Contact> allOrganizationContacts = Arrays.asList(
                new Contact("g705208", "a705208"),
                new Contact("a705208", null),
                new Contact("d705208", "g705208"),
                new Contact("e705208", "b705208"),
                new Contact("f705208", "b705208"),
                new Contact("c705208", "g705208"),
                new Contact("b705208", "a705208")
        );

        // Test case 1: SID "a705208"
        List<String> result1 = getSidsInHierarchy(allOrganizationContacts, "a705208");
        System.out.println(result1);  // Expected: ["a705208", "g705208", "b705208", "d705208", "c705208", "e705208", "f705208"]

        // Test case 2: SID "g705208"
        List<String> result2 = getSidsInHierarchy(allOrganizationContacts, "g705208");
        System.out.println(result2);  // Expected: ["g705208", "d705208", "c705208"]

        // Test case 3: SID "b705208"
        List<String> result3 = getSidsInHierarchy(allOrganizationContacts, "b705208");
        System.out.println(result3);  // Expected: ["b705208", "e705208", "f705208"]
    }
}
