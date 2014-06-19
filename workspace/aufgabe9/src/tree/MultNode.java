package tree;

public class MultNode extends BinOpNode {
    public MultNode(Node left, Node right) {
        super(left, right);
    }

    public double value() {
        return (this.left.value() * this.right.value());
    }

    protected String operator() {
        return "*";
    }
}
