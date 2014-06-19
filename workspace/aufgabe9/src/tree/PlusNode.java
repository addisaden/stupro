package tree;

public class PlusNode extends BinOpNode {
    public PlusNode(Node left, Node right) {
        super(left, right);
    }

    public double value() {
        // TODO korrigieren
        return 0.0;
    }
    
    protected String operator() {
        return "+";
    }
}
