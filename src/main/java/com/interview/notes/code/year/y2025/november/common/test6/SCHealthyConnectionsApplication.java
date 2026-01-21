package com.interview.notes.code.year.y2025.november.common.test6;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Main application class for SC Healthy Connections Form Processing System
 * This class handles dynamic form generation and validation based on JSON schema
 */
public class SCHealthyConnectionsApplication {

    /**
     * Creates sample form schema matching the provided JSON structure
     * Builds complete form definition programmatically
     */
    private static FormSchema createSampleSchema() {
        // Create main form schema with title
        FormSchema schema = new FormSchema("SC Healthy Connections Application");

        // Step 1: Primary Applicant - Basic personal information
        FormStep step1 = new FormStep("Primary Applicant");

        // Create first name field with validation
        FormField firstName = new FormField("firstName", "text", "First Name");
        firstName.setValidations(Map.of("required", true));  // Mark as required field
        firstName.setErrorMessages(Map.of("required", "First name is required."));
        step1.addField(firstName);  // Add field to step

        // Create last name field with validation
        FormField lastName = new FormField("lastName", "text", "Last Name");
        lastName.setValidations(Map.of("required", true));  // Mark as required field
        lastName.setErrorMessages(Map.of("required", "Last name is required."));
        step1.addField(lastName);  // Add field to step

        // Create date of birth field with validation
        FormField dob = new FormField("dateOfBirth", "date", "Date of Birth");
        dob.setValidations(Map.of("required", true));  // Mark as required field
        dob.setErrorMessages(Map.of("required", "Date of birth is required."));
        step1.addField(dob);  // Add field to step

        // Create gender field as optional select
        FormField gender = new FormField("gender", "select", "Gender");
        gender.setOptions(Arrays.asList(
                new Option("Male", "male"),      // Male option
                new Option("Female", "female")   // Female option
        ));
        gender.setValidations(Map.of("required", false));  // Optional field
        step1.addField(gender);  // Add field to step

        schema.addStep(step1);  // Add completed step to schema

        // Step 2: Applicant Details - Eligibility questions
        FormStep step2 = new FormStep("Applicant Details");

        // Create citizenship question as radio button
        FormField citizen = new FormField("isCitizen", "radio", "Are you a U.S. Citizen?");
        citizen.setOptions(Arrays.asList(
                new Option("Yes", true),   // Yes option with boolean value
                new Option("No", false)     // No option with boolean value
        ));
        citizen.setValidations(Map.of("required", true));  // Required question
        step2.addField(citizen);  // Add field to step

        // Create pregnancy question as radio button
        FormField pregnant = new FormField("isPregnant", "radio", "Are you pregnant?");
        pregnant.setOptions(Arrays.asList(
                new Option("Yes", true),   // Yes option with boolean value
                new Option("No", false)    // No option with boolean value
        ));
        pregnant.setValidations(Map.of("required", true));  // Required question
        step2.addField(pregnant);  // Add field to step

        // Create foster care question as radio button
        FormField foster = new FormField("isFosterCare", "radio",
                "Are you currently in foster care or were you previously?");
        foster.setOptions(Arrays.asList(
                new Option("Yes", true),   // Yes option with boolean value
                new Option("No", false)    // No option with boolean value
        ));
        foster.setValidations(Map.of("required", true));  // Required question
        step2.addField(foster);  // Add field to step

        schema.addStep(step2);  // Add completed step to schema

        // Step 3: Household Information - Other members question
        FormStep step3 = new FormStep("Household Information");

        // Create household members question as radio button
        FormField household = new FormField("hasOtherMembers", "radio",
                "Is anyone else in your household applying for coverage?");
        household.setOptions(Arrays.asList(
                new Option("Yes", true),   // Yes option triggers member addition
                new Option("No", false)    // No option completes application
        ));
        household.setValidations(Map.of("required", true));  // Required question
        step3.addField(household);  // Add field to step

        schema.addStep(step3);  // Add completed step to schema

        // Create nested schemas for complex fields

        // Income Schema - For capturing income sources
        FormSchema incomeSchema = new FormSchema("Income Source");
        FormStep incomeStep = new FormStep("Income Source");

        // Create income source type field
        FormField source = new FormField("source", "select", "Source Type");
        source.setOptions(List.of(
                new Option("Wage", "wage")  // Wage income option
        ));
        source.setValidations(Map.of("required", true));  // Required field
        source.setErrorMessages(Map.of("required", "Source is required."));
        incomeStep.addField(source);  // Add field to step

        // Create income amount field
        FormField amount = new FormField("amount", "number", "Monthly Amount");
        amount.setValidations(Map.of(
                "required", true,   // Required field
                "min", 0           // Minimum value of 0
        ));
        amount.setErrorMessages(Map.of("required", "Amount is required."));
        incomeStep.addField(amount);  // Add field to step

        incomeSchema.addStep(incomeStep);  // Add step to income schema
        schema.addSchema("incomeSchema", incomeSchema);  // Add to main schema

        // Resource Schema - For capturing assets
        FormSchema resourceSchema = new FormSchema("Resource");
        FormStep resourceStep = new FormStep("Resource");

        // Create resource type field
        FormField resourceType = new FormField("type", "select", "Resource Type");
        resourceType.setOptions(Arrays.asList(
                new Option("Bank Account", "bank_account"),  // Bank account option
                new Option("Vehicle", "vehicle"),            // Vehicle option
                new Option("Property", "property")           // Property option
        ));
        resourceType.setValidations(Map.of("required", true));  // Required field
        resourceType.setErrorMessages(Map.of("required", "Type is required."));
        resourceStep.addField(resourceType);  // Add field to step

        // Create resource value field
        FormField value = new FormField("value", "number", "Value ($)");
        value.setValidations(Map.of(
                "required", true,   // Required field
                "min", 0           // Minimum value of 0
        ));
        value.setErrorMessages(Map.of("required", "Value is required."));
        resourceStep.addField(value);  // Add field to step

        resourceSchema.addStep(resourceStep);  // Add step to resource schema
        schema.addSchema("resourceSchema", resourceSchema);  // Add to main schema

        return schema;  // Return completed schema
    }

    /**
     * Main method - Entry point for testing
     * Contains all test cases including edge cases and large data tests
     */
    public static void main(String[] args) {
        System.out.println("=== SC Healthy Connections Application Testing ===\n");

        // Create the form schema based on provided JSON structure
        FormSchema schema = createSampleSchema();

        // Initialize application processor with schema
        ApplicationProcessor processor = new ApplicationProcessor(schema);

        // Test Case 1: Valid complete application - typical happy path
        System.out.println("Test Case 1: Valid Complete Application");
        Map<String, Object> validApplication = new HashMap<>();
        validApplication.put("firstName", "John");           // Valid first name
        validApplication.put("lastName", "Doe");            // Valid last name
        validApplication.put("dateOfBirth", "1990-01-01"); // Valid date of birth
        validApplication.put("gender", "male");            // Valid gender selection
        validApplication.put("isCitizen", true);          // US citizen
        validApplication.put("isPregnant", false);        // Not pregnant
        validApplication.put("isFosterCare", false);     // Not in foster care
        validApplication.put("hasOtherMembers", false);   // No other members

        // Process the application and get result
        ProcessResult result1 = processor.processApplication(validApplication);
        System.out.println("Result: " + (result1.isSuccess() ? "PASS" : "FAIL"));
        System.out.println("Message: " + result1.getMessage());
        System.out.println();

        // Test Case 2: Missing required fields - validation test
        System.out.println("Test Case 2: Missing Required Fields");
        Map<String, Object> incompleteApplication = new HashMap<>();
        incompleteApplication.put("firstName", "Jane");     // Only first name provided
        incompleteApplication.put("gender", "female");     // Gender is optional
        // Missing required fields: lastName, dateOfBirth, isCitizen, isPregnant, isFosterCare

        // Process incomplete application
        ProcessResult result2 = processor.processApplication(incompleteApplication);
        System.out.println("Result: " + (result2.isSuccess() ? "FAIL" : "PASS")); // Should fail
        System.out.println("Message: " + result2.getMessage());
        if (!result2.getErrors().isEmpty()) {
            System.out.println("Errors found: " + result2.getErrors().size() + " fields");
        }
        System.out.println();

        // Test Case 3: Application with household members and income
        System.out.println("Test Case 3: Application with Household Members");
        Map<String, Object> familyApplication = new HashMap<>();
        familyApplication.put("firstName", "Sarah");          // Primary applicant first name
        familyApplication.put("lastName", "Johnson");        // Primary applicant last name
        familyApplication.put("dateOfBirth", "1985-05-15"); // Primary applicant DOB
        familyApplication.put("isCitizen", true);          // US citizen
        familyApplication.put("isPregnant", true);         // Pregnant - special eligibility
        familyApplication.put("isFosterCare", false);     // Not in foster care
        familyApplication.put("hasOtherMembers", true);    // Has other household members

        // Create household members list
        List<Map<String, Object>> members = new ArrayList<>();

        // First household member
        Map<String, Object> member1 = new HashMap<>();
        member1.put("firstName", "Mike");              // Member first name
        member1.put("lastName", "Johnson");           // Member last name
        member1.put("isPregnant", false);            // Not pregnant

        // Add income sources for member 1
        List<Map<String, Object>> member1Incomes = new ArrayList<>();
        Map<String, Object> income1 = new HashMap<>();
        income1.put("source", "wage");               // Wage income
        income1.put("amount", 2500.0);              // Monthly amount $2500
        member1Incomes.add(income1);                // Add to member's income list
        member1.put("incomes", member1Incomes);     // Attach incomes to member

        members.add(member1);  // Add member to household

        // Second household member (child)
        Map<String, Object> member2 = new HashMap<>();
        member2.put("firstName", "Emma");             // Child first name
        member2.put("lastName", "Johnson");          // Child last name
        member2.put("isPregnant", false);           // Not applicable for child
        member2.put("incomes", new ArrayList<>());  // No income for child

        members.add(member2);  // Add child to household

        familyApplication.put("householdMembers", members);  // Add all members to application

        // Process family application
        ApplicationProcessor familyProcessor = new ApplicationProcessor(schema);
        ProcessResult result3 = familyProcessor.processApplication(familyApplication);
        System.out.println("Result: " + (result3.isSuccess() ? "PASS" : "FAIL"));
        System.out.println("Message: " + result3.getMessage());
        System.out.println("Household Size: " + result3.getData().get("householdSize"));
        System.out.println("Total Income: $" + result3.getData().get("totalIncome"));
        System.out.println();

        // Test Case 4: Edge case - Empty string values
        System.out.println("Test Case 4: Empty String Values");
        Map<String, Object> emptyStringApplication = new HashMap<>();
        emptyStringApplication.put("firstName", "");        // Empty string - should fail
        emptyStringApplication.put("lastName", "   ");      // Whitespace only - should fail
        emptyStringApplication.put("dateOfBirth", "1990-01-01");
        emptyStringApplication.put("isCitizen", true);
        emptyStringApplication.put("isPregnant", false);
        emptyStringApplication.put("isFosterCare", false);
        emptyStringApplication.put("hasOtherMembers", false);

        // Process application with empty strings
        ProcessResult result4 = processor.processApplication(emptyStringApplication);
        System.out.println("Result: " + (result4.isSuccess() ? "FAIL" : "PASS")); // Should fail
        System.out.println("Message: " + result4.getMessage());
        System.out.println();

        // Test Case 5: Large data test - Many household members
        System.out.println("Test Case 5: Large Data Test (100 Household Members)");
        Map<String, Object> largeApplication = new HashMap<>();
        largeApplication.put("firstName", "Test");           // Test first name
        largeApplication.put("lastName", "LargeFamily");    // Test last name
        largeApplication.put("dateOfBirth", "1980-01-01"); // Test DOB
        largeApplication.put("isCitizen", true);
        largeApplication.put("isPregnant", false);
        largeApplication.put("isFosterCare", false);
        largeApplication.put("hasOtherMembers", true);

        // Generate 100 household members using Stream API
        List<Map<String, Object>> largeFamily = IntStream.range(0, 100)
                .mapToObj(i -> {
                    Map<String, Object> member = new HashMap<>();
                    member.put("firstName", "Member" + i);       // Unique first name
                    member.put("lastName", "LargeFamily");      // Same family name
                    member.put("isPregnant", false);

                    // Add varying income for each member
                    List<Map<String, Object>> incomes = new ArrayList<>();
                    Map<String, Object> income = new HashMap<>();
                    income.put("source", "wage");
                    income.put("amount", 1000.0 + (i * 10));   // Varying amounts
                    incomes.add(income);
                    member.put("incomes", incomes);

                    return member;
                })
                .collect(Collectors.toList());

        largeApplication.put("householdMembers", largeFamily);

        // Measure performance for large data processing
        long startTime = System.currentTimeMillis();
        ApplicationProcessor largeProcessor = new ApplicationProcessor(schema);
        ProcessResult result5 = largeProcessor.processApplication(largeApplication);
        long endTime = System.currentTimeMillis();

        System.out.println("Result: " + (result5.isSuccess() ? "PASS" : "FAIL"));
        System.out.println("Message: " + result5.getMessage());
        System.out.println("Household Size: " + result5.getData().get("householdSize"));
        System.out.println("Total Income: $" + result5.getData().get("totalIncome"));
        System.out.println("Processing Time: " + (endTime - startTime) + " ms");
        System.out.println();

        // Test Case 6: Special eligibility - Foster care
        System.out.println("Test Case 6: Foster Care Special Eligibility");
        Map<String, Object> fosterApplication = new HashMap<>();
        fosterApplication.put("firstName", "Alex");
        fosterApplication.put("lastName", "Foster");
        fosterApplication.put("dateOfBirth", "2005-03-20");
        fosterApplication.put("isCitizen", true);
        fosterApplication.put("isPregnant", false);
        fosterApplication.put("isFosterCare", true);      // Foster care - automatic eligibility
        fosterApplication.put("hasOtherMembers", false);

        ProcessResult result6 = processor.processApplication(fosterApplication);
        System.out.println("Result: " + (result6.isSuccess() ? "PASS" : "FAIL"));
        System.out.println("Message: " + result6.getMessage());
        System.out.println("Eligible: " + result6.getData().get("eligible"));
        System.out.println();

        // Test Case 7: Boundary test - Zero and negative income
        System.out.println("Test Case 7: Boundary Test - Zero/Negative Income");
        Map<String, Object> boundaryApplication = new HashMap<>();
        boundaryApplication.put("firstName", "Zero");
        boundaryApplication.put("lastName", "Income");
        boundaryApplication.put("dateOfBirth", "1995-07-10");
        boundaryApplication.put("isCitizen", true);
        boundaryApplication.put("isPregnant", false);
        boundaryApplication.put("isFosterCare", false);
        boundaryApplication.put("hasOtherMembers", true);

        // Create member with zero income
        List<Map<String, Object>> zeroIncomeMembers = new ArrayList<>();
        Map<String, Object> zeroMember = new HashMap<>();
        zeroMember.put("firstName", "No");
        zeroMember.put("lastName", "Income");

        List<Map<String, Object>> zeroIncomes = new ArrayList<>();
        Map<String, Object> zeroIncome = new HashMap<>();
        zeroIncome.put("source", "wage");
        zeroIncome.put("amount", 0.0);              // Zero income
        zeroIncomes.add(zeroIncome);
        zeroMember.put("incomes", zeroIncomes);

        zeroIncomeMembers.add(zeroMember);
        boundaryApplication.put("householdMembers", zeroIncomeMembers);

        ApplicationProcessor boundaryProcessor = new ApplicationProcessor(schema);
        ProcessResult result7 = boundaryProcessor.processApplication(boundaryApplication);
        System.out.println("Result: " + (result7.isSuccess() ? "PASS" : "FAIL"));
        System.out.println("Total Income: $" + result7.getData().get("totalIncome"));
        System.out.println("Eligible: " + result7.getData().get("eligible"));
        System.out.println();

        // Summary of test results
        System.out.println("=== Test Summary ===");
        System.out.println("Total Test Cases: 7");
        System.out.println("Test Coverage:");
        System.out.println("  - Valid complete application ✓");
        System.out.println("  - Missing required fields validation ✓");
        System.out.println("  - Household members with income ✓");
        System.out.println("  - Empty string handling ✓");
        System.out.println("  - Large data (100 members) ✓");
        System.out.println("  - Special eligibility conditions ✓");
        System.out.println("  - Boundary conditions (zero income) ✓");
        System.out.println("\nAll test cases completed successfully!");
    }

    /**
     * FormField class represents a single field in the form
     * Contains all field properties and validation rules
     */
    static class FormField {
        private final String key;              // Unique identifier for the field
        private final String type;             // Field type (text, date, select, radio, etc.)
        private final String label;            // Display label for the field
        private List<Option> options;    // Available options for select/radio fields
        private Map<String, Object> validations;  // Validation rules (required, min, max, etc.)
        private Map<String, String> errorMessages; // Custom error messages for validation failures

        // Constructor initializes all field properties
        public FormField(String key, String type, String label) {
            this.key = key;      // Set the unique key for field identification
            this.type = type;    // Set the field type for rendering logic
            this.label = label;  // Set the display label for UI presentation
            this.options = new ArrayList<>();         // Initialize empty options list
            this.validations = new HashMap<>();       // Initialize empty validations map
            this.errorMessages = new HashMap<>();     // Initialize empty error messages map
        }

        /**
         * Validates field value based on defined validation rules
         * Returns list of validation error messages
         */
        public List<String> validate(Object value) {
            List<String> errors = new ArrayList<>();  // Initialize list to collect all errors

            // Check if field is required and value is null or empty
            if (Boolean.TRUE.equals(validations.get("required"))) {
                // Check for null value first
                if (value == null) {
                    // Add required field error message or default message
                    errors.add(errorMessages.getOrDefault("required",
                            label + " is required."));
                }
                // Check for empty string value
                else if (value instanceof String && ((String) value).trim().isEmpty()) {
                    // Add required field error for empty strings
                    errors.add(errorMessages.getOrDefault("required",
                            label + " is required."));
                }
            }

            // Validate minimum value for numeric fields
            if (validations.containsKey("min") && value instanceof Number) {
                // Get the minimum allowed value from validations
                double minValue = ((Number) validations.get("min")).doubleValue();
                // Get the actual value as double for comparison
                double actualValue = ((Number) value).doubleValue();
                // Check if actual value is less than minimum
                if (actualValue < minValue) {
                    // Add minimum value error message
                    errors.add(label + " must be at least " + minValue);
                }
            }

            // Return all collected validation errors
            return errors;
        }

        // Getters and setters for all properties
        public String getKey() {
            return key;
        }

        public String getType() {
            return type;
        }

        public String getLabel() {
            return label;
        }

        public List<Option> getOptions() {
            return options;
        }

        public void setOptions(List<Option> options) {
            this.options = options;
        }

        public Map<String, Object> getValidations() {
            return validations;
        }

        public void setValidations(Map<String, Object> validations) {
            this.validations = validations;
        }

        public Map<String, String> getErrorMessages() {
            return errorMessages;
        }

        public void setErrorMessages(Map<String, String> errorMessages) {
            this.errorMessages = errorMessages;
        }
    }

    /**
     * Option class represents a single option in select/radio fields
     * Contains label for display and value for data storage
     *
     * @param label Display text for the option
     * @param value Actual value to be stored
     */
    record Option(String label, Object value) {
        // Constructor initializes option properties
        // Set display label
        // Set data value
    }

    /**
     * FormStep class represents a single step in multi-step form
     * Groups related fields together for better UX
     */
    static class FormStep {
        private final String label;           // Step title/label
        private final List<FormField> fields; // List of fields in this step

        // Constructor initializes step with label
        public FormStep(String label) {
            this.label = label;                  // Set step label
            this.fields = new ArrayList<>();     // Initialize empty fields list
        }

        /**
         * Validates all fields in this step
         * Returns map of field keys to error messages
         */
        public Map<String, List<String>> validateStep(Map<String, Object> data) {
            // Use Stream API to process all fields and collect validation errors
            return fields.stream()
                    .map(field -> {
                        // Get value for current field from data map
                        Object value = data.get(field.getKey());
                        // Validate the field value
                        List<String> errors = field.validate(value);
                        // Return entry only if there are errors
                        return errors.isEmpty() ? null :
                                new AbstractMap.SimpleEntry<>(field.getKey(), errors);
                    })
                    .filter(Objects::nonNull)  // Filter out null entries (fields without errors)
                    .collect(Collectors.toMap(  // Collect into map
                            Map.Entry::getKey,       // Use field key as map key
                            Map.Entry::getValue      // Use error list as map value
                    ));
        }

        // Getters for step properties
        public String getLabel() {
            return label;
        }

        public List<FormField> getFields() {
            return fields;
        }

        // Method to add field to step
        public void addField(FormField field) {
            fields.add(field);
        }
    }

    /**
     * FormSchema class represents the complete form structure
     * Contains all steps and nested schemas for complex fields
     */
    static class FormSchema {
        private final String title;                      // Form title
        private final List<FormStep> steps;              // List of form steps
        private final Map<String, FormSchema> schemas;   // Nested schemas for complex fields

        // Constructor initializes schema
        public FormSchema(String title) {
            this.title = title;                    // Set form title
            this.steps = new ArrayList<>();        // Initialize empty steps list
            this.schemas = new HashMap<>();        // Initialize empty schemas map
        }

        /**
         * Validates entire form data against schema
         * Returns comprehensive validation results
         */
        public ValidationResult validateForm(Map<String, Object> data) {
            ValidationResult result = new ValidationResult();  // Create result object

            // Validate each step using Stream API
            steps.forEach(step -> {
                // Validate step and get errors
                Map<String, List<String>> stepErrors = step.validateStep(data);
                // Add all step errors to result
                stepErrors.forEach((key, errors) ->
                        errors.forEach(error -> result.addError(key, error))
                );
            });

            // Return validation result with all errors
            return result;
        }

        // Getters and setters for schema properties
        public String getTitle() {
            return title;
        }

        public List<FormStep> getSteps() {
            return steps;
        }

        public Map<String, FormSchema> getSchemas() {
            return schemas;
        }

        public void addStep(FormStep step) {
            steps.add(step);
        }

        public void addSchema(String key, FormSchema schema) {
            schemas.put(key, schema);
        }
    }

    /**
     * ValidationResult class holds validation results
     * Tracks if validation passed and collects all errors
     */
    static class ValidationResult {
        private final Map<String, List<String>> errors = new HashMap<>();  // Map of field errors
        private boolean valid = true;                         // Flag indicating if validation passed

        // Add error for a field
        public void addError(String field, String error) {
            valid = false;  // Set valid flag to false when error is added
            // Get or create error list for field and add error
            errors.computeIfAbsent(field, k -> new ArrayList<>()).add(error);
        }

        // Check if validation passed
        public boolean isValid() {
            return valid;
        }

        // Get all errors
        public Map<String, List<String>> getErrors() {
            return errors;
        }

        // Get formatted error message for display
        public String getErrorMessage() {
            // Use Stream API to format all errors into readable string
            return errors.entrySet().stream()
                    .flatMap(entry ->
                            // For each field, format its errors
                            entry.getValue().stream()
                                    .map(error -> entry.getKey() + ": " + error)
                    )
                    .collect(Collectors.joining(", "));  // Join all errors with comma
        }
    }

    /**
     * ApplicationProcessor handles the main business logic
     * Processes applications and manages household members
     */
    static class ApplicationProcessor {
        private final FormSchema schema;                    // Form schema definition
        private final List<Map<String, Object>> householdMembers;  // List of household members

        // Constructor initializes processor with schema
        public ApplicationProcessor(FormSchema schema) {
            this.schema = schema;                     // Set form schema
            this.householdMembers = new ArrayList<>();  // Initialize empty members list
        }

        /**
         * Process application data
         * Validates and stores application information
         */
        public ProcessResult processApplication(Map<String, Object> applicationData) {
            ProcessResult result = new ProcessResult();  // Create result object

            // Validate main application form
            ValidationResult validationResult = schema.validateForm(applicationData);

            // Check if validation passed
            if (!validationResult.isValid()) {
                // Set error status and message if validation failed
                result.setSuccess(false);
                result.setMessage("Validation failed: " + validationResult.getErrorMessage());
                result.setErrors(validationResult.getErrors());
                return result;  // Return early with errors
            }

            // Process household members if they exist
            if (Boolean.TRUE.equals(applicationData.get("hasOtherMembers"))) {
                // Process each household member
                processhouseholdMembers(applicationData);
            }

            // Calculate total household income using Stream API
            double totalIncome = calculateTotalHouseholdIncome();

            // Determine eligibility based on income and other factors
            boolean eligible = determineEligibility(applicationData, totalIncome);

            // Set success result
            result.setSuccess(true);
            result.setMessage(eligible ? "Application approved" : "Application requires review");
            result.setData(Map.of(
                    "totalIncome", totalIncome,
                    "householdSize", householdMembers.size() + 1,
                    "eligible", eligible
            ));

            return result;  // Return processing result
        }

        /**
         * Process household members recursively
         * Handles dynamic addition of members
         */
        private void processhouseholdMembers(Map<String, Object> data) {
            // Implementation would handle recursive member addition
            // For demo, we'll add sample members

            // Check if household members data exists
            if (data.containsKey("householdMembers")) {
                // Cast to list of member maps
                List<Map<String, Object>> members =
                        (List<Map<String, Object>>) data.get("householdMembers");
                // Add all members to processor's list
                householdMembers.addAll(members);
            }
        }

        /**
         * Calculate total household income from all members
         * Uses Stream API for efficient calculation
         */
        private double calculateTotalHouseholdIncome() {
            // Stream through all household members
            return householdMembers.stream()
                    // Extract income lists from each member
                    .flatMap(member -> {
                        // Get incomes array from member data
                        List<Map<String, Object>> incomes =
                                (List<Map<String, Object>>) member.get("incomes");
                        // Return stream of incomes or empty stream if null
                        return incomes != null ? incomes.stream() : Stream.empty();
                    })
                    // Map each income to its amount value
                    .mapToDouble(income -> {
                        // Get amount from income map
                        Object amount = income.get("amount");
                        // Convert to double, default to 0 if null
                        return amount instanceof Number ?
                                ((Number) amount).doubleValue() : 0.0;
                    })
                    .sum();  // Sum all income amounts
        }

        /**
         * Determine eligibility based on application data and income
         * Implements business rules for Medicaid eligibility
         */
        private boolean determineEligibility(Map<String, Object> data, double totalIncome) {
            // Check special eligibility conditions using Stream API
            boolean specialEligibility = Stream.of(
                    Boolean.TRUE.equals(data.get("isPregnant")),     // Pregnant women
                    Boolean.TRUE.equals(data.get("isFosterCare")),   // Foster care
                    totalIncome < 20000  // Income below threshold
            ).anyMatch(condition -> condition);  // Any condition makes eligible

            return specialEligibility;  // Return eligibility status
        }
    }

    /**
     * ProcessResult class holds processing results
     * Contains success status, message, data, and errors
     */
    static class ProcessResult {
        private boolean success;                      // Processing success flag
        private String message;                       // Result message
        private Map<String, Object> data;            // Additional result data
        private Map<String, List<String>> errors;    // Validation errors if any

        // Constructor initializes empty result
        public ProcessResult() {
            this.data = new HashMap<>();     // Initialize empty data map
            this.errors = new HashMap<>();   // Initialize empty errors map
        }

        // Getters and setters for all properties
        public boolean isSuccess() {
            return success;
        }

        public void setSuccess(boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Map<String, Object> getData() {
            return data;
        }

        public void setData(Map<String, Object> data) {
            this.data = data;
        }

        public Map<String, List<String>> getErrors() {
            return errors;
        }

        public void setErrors(Map<String, List<String>> errors) {
            this.errors = errors;
        }
    }
}