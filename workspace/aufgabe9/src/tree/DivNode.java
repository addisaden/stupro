package tree;

public class DivNode extends BinOpNode {
    public DivNode(Node left, Node right) {
        super(left, right);
    }

    public double value() {
        // 
        return 0.0;
    }
    
    protected String operator() {
        return "/";
    }    
}
