package util;

/**
 * Die Klasse implementiert ein Verzeichnis, in dem nach
 * unter einem Schluessel gespeicherten Daten gesucht werden
 * kann.
 * <p>
 * Als Implementierungsmethode wird die Kollisionsbehandlung der
 * direkten Verkettung verwendet.
 */
public  class Hashing<K,V> implements IMap<K,V> {
	/**
     * Anzahl der gespeicherten Key-Value-Paare.
     */
    private int size = 0;
    
    /**
     * Feld mit den Daten.
     */
    @SuppressWarnings("unchecked")
    private Entry<K,V>[] data = new Entry[100];

    @Override
    public int size() {
		return size;
	}

    @Override
    public V put(K key, V value) {
		Entry<K,V> p = referenceOf(key);
		if (p == null) {
		    int index = hash(key);
			data[index] = new Entry<K,V>(key, value, data[index]);
		    size++;
			return null;
		}
		return p.setValue(value);
	}

    @Override
    public V get(K key) {
		Entry<K,V> p = referenceOf(key);
		return p == null ? null : p.value;
	}

    @Override
    public boolean containsKey(K key) {
        // TODO den richtigen Algorithmus einsetzen.
        return false;
    }    

    /**
     * Findet die Referenz zu dem Entry mit key oder gibt null zurueck.
     * @param key Suchschluessel
     * @return null oder Referenz auf gefundenen Entry.
     */
    private Entry<K,V> referenceOf(K key) {
        // TODO den richtigen Suchalgorithmus (direkte Verkettung) einsetzen.
    	return null;
    }

    private int hash(Object key) {
	    return key == null ? 0 : (key.hashCode() & 0x7fffffff) % data.length;
	}
	
	private static boolean safeEquals(Object a, Object b) {
        return a == null ? a == b : a.equals(b);
    }

    /**
	 * Klasse fuer die einzelnen Eintraege.
	 */
	static class Entry<K,V> {
	    private final K key;
	    private V value;
	    private Entry<K,V> link;

	    /**
	     * Konstruktor.
	     * 
	     * @param key Schluesselbegriff.
	     * @param value eigentlicher Inhalt.
	     */
	    Entry(K key, V value, Entry<K,V> link) {
	        this.key = key;
	        this.value = value;
	        this.link = link;
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
}