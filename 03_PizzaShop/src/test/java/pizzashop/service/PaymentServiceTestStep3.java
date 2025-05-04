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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class PaymentServiceTestStep3 {

    PaymentRepository payRepo;

    PaymentService service;

    PaymentValidator val;

    @BeforeEach
    void setUp() {
        payRepo=new PaymentRepository();
        val = new PaymentValidator();
        service=new PaymentService(null,payRepo,val);
    }

    @AfterEach
    void tearDown() {
        payRepo.getAll().clear();
        payRepo.writeAll();
    }

    @Test
    void getPayments() {
        List<Payment> result=service.getPayments();
        assertEquals(0,result.size());
    }

    @Test
    void addPayment() {
        Payment payment=new Payment(3,PaymentType.Cash,3);

        service.addPayment(payment.getTableNumber(),payment.getType(),payment.getAmount());

        List<Payment> result=service.getPayments();
        assertEquals(1,result.size());
        assertEquals(payment.getTableNumber(), result.get(0).getTableNumber());
        assertEquals(payment.getType(), result.get(0).getType());
        assertEquals(payment.getAmount(), result.get(0).getAmount());
    }
}