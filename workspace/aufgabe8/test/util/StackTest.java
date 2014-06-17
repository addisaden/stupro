package util;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StackTest {
    private Stack stk;

    @Before
    public void setUp() throws Exception {
        stk = new Stack();
    }

    @Test
    public void testPushPop() {
        stk.push("a");
        stk.push("b");
        stk.push("c");
        assertEquals("c", stk.pop());
        assertEquals("b", stk.pop());
        assertEquals("a", stk.pop());
    }
}
