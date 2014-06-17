package tree;

import vm.OpCode;

public class PlusNode extends BinOpNode {
    // TODO: Konstruktor fehlt

    protected OpCode opCode() {
        return OpCode.ADD;
    }    
}
