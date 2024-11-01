package com.interview.notes.code.months.nov24.test1;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * MarketplaceTest is a class designed to perform manual testing of the Marketplace system.
 * It includes various test cases to verify the correctness and performance of the system.
 * Each test case outputs a PASS or FAIL result based on the verification.
 */
public class MarketplaceTest {

    // Simulated data structures for the Marketplace system
    static List<JobPosting> jobPostings = new ArrayList<>();
    static List<Bid> bids = new ArrayList<>();
    static int bidIdCounter = 1;

    public static void main(String[] args) {
        // Execute all test cases
        testPublishJob();
        testPlaceBid();
        testAuctionClosure();
        testWinnerAssignment();
        testLargeDataHandling();
    }

    /**
     * Test Case 1: Test publishing a new job posting.
     */
    public static void testPublishJob() {
        try {
            JobPosting job = new JobPosting(
                    "Fix leaking sink",
                    "Must have plumbing experience.",
                    "John Doe",
                    "john@example.com",
                    LocalDateTime.now().plusDays(1)
            );
            jobPostings.add(job);

            // Verification
            if (jobPostings.contains(job) && job.getDescription().equals("Fix leaking sink")) {
                System.out.println("Test Publish Job: PASS");
            } else {
                System.out.println("Test Publish Job: FAIL");
            }
        } catch (Exception e) {
            System.out.println("Test Publish Job: FAIL - Exception occurred");
        }
    }

    /**
     * Test Case 2: Test placing a bid on a job posting.
     */
    public static void testPlaceBid() {
        try {
            if (jobPostings.isEmpty()) {
                throw new Exception("No job postings available.");
            }

            JobPosting job = jobPostings.get(0);
            Bid bid = new Bid(bidIdCounter++, "Alice", "alice@example.com", 100.0, job.getId());
            job.placeBid(bid);
            bids.add(bid);

            // Verification
            if (job.getBids().contains(bid) && job.getLowestBidAmount() == 100.0) {
                System.out.println("Test Place Bid: PASS");
            } else {
                System.out.println("Test Place Bid: FAIL");
            }
        } catch (Exception e) {
            System.out.println("Test Place Bid: FAIL - Exception occurred");
        }
    }

    /**
     * Test Case 3: Test automatic closure of bidding after expiration.
     */
    public static void testAuctionClosure() {
        try {
            if (jobPostings.isEmpty()) {
                throw new Exception("No job postings available.");
            }

            JobPosting job = jobPostings.get(0);
            // Fast-forward time to after expiration
            job.setExpirationDate(LocalDateTime.now().minusMinutes(1));
            job.checkAndCloseAuction();

            // Verification
            if (job.isClosed()) {
                System.out.println("Test Auction Closure: PASS");
            } else {
                System.out.println("Test Auction Closure: FAIL");
            }
        } catch (Exception e) {
            System.out.println("Test Auction Closure: FAIL - Exception occurred");
        }
    }

    /**
     * Test Case 4: Test automatic assignment of the lowest bidder as winner.
     */
    public static void testWinnerAssignment() {
        try {
            if (jobPostings.isEmpty()) {
                throw new Exception("No job postings available.");
            }

            JobPosting job = jobPostings.get(0);
            job.assignWinner();

            // Verification
            Bid winner = job.getWinner();
            if (winner != null && winner.getBidAmount() == 100.0 && winner.getBidderName().equals("Alice")) {
                System.out.println("Test Winner Assignment: PASS");
            } else {
                System.out.println("Test Winner Assignment: FAIL");
            }
        } catch (Exception e) {
            System.out.println("Test Winner Assignment: FAIL - Exception occurred");
        }
    }

    /**
     * Test Case 5: Test handling of large data inputs.
     */
    public static void testLargeDataHandling() {
        try {
            int initialJobCount = jobPostings.size();
            int jobsToAdd = 250; // As per system targets
            for (int i = 0; i < jobsToAdd; i++) {
                JobPosting job = new JobPosting(
                        "Job " + (initialJobCount + i + 1),
                        "Description for job " + (initialJobCount + i + 1),
                        "Poster " + (initialJobCount + i + 1),
                        "poster" + (initialJobCount + i + 1) + "@example.com",
                        LocalDateTime.now().plusDays(7)
                );
                jobPostings.add(job);
            }

            // Simulate bidding
            int bidsPerJob = 20;
            for (int i = initialJobCount; i < initialJobCount + jobsToAdd; i++) {
                JobPosting job = jobPostings.get(i);
                for (int j = 0; j < bidsPerJob; j++) {
                    Bid bid = new Bid(bidIdCounter++, "Bidder " + j, "bidder" + j + "@example.com", 50.0 + j, job.getId());
                    job.placeBid(bid);
                    bids.add(bid);
                }
            }

            // Verification
            if (jobPostings.size() >= initialJobCount + jobsToAdd) {
                boolean allJobsHaveBids = true;
                for (int i = initialJobCount; i < initialJobCount + jobsToAdd; i++) {
                    JobPosting job = jobPostings.get(i);
                    if (job.getBids().size() != bidsPerJob) {
                        allJobsHaveBids = false;
                        break;
                    }
                }
                if (allJobsHaveBids) {
                    System.out.println("Test Large Data Handling: PASS");
                } else {
                    System.out.println("Test Large Data Handling: FAIL - Not all jobs have correct number of bids");
                }
            } else {
                System.out.println("Test Large Data Handling: FAIL - Not all jobs were added");
            }
        } catch (Exception e) {
            System.out.println("Test Large Data Handling: FAIL - Exception occurred");
        }
    }
}

/**
 * Represents a job posting in the Marketplace system.
 */
class JobPosting {
    private static int idCounter = 1;
    private int id;
    private String description;
    private String requirements;
    private String posterName;
    private String posterContact;
    private double lowestBidAmount;
    private List<Bid> bids;
    private LocalDateTime expirationDate;
    private boolean isClosed;
    private Bid winner;

    public JobPosting(String description, String requirements, String posterName, String posterContact, LocalDateTime expirationDate) {
        this.id = idCounter++;
        this.description = description;
        this.requirements = requirements;
        this.posterName = posterName;
        this.posterContact = posterContact;
        this.lowestBidAmount = Double.MAX_VALUE;
        this.bids = new ArrayList<>();
        this.expirationDate = expirationDate;
        this.isClosed = false;
        this.winner = null;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public double getLowestBidAmount() {
        return lowestBidAmount == Double.MAX_VALUE ? 0.0 : lowestBidAmount;
    }

    public List<Bid> getBids() {
        return bids;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public Bid getWinner() {
        return winner;
    }

    /**
     * Places a bid on this job posting.
     *
     * @param bid The bid to place.
     */
    public void placeBid(Bid bid) throws Exception {
        if (isClosed) {
            throw new Exception("Auction is closed.");
        }
        bids.add(bid);
        if (bid.getBidAmount() < lowestBidAmount) {
            lowestBidAmount = bid.getBidAmount();
        }
    }

    /**
     * Checks if the auction should be closed based on the current time.
     */
    public void checkAndCloseAuction() {
        if (!isClosed && LocalDateTime.now().isAfter(expirationDate)) {
            isClosed = true;
        }
    }

    /**
     * Assigns the winner based on the lowest bid.
     */
    public void assignWinner() {
        if (isClosed && winner == null && !bids.isEmpty()) {
            winner = bids.stream().min(Comparator.comparingDouble(Bid::getBidAmount)).orElse(null);
        }
    }
}

/**
 * Represents a bid in the Marketplace system.
 */
class Bid {
    private int bidId;
    private String bidderName;
    private String bidderContact;
    private double bidAmount;
    private int jobId;

    public Bid(int bidId, String bidderName, String bidderContact, double bidAmount, int jobId) {
        this.bidId = bidId;
        this.bidderName = bidderName;
        this.bidderContact = bidderContact;
        this.bidAmount = bidAmount;
        this.jobId = jobId;
    }

    public int getBidId() {
        return bidId;
    }

    public String getBidderName() {
        return bidderName;
    }

    public double getBidAmount() {
        return bidAmount;
    }

    public int getJobId() {
        return jobId;
    }
}
