package com.interview.notes.code.months.july23.test2;

import java.util.Objects;
import java.util.Optional;

public class ProcessedTransaction {
    private final String id;
    private final Optional<String> status;

    public ProcessedTransaction(String id, Optional<String> status) {
        this.id = id;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public Optional<String> getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProcessedTransaction that = (ProcessedTransaction) o;
        return Objects.equals(id, that.id) && Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status);
    }
}
