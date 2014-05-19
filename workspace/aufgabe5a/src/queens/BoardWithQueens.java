package queens;

/**
 * Stellt den momentanen Loesungszustand des N-Damen Problems dar.
 * Damen werden in der Reihenfolge von links nach rechts durch Angabe
 * der Zeilennummer (0 - n-1) gesetzt. Das Objekt merkt sich jeweils die
 * aktuelle Spalte. Sind alle Spalten erfolgreich gesetzt, ist das Problem
 * geloest.
 *
 * @author E. Ehses, FH Koeln
 */
public final class BoardWithQueens {
    /**
     * Die Position der (sicher) plazierten Damen.
     */
    private final int[] placedQueens;
    /**
     * Die Anzahl der plazierden Damen.
     */
    private int numberOfPlacedQueens;
    
    /**
     * Erzeugt ein neues (leeres) Loesungsfeld.
     * @param numberOfQueens Anzahl der Damen = Groesse des Feldes
     */
    public BoardWithQueens(int numberOfQueens) {
        this.numberOfPlacedQueens = 0;
        this.placedQueens = new int[numberOfQueens];
    }
    
    /**
     * Das Brett wurde gefuellt.
     * Es wird nicht geprueft ob die Damen sicher stehen!
     * @return true wenn gefuellt.
     */ 
    public boolean allDone() {
        return numberOfPlacedQueens == placedQueens.length;
    }
    
    /**
     * Die bisher plazierten Damen koennen in der aktuellen Spalte in der
     * Zeile <tt>row</tt> zu setzende Dame nicht schlagen.
     * @param row naechste Zeile.
     * @return <tt>true</tt> wenn eine Dame in der angegebenen Zeile sicher
     * ist.
     */
    public boolean isSafe(int row) {
        require(! allDone() && row >= 0 && row < numberOfQueens());
        int up = row - 1;
        int down = row + 1;
        for (int i = numberOfPlacedQueens - 1; i >= 0; i--) {
            int qx = placedQueens[i];
            if (row == qx || up == qx || down == qx) return false;
            up -= 1;
            down += 1;
        }
        return true;
    }
    
    /**
     * Positioniert eine neue Dame in der aktuellen Spalte in der angegebenen 
     * Zeile. Die Dame muss dort sicher sein!
     * @param row Zeile der neuen Dame.
     * @throws IllegalArgumentException wenn in die Dame nicht gesetzt werden
     * kann
     */
    public void placeNextQueen(int row) {
        require(!allDone() && isSafe(row));
        placedQueens[numberOfPlacedQueens] = row;
        numberOfPlacedQueens += 1;
    }
    
    /**
     * Entfernt die zuletzt gesetzte Dame.
     * @throws IllegalArgumentException wenn bereits alle Damen entfernt wurden
     */
    public void removeLastQueen() {
        require(numberOfPlacedQueens() > 0);
        numberOfPlacedQueens -= 1;
    }
    
    /**
     * Gibt die Anzahl der erfolgreich gesetzten Damen zurueck.
     * @return Anzahl der plazierten Damen
     */
    public int numberOfPlacedQueens() {
        return numberOfPlacedQueens;
    }
    
    /**
     * Gibt die Groesse des Bretts zurueck. Dies ist gleich der Anzahl der zu
     * setzenden Damen.
     * @return Groesse des Schachbretts
     */
    public int numberOfQueens() {
        return placedQueens.length;
    }
    
    /**
     * Gibt ein Array mit den plazierten Positionen zurueck,
     * @return Array mit Positionen
     */
    public int[] getQueens() {
        int[] placed = new int[numberOfPlacedQueens];
        System.arraycopy(placedQueens, 0, placed, 0, numberOfPlacedQueens);
        return placed;
    }
    
    private void require(boolean condition) {
        if (! condition) 
            throw new IllegalArgumentException();
    }
    
    /**
     * Gibt die aktuelle Plazierung zurueck. Die Darstellung ist fuer
     * Pruefzwecke gedacht.
     * @return Stringdarstellung der aktuellen Loesung
     */
    public String toString() {
        StringBuilder b = new StringBuilder(2*placedQueens.length);
        b.append("[ ");
        for (int i = 0; i < numberOfPlacedQueens; i++) {
            b.append(placedQueens[i]).append(" ");
        }
        for (int i = numberOfPlacedQueens; i < placedQueens.length; i++) {
            b.append(". ");
        }
        return b.append("]").toString();
    }
} 
