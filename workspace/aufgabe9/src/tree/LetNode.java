package tree;

import memory.Variables;

/**
 * Repraesentieriert eine Zuweisung.
 */
public class LetNode implements Node {
    private final String varName;
    private final Node expression;
    
    public LetNode(String varName, Node expression) {
        this.varName = varName;
        this.expression = expression;
    }

    public double value() {
        double value = 0.0;
        // TODO korrigieren
        /*
         * Vorgehensweise:
         * 1. Wert von expression berechnen.
         * 2. Wert unter varName mittels memory.Variables speichern
         * 3. Wert zurueckgeben 
         */
        return value;
    }
  
    @Override
    public String toString() {
        // TODO korrigieren
        return "\n";
    }
}
