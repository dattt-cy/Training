package practice1;

public class Main {
    public static void main(String[] args) {
        Shape shape = new Shape(4, 5);
        Rectangle rectangle = new Rectangle(4, 5);
        Circle circle = new Circle(3);

        System.out.println(shape);
        System.out.println(rectangle);
        System.out.println(circle);
    }
}

