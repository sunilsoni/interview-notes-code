package com.interview.notes.code.year.y2025.september.common.test6;

import java.util.Date;
import java.util.Objects;

public record Person(String name, Date birthDate) {
    public Person(String name, Date birthDate) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        Objects.requireNonNull(birthDate, "birthDate must not be null");
        this.birthDate = new Date(birthDate.getTime());
    }

    @Override
    public Date birthDate() {
        return new Date(birthDate.getTime());
    }
}