package tree;

public class DivNode extends BinOpNode {
    public DivNode(Node left, Node right) {
        super(left, right);
    }

    public double value() {
        return (this.left.value() / this.right.value());
    }
    
    protected String operator() {
        return "/";
    }    
}
