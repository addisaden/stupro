import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * Die Klasse implementiert ein Verzeichnis, in dem nach unter einem Schluessel
 * gespeicherten Daten gesucht werden kann.
 */
public final class LinearMap<K, V> implements IMap<K, V> {
    /*
     * Hinweis:
     * 
     * @Override zeigt, dass eine Methode einer Oberklasse ueberschrieben wird
     * oder die Methode eines Interfaces implementiert wird.
     * 
     * Im letzten Jahr waren diese Annotationen noch auskommentiert, da
     * Java 5 damit Probleme hat. 
     * 
     * Bei sourcce->Clean Up fuegt Eclipse diese automatisch ein. Sie sind auch
     * sinnvoll, da sie Lesbarkeit und Fehlersicherheit verbessern.
     * Die Studierenden brauchen sie aber erst nicht zu beachten.
     * 
     * In Eclipse wird override am Rand durch einen gruenen Pfeil nach oben
     * angezeigt. Implementierung erkennt man an dem blauen Dreieck.
     */

    private static final int NOT_FOUND = -1;

    /**
     * Anzahl der gespeicherten Key-Value-Paare.
     */
    private int size = 0;

    /**
     * Feld mit den Daten.
     * <p>
     * Umgeht die Probleme beim Anlegen eines neuen Arrays mit Typparameter.
     * Beim Erzeugen einess Arrays darf kein Typparameter verwendet werden.
     */
    @SuppressWarnings("unchecked")
    private KeyValuePair<K, V>[] data = new KeyValuePair[4];

    @Override
    public int size() {
        return 0;
    }

    @Override
    public V put(K key, V value) {
        int index = indexOf(key);
        if (index == NOT_FOUND) {
            // TODO: Was zu tun?
            data[size++] = new KeyValuePair<K, V>(key, value);
            return null;
        }
        return data[index].setValue(value);
    }

    /**
     * Sorgt dafuer, dass das data-Array gross genug ist. Bei Bedarf, werden
     * die Daten in ein doppelt grosses Array uebernommen.
     */
    private void checkCapacity() {
        if (size == data.length)
            data = Arrays.copyOf(data, size * 2);
    }

    @Override
    public V get(K key) {
        // TODO: Was zu tun?
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        // TODO: Was zu tun?
        return false;
    }

    @Override
    public V remove(K key) {
        // TODO: Was zu tun?
        return null;
    }

    /**
     * Sucht in dem Array data nach einem Key-Value-Paar das zu dem gesuchten
     * 'key' passt. Wenn gefunden wird der Feldindex zurueckgegeben. Wenn nicht,
     * der Wert 'NOT_FOUND'.
     * <p>
     * Es ist nicht erlaubt, dass <tt>key == null</tt> ist.
     * 
     * @param key
     *            der zu suchende Schluessel
     * @return NOT_FOUND oder der index wo key gefunden wurde.
     * @throws NullPointerException
     *             wenn <tt>key</tt> gleich <tt>null</tt> ist.
     */
    private int indexOf(K key) {
        if (key == null)
            throw new NullPointerException();
        // TODO: Was zu tun?
        return NOT_FOUND;
    }

    @Override
    public Set<K> keys() {
        Set<K> keys = new HashSet<K>(2 * size);
        for (int i = 0; i < size; i++)
            keys.add(data[i].getKey());
        return keys;
    }

    @Override
    public Collection<V> values() {
        Collection<V> values = new ArrayList<V>(size);
        for (int i = 0; i < size; i++)
            values.add(data[i].getValue());
        return values;
    }

    @Override
    public String toString() {
        return Arrays.toString(data);
    }
}