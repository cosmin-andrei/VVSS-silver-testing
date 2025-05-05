package pizzashop.service;

import org.junit.jupiter.api.*;
import pizzashop.model.Payment;
import pizzashop.model.PaymentType;
import pizzashop.repository.MenuRepository;
import pizzashop.repository.PaymentRepository;
import pizzashop.validator.PaymentValidator;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@DisplayName("White Box Testing - getTotalAmount")
public class PaymentServiceWBTTest {

    private PaymentService paymentService;

    static class TestablePaymentService extends PaymentService {
        private List<Payment> customPayments; //lista plati

        //constructor pt constructor superclasa (PaymentService)
        public TestablePaymentService(MenuRepository menuRepo, PaymentRepository paymentRepo, PaymentValidator validator) {
            super(menuRepo, paymentRepo, validator);
        }

        //setez lista de payments
        public void setPayments(List<Payment> payments) {
            this.customPayments = payments;
        }

        //obtin lista -suprascriu metoda
        @Override
        public List<Payment> getPayments() {
            return customPayments;
        }
    }

    //initializez obiectele
    @BeforeEach
    void setUp() {
        paymentService = new TestablePaymentService(
                new MenuRepository(),
                new PaymentRepository("data/test_payments.txt"),
                new PaymentValidator()
        );
    }

    @Test
    @DisplayName("Test 1 - l=null")
    void testGetTotalAmount_ListNull() {
        ((TestablePaymentService) paymentService).setPayments(null);
        assertEquals(0.0, paymentService.getTotalAmount(PaymentType.Cash));
        assertEquals(0.0, paymentService.getTotalAmount(PaymentType.Card));
    }

    @Test
    @DisplayName("Test 2 - size(l) = 0")
    void testGetTotalAmount_EmptyList() {
        ((TestablePaymentService) paymentService).setPayments(List.of());
        assertEquals(0.0, paymentService.getTotalAmount(PaymentType.Cash));
        assertEquals(0.0, paymentService.getTotalAmount(PaymentType.Card));
    }

    @Test
    @DisplayName("Test 3 - [Payment(1, CASH, 10.0f)]")
    void testGetTotalAmount_OneMatchingCash() {
        Payment p = new Payment(1,PaymentType.Cash, 10.0f);
        ((TestablePaymentService) paymentService).setPayments(List.of(p));
        assertEquals(10.0, paymentService.getTotalAmount(PaymentType.Cash));
    }

    @Test
    @DisplayName("Test 4 - [Payment(1, CASH, 10.0f)]")
    void testGetTotalAmount_OneNonMatchingCard() {
        Payment p = new Payment(1,PaymentType.Cash, 10.0f);
        ((TestablePaymentService) paymentService).setPayments(List.of(p));
        assertEquals(0.0, paymentService.getTotalAmount(PaymentType.Card));
    }

    @Test
    @DisplayName("Test 5 - [Payment(1, CASH, 10.0f), Payment(2,CARD,10.0f)]")
    void testGetTotalAmount_MixedPayments() {
        Payment p1 = new Payment(1,PaymentType.Cash, 10.0f);
        Payment p2 = new Payment(2,PaymentType.Card, 10.0f);
        ((TestablePaymentService) paymentService).setPayments(List.of(p1, p2));
        assertEquals(10.0, paymentService.getTotalAmount(PaymentType.Cash));
    }
}
