package practice3;

import java.time.Year;
import java.util.List;

public abstract class Vehicle {
    private static final List<String> MANUFACTURERS =
            List.of("Honda", "Yamaha", "Toyota", "Suzuki");

    protected String vehicleNumber;
    protected String manufacturer;
    protected int manufactureYear;
    protected String color;
    protected Owner owner;

    public Vehicle(String vehicleNumber, String manufacturer, int manufactureYear,
                   String color, Owner owner) {
        if (vehicleNumber.length() != 5) {
            throw new IllegalArgumentException("Vehicle number must contain 5 characters");
        }
        if (!MANUFACTURERS.contains(manufacturer)) {
            throw new IllegalArgumentException("Manufacturer must be Honda, Yamaha, Toyota or Suzuki");
        }
        if (manufactureYear <= 2000 || manufactureYear > Year.now().getValue()) {
            throw new IllegalArgumentException("Invalid manufacture year");
        }
        this.vehicleNumber = vehicleNumber;
        this.manufacturer = manufacturer;
        this.manufactureYear = manufactureYear;
        this.color = color;
        this.owner = owner;
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Owner getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return vehicleNumber + " - " + manufacturer + " - " + manufactureYear
                + " - " + color + " - owner: " + owner;
    }
}

