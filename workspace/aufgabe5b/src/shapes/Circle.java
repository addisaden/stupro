package shapes;

public final class Circle implements IShape {
    private final double radius;
    private final String name;

    public Circle(String name, double radius) {
        this.name = name;
        this.radius = radius;
    }

    @Override
    public double getArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public String toString() {
        return "Circle."+ getName();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int compareTo(IShape that) {
        return (int) Math.signum(this.getArea() - that.getArea());
    }
}
