package tree;

import vm.OpCode;

public class DivNode extends BinOpNode {
	public DivNode(Node left, Node right) {
        super(left, right);
    }

    protected OpCode opCode() {
        return OpCode.DIV;
    }    
}
