package com.interview.notes.code.year.y2025.march.common.test20;

public class MenuParserTest {
    public static void main(String[] args) {
        // Initialize your provided MenuStream implementation
        MenuStream stream = new MenuStreamImpl();

        // Instantiate and parse stream using Menu class
        Menu menu = new Menu();
        menu.parseStream(stream);

        // Perform basic validation of the parsed result
        String reconstructedMenu = menu.reconstruct();

        // Simple pass/fail check for essential cases
        boolean passTest =
                reconstructedMenu.contains("Spaghetti") &&
                        reconstructedMenu.contains("CATEGORY") &&
                        reconstructedMenu.contains("Pasta") &&
                        reconstructedMenu.contains("Meatballs") &&
                        reconstructedMenu.contains("Chicken") &&
                        reconstructedMenu.contains("Lasagna") &&
                        reconstructedMenu.contains("Caesar Salad");

        System.out.println("Basic Parsing Test: " + (passTest ? "PASS" : "FAIL"));

        // Validate the exact count of parsed items (should be 6 based on provided data)
        boolean correctCount = menu.items.size() == 6;
        System.out.println("Parsed Item Count Test: " + (correctCount ? "PASS" : "FAIL"));

        // Large input data handling test
        largeDataTest();
    }

    private static void largeDataTest() {
        Menu largeMenu = new Menu();

        // Test large input (simulate large number of items)
        MenuStream largeStream = new MenuStream() {
            final int totalItems = 500000; // 500k items (manageable for simple testing)
            int currentLine = 0;

            public String nextLine() {
                if (currentLine >= totalItems * 5) return null;
                int itemNum = currentLine / 5;
                int position = currentLine % 5;
                currentLine++;

                switch (position) {
                    case 0:
                        return Integer.toString(itemNum);
                    case 1:
                        return "OPTION";
                    case 2:
                        return "LargeItem" + itemNum;
                    case 3:
                        return "0.99";
                    case 4:
                        return "";
                    default:
                        return null;
                }
            }
        };

        // Measure performance
        long startTime = System.currentTimeMillis();
        largeMenu.parseStream(largeStream);
        long duration = System.currentTimeMillis() - startTime;

        boolean passLargeTest = largeMenu.items.size() == 500000;
        System.out.println("Large Data Parsing Test: " + (passLargeTest ? "PASS" : "FAIL"));
        System.out.println("Large Data Parsing Time: " + duration + " ms");
    }
}
