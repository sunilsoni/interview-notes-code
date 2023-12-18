package com.interview.notes.code.months.dec23.DreamPayments.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Payee extends Entity {
    private LocalDate entityBirthDate;
    private int verificationCode;

    // Constructor
    public Payee(String entityId, String entityCreationDate, String entityName, EntityStatus entityStatus, String entityBirthDateStr, int verificationCode) {

        super(entityId, entityCreationDate, entityName, null, entityStatus, null);


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        this.entityBirthDate = LocalDate.parse(entityBirthDateStr, formatter);
        this.verificationCode = verificationCode;
    }

    // Getters and Setters
    public LocalDate getEntityBirthDate() {
        return entityBirthDate;
    }

    public void setEntityBirthDate(LocalDate entityBirthDate) {
        this.entityBirthDate = entityBirthDate;
    }

    public int getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(int verificationCode) {
        this.verificationCode = verificationCode;
    }

}
