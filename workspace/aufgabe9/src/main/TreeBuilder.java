package main;
import parser.Builder;
import tree.DivNode;
import tree.IdNode;
import tree.LetNode;
import tree.MinusNode;
import tree.NegateNode;
import tree.Node;
import tree.NumberNode;
import tree.PlusNode;
import tree.MultNode;

/**
 * Builds an Abstract Syntax Tree.
 */
public class TreeBuilder implements Builder<Node> {

    public Node add(Node left, Node right) {
        return new PlusNode(left, right);
    }

    public Node sub(Node left, Node right) {
        return new MinusNode(left, right);
    }

    public Node mul(Node left, Node right) {
        return new MultNode(left, right);
    }

    public Node div(Node left, Node right) {
        return new DivNode(left, right);
    }

    public Node negate(Node expression) {
        return new NegateNode(expression);
    }

    public Node number(String number) {
        return new NumberNode(Double.parseDouble(number));
    }

    public Node let(String id, Node expression) {
        return new LetNode(id, expression);
    }

    public Node id(String id) {
        return new IdNode(id);
    }
}
