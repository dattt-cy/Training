package practice2;

import java.time.LocalDate;

public class Electronic extends Goods {
    private final int warrantyMonths;
    private final double power;

    public Electronic(String code, String name, int quantity, double unitPrice,
                      int warrantyMonths, double power) {
        super(code, name, quantity, unitPrice);
        if (warrantyMonths < 0 || power < 0) {
            throw new IllegalArgumentException("Warranty and power must be >= 0");
        }
        this.warrantyMonths = warrantyMonths;
        this.power = power;
    }

    @Override
    public double calculateVat() {
        return unitPrice * 0.10;
    }

    @Override
    public String evaluate(LocalDate today) {
        return quantity < 3 ? "Sold well" : "Not evaluated";
    }

    @Override
    public String toString() {
        return super.toString() + " - warranty: " + warrantyMonths
                + " months - power: " + power + " kW";
    }
}

