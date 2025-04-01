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
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) //ordinea de executie
@DisplayName("Black Box Testing - PaymentService ECP & BVA")
class PaymentServiceTest {

    private PaymentService paymentService;
    private PaymentRepository paymentRepository;

    @BeforeAll
    void initAll() {
        System.out.println("Start PaymentService tests...");
    }

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository("data/test_payments.txt");
        paymentRepository.getAll().clear();
        paymentRepository.writeAll();

        paymentService = new PaymentService(new MenuRepository(), paymentRepository, new PaymentValidator());
    }

    @AfterEach
    void tearDown() {
        paymentRepository.getAll().clear();
        paymentRepository.writeAll();
    }

    @AfterAll
    void tearDownAll() {
        System.out.println("PaymentService tests complete.");
    }

    @Test
    @Order(1)
    @DisplayName("ECP - Valid input: table=2, amount=50.0")
    @Tag("ECP")
    void testAddPayment_ECP_Valid1() {
        paymentService.addPayment(2, PaymentType.Card, 50.0);
        assertEquals(1, paymentService.getPayments().size());
    }

    @Test
    @Order(2)
    @DisplayName("ECP - Invalid table: table=-1")
    @Tag("ECP")
    void testAddPayment_ECP_InvalidTable() {
        // Act + Assert
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(-1, PaymentType.Cash, 30.0);
        });
        assertTrue(e.getMessage().contains("Table number must be between 1 and 8"));
    }

    @Test
    @Order(3)
    @DisplayName("ECP - Invalid amount: amount=-20.0")
    @Tag("ECP")
    void testAddPayment_ECP_InvalidAmount() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(3, PaymentType.Card, -20.0);
        });
        assertTrue(e.getMessage().contains("Amount must be between 0 and 500."));
    }

    @Test
    @Order(4)
    @DisplayName("ECP - Invalid amount: amount=550.0")
    @Tag("ECP")
    void testAddPayment_ECP_TooBigAmount() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(4, PaymentType.Card, 550.0);
        });
        assertTrue(e.getMessage().contains("Amount must be between 0 and 500."));
    }

    @Test
    @Order(5)
    @DisplayName("BVA - Lower bound valid: table=1, amount=0.01")
    @Tag("BVA")
    void testAddPayment_BVA_ValidLowerBound() {
        paymentService.addPayment(3, PaymentType.Cash, 0.01);
        assertEquals(1, paymentService.getPayments().size());
    }

    @Test
    @Order(6)
    @DisplayName("BVA - Upper bound valid: table=8, amount=250.50")
    @Tag("BVA")
    void testAddPayment_BVA_ValidUpperBound() {
        paymentService.addPayment(8, PaymentType.Cash, 250.50);
        assertEquals(1, paymentService.getPayments().size());
    }

    @Test
    @Order(7)
    @DisplayName("BVA - Invalid lower bound: table=0")
    @Tag("BVA")
    void testAddPayment_BVA_InvalidTableLow() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(0, PaymentType.Card, 100.0);
        });
        assertTrue(e.getMessage().contains("Table number must be between 1 and 8"));
    }

    @Test
    @Order(8)
    @DisplayName("BVA - Invalid upper bound: table=9")
    @Tag("BVA")
    void testAddPayment_BVA_InvalidTableHigh() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(9, PaymentType.Card, 100.0);
        });
        assertTrue(e.getMessage().contains("Table number must be between 1 and 8"));
    }

    @Test
    @Order(8)
    @DisplayName("BVA - Invalid upper bound: amount=500.01")
    @Tag("BVA")
    void testAddPayment_BVA_InvalidAmountHigh() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            paymentService.addPayment(1, PaymentType.Card, 500.01);
        });
        assertTrue(e.getMessage().contains("Amount must be between 0 and 500."));
    }


}
