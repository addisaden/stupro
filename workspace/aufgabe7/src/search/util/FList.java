package search.util;

import java.util.AbstractList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Die Klasse FListrealisiert eine unveraenderliche Liste. 
 * FList steht fuer "Functional List". Veraenderung sind nur moeglich, indem
 * ueber die cons-Operation neue erweiterte Listen erzeugt werden. Andere
 * Operationen zum Erzeugen veraenderter Liste (Teillisten, Loeschen von
 * Elementen, Append, ...) sind nicht implementiert.
 * <p>
 * Die veraendernden Operationen der Schnittstelle List erzeugen eine
 * <tt>UnsupportedOperationException</tt>.
 * <p>
 * FLists sind so implementiert, dass nach der cons-Operation die Ausgangsliste
 * und die neue Liste sich die gemeinsamen Daten teilen.
 * <p>
 * Alle Operationen der Schnittstelle <code>List</code> die einen wahlfreien
 * Zugriff auf einzelne Listenelemente erlauben und auch die Iteration ueber
 * einen <code>ListIterator</code> sind sehr ineffizient.
 * <p>
 * Zugriffe auf das erste Element und die Iteration ueber einen einfachen
 * <code>Iterator</code> sind effizient.
 * 
 * @author Erich Ehses
 */
public final class FList<T> extends AbstractList<T> {
    /**
     * Die Klasse stellt ein Element der Liste dar. 
     */
    private static class Entry<T> {
        final T value;
        final Entry<T> next;

        Entry(T value, Entry<T> next) {
            this.value = value;
            this.next = next;
        }
    }
    private final Entry<T> first;
    private final int size;
    private static final FList<Object> NIL = new FList<Object>();

    /**
     * Privater Konstruktor zum Erzeugen der leeren Liste.
     */
    private FList() {
        first = null;
        size = 0;
    }

    /**
     * Privater Konstruktor zum Erzeugen einer erweiterten Lsite
     * @param head neuer anfangswert
     * @param tail Ausgangsliste
     */
    private FList(T head, FList<T> tail) {
        first = new Entry<T>(head, tail.first);
        size = tail.size + 1;
    }
    
    /**
     * Liefert die leere Liste.
     * @return leere Liste
     */
    @SuppressWarnings("unchecked")
    public static <T> FList<T> Nil() {
        return (FList<T>) NIL;
    }
    
    /**
     * Erzeugt eine List aus n Werten
     * @param elements zu speichernde Werte
     * @return neue Liste
     */
    @SafeVarargs          // in Java 5 nicht definiert!
    public static <T> FList<T> create(T... elements) {
        FList<T> lst = Nil();
        for (int i = elements.length - 1; i >= 0; i--)
            lst = lst.cons(elements[i]);
        return lst;
    }
    
    /**
     * Erzeugt eine neue Liste, in der element an den
     * Anfang der Liste gesetzt wurde.
     * 
     * @param element neues Head-Element.
     */
    public FList<T> cons(T element) {
         return new FList<T>(element, this);
    }
    
    /**
     * Gibt die Anzahl der Listenelemente zurueck.
     * @return Anzahl der Listenelemente
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Gibt das erste Listenelement zurück.
     * 
     * @return erstes Element.
     */
    public T getFirst() {
        return first.value;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Entry<T> p = first;
        for (int i = 0; i < index; i++)
            p = p.next;
        return p.value;
    }
    
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Entry<T> p = first;

            @Override
            public boolean hasNext() {
                return p != null;
            }

            @Override
            public T next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                T result = p.value;
                p = p.next;
                return result;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}