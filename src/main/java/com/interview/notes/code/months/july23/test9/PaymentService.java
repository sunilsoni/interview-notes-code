package com.interview.notes.code.months.july23.test9;

import org.springframework.stereotype.Component;

@Component
public interface PaymentService {
    PaymentServiceResponse check();
}
