package com.interview.notes.code.months.july24.test17;

import java.util.Optional;

public class UserStats {
    private Optional<Long> visitCount;

    public UserStats(Long visitCount) {
        this.visitCount = Optional.ofNullable(visitCount);
    }

    public Optional<Long> getVisitCount() {
        return visitCount;
    }

    // Setter method if modification of visitCount is necessary
    public void setVisitCount(Long visitCount) {
        this.visitCount = Optional.ofNullable(visitCount);
    }
}
