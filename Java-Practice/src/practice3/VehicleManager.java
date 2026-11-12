package practice3;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class VehicleManager {
    private final List<Vehicle> vehicles = new ArrayList<>();

    public boolean add(Vehicle vehicle) {
        if (findByNumber(vehicle.getVehicleNumber()) != null) {
            return false;
        }
        vehicles.add(vehicle);
        return true;
    }

    public Vehicle findByNumber(String number) {
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getVehicleNumber().equalsIgnoreCase(number)) {
                return vehicle;
            }
        }
        return null;
    }

    public List<Vehicle> findByOwnerId(String identityNumber) {
        List<Vehicle> result = new ArrayList<>();
        for (Vehicle vehicle : vehicles) {
            if (vehicle.getOwner().getIdentityNumber().equals(identityNumber)) {
                result.add(vehicle);
            }
        }
        return result;
    }

    public void deleteByManufacturer(String manufacturer) {
        vehicles.removeIf(vehicle -> vehicle.getManufacturer().equalsIgnoreCase(manufacturer));
    }

    public String findManufacturerWithMostVehicles() {
        Map<String, Integer> counts = new HashMap<>();
        for (Vehicle vehicle : vehicles) {
            counts.put(vehicle.getManufacturer(),
                    counts.getOrDefault(vehicle.getManufacturer(), 0) + 1);
        }
        return counts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey)
                .orElse("No data");
    }

    public void sortByVehicleNumberDescending() {
        vehicles.sort(Comparator.comparing(Vehicle::getVehicleNumber).reversed());
    }

    public void printStatistics() {
        long cars = vehicles.stream().filter(vehicle -> vehicle instanceof Car).count();
        long motorbikes = vehicles.stream().filter(vehicle -> vehicle instanceof Motorbike).count();
        long trucks = vehicles.stream().filter(vehicle -> vehicle instanceof Truck).count();
        System.out.println("Cars: " + cars);
        System.out.println("Motorbikes: " + motorbikes);
        System.out.println("Trucks: " + trucks);
    }

    public void display() {
        vehicles.forEach(System.out::println);
    }
}

