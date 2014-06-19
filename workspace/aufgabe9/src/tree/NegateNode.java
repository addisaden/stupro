package tree;

public class NegateNode implements Node {
    private Node expression;
    
    public NegateNode(Node expression) {
        this.expression = expression;
    }
    
    public double value() {
        // TODO korrigieren
        return 0.0;
    }
    
    @Override
    public String toString() {
        return "";
    }
}
