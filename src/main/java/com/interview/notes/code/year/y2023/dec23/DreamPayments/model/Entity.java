package com.interview.notes.code.year.y2023.dec23.DreamPayments.model;

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
