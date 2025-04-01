package pizzashop.validator;

import pizzashop.model.PaymentType;

public class PaymentValidator {

    public static void validate(int table, double amount) {

        if (table < 1 || table > 8) {
            throw new IllegalArgumentException("Table number must be between 1 and 8.");
        }

        if (amount <= 0.0 || amount > 500.0) {
            throw new IllegalArgumentException("Amount must be between 0 and 500.");
        }
    }
}
