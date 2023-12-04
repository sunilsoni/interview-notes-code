package com.interview.notes.code.months.dec23.DreamPayments.model;

// TODO: Implement a child/subclass of this parent/superclass for both Payee and Business individually.

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Entity {
    private String entityId;
    private String entityCreationDate;
    private String entityName;
    private EntityType entityType;
    private EntityStatus entityStatus;
    private FailureReason failureReason;


}
