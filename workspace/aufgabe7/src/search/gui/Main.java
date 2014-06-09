package search.gui;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import search.graph.Examples;
import search.graph.GraphSearch;
import search.graph.Graph;

/**
 * Main-Klasse fuer Suchalgorithmen mit graphischer Oberflaeche.
 */
@SuppressWarnings("serial")
public final class Main extends JFrame {
    /**
     * Erzeugt die graphische Operfl�che.
     * 
     * @param solver Loesungsverfahren.
     * @param graph Graph in dem gesucht wird.
     */
    public Main(GraphSearch solver, Graph graph) {
        super("Demonstration der Graphsuche");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());
       
        DataPanel data = new DataPanel(solver, graph);
        solver.addObserver(data);
        getContentPane().add(data, BorderLayout.WEST);
        
        Roadmap map = new Roadmap(graph, 500, 600);
        solver.addObserver(map);
        getContentPane().add(map, BorderLayout.EAST);
        
        pack();
        setResizable(false);
        setVisible(true);
    }
    
    /**
     * main-Methode.
     * 
     * @param args keine Kommandozeilenargumente.
     */
    public static void main(String[] args) {
        // erzeuge den Graphen:
        Graph g = Examples.germany();
        // erzeuge das Sucher-Objekt:
        GraphSearch s = new GraphSearch();

        // erzeuge die GUI
        new Main(s, g);
        // starte die Anwendungsmethode.
        //s.runloop();
    } 
}
