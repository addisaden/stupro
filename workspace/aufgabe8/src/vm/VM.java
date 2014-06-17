package vm;

import java.util.Iterator;

import util.LinkedObjects;
import util.Stack;

public class VM {
    public static double compute(LinkedObjects program) {
        Stack stk = new Stack();
        Iterator<Object> pc = program.iterator();
        while (pc.hasNext()) {
            OpCode code = (OpCode) pc.next();
            double operand1 = 0.0;
            double operand2 = 0.0;
            if (code.compareTo(OpCode.DIV) <= 0) {
                operand2 = (Double) stk.pop();
                operand1 = (Double) stk.pop();
            }
            switch (code) {
            case ADD: {
                stk.push(operand1 + operand2);
                break;
            }
            case SUB: {
            	stk.push(operand1 - operand2);
                break;
            }
            case MUL: {
            	stk.push(operand1 * operand2);
                break;
            }
            case DIV: {
            	stk.push(operand1 / operand2);
                break;
            }
            case NEG: {
                double arg = (Double) stk.pop();
                stk.push(-arg);
                break;
            }
            case NUM: {
                Object arg = pc.next();
                stk.push(arg);
                break;
            }
            case LOAD:
                // not yet implemented
            case STORE:
                // not yet implemented

            }
        }
        return (Double) stk.pop();
    }
}
