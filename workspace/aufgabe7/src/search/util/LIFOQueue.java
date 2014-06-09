package search.util;

import java.util.NoSuchElementException;

/**
 * Diese Realisierung von {@code Queue} verfaehrt nach dem LIFO-Prinzip.
 * Es handelt sich also um dass, wass man allgemein als Stack
 * bezeichnet.
 * 
 * @author Erich Ehses.
 */
public final class LIFOQueue<T> implements IQueue<T> {
    private static class Link<T> {
        Link(T value, Link<T> next) {
            this.value = value;
            this.next = next;
        }
        T value;
        Link<T> next;     
    }
    Link<T> first = null;

    /* Klasseninvariante:
     * bei leerer Liste ist first = null
     * sonst enthaelt first die Referenz auf den 1. Listenknoten (Link).
     * Links enthalten einen Wert (value = Nutzinformation) und die
     * Referenz auf den nachfolgenden Knoten (next). Das next-Feld des 
     * letzten Knotens ist null.
     * 
     * Hinweis: Beachten Sie auch die Kommentare in IQueue!
     */

    @Override
    public void put(T value) {
    	if(first == null) {
    		first = new Link<T>(value, null);
    		return;
    	}
    	
    	Link<T> i = first;
    	while(i.next != null) {
    		i = i.next;
    	}
    	i.next = new Link<T>(value, null);
    }

    @Override
    public T get() throws NoSuchElementException {
    	if(first == null)
    		throw new NoSuchElementException();
    	
    	if(first.next == null) {
    		T result = first.value;
    		first = null;
    		return result;
    	}
    	
    	Link<T> i = first;
    	while(i.next.next != null)
    		i = i.next;
    	
    	T result = i.next.value;
    	i.next = null;
    	
        return result;
    }

    @Override
    public boolean isEmpty() {
        return (first == null);
    }

    @Override
    public void clear() {
        first = null;
    }
}
