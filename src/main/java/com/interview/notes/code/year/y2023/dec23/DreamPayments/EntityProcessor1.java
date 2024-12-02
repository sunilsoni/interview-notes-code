package com.interview.notes.code.year.y2023.dec23.DreamPayments;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.interview.notes.code.year.y2023.dec23.DreamPayments.model.EntityType;

public class EntityProcessor1 {
    private int totalEntitiesProcessed = 0;
    private int totalEntitiesValid = 0;
    private int totalEntitiesInvalid = 0;

    public EntityProcessor1() {

    }

    public void processEntityFile(String inputFileName, String outputFileName, EntityType entityType) {
        // TODO: You will have to handle one or more exceptions once you start implementing the entire method below.
        // What is the standard way to handle exceptions in Java?
        ObjectMapper objectMapper = new ObjectMapper();
        if (entityType == EntityType.PAYEE) {
            // TODO: Implement the Payee class as a subclass/child of the Entity class.
            /*
            List<Payee> entityList = objectMapper.readValue(new File(inputFileName), new TypeReference<>() {
            });
            List<Payee> processedPayeeList = processPayeeList(entityList);
            */
            // TODO: BONUS OBJECTIVE
            // How would you write out the processedPayeeList to the output file?
            // If you are to produce the correct output file, then you will be awarded bonus marks.
        } else {
            // TODO: Implement the Business class as a subclass/child of the Entity class.
            /*
            List<Business> entityList = objectMapper.readValue(new File(inputFileName), new TypeReference<>() {
            });
            List<Business> processedBusinessList = processBusinessList(entityList);
            */
            // TODO: BONUS OBJECTIVE
            // How would you write out the processedBusinessList to the output file?
            // If you are to produce the correct output file, then you will be awarded bonus marks.
        }
    }

    // TODO: Uncomment this method once the processPayeeEntityInformation() method is implemented.
    /*
    private List<Payee> processPayeeList(List<Payee> entityList) {
        Payee payee;
        List<Payee> payeeList = new ArrayList<>();
        for (Payee entity : entityList) {
            payee = processPayeeEntityInformation(entity);
            payeeList.add(payee);
            System.out.printf("Payee %s was processed.\n", payee.getEntityName());
        }
        return payeeList;
    }
    */

    // TODO: Uncomment this method once the processBusinessEntityInformation() method is implemented.
    /*
    private List<Business> processBusinessList(List<Business> entityList) {
        Business business;
        List<Business> businessList = new ArrayList<>();
        for (Business entity : entityList) {
            business = processBusinessEntityInformation(entity);
            businessList.add(business);
            System.out.printf("Business %s was processed.\n", business.getEntityName());
        }
        return businessList;
    }
    */

    // TODO: Implement the two methods below. You will need to process entities with the following requirements:
    // 1. A payee is considered valid if they meet the following criteria:
    // EntityStatus is VERIFIED. A VERIFIED payee is ALWAYS considered to be VALID,
    // regardless of other validation criteria.
    // If an payee is NOT_VERIFIED, then as long as the payee's verification code is valid
    // (look in EntityValidator for its specific criteria), then,
    // the payee is still considered VALID and their EntityStatus can be set to VERIFIED.
    // If none of the above criteria matches, or EntityStatus is TERMINATED, then the payee is considered INVALID.

    // 2. A business is considered valid if they meet the following criteria:
    // EntityStatus is VERIFIED. A VERIFIED business is ALWAYS considered to be VALID,
    // regardless of other validation criteria.
    // If a business is NOT_VERIFIED,
    // then if their verification code is valid (look in EntityValidator for its specific criteria),
    // the business is still considered VALID and their EntityStatus can be set to VERIFIED.
    // If none of the above criteria matches, or EntityStatus is TERMINATED, then the business is considered INVALID.

    // 3. Additionally, if an entity is marked as INVALID, their FailureReason must be set with the appropriate
    // FailureReason enum value (using the failureReason string itself).

    // 4. You must keep track of the total amount of entities processed, and the total amount of
    // valid and invalid entities processed and update this class' instance variables correspondingly.

    // 5. Lastly, be sure to handle any exceptions that are not already handled in this class once you
    // start writing code and call any methods that may cause exceptions.

    // TODO: Implement this method.
    /*
    private Payee processPayeeEntityInformation(Payee entity) {

        return entity;
    }
     */

    // TODO: Implement this method.
    /*
    private Business processBusinessEntityInformation(Business entity) {

        return entity;
    }
     */

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
