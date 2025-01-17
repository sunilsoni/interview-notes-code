package com.interview.notes.code.year.y2025.jan24.test10;

import java.util.ArrayList;
import java.util.List;

class QuoteSvc {
    private List<Quote> previousCloseQuotes;

    public QuoteSvc() {
        this.previousCloseQuotes = new ArrayList<>();
        initializeQuotes();
    }

    private void initializeQuotes() {
        previousCloseQuotes.add(new Quote("ABBV,91.87"));
        previousCloseQuotes.add(new Quote("BDX,233.54"));
        previousCloseQuotes.add(new Quote("CAH,49.30"));
        previousCloseQuotes.add(new Quote("DOV,109.26"));
        previousCloseQuotes.add(new Quote("ECL,204.96"));
        previousCloseQuotes.add(new Quote("FRT,81.83"));
        previousCloseQuotes.add(new Quote("GD,151.24"));
        previousCloseQuotes.add(new Quote("HRL,50.81"));
        previousCloseQuotes.add(new Quote("JNJ,147.97"));
        previousCloseQuotes.add(new Quote("KMB,150.74"));
        previousCloseQuotes.add(new Quote("LEG,41.73"));
        previousCloseQuotes.add(new Quote("MCD,211.79"));
        previousCloseQuotes.add(new Quote("NUE,46.91"));
        previousCloseQuotes.add(new Quote("OTIS,60.99"));
        previousCloseQuotes.add(new Quote("PBCT,10.86"));
        previousCloseQuotes.add(new Quote("PEP,138.33"));
        previousCloseQuotes.add(new Quote("ROP,419.45"));
        previousCloseQuotes.add(new Quote("SHW,666.77"));
        previousCloseQuotes.add(new Quote("VFC,68.15"));
        previousCloseQuotes.add(new Quote("WMT,141.15"));
        previousCloseQuotes.add(new Quote("XOM,38.88"));
    }

    public List<Quote> getAllPreviousClose() {
        return previousCloseQuotes;
    }
}

