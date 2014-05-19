package queens;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * Stellt den Zustand des Spiels dar.
 * 
 * @author E. Ehses, FH Koeln
 */
@SuppressWarnings("serial")
public final class CheckerboardGraphics extends JPanel {
    private static final int SCALE = 50;
    private int numberOfQueens;
    private int[] rows;

    /**
     * Stellt das Schachbrett dar.
     * 
     * @param numberOfQueens
     *            Anzahl zu setzender Damen
     */
    public CheckerboardGraphics(int numberOfQueens) {
        this.numberOfQueens = numberOfQueens;
        setBackground(Color.WHITE);
        setPreferredSize(new Dimension(numberOfQueens * SCALE + 1,
                numberOfQueens * SCALE + 1));
    }

    /**
     * Malt den Bildschim neu.
     * 
     * @param g
     *            zu benutzender Graphics-Context
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawBoard(g);
        g.setColor(Color.RED);
        synchronized (this) {
            if (rows == null) return;
            for (int i = 0; i < rows.length; i++) {
                // die j-Richtung wird umgedreht, da Swing von oben nach unten
                // zaehlt
                int j = numberOfQueens - rows[i] - 1;
                g.fillRect(i * SCALE + 9, j * SCALE + 9, SCALE - 16, SCALE - 16);
            }
        }
    }

    public synchronized void setNewState(int[] rows) {
        this.rows = rows;
        repaint();
    }

    /**
     * Malt das Schachbrettmuster.
     * 
     * @param g
     *            zu benutzender Graphics-Context
     */
    private void drawBoard(Graphics g) {
        g.setColor(Color.black);
        for (int i = 0; i < numberOfQueens; i++)
            for (int j = 0; j < numberOfQueens; j++)
                if ((i + j) % 2 == 1)
                    g.fillRect(i * SCALE + 1, j * SCALE + 1, SCALE, SCALE);
    }
}
