package practice3;

public class Main {
    public static void main(String[] args) {
        Owner owner1 = new Owner("012345678901", "Nguyen Van An", "an@gmail.com");
        Owner owner2 = new Owner("012345678902", "Tran Thi Binh", "binh@gmail.com");

        VehicleManager manager = new VehicleManager();
        manager.add(new Car("30A01", "Toyota", 2024, "Black", owner1, 5, "Gasoline"));
        manager.add(new Motorbike("29B02", "Honda", 2023, "Red", owner1, 150));
        manager.add(new Truck("51C03", "Toyota", 2022, "White", owner2, 5));

        System.out.println("Find vehicle 30A01: " + manager.findByNumber("30A01"));
        System.out.println("Vehicles of owner 012345678901:");
        manager.findByOwnerId("012345678901").forEach(System.out::println);
        System.out.println("Manufacturer with most vehicles: "
                + manager.findManufacturerWithMostVehicles());

        manager.sortByVehicleNumberDescending();
        System.out.println("Vehicles sorted in descending order:");
        manager.display();
        manager.printStatistics();

        manager.deleteByManufacturer("Toyota");
        System.out.println("After deleting Toyota vehicles:");
        manager.display();
    }
}

