package tree;

public class MinusNode extends BinOpNode {
    public MinusNode(Node left, Node right) {
        super(left, right);
    }

    public double value() {
        // TODO korrigieren
        return 0.0;
    }
    
    protected String operator() {
        return "-";
    }
}
