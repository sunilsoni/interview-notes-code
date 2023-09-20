package com.interview.notes.code.months.july23.test9;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PaymentServiceResponse {
    private String status;
    private String reason;
    private LocalDateTime eta;

    // getters and setters
}
