package practice2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Crockery extends Goods {
    private final String manufacturer;
    private final LocalDate arrivalDate;

    public Crockery(String code, String name, int quantity, double unitPrice,
                    String manufacturer, LocalDate arrivalDate) {
        super(code, name, quantity, unitPrice);
        this.manufacturer = manufacturer;
        this.arrivalDate = arrivalDate;
    }

    @Override
    public double calculateVat() {
        return unitPrice * 0.10;
    }

    @Override
    public String evaluate(LocalDate today) {
        long storageDays = ChronoUnit.DAYS.between(arrivalDate, today);
        return quantity > 50 && storageDays > 10 ? "Slow selling" : "Not evaluated";
    }

    @Override
    public String toString() {
        return super.toString() + " - manufacturer: " + manufacturer
                + " - arrival date: " + arrivalDate;
    }
}

