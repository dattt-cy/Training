package practice2;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        LocalDate today = LocalDate.now();
        GoodsList goodsList = new GoodsList();

        Goods food = new Food("F01", "Milk", 10, 25_000,
                today.minusDays(10), today.minusDays(1), "Vinamilk");
        Goods electronic = new Electronic("E01", "Television", 2, 8_000_000, 24, 0.15);
        Goods crockery = new Crockery("C01", "Plate", 60, 30_000,
                "Bat Trang", today.minusDays(20));

        goodsList.add(food);
        goodsList.add(electronic);
        goodsList.add(crockery);
        goodsList.display();

        System.out.println("Food: " + food.evaluate(today));
        System.out.println("Electronic: " + electronic.evaluate(today));
        System.out.println("Crockery: " + crockery.evaluate(today));
        System.out.println("Total quantity: " + goodsList.getTotalQuantity());
    }
}

