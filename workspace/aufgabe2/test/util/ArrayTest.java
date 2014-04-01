package util;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import util.Array;

public class ArrayTest {
    private Array<String> a;
    
    @Before
    public void setUp() throws Exception {
        a = new Array<String>();
    }
    
    @Test
    public void testLeeresArray() {
        assertEquals("am Anfang enthaelt das Array nichts", 0, a.size());
    }
    
    @Test
    public void testEinElement() {
        a.add("a");
        assertEquals("es gibt genau 1 Element", 1, a.size());
        assertSame("es wurde die Referenz gespeichert", "a", a.get(0));
    }
    
    @Test
    public void testSpeichern() {
        fuelleA();
        assertEquals("wir haben 4 Elemente", 4, a.size());
        assertSame("a", a.get(0));
        assertSame("b", a.get(1));
        assertSame("c", a.get(2));
        assertSame("d", a.get(3));
        assertEquals("wir haben immer noch 4 Elemente", 4, a.size());
    }
    
    @Test
    public void testIndexOf() {
    	fuelleA();
    	assertEquals("a wird richtig gefunden", 0, a.indexOf("a"));
    	assertEquals("c wird richtig gefunden", 2, a.indexOf("c"));
    	assertEquals("x ist nicht vorhanden", -1, a.indexOf("x"));
    }
    
    @Test
    public void testIndexOfNull() {
        fuelleA();
        assertEquals("null ist nicht vorhanden", -1, a.indexOf(null));
        a.add(null);
        assertEquals("jetzt ist null vorhanden", 4, a.indexOf(null));
    }
    
    @Test
    public void testObEqualsGenutzt() {
        a.add("xyz");
        a.add("abcde");
        a.add("abd");
    	assertEquals( "== ist verkehrt, equals() benutzen!",
    			1, a.indexOf(new String("abcde")));
    }
    
    /**
     * Ergebnis von reversed ist umgedreht.
     */
    @Test
    public void testReversed() {
        fuelleA();
        Array<String> r = a.reversed();
        assertEquals("a", r.get(3));
        assertEquals("b", r.get(2));
        assertEquals("c", r.get(1));
        assertEquals("d", r.get(0));
    }
    
    /**
     * Original von reversed ist nicht veraendert.
     */
    @Test
    public void noModificationWithReverse() {
        fuelleA();
        a.reversed();
        assertEquals("a", a.get(0));
        assertEquals("b", a.get(1));
        assertEquals("c", a.get(2));
        assertEquals("d", a.get(3));
        
    }
    
    /**
     * Bei einem leeren Array wird get(0) eine 
     * {@code IndexOutOfBoundsException}.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void leer() {
            a.get(0);
    }
    
    /**
     * Der Zugriff auf ein nicht vorhandenes Element wirft eine 
     * {@code IndexOutOfBoundsException}.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void zugross() {
        fuelleA();
        a.get(5);
    }
    
    /**
     * Fuellt das Array mit "a" bis "d".
     */
    private void fuelleA() {
        a.add("a");
        a.add("b");
        a.add("c");
        a.add("d");
    }
}
