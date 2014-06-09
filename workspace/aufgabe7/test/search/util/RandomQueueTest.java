package search.util;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class RandomQueueTest extends AbstractQueueTest {
    
    @Before
    public void setUp() {
        q = new search.util.RandomQueue<Object>();
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
        String[] data = new String[4];
        for (int i = 0; i < data.length; i++)
            data[i] = (String) q.get();
        Arrays.sort(data);
        assertSame(s1, data[0]);
        assertSame(s1, data[1]);
        assertSame(s2, data[2]);
        assertSame(s3, data[3]);
    }
    
    @Override
    protected void determineOrderingSequence(List<Object> d) {
        // nothing to do
    }
    
    @Override
    public void testRandomData() {
        // overrides Test from Superclass.
    } 
}
