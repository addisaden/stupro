package search.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;

import javax.swing.JPanel;

import search.graph.Graph;
import search.graph.IObserver;
import search.graph.Node;
import search.graph.Path;

/**
 * Stellt den aktuellen Zustand der Suche graphisch dar.
 * 
 * @author Erich Ehses.
 */
@SuppressWarnings("serial")
public final class Roadmap extends JPanel implements IObserver {
    /*
     * Konstanten fuer das Aussehen der Graphik
     */
    private static final int NODE_RADIUS = 8;
    private static final Color GRAPH_COLOR = Color.black;
    private static final Color PATH_COLOR = Color.red;
    private static final Color CLOSED_COLOR = Color.green;
    private static final Color BG_COLOR = Color.white;
    /*
     * Modelldaten.
     */
    private final Graph graph;
    private final Scales scale;
    private List<Node> currentPath;
    private Node goalNode;
    /*
     * aktueller (Hintergrund) Kontext fuer die Graphik.
     */
    private Image img = null;
    private boolean repaintPending;
    /**
     * Konstruktor. Breite und Hoehe fuehren zu einer automatischen Skalierung.
     * 
     * @param graph der Graph in dem die Suche stattfindet.
     * @param width die Breite der Darstellung.
     * @param height die Hoehe der Darstellung.
     */
    public Roadmap(Graph graph, int width, int height) {
        this.graph = graph;
        scale = new Scales(graph, width, height);
        setPreferredSize(new Dimension(width, height));
    }
    /**
     * Observer-Schnittstelle.
     *
     * @param path aktueller Pfad.
     */
    public synchronized void update(final Path path) {
        currentPath = path.nodes();
        goalNode = path.goalNode();
        if (currentPath.size() <= 1) img = null;
        repaintPending = true;
        repaint();
        try {
            while (repaintPending) wait();
        }
        catch (InterruptedException neverHappens) {}
    }

    public synchronized void paintComponent(Graphics g) {
        drawGraph(g);
        drawPath(g);
        drawGoalNode(g);
        repaintPending = false;
        notifyAll();
    }
    /**
     * Stellt den Graphen und eventuell vorher aufgezeichnete Weginformation
     * dar. Bei der Implementierung werden das <code>Image</code> Objekt
     * <code>img</code> und die damit verbundene Graphik <code>g0</code>
     * benutzt.
     * 
     * @param g aktueller Graphics-Kontext.
     */
    private void drawGraph(Graphics g) {
        int width = scale.width();
        int height = scale.height();
        if (img == null) {
            img = createImage(width, height);
            Graphics g0 = img.getGraphics();
            g0.setColor(BG_COLOR);
            g0.fillRect(0, 0, width, height);
            g0.setColor(GRAPH_COLOR);
            for (Node node : graph.nodes()) {
                for (Node dest : node.directedNeighbours())
                    drawLine(g0, node, dest);
                fillCircle(g0, node);
            }
        }
        g.drawImage(img, 0, 0, width, height, null);
    }
    /**
     * Stellt den aktuellen Weg dar. Der Weg wird gleichzeitig in das
     * Bildobjekt <code>img</code> zwecks spaeterer Darstellung uebernommen.
     * 
     * @param g aktueller Graphics-Kontext.
     */
    private void drawPath(Graphics g) {
        if (currentPath != null) {
            Graphics g0 = img.getGraphics();
            g.setColor(PATH_COLOR);
            g0.setColor(CLOSED_COLOR);
            Node previousNode = null;
            for (Node node : currentPath) {
                fillCircle(g, node);
                fillCircle(g0, node);
                if(previousNode != null) {
                    drawLine(g, previousNode, node);
                    drawLine(g0, previousNode, node);
                }
                previousNode = node;
            }
        }
    }
    
    /**
     * Zeichnet den Zielknoten.
     * 
     * @param g aktueller Graphics Kontext
     */
    private void drawGoalNode(Graphics g) {
        if (goalNode != null) {
            int x = scale.xCoord(goalNode);
            int y = scale.yCoord(goalNode);
            int r = NODE_RADIUS + 4;
            g.drawArc(x - r / 2, y - r / 2, r, r, 0, 360);
        }
    }
    /**
     * Zeichnet eine Wegkante.
     * 
     * @param g aktueller Graphics Kontext.
     * @param x0 Anfangspunk-x.
     * @param y0 Anfangspunkt-y.
     * @param x1 Endpunkt-x.
     * @param y1 Endpunkt-y.
     */
    private void drawLine(Graphics g, Node from, Node to) {
        int ix0 = scale.xCoord(from);
        int iy0 = scale.yCoord(from);
        int ix1 = scale.xCoord(to);
        int iy1 = scale.yCoord(to);
        g.drawLine(ix0, iy0, ix1, iy1);
    }
    /**
     * Zeichnet einen Knoten (Ort).
     * 
     * @param g aktueller Graphics Kontext.
     * @param x x-Koordinate (Pixel).
     * @param y y-Koordinate (Pixel).
     */
    private void fillCircle(Graphics g, Node node) {
        int x = scale.xCoord(node);
        int y = scale.yCoord(node);
        g.fillArc(x - NODE_RADIUS / 2, y - NODE_RADIUS / 2, NODE_RADIUS,
                NODE_RADIUS, 0, 360);
    }
}