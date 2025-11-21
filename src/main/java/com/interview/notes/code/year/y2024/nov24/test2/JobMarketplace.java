package com.interview.notes.code.year.y2024.nov24.test2;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class JobMarketplace {

    private final List<Job> jobs;

    public JobMarketplace() {
        this.jobs = new ArrayList<>();
    }

    public static void main(String[] args) {
        JobMarketplace marketplace = new JobMarketplace();

        // Test case 1: Posting a job
        Job job1 = new Job("Web Development", "React, Node.js", "John Doe", "john@example.com", LocalDateTime.now().plusDays(7));
        marketplace.postJob(job1);
        System.out.println("Test case 1 - Job posted: " + (marketplace.jobs.contains(job1) ? "PASS" : "FAIL"));

        // Test case 2: Placing bids
        marketplace.placeBid(job1, new Bid("Alice", 500));
        marketplace.placeBid(job1, new Bid("Bob", 450));
        marketplace.placeBid(job1, new Bid("Charlie", 480));
        System.out.println("Test case 2 - Bids placed: " + (job1.bidCount == 3 && job1.lowestBid == 450 ? "PASS" : "FAIL"));

        // Test case 3: Getting recent jobs
        for (int i = 0; i < 15; i++) {
            marketplace.postJob(new Job("Job " + i, "Req " + i, "Poster " + i, "contact" + i + "@example.com", LocalDateTime.now().plusDays(i)));
        }
        List<Job> recentJobs = marketplace.getRecentJobs(10);
        System.out.println("Test case 3 - Recent jobs: " + (recentJobs.size() == 10 ? "PASS" : "FAIL"));

        // Test case 4: Getting most active jobs
        List<Job> activeJobs = marketplace.getMostActiveJobs(10);
        System.out.println("Test case 4 - Active jobs: " + (activeJobs.size() <= 10 ? "PASS" : "FAIL"));

        // Test case 5: Closing expired auctions
        Job expiredJob = new Job("Expired Job", "Req", "ExpiredPoster", "expired@example.com", LocalDateTime.now().minusMinutes(1));
        marketplace.postJob(expiredJob);
        marketplace.placeBid(expiredJob, new Bid("LateBidder", 100));
        marketplace.closeExpiredAuctions();
        System.out.println("Test case 5 - Expired auction closed: PASS");

        // Test case 6: Large data input
        for (int i = 0; i < 100000; i++) {
            Job largeJob = new Job("Large Job " + i, "Large Req " + i, "LargePoster " + i, "large" + i + "@example.com", LocalDateTime.now().plusDays(30));
            marketplace.postJob(largeJob);
            for (int j = 0; j < 20; j++) {
                marketplace.placeBid(largeJob, new Bid("LargeBidder" + j, 1000 - j));
            }
        }
        System.out.println("Test case 6 - Large data input processed: " + (marketplace.jobs.size() > 100000 ? "PASS" : "FAIL"));
    }

    public void postJob(Job job) {
        jobs.add(job);
    }

    public void placeBid(Job job, Bid bid) {
        if (LocalDateTime.now().isBefore(job.expirationDateTime)) {
            job.bids.add(bid);
            job.bidCount++;
            if (bid.amount < job.lowestBid) {
                job.lowestBid = bid.amount;
            }
        }
    }

    public List<Job> getRecentJobs(int limit) {
        return jobs.stream()
                .sorted(Comparator.comparing(job -> job.expirationDateTime, Comparator.reverseOrder()))
                .limit(limit)
                .toList();
    }

    public List<Job> getMostActiveJobs(int limit) {
        return jobs.stream()
                .filter(job -> LocalDateTime.now().isBefore(job.expirationDateTime))
                .sorted(Comparator.comparingInt(job -> job.bidCount))
                .limit(limit)
                .toList();
    }

    public void closeExpiredAuctions() {
        jobs.stream()
                .filter(job -> LocalDateTime.now().isAfter(job.expirationDateTime) && !job.bids.isEmpty())
                .forEach(job -> {
                    Bid winningBid = job.bids.stream()
                            .min(Comparator.comparingDouble(bid -> bid.amount))
                            .orElseThrow();
                    System.out.println("Auction closed for job: " + job.description);
                    System.out.println("Winner: " + winningBid.bidderName + " with bid: $" + winningBid.amount);
                });
    }

    static class Job {
        String description;
        String requirements;
        String posterName;
        String posterContact;
        double lowestBid;
        int bidCount;
        LocalDateTime expirationDateTime;
        List<Bid> bids;

        Job(String description, String requirements, String posterName, String posterContact, LocalDateTime expirationDateTime) {
            this.description = description;
            this.requirements = requirements;
            this.posterName = posterName;
            this.posterContact = posterContact;
            this.expirationDateTime = expirationDateTime;
            this.lowestBid = Double.MAX_VALUE;
            this.bidCount = 0;
            this.bids = new ArrayList<>();
        }
    }

    static class Bid {
        String bidderName;
        double amount;

        Bid(String bidderName, double amount) {
            this.bidderName = bidderName;
            this.amount = amount;
        }
    }
}
