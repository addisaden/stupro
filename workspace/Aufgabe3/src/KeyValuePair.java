/**
 * Hilfsklasse fuer einzelne Eintraege.
 * 
 * @param K Typ der Schluesselobjekte
 * @param V Typ der zugeordneten Daten
 */
final class KeyValuePair<K,V> {
    private final K key;
    private V value;

    /**
     * Konstruktor. Der Schlüssel darf nicht null sein.
     * 
     * @param key Schluesselbegriff.
     * @param value eigentlicher Inhalt.
     * @throws NullPointerException wenn <tt>key == null</tt> ist.
     */
    KeyValuePair(K key, V value) {
        if (key == null) throw new NullPointerException();
        this.key = key;
        this.value = value;
    }
    
    /**
     * Gibt den Schluesselbegriff des Eintrags zurueck.
     * 
     * @return Schluessel.
     */
    K getKey() {
        return key;
    }
    
    /**
     * Gibt den Inhalt des Eintrags zurueck.
     * 
     * @return Inhalt.
     */
    V getValue() {
        return value;
    }
    
    /**
     * Veraendert den Inhalt des Eintrags und gibt den ueberschriebenen
     * Inhalt zurueck.
     * 
     * @param value neuer Inhalt.
     * @return vorhergehender Inhalt.
     */
    V setValue(V value) {
        V oldValue = this.value;
        this.value = value;
        return oldValue;
    }
    
    @Override
    public String toString() {
        return key + ":" + value;
    }
}