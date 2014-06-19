package tree;

import memory.Variables;

/**
 * Repraesentiert den Zugriff auf eine Variable.
 */
public class IdNode implements Node {
    private String varName;

    public IdNode(String varName) {
        this.varName = varName;
    }

    public double value() {
        /*
         * Variablenwert aus memory.Variables holen und zurueckgeben.
         */
        return Variables.load(this.varName);
    }

    @Override
    public String toString() {
        return varName;
    }
}
