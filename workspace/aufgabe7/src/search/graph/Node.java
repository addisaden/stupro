package search.graph;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Klasse zum Speichern eines Graphknotens mit geometrischer Information (2D).
 */
public final class Node {
    
    private final String name;        // Knotenname
    private final double x;           // x-Koordinate
    private final double y;           // y-Koordinate
    private final List<Edge> edges = new LinkedList<Edge>();    // Kantenkliste.
    
    /**
     * Erzeugt einen neuen Knoten.
     * @param name Knotenname.
     * @param x x-Koordinate.
     * @param y y-Koordinate.
     */
    public Node(String name, double x, double y) {
        this.name = name;
        this.x = x;
        this.y = y;
    }
    
    /**
     * Fuegt eine Kante hinzu.
     * @param dest Zielknoten der Kante.
     */
    public void addEdge(Node dest) {
        Edge edge = new Edge(this, dest);
        edges.add(edge);
        dest.edges.add(edge);
    }
    
    /**
     * Erfragt x-Koordinate.
     * @return x-Koordinate.
     */
    public double getX() {
        return x;
    }
    
    /**
     * Erfragt y-Koordinate.
     * @return y-Koordinate.
     */
    public double getY() {
        return y;
    }
    
    /**
     * Luftlinenentfernung zu anderem Knoten.
     * @param other anderer Knoten.
     * @return Entfernung.
     */
    public double distTo(Node other) {
        double dx = x - other.x;
        double dy = y - other.y;
        return Math.sqrt(dx*dx + dy*dy);
    }
      
    /**
     * Kante an der ein Nachbarknoten sitzt.
     * @param other Nachbarknoten.
     * @return verbindende Kante.
     */
    public Edge edgeTo(Node other) {
        for (Edge edge : edges) {
            if (edge.partner(this) == other) {
                return edge;
            }
        }
        return null;
    }
    
    public String toString() {
        return name;
    }
        
    /**
     * Gibt einen Iterable-Objekt ueber die Nachbarknoten zurueck.
     * @return Iterable ueber Nachbarknoten.
     */
    public Iterable<Node> neighbours() {
        return new Iterable<Node>() {
            public Iterator<Node> iterator() {
                return new NodeIterator(Node.this);
            }
        };
    }
    
    /**
     * Gibt ein Iterable-Object ueber diejenigen
     * Nachbarknoten, die an einer ausgehenden gerichteten
     * Kante liegen.
     * 
     * @return Iterable ueber Nachbarknoten.
     */
    public Iterable<Node> directedNeighbours() {
        return new Iterable<Node>() {
            public Iterator<Node> iterator() {
                return new DirectedNodeIterator(Node.this);
            }
        };
    }
    
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other instanceof Node) {
            Node that = (Node) other;
            return this.name.endsWith(that.name) && this.x == that.x
                    && this.y == that.y;
        }
        return false;
    }
    
    /**
     * Iterator ueber alle Nachbarn.
     */
    private static class NodeIterator implements Iterator<Node> {
        final Iterator<Edge> iter;
        final Node origin;
        
        NodeIterator(Node origin) {
            this.origin = origin;
            this.iter = origin.edges.iterator();
        }
        
        public boolean hasNext() {
            return iter.hasNext();
        }

        public Node next() {
            return iter.next().partner(origin);
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    
    /**
     * Iterator ueber alle Nachbarn in ausgehender Kantenrichtung.
     */
    private static class DirectedNodeIterator implements Iterator<Node> {
        Edge lookahead;
        final Iterator<Edge> iter;
        final Node origin;
        
        DirectedNodeIterator(Node origin) {
            this.origin = origin;
            this.iter = origin.edges.iterator();
            findNextEdge();
        }

        void findNextEdge() {
            while (iter.hasNext()) {
                lookahead = iter.next();
                if (lookahead.get(0) == origin) return;
            }
            lookahead = null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public boolean hasNext() {
            return lookahead != null;
        }

        public Node next() {
            if (lookahead == null) throw new NoSuchElementException();
            Node result = lookahead.get(1);
            findNextEdge();
            return result;
        }
    }
}
