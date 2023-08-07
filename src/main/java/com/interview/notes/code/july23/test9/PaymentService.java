package com.interview.notes.code.july23.test9;

import org.springframework.stereotype.Component;

@Component
public interface PaymentService {
    PaymentServiceResponse check();
}
