package search.util;

/**
 * Diese Schnittstelle beschreibt eine sequentielle Datenstruktur.
 * Ex gibt zwei grundlegende Operationen zur Veraenderung der Struktur, naemlich
 * <code>put</code> um ein neues Datenelement hinzuzufuegen und
 * <code>get</code> um ein Datenelement aus der Datenstruktur zu
 * entnehmen. Indizierte oder iterative Zugriffe auf die Daten sind nicht
 * vorgesehen.
 * <p>
 * In den jeweiligen Implementierungen kann die Anordnung der Daten
 * nach unterschiedlichen Kriterien erfolgen. Gedacht ist dabei zum
 * Beispiel an eine FIFO-, LIFO-Struktur oder an eine nach einem
 * Ordnungskriterium geordnete Prioritaetswarteschlange. 
 * <p>
 * <b>Hinweis:</b> Der Name <code>Queue</code> drueckt nur die eingeschraenkte
 * Zugriffsmoeglichkeit aus, ohne etwas ueber die Reihenfolge der Daten
 * auszusagen.
 * 
 * @author Erich Ehses.
 */
public interface IQueue<T> {

    /**
     * Fuegt einen neuen Wert hinzu.
     * @param p neuer Wert.
     */
    public void put(T p);

    /**
     * Gibt einen Wert zurueck und entfernt ihn aus
     * der Queue.
     * @return naechster Wert.
     * @throws java.util.NoSuchElementException wenn kein Wert vorhanden ist.
     */
    public T get();

    /**
     * Ueberprueft, ob die Queue Werte enthaelt.
     * @return <code>true</code> wenn keine Werte vorhanden.
     */
    public boolean isEmpty();
    
    /**
     * Entfernt alle Inhalte der Queue.
     */
    public void clear();
}
