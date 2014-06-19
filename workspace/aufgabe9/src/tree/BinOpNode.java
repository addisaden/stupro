package tree;

/**
 * Realisiert die gemeinsamen Operation der binaeren Operationen.
 */
abstract class BinOpNode implements Node {
    protected final Node left;
    protected final Node right;
    
    protected BinOpNode(Node left, Node right) {
        this.left = left;
        this.right = right;
    }

    /**
     * Erzeugt die Darstellung fuer eine binaere Operation.
     * Der String lautet:
     * <pre>
     *     ( linke-Seite Operator rechte-Seite )
     * </pre>
     * Der Operator ist +,-,*,/ 
     * linke/rechte Seite sind die Stringdarstellung des jeweiligen Teilbaums.
     * @return Baum als String
     */
    public String toString() {
        // TODO korrigieren
        return "";
    }
    
    /**
     * Ermittelt den Operator fuer dieses Objekt.
     * @return Name des Operators
     */
    protected abstract String operator();
}
