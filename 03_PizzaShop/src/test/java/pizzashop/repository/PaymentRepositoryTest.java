//package pizzashop.repository;
//
//import org.junit.jupiter.api.*;
//import org.junit.jupiter.api.Nested;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ValueSource;
//import pizzashop.model.Payment;
//import pizzashop.model.PaymentType;
//
//import java.io.*;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class PaymentRepositoryTest {
//
//    private static final String TEST_FILE = "test_payments.txt";
//    private PaymentRepository paymentRepository;
//
//    @BeforeEach
//    void setUp() throws IOException {
//        BufferedWriter writer = new BufferedWriter(new FileWriter(TEST_FILE));
//        writer.write("");
//        writer.close();
//
//        paymentRepository = new PaymentRepository(TEST_FILE);
//    }
//
//    @AfterEach
//    void tearDown() {
//        File file = new File(TEST_FILE);
//        if (file.exists()) {
//            file.delete();
//        }
//    }
//
//    // -------------------------
//    // ECP (Equivalence Partitioning)
//    // -------------------------
//
//    @Nested
//    @DisplayName("Teste ECP")
//    class ECPTTests {
//
//        @DisplayName("ECP valid: Adăugare cu masă validă și sumă validă")
//        @Test
//        void addPayment_ECP_ValidTableAndAmount() {
//            Payment p = new Payment(3, PaymentType.Card, 100.0);
//            paymentRepository.add(p);
//            assertTrue(paymentRepository.getAll().contains(p));
//        }
//
//        @Test
//        void addPayment_ECP_InvalidTableNumber() {
//            Payment p = new Payment(0, PaymentType.Cash, 100.0);
//            assertThrows(IllegalArgumentException.class, () -> paymentRepository.add(p));
//        }
//
//        @Test
//        void addPayment_ECP_ValidAmount() {
//            Payment p = new Payment(4, PaymentType.Cash, 250.0);
//            paymentRepository.add(p);
//            assertTrue(paymentRepository.getAll().contains(p));
//        }
//
//        @Test
//        void addPayment_ECP_InvalidAmount() {
//            Payment p = new Payment(5, PaymentType.Cash, -10.0);
//            assertThrows(IllegalArgumentException.class, () -> paymentRepository.add(p));
//        }
//    }
//
//    // -------------------------
//    // BVA (Boundary Value Analysis)
//    // -------------------------
//
//    @Nested
//    @DisplayName("Teste BVA")
//    class BVATests {
//
//        @Test
//        void addPayment_BVA_TableLowerValid() {
//            Payment p = new Payment(1, PaymentType.Cash, 99.0);
//            paymentRepository.add(p);
//            assertTrue(paymentRepository.getAll().contains(p));
//        }
//
//        @Test
//        void addPayment_BVA_TableUpperValid() {
//            Payment p = new Payment(8, PaymentType.Cash, 70.0);
//            paymentRepository.add(p);
//            assertTrue(paymentRepository.getAll().contains(p));
//        }
//
//        @Test
//        void addPayment_BVA_AmountLowerInvalid() {
//            Payment p = new Payment(2, PaymentType.Card, 0.0);
//            assertThrows(IllegalArgumentException.class, () -> paymentRepository.add(p));
//        }
//
//        @Test
//        void addPayment_BVA_AmountUpperInvalid() {
//            Payment p = new Payment(2, PaymentType.Cash, 500.01);
//            assertThrows(IllegalArgumentException.class, () -> paymentRepository.add(p));
//        }
//    }
//
//    // -------------------------
//    // Alte adnotări cerute
//    // -------------------------
//
//    @Disabled("Test temporar dezactivat pentru depanare")
//    @Test
//    void testTemporar() {
//        fail("Nu ar trebui să ruleze");
//    }
//
//    @ParameterizedTest
//    @ValueSource(ints = {-5, 0, 9})
//    @DisplayName("Testare parametrizată: mese invalide")
//    void testTableNumberInvalid_Parametrized(int tableNumber) {
//        Payment p = new Payment(tableNumber, PaymentType.Cash, 100.0);
//        assertThrows(IllegalArgumentException.class, () -> paymentRepository.add(p));
//    }
//}
