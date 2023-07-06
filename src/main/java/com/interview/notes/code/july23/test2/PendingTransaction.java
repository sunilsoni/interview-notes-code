package com.interview.notes.code.july23.test2;

import java.util.Objects;

public class PendingTransaction {
    private final Long id;

    public PendingTransaction(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PendingTransaction that = (PendingTransaction) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
