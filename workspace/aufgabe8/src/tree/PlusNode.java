package tree;

import vm.OpCode;

public class PlusNode extends BinOpNode {
	public PlusNode(Node left, Node right) {
        super(left, right);
    }

    protected OpCode opCode() {
        return OpCode.ADD;
    }    
}
