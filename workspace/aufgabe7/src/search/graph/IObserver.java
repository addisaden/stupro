package search.graph;

/**
 * Definiert eine passende Observer-Schnittstelle fuer die Graphsuche.
 */
public interface IObserver {
    /**
     * Teil dem Beobachter einen neuen (Teil-)Weg mit.
     * 
     * @param p aktueller Zustand der Wegsuche.
     */
    public void update(Path p);

}
