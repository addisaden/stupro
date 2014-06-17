package tree;

import util.LinkedObjects;

import vm.OpCode;

public class NumberNode implements Node {
    private double number;

    public NumberNode(double number) {
        this.number = number;
    }

    public void compileTo(LinkedObjects dest) {
        dest.add(OpCode.NUM);
        dest.add(Double.valueOf(number));
    }
}
