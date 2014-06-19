package tree;

public class PlusNode extends BinOpNode {
    public PlusNode(Node left, Node right) {
        super(left, right);
    }

    public double value() {
        return (this.left.value() + this.right.value());
    }
    
    protected String operator() {
        return "+";
    }
}
