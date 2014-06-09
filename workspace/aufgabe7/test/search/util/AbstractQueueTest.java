package search.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;

import search.util.IQueue;

public abstract class AbstractQueueTest {

    protected IQueue<Object> q;

    @Test
    public void testEmptyQueue() {
        assertTrue("Queue muss leer sein.", q.isEmpty());
    }

    @Test(expected = NoSuchElementException.class)
    public void testException() {
            q.get();
    }

    @Test
    public void testPut() {
        q.put("x");
        assertFalse("Queue darf nicht leer sein.", q.isEmpty());
    }

    @Test
    public void testClear() {
        q.put(1.0);
        q.put(2.0);
        q.clear();
        assertTrue("nach clear soll die Queue leer sein", q.isEmpty());
    }

    @Test
    public void testPutGetOne() {
        Double d = 2.3;
        q.put(d);
        assertSame(d, q.get());
        assertTrue("Queue muss leer sein.", q.isEmpty());
    }
    
    @Test
    public void testRandomData() {
        ArrayList<Object> data = new ArrayList<Object>();
        for (int i = 0; i < 1000; i++) {
            Double d = Math.random();
            data.add(d);
            q.put(d);
        }
        determineOrderingSequence(data);
        for (Object number : data) {
            assertEquals(number, q.get());
        }
        assertTrue("muss leer sein.", q.isEmpty());
    }
    
    /**
     * Reorder data so that it corresponds to the expected sequence of return
     * valule of the operation get. Data elements are initially ordered
     * by their creation sequence (= sequence of put messages).
     * 
     * @param data data as sequenced by put messages.
     */
    protected abstract void determineOrderingSequence(List<Object> data);
}
