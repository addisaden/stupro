package search.util;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

public class PriorityQueue<T> implements IQueue<T> {
    /*
     * Implementierungshinweise:
     * 
     * Die Klasse realisiert eine Priority-Queue, deren put- und get-Operationen
     * eine Komplexitaet von O(log n) haben.
     * 
     * Dazu werden die Daten in einem Array als Heap gespeichert.
     * Vergleichen Sie dazu die Folien (folie6.pdf) zu dem Thema "Heapsort".
     * 
     * Wenn von Eltern (parent) und Kindern (child) die Rede ist,
     * sind die die Feldindizes in dem Array data gemeint.
     * 
     * In dem hier verwendeten Heap ist der Inhalte des Elternknoten kleiner
     * als die Inhalte der Kindknoten.
     * 
     * Der Elternknoten p hat die Kinder
     *       k1 = 2 * p + 1 und k2 = 2 * p + 2
     * Ein Kind k hat den Elternknoten p = (k - 1) / 2.
     * 
     * Zur Vereinfachung sind die beiden Methoden parent und child angegeben.
     */
    /*
     * Array fuer die Daten.
     */
    private Object[] data;
    private int size = 0;
    private static final int ROOT = 0;

    /*
     * Comparator zur Definition der Reihenfolge.
     * (jeder Comparator, der fuer den Typ T oder sogar fuer einen Obertyp
     * von T definiert ist, funktioniert.)
     */
    private Comparator<? super T> cmp;

    /**
     * Erzeugt eine Priority-Queue fuer die angegebene Ordnungsrelation.
     * 
     * @param cmp
     *            <code>Comparator</code> zur Definition der Reihenfolge.
     */
    public PriorityQueue(Comparator<? super T> cmp) {
        this(10, cmp);
    }
    
    /**
     * Erzeugt eine Priority-Queue fuer die angegebene Ordnungsrelation.
     * 
     * @param length anfaengliche Groesse der Queue.
     * @param cmp
     *            <code>Comparator</code> zur Definition der Reihenfolge.
     */
    public PriorityQueue(int length, Comparator<? super T> cmp) {
        if (cmp == null)
            throw new NullPointerException();
        this.cmp = cmp;
        this.data = new Object[length];
    }

    public void clear() {
        Arrays.fill(data, 0, size, null);
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void put(T value) {
        if (size == data.length)
            data = Arrays.copyOf(data, data.length*2);
        data[size++] = value;
        swim();
    }

    /**
     * Gibt das kleinste Queue-Element zurueck und entfernt es aus der
     * Queue.
     * Was klein ist, wird durch den Comparator definiert.
     * @return kleinstes Element.
     * @throws NoSuchElementException wenn die Queue leer ist.
     */
    @SuppressWarnings("unchecked")
    public T get() {
        if (size == 0) throw new NoSuchElementException();
        Object result = data[ROOT];
        data[ROOT] = data[--size];
        data[size] = null;
        sink();
        return (T) result;
    }

    /**
     * Das zuletzt eingefuegt Element "schwimmt" solange
     * nach oben, bis es seinen "Meister" gefunden hat,
     * d.h. ein Elternelement, das einen besseren (kleineren)
     * Wert hat.
     */
    private void swim() {
        /*
         * Pseudocode
         * child = zuletzt eingefuegtes Element (= size - 1)
         * parent = Elternknoten von child
         * solange child einen 'schlechteren' Elternknoten hat:
         *     vertausche die Werte von child und parent
         *     gehe nach 'oben' (child = parent,  ...) 
         *     
         * Invariante:
         *    ab child nach unten haben wir einen Heap
         */
        int child = size - 1;
        int parent = parentOf(child);

        // TODO: Algorithmus einfuegen
        
        
        while(parent > ROOT && precedes(child, parent)) {
        	swap(parent, child);
        	child = parent;
        	parent = parentOf(child);
        }
        
        
        
        // Ziel wir haben einen Heap
    }
    
    /**
     * Das oberste Element sinkt solange nach unten, bis es besser ist
     * als die beiden Kinder. Das bessere (mit kleinerem Wert) Kind
     * steigt jeweils auf.
     */
    private void sink() {
        /*
         * Der Aufruf erfolgt, nachdem das oberste Element aus dem Heap 
         * entfernt wurde, und an seiner Stelle das letzte Element eingefuegt
         * wurde.
         * 
         * Pseudocode:
         * parent = oberstes Element (= ROOT = 0)
         * child das erste Kind davon
         * solange es noch ein child gibt:
         *    falls das eventuelle Geschwisterkind (child + 1) besser ist:
         *         nehmen wir das Geschwisterkind (child = child + 1)
         *    falls parent besser ist als child:
         *        sind wir fertig
         *    sonst:
         *        vertausche die Werte von child und parent
         *        gehe nach "unten" (parent = child, ...)
         * 
         * Invariante: ab parent nach oben haben wir einen Heap.
         */
        int parent = ROOT;
        int child = firstChildOf(parent);
        
        // TODO: Algorithmus einfuegen
        while(child < size) {
        	if(precedes(child + 1, child)) {
        		child++;
        	}
        	
        	if(precedes(parent, child)) {
        		break;
        	} else {
        		swap(parent, child);
        		parent = child;
        		child = firstChildOf(parent);
        	}
        }
        
        // Ziel wir haben einen Heap
    }

    /**
     * Vergleicht zwei Feldelemente hinsichtlich der Heap-Bedingung.
     * 
     * @param index1 Index des ersten Elements
     * @param index2 Index des zweiten Elements
     * @return {@code true} wenn das erste Element bevorzugt wird
     */
    @SuppressWarnings("unchecked")
    private boolean precedes(int index1, int index2) {
        return cmp.compare((T) data[index1], (T) data[index2]) <= 0;
    }

    /**
     * Vertauscht zwei Elemente des Feldes {@code data}.
     * @param index1 Index 1
     * @param index2 Index 2
     */
    private void swap(int index1, int index2) {
        Object t = data[index1];
        data[index1] = data[index2];
        data[index2] = t;
    }

    /**
     * Gibt den Index des Elternknotens zurueck.
     * @param child Index des Kindes
     * @return Index des Elternknotens
     */
    private int parentOf(int child) {
        return (child - 1) / 2;
    }
    
    /**
     * Gibt den Index des 1. Kindes zurueck.
     * @param parent Index des Elternknotens
     * @return Index des Kindes
     */
    private int firstChildOf(int parent) {
        return 2 * parent + 1;
    }
}
