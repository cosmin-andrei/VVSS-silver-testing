package pizzashop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTestStep1 {

    Payment payment;

    @BeforeEach
    void setUp() {
        payment = new Payment(3, PaymentType.Cash, 25.5);
    }

    @Test
    void testGetAndSetTableNumber() {
        assertEquals(3, payment.getTableNumber());
        payment.setTableNumber(5);
        assertEquals(5, payment.getTableNumber());
    }

    @Test
    void testGetAndSetType() {
        assertEquals(PaymentType.Cash, payment.getType());
        payment.setType(PaymentType.Card);
        assertEquals(PaymentType.Card, payment.getType());
    }

    @Test
    void testGetAndSetAmount() {
        assertEquals(25.5, payment.getAmount());
        payment.setAmount(30.0);
        assertEquals(30.0, payment.getAmount());
    }

    @Test
    void testToString() {
        String expected = "3,Cash,25.5";
        assertEquals(expected, payment.toString());
    }
}
