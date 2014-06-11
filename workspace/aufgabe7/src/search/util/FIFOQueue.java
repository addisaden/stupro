package search.util;

import java.util.NoSuchElementException;
import java.util.LinkedList;

public final class FIFOQueue<T> implements IQueue<T> {
    LinkedList<T> list = new LinkedList<T>();
    
    @Override
    public void put(T value) {
    	list.addLast(value);
    }
    
    @Override
    public T get() throws NoSuchElementException {
    	return list.removeFirst();
    }
    
    @Override
    public boolean isEmpty() {
    	return list.isEmpty();
    }

    @Override
    public void clear() {
    	list.clear();
    }
}
