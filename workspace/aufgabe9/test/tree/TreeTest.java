package tree;

import static org.junit.Assert.assertEquals;
import main.TreeBuilder;

import org.junit.Before;
import org.junit.Test;

public class TreeTest {
    private Node r;
    
    @Before
    public void createTree() {
        TreeBuilder b = new TreeBuilder();
        r = b.add(b.number("2"), b.mul(b.number("3"), b.number("4")));
    }
    
    @Test
    public void evaluate() {
        assertEquals(14.0, r.value(), 0.001);
    }
    
    @Test
    public void infix() {
        assertEquals("(2.0 + (3.0 * 4.0))", r.toString());
    }
}
