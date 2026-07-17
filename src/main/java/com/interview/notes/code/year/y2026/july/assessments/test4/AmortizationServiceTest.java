//package com.interview.notes.code.year.y2026.july.assessments.test4;
//
//import com.crestline.analytics.model.AmortizationSchedule;
//import com.crestline.analytics.model.LoanRequest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class AmortizationServiceTest {
//
//    private AmortizationService service;
//
//    @BeforeEach
//    void setUp() {
//        service = new AmortizationService();
//    }
//
//    @Test
//    void testMonthlyPayment_standardFixedRate() {
//        LoanRequest request = new LoanRequest(300_000, 6.0, 360);
//
//        double payment = service.calculateMonthlyPayment(request);
//
//        assertEquals(1798.65, payment, 0.01);
//    }
//
//    @Test
//    void testMonthlyPayment_zeroInterestRate() {
//        LoanRequest request = new LoanRequest(200_000, 0.0, 120);
//
//        double payment = service.calculateMonthlyPayment(request);
//
//        assertEquals(1666.67, payment, 0.01);
//    }
//
//    @Test
//    void testMonthlyPayment_zeroPrincipal() {
//        LoanRequest request = new LoanRequest(0, 5.0, 240);
//
//        double payment = service.calculateMonthlyPayment(request);
//
//        assertEquals(0.0, payment, 0.001);
//    }
//
//    @Test
//    void testBuildSchedule_entryCount() {
//        LoanRequest request = new LoanRequest(10_000, 10.0, 3);
//
//        AmortizationSchedule schedule = service.buildSchedule(request);
//
//        assertEquals(3, schedule.getEntries().size());
//    }
//
//    @Test
//    void testBuildSchedule_firstEntry_interestPortion() {
//        LoanRequest request = new LoanRequest(10_000, 10.0, 3);
//
//        AmortizationSchedule.Entry first =
//                service.buildSchedule(request).getEntries().get(0);
//
//        assertEquals(83.33, first.getInterestPaid(), 0.01);
//    }
//
//    @Test
//    void testBuildSchedule_firstEntry_paymentNumber() {
//        LoanRequest request = new LoanRequest(10_000, 10.0, 3);
//
//        AmortizationSchedule.Entry first =
//                service.buildSchedule(request).getEntries().get(0);
//
//        assertEquals(1, first.getPaymentNumber());
//    }
//
//    @Test
//    void testBuildSchedule_balanceDecreases() {
//        LoanRequest request = new LoanRequest(1_200, 0.0, 12);
//
//        List<AmortizationSchedule.Entry> entries =
//                service.buildSchedule(request).getEntries();
//
//        for (AmortizationSchedule.Entry entry : entries) {
//            assertTrue(
//                    entry.getClosingBalance() < entry.getOpeningBalance(),
//                    "Balance did not decrease for payment "
//                            + entry.getPaymentNumber()
//            );
//        }
//    }
//
//    @Test
//    void testBuildSchedule_finalBalanceNearZero() {
//        LoanRequest request = new LoanRequest(1_200, 0.0, 12);
//
//        List<AmortizationSchedule.Entry> entries =
//                service.buildSchedule(request).getEntries();
//
//        AmortizationSchedule.Entry last =
//                entries.get(entries.size() - 1);
//
//        assertEquals(0.0, last.getClosingBalance(), 0.01);
//    }
//
//    @Test
//    void testEarlyPayoffSavings_isPositive() {
//        LoanRequest request = new LoanRequest(400_000, 5.5, 360);
//
//        double savings =
//                service.calculateEarlyPayoffSavings(request, 60);
//
//        assertTrue(savings > 0);
//    }
//
//    @Test
//    void testEarlyPayoffSavings_payoffAtLastMonth_isZeroOrNearZero() {
//        LoanRequest request = new LoanRequest(400_000, 5.5, 360);
//
//        double savings =
//                service.calculateEarlyPayoffSavings(request, 360);
//
//        assertEquals(0.0, savings, 0.01);
//    }
//
//    @Test
//    void testEarlyPayoffSavings_earlierPayoffYieldsGreaterSavings() {
//        LoanRequest request = new LoanRequest(400_000, 5.5, 360);
//
//        double month60Savings =
//                service.calculateEarlyPayoffSavings(request, 60);
//
//        double month120Savings =
//                service.calculateEarlyPayoffSavings(request, 120);
//
//        assertTrue(month60Savings > month120Savings);
//    }
//
//    @Test
//    void testEarlyPayoffSavings_zeroInterest_isZero() {
//        LoanRequest request = new LoanRequest(120_000, 0.0, 120);
//
//        double savings =
//                service.calculateEarlyPayoffSavings(request, 60);
//
//        assertEquals(0.0, savings, 0.001);
//    }
//}