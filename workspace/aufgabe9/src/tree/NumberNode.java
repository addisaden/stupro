package tree;

public class NumberNode implements Node {
    private double number;

    public NumberNode(double number) {
        this.number = number;
    }
    
    @Override
    public String toString() {
        return (new Double(this.number)).toString();
    }
    
    public double value() {
        return this.number;
    }
}
