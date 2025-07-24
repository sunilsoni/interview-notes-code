package com.interview.notes.code.year.y2025.july.google.test5;

import java.util.*;

class Node {
    String name;        
    Long start;         
    Long end;          
    List<Node> children = new ArrayList<>();  

    Node(String name, Long start, Long end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }
}

class FunctionRangeFlattener {
    
    static class Range {
        String name;
        Long start;
        Long end;
        
        Range(String name, Long start, Long end) {
            this.name = name;
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return String.format("%s(): %d-%d", name, start, end);
        }
    }

    public static List<Range> flattenRanges(Node root) {
        List<Range> result = new ArrayList<>();
        
        // If node has children, split its range
        if (!root.children.isEmpty()) {
            for (Node child : root.children) {
                // Add parent's range before child
                if (root.start < child.start) {
                    result.add(new Range(root.name, root.start, child.start));
                }
                
                // Add child's range
                result.add(new Range(child.name, child.start, child.end));
                
                // Add parent's range after child
                if (child.end < root.end) {
                    result.add(new Range(root.name, child.end, root.end));
                }
            }
        } else {
            // If no children, add the full range
            result.add(new Range(root.name, root.start, root.end));
        }
        
        return result;
    }

    public static void main(String[] args) {
        // Test Case: foo(1-100) containing bar(25-75)
        Node foo = new Node("foo", 1L, 100L);
        Node bar = new Node("bar", 25L, 75L);
        foo.children.add(bar);
        
        System.out.println("Input structure:");
        System.out.println("foo(): 1-100");
        System.out.println("  bar(): 25-75 (nested inside foo)");
        
        List<Range> result = flattenRanges(foo);
        
        System.out.println("\nFlattened output:");
        for (Range r : result) {
            System.out.println(r);
        }
    }
}
