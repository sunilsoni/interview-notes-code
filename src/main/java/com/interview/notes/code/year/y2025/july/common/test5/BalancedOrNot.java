package com.interview.notes.code.year.y2025.july.common.test5;

import java.util.*;
import java.util.stream.*;

public class BalancedOrNot {
  /**
   * For each expression, returns 1 if it can be balanced using up to maxReplacements,
   * or 0 otherwise.
   */
  public static List<Integer> balancedOrNot(List<String> expressions,
                                            List<Integer> maxReplacements) {
    return IntStream.range(0, expressions.size())
      .mapToObj(i -> canBalance(expressions.get(i), maxReplacements.get(i)) ? 1 : 0)
      .collect(Collectors.toList());
  }

  /** 
   * Returns true if `expr` can be balanced by replacing at most maxR closures.
   */
  private static boolean canBalance(String expr, int maxR) {
    int open = 0, used = 0;
    for (char c : expr.toCharArray()) {
      if (c == '<') {
        open++;
      } else { // c == '>'
        if (open > 0) {
          open--;
        } else if (used < maxR) {
          used++;
          // replaced this '>' with '<>', which nets zero change in `open`
        } else {
          return false;
        }
      }
    }
    return open == 0;  // any leftover '<' cannot be closed
  }

  public static void main(String[] args) {
    // --- Sample tests from the prompt:
    List<List<String>> inputs = Arrays.asList(
      Arrays.asList("<>>", "<>>>>"),
      Arrays.asList("<>", "<>><"),
      Arrays.asList("<<<><>>")
    );
    List<List<Integer>> maxRs = Arrays.asList(
      Arrays.asList(2, 2),
      Arrays.asList(1, 0),
      Arrays.asList(2)
    );
    List<List<Integer>> expect = Arrays.asList(
      Arrays.asList(1, 0),
      Arrays.asList(1, 0),
      Arrays.asList(0)
    );

    for (int i = 0; i < inputs.size(); i++) {
      List<String> expr = inputs.get(i);
      List<Integer> mr  = maxRs.get(i);
      List<Integer> out = balancedOrNot(expr, mr);
      System.out.printf(
        "Test %d: expr=%s, maxR=%s → got %s, expected %s : %s%n",
        i, expr, mr, out, expect.get(i),
        out.equals(expect.get(i)) ? "PASS" : "FAIL"
      );
    }

    // --- A large stress test (100k '<' followed by 100k '>')
    String big = String.join("", Collections.nCopies(100_000, "<"))
               + String.join("", Collections.nCopies(100_000, ">"));
    int result = balancedOrNot(Collections.singletonList(big),
                               Collections.singletonList(0)).get(0);
    System.out.println("Large stress test (100k pairs) → " 
                       + result + "  (expected 1)");
  }
}