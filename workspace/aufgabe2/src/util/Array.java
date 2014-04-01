package util;

/**
 * Klasse zum Speichern von Daten. (sich selbst vergroesserndes Array).
 * <p>
 * Neue Daten werden stets am Ende angehaengt.. Der Zugriff auf die Inhalte
 * erfolgt ueber die Indexnummer von 0 bis Anzahl der Elemente - 1.
 * 
 * @param T
 *            Elementtyp
 */
public final class Array<T> {
    /**
     * Kennung "nicht gefunden".
     */
    private static final int NOT_FOUND = -1;

    /**
     * Anzahl der gespeicherten Datenelemente
     */
    private int size = 0;
    /**
     * Array zum Speichern der Daten.
     */
    private Object[] data = new Object[2];

    /**
     * Gibt die Anzahl der gespeicherten Inhalte zurueck.
     * 
     * @return Anzahl der Inhalte
     */
    public int size() {
        return size;
    }

    /**
     * Gibt das index-te Element zurueck. Nur erlaubt, wenn genuegend viele
     * Elemente gespeichert sind.
     * 
     * @param index
     *            Elementnummer
     * @return gewaehltes Element
     * @throws IndexOutOfBoundsException
     *             wenn <tt>index</tt> illegal ist.
     */
    @SuppressWarnings("unchecked")
    public T get(int index) {
        checkIndex(index);
        return (T) data[index];
    }

    /**
     * Speichert Inhalt. Das Objekt wird als letztes Element hinzugefuegt.
     * 
     * @param neu
     *      zu speichernde Transkation.
     */
    public void add(T neu) {
        if (size == data.length)
            increaseCapacity();
        data[size] = neu;
        size++;
    }

    /**
     * Gibt ein Feld zurueck, das die umgekehrte Elementreihenfolge hat. Das
     * this-Objekt und seine Atribute werden nicht veraendert!
     * 
     * @return neues umgedrehtes Array
     */
    public Array<T> reversed() {
    	Array<T> result = new Array<T>();
    	
    	for(int i = size - 1; i >= 0; i--)
    		result.add(get(i));
    	
    	return result;
    }

    /**
     * Gibt den Index des ersten Vorkommens von <tt>gesucht</tt> zurueck.
     * 
     * @param gesucht
     *            Objekt das gesucht wird.
     * @return Index des ersten Vorkommens oder -1 wenn nicht gefunden.
     */
    public int indexOf(T gesucht) {
    	for(int i = 0; i < size; i++) {
    		if(safeEquals(gesucht, get(i))) {
    			return i;
    		}
    	}
        return NOT_FOUND;
    }

    /**
     * Sicherer Vergleich zweier Objekte auf Gleichheit.
     * Auch der Vergleich mit {@code null} ist korrekt. 
     * 
     * @param a erstes Objekt
     * @param b zweites Object
     * 
     * @return {@code} true wenn beide Objekte gleich sind oder wenn beide
     * Objekte {@code null} sind
     */
    private static boolean safeEquals(Object a, Object b) {
        return !(a != null && b == null || a == null && b != null) && 
        		((a == null && b == null) || a.equals(b));
    }

    /**
     * Ersetzt das bestehende Array durch ein Array doppelter Groesse.
     */
    private void increaseCapacity() {
        Object[] t = new Object[2 * data.length];
        for (int i = 0; i < data.length; i++)
            t[i] = data[i];
        data = t;
    }

    /**
     * Prueft ob <tt>index</tt> im erlaubten Bereich von 0 bis Anzahl der
     * Elemente - 1 liegt.
     * 
     * @param index
     *            zu pruefender Index
     * @throws IndexOutOfBoundsexception
     *             wenn <tt>index</tt> ausserhalb des erlaubten Bereichs liegt.
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException(index + " nicht erlaubt");
    }
}
