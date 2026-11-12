package practice1;

public class Rectangle extends Shape {
    public Rectangle(double width, double height) {
        super(width, height);
    }

    public double getArea() {
        return width * height;
    }

    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public String toString() {
        return "Rectangle{width=" + width + ", height=" + height
                + ", area=" + getArea() + ", perimeter=" + getPerimeter() + '}';
    }
}

