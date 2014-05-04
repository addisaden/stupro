package objects;

import java.util.Random;

/**
 * Erzeugt eine "neue Welt."
 */
public class WorldFactory {
    
    /**
     * Ein Feld mit der Anfangsbelegung
     * <pre>
     *     xx
     *    xx
     *     x
     * </pre>
     * @return das initialisierte World-Objekt
     */
    public static World createPentomino(int nx, int ny) {
        // TODO das Feld mit dem Pentomino (s.o.) belegen.
        return null;
    }
    
    /**
     * Ein Feld mit zufaelliger Anfangsbelegung.
     * @return das initialisierte World-Objekt
     */    
    public static World createRandom(int nx, int ny) {
        World world = new World(nx, ny);
        Random rgen = new Random();
        for (int i = 0; i < nx; i++) 
            for (int j = 0; j < ny; j++) 
                if (rgen.nextInt(7) == 0)
                    world.set(i, j, BlackToken.instance());
        return world;
    }
    
    /**
     * Erzeugt ein neues Feld. Die Feldkoordinaten sind (0..nx-1, 0..ny-1).
     * Fuer die Nachbarschaftsberechnung wird als Topologie ein Torus verwendet.
     * @param nx Anzahl der Zellen in x-Richtung
     * @param ny Anzahl der Zellen in y-Richtung
     * @param perRandom wenn <tt>true</tt> wird der Inhalt durch Zufall erzeugt.
     * @return das neue World-Objekt
     */
    public static World createWorld(int nx, int ny, boolean perRandom) {
        return perRandom ?
                createRandom(nx, ny) :
                createPentomino(nx, ny);  
    }
}
