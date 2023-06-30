package com.interview.notes.code.june23.test11;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The overall aim of the task is to observe how you work through the exercise below and how effective you are at pairing.
 * The emphasis of the task is to observe how you collaborate with others over completion of task.
 * This session will last up to 90 minutes. You will be pairing for 60 minutes, and the remainder of the time will be spent
 * discussing your solution. You will be pairing with one of our developers, and the other will be taking notes.
 * <p>
 * Objective
 * Below we've defined a problem for you to solve. We're interested in finding out how you approach the issues and interact
 * with your partner whilst you answer the problem, not how complete your answer is.
 * This is your project and **you can modify or delete any of the files**.J
 * I
 * You can use the internet for research purposes and add third party libraries to the dependencies in the ’build.gradle’.
 * <p>
 * The exercise
 * An advert's click through rate tells us how often an advert was opened from search results-it is the ratio of views to
 * search appearances i.e. click through rate = advert views / search appearances’ (a decimal number typically between
 * zero and one). This is used at Dealer Auction as a metric representing how attractive the advert is.
 * The file data/advert-performance.csv’ contains a number of results from our advert performance analysis. The columns
 * labelled ’search appearances' and 'advert views* contain the aggregated counts for users interactions with each advert.
 * <p>
 * <p>
 * * 'csv
 * advert id,advertiser id,summary,days on site,search appearances,advert views,dwell time
 * 2b603783-4c7c-49cl-a7e5-dcfae435ff46,cec47c02c7b7406691ab6f0e214eed91,"GUARDS RED, Very Competitive HP & PCP Rates on Offer Please Ask for a Quote.",19 days,30374,9634.3 minutes
 * 7042b4c7-albf-4e58-8aae-c35b8f3a62al,cle90ca6dll443db93c33e0fl4e67572,"Service history, RED, Celebrating 29 Years In Business",2 months,88833,43384,2 minutes
 * <p>
 * advert-performance.csv
 * advert id,advertiser id,summary,days on site,search appearances,advert views,dwell time
 * 2b603783-4c7c-49c1-a7e5-dcfae435ff46,cec47c02c7b7406691ab6f0e214eed91,"GUARDS RED, Very Competitive HP & PCP Rates on Offer Please Ask for a Quote.",19 days,30374,9634,3 minutes
 * 7042b4c7-a1bf-4e58-8aae-c35b8f3a62a1,c1e90ca6d11443db93c33e0f14e67572,"Service history, RED, Celebrating 29 Years In Business",2 months,88833,43304,2 minutes
 * <p>
 * Write a program that finds the ID of the advert with the largest click through rate. In reality, there may be multiple
 * adverts with the same CTR, but in this data set you do not need to consider this.
 */
public class CalculateClickThroughRate {

    public static void main(String[] args) throws IOException {
        Reader reader = new FileReader("data/advert-performance.csv");
        Scanner scanner = new Scanner(reader);
        scanner.nextLine();  // Skip header

        List<Advert> adverts = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] columns = line.split(",");

            Advert advert = new Advert();
            advert.setAdvertId(columns[0]);
            advert.setAdvertiserId(columns[1]);
            advert.setSummary(columns[2]);
            advert.setDaysOnSite(Integer.parseInt(columns[3]));
            advert.setSearchAppearances(Integer.parseInt(columns[4]));
            advert.setAdvertViews(Integer.parseInt(columns[5]));

            adverts.add(advert);
        }

        for (Advert advert : adverts) {
            double clickThroughRate = calculateClickThroughRate(advert.getSearchAppearances(), advert.getAdvertViews());
            System.out.println("Advert ID: {advert.getAdvertId()}, Click-through rate: {clickThroughRate}");
        }
    }

    private static double calculateClickThroughRate(int searchAppearances, int advertViews) {
        if (searchAppearances == 0) {
            return 0;
        }

        return advertViews / searchAppearances;
    }

    private static class Advert {

        private String advertId;
        private String advertiserId;
        private String summary;
        private int daysOnSite;
        private int searchAppearances;
        private int advertViews;

        public String getAdvertId() {
            return advertId;
        }

        public void setAdvertId(String advertId) {
            this.advertId = advertId;
        }

        public String getAdvertiserId() {
            return advertiserId;
        }

        public void setAdvertiserId(String advertiserId) {
            this.advertiserId = advertiserId;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public int getDaysOnSite() {
            return daysOnSite;
        }

        public void setDaysOnSite(int daysOnSite) {
            this.daysOnSite = daysOnSite;
        }

        public int getSearchAppearances() {
            return searchAppearances;
        }

        public void setSearchAppearances(int searchAppearances) {
            this.searchAppearances = searchAppearances;
        }

        public int getAdvertViews() {
            return advertViews;
        }

        public void setAdvertViews(int advertViews) {
            this.advertViews = advertViews;
        }
    }
}
