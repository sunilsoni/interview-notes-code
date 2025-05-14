package com.interview.notes.code.year.y2025.may.common.test4;

import java.util.*;

class PageView {
    long timestamp;
    int pageId;
    int customerId;
    
    public PageView(long timestamp, int pageId, int customerId) {
        this.timestamp = timestamp;
        this.pageId = pageId;
        this.customerId = customerId;
    }
}

public class LoyalCustomerTracker {
    static class CustomerData {
        Set<Integer> pages = new HashSet<>();
        boolean visitedDay2 = false;
    }

    public static Collection<Integer> getLoyalCustomers(Iterator<PageView> file1, Iterator<PageView> file2) {
        // Single map to track everything
        Map<Integer, CustomerData> customerMap = new HashMap<>();
        
        // Process day 1 - just populate the map with pages
        while (file1.hasNext()) {
            PageView view = file1.next();
            customerMap.computeIfAbsent(view.customerId, k -> new CustomerData())
                      .pages.add(view.pageId);
        }
        
        // Process day 2 - check conditions in real-time
        Set<Integer> loyalCustomers = new HashSet<>();
        while (file2.hasNext()) {
            PageView view = file2.next();
            CustomerData data = customerMap.get(view.customerId);
            
            if (data != null) {  // customer visited on day 1
                data.visitedDay2 = true;
                if (data.pages.add(view.pageId) && data.pages.size() >= 2 || 
                    data.pages.size() >= 2) {
                    loyalCustomers.add(view.customerId);
                }
            }
        }
        
        // Final filter to ensure we only return customers who visited both days
        return loyalCustomers;
    }

    // Test cases remain the same...
}
