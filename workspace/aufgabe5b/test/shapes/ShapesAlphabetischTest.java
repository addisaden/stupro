package shapes;
import static org.junit.Assert.*;

import java.util.Comparator;

import org.junit.Before;
import org.junit.Test;

public class ShapesAlphabetischTest {
    private Comparator<IShape> c;
    private IShape c1;
    private IShape c2;
    private IShape r1;
    private IShape r2;
    
    @Before
    public void initialize() {
        c1 = new Circle("alfa", 10.0);
        c2 = new Circle("delta", 10.0);
        // TODO: Kommentar entfernen!
        // r1 = new Rectangle("beta", 10, 20);
        // r2 = new Rectangle("alfa", 10, 20);        
        // c = new ShapesAlphabetisch();
    }
    @Test
    public void testCompare() {
        assertTrue(c.compare(c1, c2) < 0);
        assertTrue(c.compare(c1, r2) == 0);
        assertTrue(c.compare(r2, c1) == 0);
        assertTrue(c.compare(c1, r1) < 0);
        assertTrue(c.compare(r1, r2) > 0);
    }
}
