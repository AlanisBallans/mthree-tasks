public class Triangle extends Shape {
    private int sideLength;

    public Triangle(int sideLength) {
        this.sideLength = sideLength;
    }

    @Override
    public double getArea() {
        return (float) (sideLength * sideLength) / 2;
    }

    @Override
    public double getPerimeter() {
        return sideLength * 3;
    }
}
