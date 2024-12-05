package com.interview.notes.code.year.y2023.dec23.DreamPayments;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.notes.code.year.y2023.dec23.DreamPayments.model.*;
import com.interview.notes.code.year.y2023.dec23.DreamPayments.validation.EntityValidator;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class EntityProcessor {
    private final EntityValidator validator;
    private int totalEntitiesProcessed = 0;
    private int totalEntitiesValid = 0;
    private int totalEntitiesInvalid = 0;

    public EntityProcessor(EntityValidator validator) {
        this.validator = validator;
    }

    public void processEntityFile(String inputFileName, String outputFileName, EntityType entityType) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (entityType == EntityType.PAYEE) {
                List<Payee> entityList = objectMapper.readValue(new File(inputFileName), new TypeReference<List<Payee>>() {
                });
                List<Payee> processedPayeeList = processPayeeList(entityList);
                objectMapper.writeValue(new File(outputFileName), processedPayeeList);
            } else {
                List<Business> entityList = objectMapper.readValue(new File(inputFileName), new TypeReference<List<Business>>() {
                });
                List<Business> processedBusinessList = processBusinessList(entityList);
                objectMapper.writeValue(new File(outputFileName), processedBusinessList);
            }
        } catch (IOException e) {
            e.printStackTrace();
            // Handle other necessary exceptions or error logic
        }
    }

    private List<Payee> processPayeeList(List<Payee> entityList) {
        List<Payee> payeeList = new ArrayList<>();
        for (Payee entity : entityList) {
            Payee processed = processPayeeEntityInformation(entity);
            payeeList.add(processed);
            System.out.printf("Payee %s was processed.\n", processed.getEntityName());
        }
        return payeeList;
    }

    private List<Business> processBusinessList(List<Business> entityList) {
        List<Business> businessList = new ArrayList<>();
        for (Business entity : entityList) {
            Business processed = processBusinessEntityInformation(entity);
            businessList.add(processed);
            System.out.printf("Business %s was processed.\n", processed.getEntityName());
        }
        return businessList;
    }

    private Payee processPayeeEntityInformation(Payee entity) {
        if (entity.getEntityStatus() == EntityStatus.VERIFIED) {
            totalEntitiesValid++;
        } else if (entity.getEntityStatus() == EntityStatus.NOT_VERIFIED && validator.validateEntityVerificationCode(entity.getVerificationCode())) {
            entity.setEntityStatus(EntityStatus.VERIFIED);
            totalEntitiesValid++;
        } else {
            entity.setFailureReason(FailureReason.TERMINATED_ENTITY);
            entity.setEntityStatus(EntityStatus.TERMINATED);
            totalEntitiesInvalid++;
        }
        totalEntitiesProcessed++;
        return entity;
    }

    private Business processBusinessEntityInformation(Business entity) {
        if (entity.getEntityStatus() == EntityStatus.VERIFIED) {
            totalEntitiesValid++;
        } else if (entity.getEntityStatus() == EntityStatus.NOT_VERIFIED /*&& validator.validateEntityVerificationCode(entity.getVerificationCode(), entity.isForeignBusiness())*/) {
            entity.setEntityStatus(EntityStatus.VERIFIED);
            totalEntitiesValid++;
        } else {
            entity.setFailureReason(FailureReason.TERMINATED_ENTITY);
            entity.setEntityStatus(EntityStatus.TERMINATED);
            totalEntitiesInvalid++;
        }
        totalEntitiesProcessed++;
        return entity;
    }

    public int getTotalEntitiesProcessed() {
        return totalEntitiesProcessed;
    }

    public int getTotalEntitiesValid() {
        return totalEntitiesValid;
    }

    public int getTotalEntitiesInvalid() {
        return totalEntitiesInvalid;
    }
}
