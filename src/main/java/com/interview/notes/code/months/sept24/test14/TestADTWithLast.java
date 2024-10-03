package com.interview.notes.code.months.sept24.test14;

public class TestADTWithLast {
    public static void main(String[] args) {
        AbstractDataTypeWithLast<String, Integer> data = new AbstractDataTypeWithLast<>();

        // Step 1: Put "a"
        data.put("a", 1);
        assert data.last().equals("a");  // "a" is the last accessed

        // Step 2: Put "b"
        data.put("b", 2);
        assert data.last().equals("b");  // "b" is the last accessed

        // Step 3: Put "a" again (overwrite)
        data.put("a", 3);
        assert data.last().equals("a");  // "a" is the last accessed

        // Step 4: Put "c"
        data.put("c", 4);
        assert data.last().equals("c");  // "c" is the last accessed

        // Step 5: Remove "c"
        data.remove("c");
        assert data.last().equals("a");  // "a" is now the last accessed after "c" is removed

        // Step 6: Get the previous most recent key
        String previousLast = data.previousLast();
        System.out.println("Previous last key: " + previousLast);  // Should print: "b"
        assert previousLast.equals("b");
    }
}
