package pizzashop.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;

import java.io.File;
import java.io.FileWriter;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentRepositoryTestStep1 {

    PaymentRepository repoSpy;
    private final String testFile = "ignored.txt";

    @BeforeEach
    void setUp() throws Exception {
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("");
        }

        repoSpy = spy(new PaymentRepository(testFile));
        lenient().doNothing().when(repoSpy).writeAll();
    }

    @AfterEach
    void tearDown() {
        new File(testFile).delete();
    }

    @Test
    void getAll() {
        assertEquals(0, repoSpy.getAll().size());
    }

    @Test
    void add() {
        Payment payment = getPayment(3, PaymentType.Cash, 33.3);

        repoSpy.add(payment);
        List<Payment> paymentList = repoSpy.getAll();

        assertEquals(1, paymentList.size());
        assertEquals(payment.getTableNumber(), paymentList.get(0).getTableNumber());
        assertEquals(payment.getType(), paymentList.get(0).getType());
        assertEquals(payment.getAmount(), paymentList.get(0).getAmount());
    }

    Payment getPayment(int tableNumber, PaymentType type, double amount) {
        Payment payment = mock(Payment.class);
        when(payment.getTableNumber()).thenReturn(tableNumber);
        when(payment.getType()).thenReturn(type);
        when(payment.getAmount()).thenReturn(amount);
        return payment;
    }
}
