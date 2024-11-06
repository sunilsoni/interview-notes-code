package com.interview.notes.code.months.nov24.test5;

import java.util.*;

/*
Let’s say we have a website and we keep track of what pages customers are viewing, for things like business metrics.

Every time somebody comes to the website, we write a record to a log file consisting of PageId and CustomerId.
At the end of the day we have a big log file with many entries in that format, and for every day we have a new file.

Now, given two log files (log file from day 1 and log file from day 2)
we want to generate a list of ‘loyal customers’ that meet the criteria of:
(a) they came on both days
(b) they visited at least two unique pages.
*/

public class LoyalCustomerApp {

    public Collection<CustomerId> getLoyalCustomers(Iterator<PageView> file1, Iterator<PageView> file2) {

        Collection<CustomerId> loyalCustomers = new HashSet<>();

        // Map to store CustomerId to Set of PageIds for Day 1
        Map<CustomerId, Set<PageId>> day1Map = new HashMap<>();
        while (file1.hasNext()) {
            PageView pv = file1.next();
            day1Map.computeIfAbsent(pv.getCustomerId(), k -> new HashSet<>()).add(pv.getPageId());
        }

        // Map to store CustomerId to Set of PageIds for Day 2
        Map<CustomerId, Set<PageId>> day2Map = new HashMap<>();
        while (file2.hasNext()) {
            PageView pv = file2.next();
            day2Map.computeIfAbsent(pv.getCustomerId(), k -> new HashSet<>()).add(pv.getPageId());
        }

        // Find customers present on both days
        Set<CustomerId> customersDay1 = day1Map.keySet();
        Set<CustomerId> customersDay2 = day2Map.keySet();

        // Retain only customers who are present on both days
        Set<CustomerId> commonCustomers = new HashSet<>(customersDay1);
        commonCustomers.retainAll(customersDay2);

        // For each common customer, check if they visited at least two unique pages across both days
        for (CustomerId cid : commonCustomers) {
            Set<PageId> uniquePages = new HashSet<>();
            uniquePages.addAll(day1Map.get(cid));
            uniquePages.addAll(day2Map.get(cid));

            if (uniquePages.size() >= 2) {
                loyalCustomers.add(cid);
            }
        }

        return loyalCustomers;
    }

    public static void main(String[] args) {
        List<PageView> file1 = new ArrayList<>();
        List<PageView> file2 = new ArrayList<>();

        CustomerId c1 = new CustomerId("C001");
        CustomerId c2 = new CustomerId("C002");
        CustomerId c3 = new CustomerId("C003");

        PageId p1 = new PageId("P001");
        PageId p2 = new PageId("P002");
        PageId p3 = new PageId("P003");

        file1.add(new PageView(c1, p1));
        file1.add(new PageView(c2, p2));
        file1.add(new PageView(c3, p3));
        file1.add(new PageView(c2, p1));

        file2.add(new PageView(c2, p1));
        file2.add(new PageView(c2, p3));
        file2.add(new PageView(c3, p2));
        file2.add(new PageView(c3, p3));

        Iterator<PageView> it1 = file1.iterator();
        Iterator<PageView> it2 = file2.iterator();

        LoyalCustomerApp main = new LoyalCustomerApp();
        Collection<CustomerId> loyalCustomers = main.getLoyalCustomers(it1, it2);

        System.out.println("Loyal customers: " + loyalCustomers);
    }


    /* Helper Classes */

    public static class CustomerId {
        private String id;

        public CustomerId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            CustomerId that = (CustomerId) obj;
            return Objects.equals(id, that.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return id;
        }
    }

    public static class PageId {
        private String id;

        public PageId(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            PageId pageId = (PageId) obj;
            return Objects.equals(id, pageId.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }

        @Override
        public String toString() {
            return id;
        }
    }

    public static class PageView {
        private CustomerId customerId;
        private PageId pageId;

        public PageView(CustomerId customerId, PageId pageId) {
            this.customerId = customerId;
            this.pageId = pageId;
        }

        public CustomerId getCustomerId() {
            return customerId;
        }

        public PageId getPageId() {
            return pageId;
        }
    }
}
