package com.interview.notes.code.year.y2025.feb25.common.test8;

import java.math.BigDecimal;
import java.util.*;
/*

100% WORKING

Background Information
In a trading account, the total account value is the sum of all individual symbol values for that account.
The individual symbol value is the number of shares held by that account in that symbol multiplied by the closing quote price of that symbol.
For example, if an account held 10 shares of Walmart (WMT) @ 141.15 and 5 shares of McDonald's (MCD) at 211.79, the total value for that account would be calculated as:
• 10 * 141.15 = 1411.50 for WMT
• 5 * 211.79 = 1058.95 for MCD
• Total = 2470.45 (1411.50 + 1058.95)
A trading firm needs to be able to provide their risk managers with a list of accounts and the total value of each. Using the code provided, build a list like this and display it to the screen.
Your output should look something like this displaying accountId: totalValue:
286ea600: 41303.03
b949203a: 50352.96
4810b949: 33622.40
3a8f7d92: 51918.68
8f7d928a: 61517.15



Here is the properly formatted and combined extracted text from the images:

---

### **Background Information**
In a trading account, the total account value is the sum of all individual symbol values for that account.

The individual symbol value is the number of shares held by that account in that symbol multiplied by the closing quote price of that symbol.

For example, if an account held **10 shares of Walmart (WMT) @ 141.15** and **5 shares of McDonald's (MCD) at 211.79**, the total value for that account would be calculated as:

- **10 * 141.15 = 1411.50 for WMT**
- **5 * 211.79 = 1058.95 for MCD**
- **Total = 2470.45 (1411.50 + 1058.95)**

A trading firm needs to be able to provide their risk managers with a list of accounts and the total value of each. Using the provided code, build a list like this and display it to the screen.

Your output should look something like this, displaying:

**accountId: totalValue**

```
286ea600: 41303.03
b949203a: 50352.96
4810b949: 33622.40
3a8f7d92: 51918.68
8f7d928a: 61517.15
```

---

### **Task**
Using the `PositionSvc` and `QuoteSvc`, retrieve all the positions and closing prices, then list each **account ID** with the **total value** of all of that account’s positions.

**Bonus:**
Can you output the accounts sorted by total value in **descending order**, so that **8f7d928a** is at the top?

---
 */
public class Solution {
    public static void main(String[] args) {
        PositionSvc positionSvc = new PositionSvc();
        QuoteSvc quoteSvc = new QuoteSvc();
        
        // Retrieve positions and quotes
        List<Position> positions = positionSvc.getCustomerPositions();
        List<Quote> quotes = quoteSvc.getAllPreviousClose();
        
        // Map to store symbol and its price
        Map<String, BigDecimal> quoteMap = new HashMap<>();
        for (Quote quote : quotes) {
            quoteMap.put(quote.getSymbol(), quote.getPrice());
        }
        
        // Map to store account total values
        Map<String, BigDecimal> accountTotalValues = new HashMap<>();
        
        for (Position position : positions) {
            BigDecimal price = quoteMap.getOrDefault(position.getSymbol(), BigDecimal.ZERO);
            BigDecimal positionValue = price.multiply(BigDecimal.valueOf(position.getNumberOfShares()));
            accountTotalValues.put(position.getAccountId(),
                    accountTotalValues.getOrDefault(position.getAccountId(), BigDecimal.ZERO).add(positionValue));
        }
        
        // Sort accounts by total value in descending order
        List<Map.Entry<String, BigDecimal>> sortedAccounts = new ArrayList<>(accountTotalValues.entrySet());
        sortedAccounts.sort((a, b) -> b.getValue().compareTo(a.getValue()));
        
        // Print sorted results
        for (Map.Entry<String, BigDecimal> entry : sortedAccounts) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
