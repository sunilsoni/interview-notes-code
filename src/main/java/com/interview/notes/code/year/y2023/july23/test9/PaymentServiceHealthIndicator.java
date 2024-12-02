package com.interview.notes.code.year.y2023.july23.test9;


import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component("payment-service")
public class PaymentServiceHealthIndicator implements HealthIndicator {

    private final PaymentService paymentService;

    public PaymentServiceHealthIndicator(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @Override
    public Health health() {
        try {
            PaymentServiceResponse response = paymentService.check();

            switch (response.getStatus()) {
                case "OK":
                    return Health.up().build();
                case "NOOK":
                    return Health.down()
                            .withDetail("reason", response.getReason())
                            .withDetail("eta", response.getEta().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME))
                            .build();
                default:
                    return Health.unknown().build();
            }
        } catch (PaymentServiceException e) {
            return Health.down()
                    .withDetail("error", e.getClass().getName() + ": " + e.getMessage())
                    .build();
        }
    }
}
