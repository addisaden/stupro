package objects;

/**
 * Repraesentiert ein weisses Feld.
 */
final class WhiteToken implements Token {
	private static final WhiteToken instance = new WhiteToken();
    /**
     * Dieser Konstruktor darf nur einmal fuer die Initialisierung von
     * instance aufgerufen werden.
     */
	private WhiteToken() { }
	
	/**
	 * Liefert ein weisses Objekt. Es gibt genau eine Instanz.
	 * @return ein weisses Objekt
	 */
	static WhiteToken instance() {
		return instance;
	}

	/**
     * Liefert false zurück, weil der Token weiß ist.
	 */
	public boolean isBlack() {
		return false;
	}

    /**
     * Legt fest, ob in der naechsten Spielrunde ein weisser
     * oder ein schwarzer Stein and dieser Stelle liegt.
     * Schwarz: Es gibt genau 3 schwarze Nachbarn.
     * Sonst bleibt das Feld weiss.
     * @param neighbours die Besetzung der 8 Nachbarfelder.
     * @return Farbe der naechsten Runde
     */
    public Token nextGeneration(Token[] neighbours) {
    	int count_black = 0;
    	
    	for(int i = 0; i < neighbours.length; i++) {
    		if(neighbours[i].isBlack())
    			count_black++;
    	}
    	
    	if(count_black == 3)
    		return BlackToken.instance();
    	else
    		return this;
    }
}
