package com.interview.notes.code.year.y2025.feb.common.test8;

import java.util.ArrayList;
import java.util.List;

class QuoteSvc {
    private List<Quote> previousCloseQuotes;

    public QuoteSvc() {
        this.previousCloseQuotes = new ArrayList<>();
        this.previousCloseQuotes.add(new Quote("ABBV,91.87"));
        this.previousCloseQuotes.add(new Quote("BDX,233.54"));
        this.previousCloseQuotes.add(new Quote("CAH,49.30"));
        this.previousCloseQuotes.add(new Quote("DOV,109.26"));
        this.previousCloseQuotes.add(new Quote("ECL,204.96"));
        this.previousCloseQuotes.add(new Quote("FRT,81.83"));
        this.previousCloseQuotes.add(new Quote("GD,151.24"));
        this.previousCloseQuotes.add(new Quote("HRL,50.81"));
        this.previousCloseQuotes.add(new Quote("JNJ,147.97"));
        this.previousCloseQuotes.add(new Quote("KMB,150.74"));
        this.previousCloseQuotes.add(new Quote("LEG,41.73"));
        this.previousCloseQuotes.add(new Quote("MCD,211.79"));
        this.previousCloseQuotes.add(new Quote("NUE,46.91"));
        this.previousCloseQuotes.add(new Quote("OTIS,60.99"));
        this.previousCloseQuotes.add(new Quote("PBCT,10.86"));
        this.previousCloseQuotes.add(new Quote("PEP,138.33"));
        this.previousCloseQuotes.add(new Quote("ROP,419.45"));
        this.previousCloseQuotes.add(new Quote("SHW,666.77"));
        this.previousCloseQuotes.add(new Quote("T,29.44"));
        this.previousCloseQuotes.add(new Quote("VFC,68.15"));
        this.previousCloseQuotes.add(new Quote("WMT,141.15"));
        this.previousCloseQuotes.add(new Quote("XOM,38.88"));
    }

    public List<Quote> getAllPreviousClose() {
        return this.previousCloseQuotes;
    }
}
