package search.gui;

import search.graph.Graph;
import search.graph.Node;
import static java.lang.Math.*;

/**
 * Die Klasse Scales hat die Aufgabe die Weltkoordinaten des Graphs in
 * Bildschirmkoordinaten umzurechnen.
 * 
 * @author Erich Ehses
 */
class Scales {

    private double sX;
    private double sY;
    private double offX;
    private double offY;
    private static final int INSET = 25;
    private int width;
    private int height;

    /**
     * Konstruktor.
     * 
     * @param g Graphdatenstruktur der Straßenkarte
     * @param width Fensterbreite in Pixeleinheiten.
     * @param height Fensterhöhe in Pixeleinheiten.
     */
    Scales(Graph g, int width, int height) {
        this.width = width;
        this.height = height;
        findScale(g);
    }

    /**
     * Bestimmt die Skalierungparameter. Diese Methode wird nur einmalig durch
     * den Konstruktor aufgerufen.
     * 
     * @param graph Datenstruktur des Graphen.
     */
    private void findScale(Graph graph) {
        double minX = Double.MAX_VALUE;
        double maxX = Double.MIN_VALUE;
        double minY = Double.MAX_VALUE;
        double maxY = Double.MIN_VALUE;
        for (Node node : graph.nodes()) {
            double x = node.getX();
            double y = node.getY();
            minX = min(x, minX);
            maxX = max(x, maxX);
            minY = min(y, minY);
            maxY = max(y, maxY);
        }
        double dx = (maxX - minX);
        double dy = (minY - maxY);
        int w = width - 2 * INSET;
        int h = height - 2 * INSET;
        sX = w / dx;
        sY = h / dy;
        offX = -w * minX / dx + INSET;
        offY = -h * maxY / dy + INSET;
    }

    /**
     * Gibt die Hoehe des Fensters zurueck.
     * 
     * @return Hoehe des Fensters
     */
    int height() {
        return height;
    }

    /**
     * Gibt die Breite des Fensters zurueck.
     * 
     * @return Breite des Fensters
     */
    int width() {
        return width;
    }

    /**
     * Transformiert x-Koordinaten auf den Bildschirm.
     * 
     * @param node Knoten.
     * @return Pixelindex in x-Richtung.
     */
    int xCoord(Node node) {
        return (int) (sX * node.getX() + offX);
    }

    /**
     * Transformiert y-Koordinaten auf den Bildschirm.
     * 
     * @param node Knoten.
     * @return Pixelindex in y-Richtung.
     */
    int yCoord(Node node) {
        return (int) (sY * node.getY() + offY);
    }
}