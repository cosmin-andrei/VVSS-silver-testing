package pizzashop.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.PaymentRepository;
import pizzashop.validator.PaymentValidator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTestStep2 {

    PaymentRepository payRepo;
    PaymentService service;
    PaymentValidator val;

    final String testFile = "test_repo.txt";

    @BeforeEach
    void setUp() throws IOException {
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("");
        }
        payRepo=new PaymentRepository(testFile);
        val = new PaymentValidator();
        service=new PaymentService(null,payRepo,val);
    }

    @AfterEach
    void tearDown() {
        payRepo.getAll().clear();
        payRepo.writeAll();
        new File(testFile).delete();
    }

    @Test
    void getPayments() {
        List<Payment> result=service.getPayments();
        assertEquals(0,result.size());
    }

    @Test
    void addPayment() {
        Payment payment = mock(Payment.class);
        when(payment.getTableNumber()).thenReturn(3);
        when(payment.getType()).thenReturn(PaymentType.Cash);
        when(payment.getAmount()).thenReturn(3.0);

        service.addPayment(payment.getTableNumber(),payment.getType(),payment.getAmount());

        List<Payment> result = service.getPayments();
        assertEquals(1, result.size());
        assertEquals(payment.getTableNumber(), result.get(0).getTableNumber());
        assertEquals(payment.getType(), result.get(0).getType());
        assertEquals(payment.getAmount(), result.get(0).getAmount());
    }
}