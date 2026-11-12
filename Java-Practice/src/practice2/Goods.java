package practice2;

import java.time.LocalDate;

public abstract class Goods {
    protected String code;
    protected String name;
    protected int quantity;
    protected double unitPrice;

    public Goods(String code, String name, int quantity, double unitPrice) {
        if (quantity < 0 || unitPrice < 0) {
            throw new IllegalArgumentException("Quantity and unit price must be >= 0");
        }
        this.code = code;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }

    public String getCode() {
        return code;
    }

    public int getQuantity() {
        return quantity;
    }

    public abstract double calculateVat();

    public abstract String evaluate(LocalDate today);

    @Override
    public String toString() {
        return code + " - " + name + " - quantity: " + quantity
                + " - unit price: " + unitPrice + " - VAT: " + calculateVat();
    }
}

