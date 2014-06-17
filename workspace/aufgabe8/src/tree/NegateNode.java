package tree;

import util.LinkedObjects;
import vm.OpCode;

public class NegateNode implements Node {
    private Node expression;
    
    public NegateNode(Node expression) {
        this.expression = expression;
    }

    public void compileTo(LinkedObjects dest) {
        expression.compileTo(dest);
        dest.add(OpCode.NEG);
    }
}
