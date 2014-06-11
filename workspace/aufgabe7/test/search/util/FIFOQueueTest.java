package search.util;

import static org.junit.Assert.assertSame;

import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class FIFOQueueTest extends AbstractQueueTest {
    
    @Before
    public void setUp() {
        q = new search.util.FIFOQueue<Object>();
    }
    
    @Test
    public void testOrdering() {
        String s1 = "a";
        String s2 = "b";
        String s3 = "c";
        q.put(s2);
        q.put(s1);
        q.put(s3);
        q.put(s1);
        assertSame(s2, q.get());
        assertSame(s1, q.get());
        assertSame(s3, q.get());
        assertSame(s1, q.get());
    }
    
    @Override
    protected void determineOrderingSequence(List<Object> d) {
        // nothing to do
    }
}
