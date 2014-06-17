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
        Link current = new Link(obj, head.prev, head);
        head.prev.next = current;
        head.prev = current;
    }
    
    public void addFirst(Object obj) {
        Link current = new Link(obj, head, head.next);
        head.next.prev = current;
        head.next = current;
    }
    
    public Object removeFirst() {
    	if (head.next == head) throw new NoSuchElementException();
    	
        Object result = head.next.obj;
        head.next = head.next.next;
        return result;
    }
    
    public Object removeLast() {
    	if (head.prev == head) throw new NoSuchElementException();
    	
    	Object result = head.prev.obj;
    	head.prev = head.prev.prev;
        return result;
    }

    public boolean isEmpty() {
        return (head.next == head) && (head.prev == head);
    }

    @Override
    public Iterator<Object> iterator() {
        return new Iterator<Object>() {
        	boolean removable = false;
            Link current = head.next;

            @Override
            public boolean hasNext() {
                return current != head;
            }
            @Override
            public Object next() {
                if (!hasNext()) throw new NoSuchElementException();
                removable = true;
                Object result = current.obj;
                current = current.next;
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
