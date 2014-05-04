package graphics;

import java.awt.BorderLayout;
import java.awt.Container;

import javax.swing.JFrame;
import javax.swing.JTextField;

import objects.World;
import objects.WorldFactory;

public class Main extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextField status = new JTextField();

    /**
     * Erzeugt die graphische Operflaeche.
     * 
     * @param thisWorld
     *            die Datenstruktur.
     */
    public Main(World thisWorld) {
        super("Game of Life");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container p = getContentPane();
        status.setHorizontalAlignment(JTextField.RIGHT);
        p.setLayout(new BorderLayout());
        p.add(new Panel(thisWorld), BorderLayout.NORTH);
        p.add(status, BorderLayout.SOUTH);
        pack();
        setResizable(false);
        setVisible(true);
    }

    /**
     * main-Methode.
     * 
     * @param args
     *            keine Kommandozeilenargumente.
     */
    public static void main(String[] args) {
        // true = zufaelliger Anfang
        // false = Anfang = "Pentomino"
        World thisWorld = WorldFactory.createWorld(150, 150, true);
        Main f = new Main(thisWorld);
        f.repaint();
        int noOfGenerations = 1;
        while (true) {
            f.status.setText(String.valueOf(noOfGenerations++));
            delay(200);
            thisWorld.computeNextGeneration();
            f.repaint();
        }
    }

    /**
     * Zeitverzoegerung. Bei einer Unterbrechung durch einen anderen Thread
     * erfolgt keine Mitteilung.
     * 
     * @param millis
     *            Dauer der Zeitverzoegerung in Millisekunden.
     */
    private static void delay(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException safelyIgnored) {
        }
    }
}
