package objects;

/**
 * Das Feld aller Tokens.
 */
public class World {
	private Token[][] field;
	private final int nx;
	private final int ny;

    /**
     * Initialisiert ein Objekt.
     * @param nx Anzahl der x-Zellen
     * @param ny Anzahl der y-Zellen
     */
	protected World(int nx, int ny) {
		this.nx = nx;
		this.ny = ny;
		this.field = new Token[nx][ny];
		for (int i = 0; i < getNX(); i++)
			for (int j = 0; j < getNY(); j++)
				field[i][j] = WhiteToken.instance();
		// to keep everything positve % n
		for (int i = 0; i < dx.length; i++) {
			dx[i] += nx;
			dy[i] += ny;
		}
	}
	
	private static final int[] dx = {0,1,1, 1, 0,-1,-1,-1};
	private static final int[] dy = {1,1,0,-1,-1,-1, 0, 1};
	
	/**
	 * Ermittelt ein Array mit allen Nachbarn einer Zelle.
	 * @param x x-Koordinate
	 * @param y y-Koordinate
	 * @return Array mit Nachbarobjekten
	 */
	Token[] neighbours(int x, int y) {
		Token[] n = new Token[dx.length];
		for (int i = 0; i < dx.length; i++) 
			n[i] = field[(x + dx[i]) % nx][(y + dy[i]) % ny];
		return n;
	}
	
	/**
	 * Ermittelt anhand der Spielregeln eine neue Belegung des Feldes. Alle
	 * Operationen laufen (quasi) gleichzeitig ab.
	 */
	public void computeNextGeneration() {
		Token[][] nField = new Token[nx][ny];
		for (int i = 0; i < nx; i++) 
			for (int j= 0; j < ny; j++) 
				nField[i][j] = field[i][j].nextGeneration(neighbours(i, j));
		field = nField;
	}
	
	/**
	 * Gibt die Groesse in x-Richtung zurueck.
	 * @return Anzahl der Zellen in x-Richtung
	 */
	public int getNX() {
		return nx;
	}
	
	/**
	 * Gibt die Groesse in y-Richtung zurueck.
	 * @return Anzahl der Zellen in y-Richtung
	 */
	public int getNY() {
		return ny;
	}
	
	/**
	 * Das Feld an der Stelle x,y ist schwarz. 
	 * @param x x-Position
	 * @param y y-Position
	 * @return <tt>true</tt> wenn die Zelle schwarz ist.
	 */
	public boolean isBlack(int x, int y) {
		return field[x][y].isBlack();
	}
	
	/**
	 * Legt die Anfangsfarbe fuer ein Feld fest.
	 * @param x x-Koordinate
	 * @param y y-Koordinate
	 * @param color Farbe
	 */
	protected void set(int x, int y, Token color) {
		field[x][y] = color;
	}
}
