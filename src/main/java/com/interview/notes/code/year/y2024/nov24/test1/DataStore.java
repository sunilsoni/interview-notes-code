package com.interview.notes.code.year.y2024.nov24.test1;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
At Intuit, we have a Datatore API that allows developers to store simple key/value information for their apps. One common feature that developers frequently request is the ability to update two dependent keys simultaneously, or not at all.
the example, person developer de his cont at for est s
shirt, the developer has to:
1) Transfer the hat from Person A to Person B
2) Transfer the shirt from Person B to Person A
If Person B receives the hat, but Person A does not receive
the shirt, then Person B pwns both the hat and the shirt while Person A owns nothing!
One way we could solve this is by implementing "transactions"
This means when A and B agree to trade, the developer "Begins" the transaction. All operations that take place after the transaction begins are said to exist only inside the transaction. When the transaction is "Committed", all of these operations are added to the main DataStore simultaneously.
If something goes wrong, the developer can "Roll back" the transaction to discard every operation that occurred since the beginning of that transaction.
Your task is to implement a DataStore class with the following methods:
set(key, value) # sets a value to a key
delete(key) # removes the value from the store associated with a given key begin() # begins a transaction commit() # commits a transaction rollback() # rolls a transaction back


• Example 1:

set("userl"
"shirt")
set("user2"
"hat")
get("user1") # shirt

• begin()

get("user1") # shirt

set("userl"
, "pants")
• get("user1") # pants

• delete("user2")


get("user2") # None or Null
• rollback()

get("user1") # shirt

get("user2") # hat

• Example 2: set("userl"
"shirt")
set ("user2"
기
"hat")
get("user1") # shirt
get("user2") # hat
• begin()
set("userl"
"pants")
get("user1") # pants
• delete("user2")
get("user2") # None or Null
commitO)
get("user1") # pants
get("user2") # None or Null
 */
public class DataStore {

    private Map<String, String> globalStore;
    private Stack<Map<String, String>> transactionStack;

    public DataStore() {
        globalStore = new HashMap<>();
        transactionStack = new Stack<>();
    }

    // Main method for testing
    public static void main(String[] args) {
        DataStore dataStore = new DataStore();

        // Example 1:
        System.out.println("Example 1:");
        dataStore.set("user1", "shirt");
        dataStore.set("user2", "hat");
        System.out.println("get('user1'): " + dataStore.get("user1")); // shirt

        dataStore.begin();
        System.out.println("After begin()");
        System.out.println("get('user1'): " + dataStore.get("user1")); // shirt

        dataStore.set("user1", "pants");
        System.out.println("After set('user1', 'pants')");
        System.out.println("get('user1'): " + dataStore.get("user1")); // pants

        dataStore.delete("user2");
        System.out.println("After delete('user2')");
        System.out.println("get('user2'): " + dataStore.get("user2")); // null

        dataStore.rollback();
        System.out.println("After rollback()");
        System.out.println("get('user1'): " + dataStore.get("user1")); // shirt
        System.out.println("get('user2'): " + dataStore.get("user2")); // hat

        // Example 2:
        System.out.println("\nExample 2:");
        dataStore.set("user1", "shirt");
        dataStore.set("user2", "hat");
        System.out.println("get('user1'): " + dataStore.get("user1")); // shirt
        System.out.println("get('user2'): " + dataStore.get("user2")); // hat

        dataStore.begin();
        dataStore.set("user1", "pants");
        System.out.println("After set('user1', 'pants')");
        System.out.println("get('user1'): " + dataStore.get("user1")); // pants

        dataStore.delete("user2");
        System.out.println("After delete('user2')");
        System.out.println("get('user2'): " + dataStore.get("user2")); // null

        dataStore.commit();
        System.out.println("After commit()");
        System.out.println("get('user1'): " + dataStore.get("user1")); // pants
        System.out.println("get('user2'): " + dataStore.get("user2")); // null

        // Edge Case: Commit without transaction
        System.out.println("\nEdge Case: Commit without transaction");
        if (!dataStore.commit()) {
            System.out.println("Commit failed as expected.");
        }

        // Edge Case: Rollback without transaction
        System.out.println("\nEdge Case: Rollback without transaction");
        if (!dataStore.rollback()) {
            System.out.println("Rollback failed as expected.");
        }

        // Large Data Test
        System.out.println("\nLarge Data Test:");
        for (int i = 0; i < 10000; i++) {
            dataStore.set("key" + i, "value" + i);
        }
        System.out.println("Set 10,000 key-value pairs.");

        boolean pass = true;
        for (int i = 0; i < 10000; i++) {
            if (!("value" + i).equals(dataStore.get("key" + i))) {
                pass = false;
                break;
            }
        }
        System.out.println("Large Data Test Pass: " + pass);
    }

    public void set(String key, String value) {
        if (!transactionStack.isEmpty()) {
            transactionStack.peek().put(key, value);
        } else {
            globalStore.put(key, value);
        }
    }

    public String get(String key) {
        if (!transactionStack.isEmpty()) {
            for (int i = transactionStack.size() - 1; i >= 0; i--) {
                Map<String, String> currentTransaction = transactionStack.get(i);
                if (currentTransaction.containsKey(key)) {
                    return currentTransaction.get(key);
                }
            }
        }
        return globalStore.get(key);
    }

    public void delete(String key) {
        if (!transactionStack.isEmpty()) {
            transactionStack.peek().put(key, null);
        } else {
            globalStore.remove(key);
        }
    }

    public void begin() {
        transactionStack.push(new HashMap<>());
    }

    public boolean commit() {
        if (transactionStack.isEmpty()) {
            System.out.println("No active transaction to commit.");
            return false;
        }
        Map<String, String> committedTransaction = transactionStack.pop();
        if (transactionStack.isEmpty()) {
            mergeTransaction(committedTransaction, globalStore);
        } else {
            mergeTransaction(committedTransaction, transactionStack.peek());
        }
        return true;
    }

    public boolean rollback() {
        if (transactionStack.isEmpty()) {
            System.out.println("No active transaction to rollback.");
            return false;
        }
        transactionStack.pop();
        return true;
    }

    private void mergeTransaction(Map<String, String> source, Map<String, String> destination) {
        for (Map.Entry<String, String> entry : source.entrySet()) {
            if (entry.getValue() == null) {
                destination.remove(entry.getKey());
            } else {
                destination.put(entry.getKey(), entry.getValue());
            }
        }
    }
}
