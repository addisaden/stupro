package util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

public class LinkedObjectTest {
    LinkedObjects liste;
    Object eins = new Object();
    Object zwei = new Object();
    Object drei = new Object();

    @Before
    public void setUp() throws Exception {
        liste = new LinkedObjects();
    }

    @Test
    public void testAdd() {
        liste.add(eins);
        liste.add(zwei);
        liste.add(drei);
        Iterator<Object> iter = liste.iterator();
        assertEquals(eins, iter.next());
        assertEquals(zwei, iter.next());
        assertEquals(drei, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testAddFirst() {
        liste.addFirst(drei);
        liste.addFirst(zwei);
        liste.addFirst(eins);
        Iterator<Object> iter = liste.iterator();
        assertEquals(eins, iter.next());
        assertEquals(zwei, iter.next());
        assertEquals(drei, iter.next());
        assertFalse(iter.hasNext());
    }

    @Test
    public void testRemoveFirst() {
        liste.add(eins);
        liste.add(zwei);
        liste.add(drei);
        assertEquals(eins, liste.removeFirst());
        assertEquals(zwei, liste.removeFirst());
        assertEquals(drei, liste.removeFirst());
    }

    @Test
    public void testRemoveLast() {
        liste.add(eins);
        liste.add(zwei);
        liste.add(drei);
        assertEquals(drei, liste.removeLast());
        assertEquals(zwei, liste.removeLast());
        assertEquals(eins, liste.removeLast());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(liste.isEmpty());
        liste.add(eins);
        assertFalse(liste.isEmpty());
    }
    
    @Test
    public void removeWithIterator() {
        liste.add(eins);
        liste.add(zwei);
        liste.add(drei);
        liste.add(zwei);
        for (Iterator<Object> i = liste.iterator(); i.hasNext();) {
            if (i.next().equals(zwei)) 
                i.remove();
        }
        Iterator<Object> i = liste.iterator();
        assertEquals(eins, i.next());
        assertEquals(drei, i.next());
        assertFalse(i.hasNext());
    }
    
    @Test(expected=IllegalStateException.class)
    public void unremovableWithIteratorStart() {
    	liste.add(eins);
    	liste.iterator().remove();
    }
    
    @Test(expected=IllegalStateException.class)
    public void unremovableWithIteratorDouble() {
    	liste.add(eins);
    	liste.add(zwei);
    	Iterator<Object> iter = liste.iterator();
    	iter.next();
    	iter.remove();
    	iter.remove();
    }

    @Test(expected=NoSuchElementException.class)
    public void removeFirstEmpty() {
        liste.removeFirst();
    }
    
    @Test(expected=NoSuchElementException.class)
    public void removeLastEmpty() {
        liste.removeLast();
    }
    
    @Test(expected=NoSuchElementException.class)
    public void removeIteratorEmpty() {
        liste.iterator().next();
    }
}
