public class Circle extends Shape {
    private int radius;

    public Circle(int radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.round(3.14 * radius * radius);
    }

    @Override
    public double getPerimeter() {
        return 3.14 * radius * 2;
    }
}
