package shapes;

public final class Rectangle implements IShape {
    private final String name;
    private final double laenge;
    private final double breite;
    
    public Rectangle(String name, double laenge, double breite)
    {
    	this.name = name;
    	this.laenge = laenge;
    	this.breite = breite;
    }
    
    @Override
    public String getName()
    {
    	return this.name;
    }
    
    @Override
    public double getArea()
    {
    	return laenge * breite;
    }
    
    @Override
    public String toString() {
        return "Rectangle."+ getName();
    }
    
    @Override
    public int compareTo(IShape other)
    {
    	return (int) Math.signum(this.getArea() - other.getArea());
    }
}
