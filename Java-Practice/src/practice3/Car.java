package practice3;

public class Car extends Vehicle {
    private final int numberOfSeats;
    private final String engineType;

    public Car(String vehicleNumber, String manufacturer, int manufactureYear,
               String color, Owner owner, int numberOfSeats, String engineType) {
        super(vehicleNumber, manufacturer, manufactureYear, color, owner);
        this.numberOfSeats = numberOfSeats;
        this.engineType = engineType;
    }

    @Override
    public String toString() {
        return "Car: " + super.toString() + " - seats: " + numberOfSeats
                + " - engine: " + engineType;
    }
}

