package tree;

public class MinusNode extends BinOpNode {
    public MinusNode(Node left, Node right) {
        super(left, right);
    }

    public double value() {
        return (this.left.value() - this.right.value());
    }
    
    protected String operator() {
        return "-";
    }
}
