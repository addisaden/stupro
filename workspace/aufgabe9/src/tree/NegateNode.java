package tree;

public class NegateNode implements Node {
    private Node expression;
    
    public NegateNode(Node expression) {
        this.expression = expression;
    }
    
    public double value() {
        return -(expression.value());
    }
    
    @Override
    public String toString() {
        return "";
    }
}
