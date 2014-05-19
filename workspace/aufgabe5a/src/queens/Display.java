package queens;

import java.awt.Container;
import javax.swing.JFrame;

/**
 * Fenster zur Darstellung der Loesung des Damen-Problems
 * 
 * @author E. Ehses, FH Koeln
 */
@SuppressWarnings("serial")
public final class Display extends JFrame {
    private final CheckerboardGraphics graphics;
    private final BoardWithQueens board;
    
    /**
     * Erzeugt die graphische Operflaeche.
     * 
     * @param board das Schachbrett mit den Damen.
     */
    public Display(BoardWithQueens board) {
        super(board.numberOfQueens() + "-Queens");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.board = board;
        Container p = getContentPane();
        graphics =new CheckerboardGraphics(board.numberOfQueens());
        p.add(graphics);
        pack();
        setResizable(false);
        setVisible(true);
    }
    
    public void setNewState() {
        graphics.setNewState(board.getQueens());
    }
}
