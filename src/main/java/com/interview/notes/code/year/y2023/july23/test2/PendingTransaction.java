package com.interview.notes.code.year.y2023.july23.test2;

import java.util.Objects;

public record PendingTransaction(Long id) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PendingTransaction that = (PendingTransaction) o;
        return Objects.equals(id, that.id);
    }

}
