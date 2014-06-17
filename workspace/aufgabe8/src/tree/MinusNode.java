package tree;

import vm.OpCode;

public class MinusNode extends BinOpNode {
	public MinusNode(Node left, Node right) {
        super(left, right);
    }

    protected OpCode opCode() {
        return OpCode.SUB;
    }    

}
