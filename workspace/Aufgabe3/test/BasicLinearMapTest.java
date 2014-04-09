
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

/**
 * Klassen zum Testen der LinearMap-Klasse aus dem AP-Praktikum.
 */
public class BasicLinearMapTest {

    /*
     * Hiermit wird der Belastungstest gesteuert. Die Zahl 1000 ist keine feste
     * Groesse, die im Programm beruecksichtigt werden darf! Sie koennte z.B. auch
     * 10 Millionen betragen.
     */
    protected static final int RANDOM_TESTS = 1000;

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
    }

    /**
     * Verlangt, dass ein gerade konstruiertes Objekt, leer ist. Es duerfen keine
     * Daten gefunden werden.
     */
    @Test
    public void testConstructor() {
        assertEquals("Tabelle muss leer sein", 0, objTable.size());
        assertFalse("bei leerer Tabelle darf containsKey nichts finden",
            objTable.containsKey(K1));
    }

    /**
     * Wenn man ein Datenelement eintraegt, hat die Map die Groesse 1.
     */
    @Test
    public void testPutOne() {
        objTable.put(K1, K2);
        assertEquals(1, objTable.size());
    }
    
    /**
     * Wenn man ein Datenelement eintraegt,dann findet man es auch wieder.
     */
    @Test
    public void findOne() {
        objTable.put(K1, K2);
        assertTrue("das eingetragene muss gefunden werden",
                objTable.containsKey(K1));
        assertSame("das gefundene Objekt muss = dem eingetragenen sein",
                K2, objTable.get(K1));
    }
    
    /**
     * Man findet keine nichtvorhandenen Schluessel.
     */
    @Test
    public void dontFindNotExistentKey() {    
        objTable.put(K1, K2);
        assertFalse("contains findet einen nicht vorhandenen Schluessel",
                objTable.containsKey(K2));
        assertNull("wenn nichts gefunden, muss get null zurueckgeben",
                objTable.get(K2));
    }
        
    /**
     * Stellt sicher, dass fuer die Suche equals benutzt wird
     * und dass nicht zuviele Vergleiche durchgefuehrt werden.
     */
    @Test
    public void implementationOfSearch() {  
        objTable.put(K3, K2);
        objTable.put(K1, K2);
        objTable.containsKey(K1);
        objTable.containsKey(K2);
        int compares = TestKey.getTotalCompares();
        assertTrue("Es wurden 5 Vergleiche erwartet, aber " + compares
                + " gezaehlt", 5 >= compares);
        assertTrue("equals sollte benutzt werden!",
        		compares > 0);
    }
    
    /**
     * Manche Studenten machen den Fehler hashKey fuer den Vergleich zu
     * benutzen. Das ist falsch!!
     */
    @Test
    public void noHashCode() {
    	objTable.put(new TestKey(7), K1);
    	objTable.put(new TestKey(-7), K2);
    	assertSame("wurde hashCode benutzt?", K1,
    			objTable.get(new TestKey(7)));
    	assertSame("put falsch oder falsche Suche", K2,
    			objTable.get(new TestKey(-7)));
    }

    /**
     * Manche Studenten machen den Fehler compareTo fuer den Vergleich zu
     * benutzen. Das ist falsch!!
     */
    @Test
    public void bitteKeinCompareTo() {
    	objTable.put(K1, K2);
    	objTable.get(K1);
        assertFalse("nutze equals und nicht compareTo", K1.calledCompareTo());
        assertFalse("nutze equals und nicht compareTo", K2.calledCompareTo());
    }

    /**
     * Verlangt, dass ein bereits vorhandener Eintrag mit einem neuen Wert
     * ueberschrieben wird.
     */
    @Test
    public void testOverwriting() {
        objTable.put(K1, K2);
        assertSame("das eingetragene Objekt kommt nicht wieder zurueck",
                K2, objTable.get(K1));
        objTable.put(K1, K3);
        assertSame("beim Ueberschreiben darf kein neues Objekt angelegt werden",
            K3, objTable.get(K1));
        assertTrue("zuviele Vergleiche!", 3 >= K1.getCompares());
        assertEquals(1, objTable.size());
    }

    @Test
    public void testEquals() {
        TestKey K11 = new TestKey(K1.value());
        objTable.put(K1, "");
        assertTrue(objTable.containsKey(K11));
        assertEquals("", objTable.remove(K11));
        objTable.put(K1, "");
        objTable.put(K2, "");
        objTable.put(K11, "x");
        assertEquals(2, objTable.size());
        assertEquals("x", objTable.get(K1));
    }

    /**
     * Verlangt, dass die Tabelle mit <code>null</code> Argumenten fuer Value
     * richtig umgeht.
     */
    @Test
    public void testNullValue() {
        objTable.put("", null);
        assertTrue("leerer String ist ein Key",
            objTable.containsKey(""));
        assertNull("null kann gespeichert werden", objTable.get(""));
    }

    /**
     * Testet ob die Rueckgabe von <code>keys()</code> wirklich ein Feld mit
     * genau allen Schluesseln ist.
     */
    @Test
    public void testKeys() {
        Object[] initKeys = {K1, K2, K3, "a", 42};
        Set<Object> keySet = new HashSet<Object>();
        for (Object key : initKeys) {
            keySet.add(key);
            objTable.put(key, key.toString());
        }
        for (Object key : objTable.keys()) {
            assertTrue("keys gibt nicht vorhandenen Schluessel zurueck",
                keySet.remove(key));
        }
        assertTrue("es darf nichts uebrigbleiben", keySet.isEmpty());
    }
    
    /**
     * Testet ob die Rueckgabe von <code>values()</code> wirklich ein Feld mit
     * genau allen value-Daten ist.
     */
    @Test
    public void testValues() {
        Object[] initKeys = {K1, K2, K3, "a", 42};
        for (Object key : initKeys) 
            objTable.put(key, key.toString());
        Collection<Object> values = objTable.values();
        assertEquals(initKeys.length, values.size());
        for (Object key : initKeys) {
            assertTrue(key + " nicht gefunden", values.contains(key.toString()));;
        }
    }


    /**
     * Testet eine grosse Anzahl von Operationen. Damit soll vor allem die
     * Speicherverwaltung getestet werden.
     */
    @Test
    public void testRandomly() {
        ArrayList<Double> keys = new ArrayList<Double>(RANDOM_TESTS);
        LinearMap<Double, Integer> table = new LinearMap<Double, Integer>();
        for (int i = 0; i < RANDOM_TESTS; i++) {
            Double key = Math.random();
            Integer oldValue = table.put(key, i);
            if (oldValue == null) keys.add(key);
        }
        assertEquals(keys.size(), table.size());
        List<Double> tableKeys = new ArrayList<Double>(table.keys());
        assertEquals(keys.size(), tableKeys.size());
        Collections.sort(keys);
        Collections.sort(tableKeys);
        assertTrue(keys.equals(tableKeys));
        for (Double key : keys) {
            assertTrue(table.containsKey(key));
            table.remove(key);
            assertFalse(table.containsKey(key));
        }
        assertEquals(0, table.size());
        assertEquals(0, table.keys().size());
    }
}