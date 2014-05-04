package objects;

/**
 * Repraesentiert ein schwarzes Feld.
 */
final class BlackToken implements Token {
	private static final BlackToken instance = new BlackToken();
	/**
	 * Dieser Konstruktor sollte nur einmal fuer die Initialisierung von
	 * instance aufgerufen werden.
	 */
	private BlackToken() {	}

	/**
	 * Liefert ein schwarzes Objekt. Es gibt genau eine Instanz.
	 * @return ein schwarzes Objekt
	 */
	static BlackToken instance() {
		return instance;
	}
	
	/**
     * TODO richtiger Kommentar
	 */
	public boolean isBlack() {
		return true;
	}


    /**
     * Legt fest, ob in der naechsten Spielrunde ein weisser
     * oder ein schwarzer Stein an dieser Stelle liegt.
     * Weiss: Es gibt weniger als 2 oder mehr als 3 schwarze Nachbarn.
     * Sonst bleibt das Feld schwarz.
     * @param neighbours die Besetzung der 8 Nachbarfelder.
     * @return Farbe der naechsten Runde
     */
    public Token nextGeneration(Token[] neighbours) {
    	int count_black = 0;
    	
    	for(int i = 0; i < neighbours.length; i++) {
    		if(neighbours[i].isBlack())
    			count_black++;
    	}
    	
    	if(count_black == 2 || count_black == 3)
    		return this;
    	else
    		return WhiteToken.instance();
    }
}
