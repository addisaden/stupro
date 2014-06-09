package search.util;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

/**
 * Objekte geben die speicherten Daten in zufaelliger Reihenfolge
 * zurueck.
 * 
 * Diese Implementierung benutzt die Klasse <code>java.util.LinkedList</code>
 * 
 * @author Erich Ehses.
 */
public final class RandomQueue<T> implements IQueue<T> {
    private final List<T> data = new LinkedList<T>();
    private final Random rdm = new Random();

    public void put(T p) {
        data.add(p);
    }

    public T get() {
        if (this.isEmpty()) throw new NoSuchElementException("Queue is empty");
        return data.remove(rdm.nextInt(data.size()));
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    public void clear() {
        data.clear();
    }
}