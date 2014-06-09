package search.util;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class PriorityQueueTest extends AbstractQueueTest implements Comparator<Object> {

    @Before
    public void setUp() {
        q = new search.util.PriorityQueue<Object>(2000, this);
    }
    
    @Test
    public void testOrdering() {
        Double d1 = 1.1;
        Double d2 = 2.2;
        Double d3 = 3.3;
        q.put(d2);
        q.put(d1);
        q.put(d3);
        q.put(d1);
        assertSame(d1, q.get());
        assertSame(d1, q.get());
        assertSame(d2, q.get());
        assertSame(d3, q.get());
    }
    
    @Override
    protected void determineOrderingSequence(List<Object> d) {
        Collections.sort(d, this);
    }

    public int compare(Object o1, Object o2) {
        return ((Double) o1).compareTo((Double) o2);
    }
}
