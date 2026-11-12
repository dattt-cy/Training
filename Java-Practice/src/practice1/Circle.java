package practice1;

public class Circle extends Shape {
    private final double radius;

    public Circle(double radius) {
        super(radius * 2, radius * 2);
        this.radius = radius;
    }

    public double getArea() {
        return Math.PI * radius * radius;
    }

    public double getCircumference() {
        return 2 * Math.PI * radius;
    }

    @Override
    public String toString() {
        return "Circle{radius=" + radius + ", area=" + getArea()
                + ", circumference=" + getCircumference() + '}';
    }
}

