package queens;

/**
 * Erzeugt eine Loesung des N-Damen Problems und stellt sie graphisch dar.
 * 
 * @author E. Ehses, FH Koeln
 */
public class Queens {
    private static final int NUMBER_OF_QUEENS = 8;
    private static Display display;

    /**
     * Initialisiert alle noetigen Objekte und ruft die Loesungssuche auf.
     * 
     * @param args
     */
    public static void main(String[] args) {
        BoardWithQueens b = new BoardWithQueens(NUMBER_OF_QUEENS);
        display = new Display(b);
        if (solvable(b))
            System.out.println("Die Loesung lautet: " + b);
        else
            System.out.println("Es gibt keine Loesung!");
    }

    /**
     * Rekursiver Backtracking Algorithmus zur Loesung des N-Damen Problems.
     * 
     * @param board
     *            das Brett mit den plazierten Damen
     * @return <tt>true</tt> wenn eine Loesung gefunden wurde
     */
    private static boolean solvable(BoardWithQueens board) {
    	// board.allDone() @return true wenn gefuellt.
    	// board.isSafe(int row) @return <tt>true</tt> wenn eine Dame in der angegebenen Zeile sicher ist.
    	// board.placeNextQueen(int row) Positioniert eine neue Dame in der aktuellen Spalte in der angegebenen Zeile. Die Dame muss dort sicher sein!
    	// board.removeLastQueen() Entfernt die zuletzt gesetzte Dame.
    	// board.numberOfPlacedQueens() Gibt die Anzahl (int) der erfolgreich gesetzten Damen zurueck.
    	// board.numberOfQueens() Gibt die Groesse des Bretts zurueck. Dies ist gleich der Anzahl der zu setzenden Damen.
    	// int[] board.getQueens() Array mit Positionen
    	
    	if(board.allDone())
    	{
    		return true;
    	}
    	else
    	{
    		for(int i = 0; i < board.numberOfQueens(); i++)
    		{
    			if(board.isSafe(i))
    			{
    				board.placeNextQueen(i);
    				displayAndWait(0.1);
    				if(solvable(board))
    				{
    					return true;
    				}
    				else
    				{
    					board.removeLastQueen();
    					displayAndWait(0.3);
    				}
    			}
    		}
    		return false;
    	}
    }

    /**
     * Bewirkt eine Aktualisierung der Graphik und wartet die angegebene Zeit.
     * 
     * @param secs
     *            Wartezeit in Sekunden
     */
    private static void displayAndWait(double secs) {
        try {
            display.setNewState();
            Thread.sleep((int) (secs * 1e3));
        } catch (InterruptedException canNeverHappen) {
        }
    }
}
