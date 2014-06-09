package search.graph;

import java.util.List;

import search.util.FList;

/**
 * Speichert einen Weg in einer Knotenfolge. Waehrend der Ausfuehrung der Suche
 * ist die Knotenfolge umgekehrt zur Wegrichtung.
 * 
 * @author Erich Ehses.
 */
public final class Path {
    private final double pathLength;    // Laenge des Weges.
    private final FList<Node> nodes;     // Liste der Knoten.
    private final Node goalNode;         // Zielknoten

    /**
     * Implementierungshilfsmittel.
     * Vollstaendiger Konstruktor.
     */
    private Path(FList<Node> nodes, double pathLength, Node goalNode) {
        this.pathLength = pathLength;
        this.nodes = nodes;
        this.goalNode = goalNode;
    }

    /**
     * Der Konstruktor legt einen bisher leeren Suchpfad zu dem Zielknoten an.
     * Zwecks Visualisierung wird gleichzeitig mit der Pfad-Liste ein Suchbaum
     * aufgebaut.
     * 
     * @param startNode Startknoten.
     * @param goalNode Zielknoten.
     */
    public Path(Node startNode, Node goalNode) {
        this(FList.create(startNode), 0.0, goalNode);
    }

    /**
     * Gibt den Endknoten des Pfades zurueck.
     * 
     * @return Endknoten.
     */
    public Node lastNode() {
        return nodes.get(0);
    }

    /**
     * Gibt den Zielknoten der Pfadsuche zurueck.
     * 
     * @return Zielknoten.
     */
    public Node goalNode() {
        return goalNode;
    }

    /**
     * Untersucht, ob der Pfad bis zum Ziel reicht.
     * 
     * @return <code>true</code> wenn das Ziel gefunden wurde.
     */
    public boolean isComplete() {
        return goalNode == nodes.get(0);
    }
    
    /**
     * Erweitert den Path um einen weiteren Knoten.
     * 
     * @param nextNode neuer Knoten
     * @return neuer erweiterter Pfad.
     */
    public Path add(Node nextNode) {
        return new Path(
                nodes.cons(nextNode),
                pathLength + nextNode.edgeTo(lastNode()).edgeLength(),
                goalNode);
    }

    /**
     * Gibt die Pfadlaenge zurueck.
     * 
     * @return Pfadlaenge
     */
    public double pathLength() {
        return pathLength;
    }

    /**
     * Gibt die Liste aller Pfadknoten zurueck. Die Reihenfolge der Knoten ist
     * umgekehrt zur Pfadrichtung.
     * 
     * @return Liste der Knoten.
     */
    public List<Node> nodes() {
        return nodes;
    }


    /**
     * Gibt die Anzahl der Knoten zurueck. Dies ist gleich der
     * graphtheoretischen Pfadlaenge + 1.
     * 
     * @return Anzahl der Knoten.
     */
    public int size() {
        return nodes.size();
    }
}