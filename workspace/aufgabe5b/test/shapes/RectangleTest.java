package shapes;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test fuer die Klasse Circle.
 */
public class RectangleTest {
    private IShape s1;
    private IShape s2;
    private IShape s3;

    @Before
    public final void setUp() {
        s1 = new Rectangle("a", 1, 2);
        s2 = new Rectangle("b", 3, 4);
        s3 = new Rectangle("c", 1, 2);
    }

    @Test
    public void testArea() {
        double area13 = 1*2;
        double area2 = 3*4;
        
        assertEquals(area13, s1.getArea(), 1e-7);
        assertEquals(area2, s2.getArea(), 1e-7);
        assertEquals(area13, s3.getArea(), 1e-7);
    }
    
    @Test
    public void testName() {
        assertEquals("a", s1.getName());
        assertEquals("b", s2.getName());
        assertEquals("c", s3.getName());
    }

    @Test
    public void testCompare() {
        assertEquals(0, s1.compareTo(s3));
        assertEquals(0, s3.compareTo(s1));
        assertTrue(s1.compareTo(s2) < 0);
        assertTrue(s2.compareTo(s1) > 0);
        assertTrue(s3.compareTo(s2) < 0);
        assertTrue(s2.compareTo(s3) > 0);
    }

    @Test
    public void testEquals() {
        assertFalse(s1.equals(s2));
        assertFalse(s1.equals(s3));
        assertFalse(s3.equals(s2));
    }
    
    private String prefix() {
        return "Rectangle.";
    }
    
    @Test
    public void testToString() {
        String p = prefix();
        assertEquals(p +"a", s1.toString());
        assertEquals(p +"b", s2.toString());
        assertEquals(p +"c", s3.toString());
    }
}
