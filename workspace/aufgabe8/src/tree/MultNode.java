package tree;

import vm.OpCode;

public class MultNode extends BinOpNode {
	public MultNode(Node left, Node right) {
        super(left, right);
    }

    protected OpCode opCode() {
        return OpCode.MUL;
    }
}
