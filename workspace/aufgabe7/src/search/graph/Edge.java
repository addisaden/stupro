package search.graph;

/**
 * Klasse zum Speichern einer Graphkante.
 * @author Erich Ehses.
 */
final class Edge {
    private final Node l1;            // Anfangsknoten
    private final Node l2;            // Endknoten
    private final double edgeLength;  // Kantenlaenge
    
    /**
     * Konstruktor. Die Kantenlaenge ist gleich der Luftlinenentfernung
     *  
     * @param l1 Anfangsknoten.
     * @param l2 Endknoten.
     */
    public Edge(Node l1, Node l2) {
        this(l1, l2, l1.distTo(l2));
    }
    
    /**
     * Konstruktor. Die Kantenlaenge wird explizit angegeben.
     * @param l1 Anfangsknoten.
     * @param l2 Endknoten.
     * @param length Kantenlaenge.
     */
    public Edge(Node l1, Node l2, double length) {
        this.l1 = l1;
        this.l2 = l2;
        this.edgeLength = length;
    }
    
    /**
     * Gibt einen Endknoten zurueck.
     * @param nr Nummer des Knotens (0 oder 1).
     * @return Knotenobjekt.
     * @throws IllegalArgumentException wenn <code>nr</code> nicht 0 oder 1.
     */ 
    public Node get(int nr) {
        if (nr < 0 || nr > 1) throw new IllegalArgumentException();
        return nr == 0 ? l1 : l2;
    }

    /**
     * Bestimmt den Partner zu einem der Endknoten.
     * @param origin gegebener Endknoten.
     * @return gesuchter Endknoten.
     */
    public Node partner(Node origin) {
        return origin == l1 ? l2 : l1;
    }
    
    /**
     * Bestimmt die Laenge der Kante.
     * @return Laenge der Kante.
     */
    public double edgeLength() {
        return edgeLength;
    }  
}
