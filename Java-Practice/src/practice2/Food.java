package practice2;

import java.time.LocalDate;

public class Food extends Goods {
    private final LocalDate manufactureDate;
    private final LocalDate expirationDate;
    private final String supplier;

    public Food(String code, String name, int quantity, double unitPrice,
                LocalDate manufactureDate, LocalDate expirationDate, String supplier) {
        super(code, name, quantity, unitPrice);
        if (expirationDate.isBefore(manufactureDate)) {
            throw new IllegalArgumentException("Expiration date must be after manufacture date");
        }
        this.manufactureDate = manufactureDate;
        this.expirationDate = expirationDate;
        this.supplier = supplier;
    }

    @Override
    public double calculateVat() {
        return unitPrice * 0.05;
    }

    @Override
    public String evaluate(LocalDate today) {
        return quantity > 0 && expirationDate.isBefore(today) ? "Hard to sell" : "Not evaluated";
    }

    @Override
    public String toString() {
        return super.toString() + " - manufactured: " + manufactureDate
                + " - expires: " + expirationDate + " - supplier: " + supplier;
    }
}

