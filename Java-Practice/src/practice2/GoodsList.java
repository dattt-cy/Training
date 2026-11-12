package practice2;

public class GoodsList {
    private final Goods[] list = new Goods[100];
    private int count = 0;

    public boolean add(Goods goods) {
        if (count == list.length || findByCode(goods.getCode()) != null) {
            return false;
        }
        list[count++] = goods;
        return true;
    }

    public Goods findByCode(String code) {
        for (int i = 0; i < count; i++) {
            if (list[i].getCode().equalsIgnoreCase(code)) {
                return list[i];
            }
        }
        return null;
    }

    public int getTotalQuantity() {
        int total = 0;
        for (int i = 0; i < count; i++) {
            total += list[i].getQuantity();
        }
        return total;
    }

    public void display() {
        for (int i = 0; i < count; i++) {
            System.out.println(list[i]);
        }
    }
}

