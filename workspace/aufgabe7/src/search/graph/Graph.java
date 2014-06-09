package search.graph;

import java.text.Collator;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Speichert einen kompletten (geometrischen) Graphen.
 */
public final class Graph {
    private final Map<String, Node> nodes = new HashMap<String, Node>();
    
    /**
     * Fuegt einen Knoten hinzu. Der Knotenname sollte eindeutig sein.
     * @param name Knotenname.
     * @param x x-Koordinate des Knotens.
     * @param y y-Koordinate des Knotens.
     */
    public void addNode(String name, double x, double y) {
        nodes.put(name, new Node(name, x, y));
    }
    
    /**
     * Fuegt eine neue Kante hinzu.
     * Die Laenge der Kante ist gleich der Entfernung der beiden Knoten.
     * Die beiden Endknoten muessen bereits existieren.
     * @param from Name des ersten Entknotens.
     * @param to Name des zweiten Endknotens.
     * @throws NullPointerException wenn einer der Endknoten nicht existiert.
     */
    public void addEdge(String from, String to) {
        Node fromNode = nodes.get(from);
        Node toNode = nodes.get(to);
        fromNode.addEdge(toNode);
    }
    
    /**
     * Findet des Knotenobjekt mit gegebenem Namen.
     * @param name Knotenname.
     * @return Knotenobjekt oder <code>null</code> wenn nicht gefunden.
     */
    public Node findNode(String name) {
        return nodes.get(name);
    }
    
    /**
     * Gibt ein Feld mit den sortierten Knotennamen zurueck.
     * @return Feld mit Knotennamen.
     */
    public String[] nodeNames() {
        String[] names = new String[nodes.size()];
        int i = 0;
        for (String name : nodes.keySet())
            names[i++] = name;
        Arrays.sort(names, Collator.getInstance(Locale.GERMANY));
        return names;
    }
    
    /**
     * Erzeugt Liste aller Knoten.
     * Die Reihenfolge der Knoten ist unbestimmt.
     * @return Collection aller Knoten.
     */
    public Collection<Node> nodes() {
        return Collections.unmodifiableCollection(nodes.values());
    }
}
