package tree;

public class MultNode extends BinOpNode {
    public MultNode(Node left, Node right) {
        super(left, right);
    }

    public double value() {
        // TODO korrigieren
        return 0.0;
    }

    protected String operator() {
        return "*";
    }
}
