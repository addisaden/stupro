package search.util;

import java.util.NoSuchElementException;

public final class FIFOQueue<T> implements IQueue<T> {
    private static class Link<T> {
        Link(T value, Link<T> next) {
            this.value = value;
            this.next = next;
        }
        T value;
        Link<T> next;     
    }
    Link<T> first = null;
    
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
    	
    	T result = first.value;
    	
    	first = first.next;
    	
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
