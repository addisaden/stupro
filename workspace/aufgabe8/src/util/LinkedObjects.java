package util;

import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Doppelt verkettete zirkuläre Liste.
 */
public class LinkedObjects implements Iterable<Object> {
    /*
     * Invariante:
     * head zeigt auf dem Kopfknoten der Liste
     * head.next zeigt auf den ersten Listenknoten
     * head.prev zeigt auf den letzten Listenknoten
     * head.obj = null
     * fuer jeden Listenknoten k zeigt
     *     k.obj auf das gespeicherte Objekt
     *     k.prev auf den Vorgaenger
     *     k.next auf den Nachfolger
     * Vor dem ersten Knoten ist head und nach dem letzten Knoten ist head.
     * In einer leeren Liste zeigt der Head-Knoten auf sich selbst.
     * 
     */
    private static class Link {
        final Object obj;
        Link next;
        Link prev;

        /**
         * Wird nur bei der Initialisierung aufgerufen!
         */
        Link() {
            obj = null;
            this.next = this.prev = this;
        }

        /**
         * Erzeugt einen neuen Knoten.
         * @param obj   gespeicherter Inhalt
         * @param prev  Vorgaenger
         * @param next  Nachfolger
         */
        Link(Object obj, Link prev, Link next) {
            this.obj = obj;
            this.prev = prev;
            this.next = next;
        }
    }
    private final Link head = new Link();
    
    // Fuegt am Ende hinzu
    public void add(Object obj) {
        // TODO: korrigieren
    }
    
    public void addFirst(Object obj) {
        // TODO: korrigieren
    }
    
    public Object removeFirst() {
        // TODO: korrigieren
        return null;
    }
    
    public Object removeLast() {
        // TODO: korrigieren
        return null;
    }

    public boolean isEmpty() {
        // TODO: korrigieren
        return true;
    }

    @Override
    public Iterator<Object> iterator() {
        return new Iterator<Object>() {
        	boolean removable = false;
            Link current = head.next;

            @Override
            public boolean hasNext() {
                // TODO: korrigieren
                return false;
            }
            @Override
            public Object next() {
                if (!hasNext()) throw new NoSuchElementException();
                removable = true;
                Object result = current.obj;
                // TODO: korrigieren
                return result;
            }
            @Override
            public void remove() {
                if (! removable) throw new IllegalStateException();
                removable = false;
                Link toRemove = current.prev;
                toRemove.prev.next = current;
                current.prev = toRemove.prev;
            }
        };
    }
}
