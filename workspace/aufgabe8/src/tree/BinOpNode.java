package tree;

import util.LinkedObjects;
import vm.OpCode;

abstract class BinOpNode implements Node {
    protected final Node left;
    protected final Node right;
    
    protected BinOpNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }
   
    public void compileTo(LinkedObjects dest) {
        left.compileTo(dest);
        right.compileTo(dest);
        dest.add(this.opCode());
    }
    
    protected abstract OpCode opCode();
}
