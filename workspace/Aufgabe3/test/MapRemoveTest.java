
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import util.IMap;
import util.LinearMap;

/**
 * Testen der Remove-Operation von LinearMap
 * 
 * @author Erich Ehses
 */
public class MapRemoveTest {
    /*
     * Ein paar Hilfsobjekte.
     */
    protected static final TestKey K1 = new TestKey(177);
    protected static final TestKey K2 = new TestKey(233);
    protected static final TestKey K3 = new TestKey(1235);

    /**
     * Variable fuer das Testobjekt.
     */
    protected IMap<Object, Object> objTable;

    /**
     * Vorbereitungsarbeiten.
     */
    @Before
    public void setUp() {
        objTable = new LinearMap<Object, Object>();
        TestKey.clearTotalCompares();
        K1.clearCompares();
        K2.clearCompares();
        K3.clearCompares();
        objTable.put(K1, "x");
        objTable.put(K2, "a");
        objTable.put(K3, K1);
        TestKey.clearTotalCompares();
        K1.clearCompares();
        K2.clearCompares();
        K3.clearCompares();
    }

    /**
     * Wenn der zu loeschende Key nicht gefunden wird, wird {@code null}
     * zurueckgegeben.
     */
    @Test
    public void removeKeyNotFound() {
        assertNull("es muss null zurueckgegeben werden",
                objTable.remove(new TestKey(99)));
    }
    
    /**
     * Wenn ein Key/Value Paar geloescht wird wird der value zurueckgegeben.
     */
    @Test
    public void returnRemovedValue() {
        assertEquals("die geloeschte Information wird zurueckgegeben",
                "a", objTable.remove(K2));
    }

    /**
     * Ein entfernter Schluessel ist weg.
     */
    @Test
    public void keyHasBeenDeleted() {
        objTable.remove(K2);
        assertFalse("der geloesche key ist weg", objTable.containsKey(K2));
    }

    /**
     * Wenn etwas geloescht wird, muss size kleiner werden.
     */
    @Test
    public void decreaseSize() {
        objTable.remove(K2);
        assertEquals(2, objTable.size());
    }
    
    /**
     * Es duerfen keine nicht betroffenen Informationen geloescht werden.
     */
    @Test
    public void noCollateralRemoval() {
        objTable.remove(K2);
        assertTrue("es darf kein fremder Key geloescht werden",
                objTable.containsKey(K1));
        assertTrue("es darf kein fremder Key geloescht werden",
                objTable.containsKey(K3));
        assertEquals("der alte Wert ist noch da", "x",
            objTable.get(K1));
        assertSame("der alte Wert ist noch da", K1,
            objTable.get(K3));
    }

}