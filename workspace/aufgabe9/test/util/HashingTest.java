package util;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Klassen zum Testen der LinearMap-Klasse aus dem AP-Praktikum.
 * 
 * @author Erich Ehses
 */
public class HashingTest {

    /*
     * Hiermit wird der Belastungstest gesteuert. Die Zahl 1000 ist keine feste
     * Groesse, die im Programm beruecksichtigt werden darf! Sie koennte z.B. auch
     * 10 Millionen betragen.
     */
    protected static final int RANDOM_TESTS = 1000;

    /**
     * Variable fuer das Testobjekt.
     */
    protected IMap<String, Double> objTable;

    /**
     * Vorbereitungsarbeiten.
     */
    @Before
    public void setUp() {
        objTable = new Hashing<String,Double>();
    }

    /**
     * Verlangt, dass ein gerade konstruiertes Objekt, leer ist. Es duerfen keine
     * Daten gefunden werden.
     */
    @Test
    public void testConstructor() {
        assertEquals("Tabelle muss leer sein", 0, objTable.size());
        assertFalse("bei leerer Tabelle darf containsKey nichts finden",
            objTable.containsKey("a"));
    }

    /**
     * Verlangt, dass sich ein einzelnes Schluessel-Wert-Paar eintragen und
     * wiederfinden laesst. Zusaetzlich wird verlangt, dass nicht unnoetig viele
     * Vergleiche stattfinden.
     */
    @Test
    public void testOnePutGet() {
        Double einsFuenf = 1.5;
        objTable.put("a", einsFuenf);
        assertEquals(1, objTable.size());
        assertTrue("containsKey findet das eingetragene Objekt nicht",
                objTable.containsKey("a"));
        assertSame(einsFuenf, objTable.get("a"));
        assertFalse("contains findet einen nicht vorhandenen Schluessel",
                objTable.containsKey("b"));
        assertNull("wenn nichts gefunden, muss get null zurueckgeben",
                objTable.get("b"));
    }
    
    /**
     * Verlangt, dass ein bereits vorhandener Eintrag mit einem neuen Wert
     * ueberschrieben wird.
     */
    @Test
    public void testOverwriting() {
        Double v1 = 1.4;
        Double v2 = 7.3;
        objTable.put("a", v1);
        assertSame("das eingetragene Objekt kommt nicht wieder zurueck", v1,
            objTable.get("a"));
        objTable.put("a", v2);
        assertSame("beim Ueberschreiben darf kein neues Objekt angelegt werden",
            v2, objTable.get("a"));
        assertEquals(1, objTable.size());
    }


    @Test(expected = NullPointerException.class)
    public void nullInPut() {
        objTable.put(null, 1.5);
    }
    
    @Test(expected = NullPointerException.class)
    public void nullInGet() {
        objTable.get(null);
    }
    
    @Test(expected = NullPointerException.class)
    public void nullInContainsKey() {
        objTable.containsKey(null);
    }

    /**
     * Verlangt, dass die Tabelle mit <code>null</code> Argumenten fuer Value
     * richtig umgeht.
     */
    @Test
    public void testNullValue() {
        objTable.put("", null);
        assertEquals("ein leerer String mit null-Wert wird falsch behandelt",
            1, objTable.size());
        assertTrue("contains-Test funktioniert nicht mit null-Wert",
            objTable.containsKey(""));
        assertNull("die Rueckgabe muesste null sein", objTable.get(""));
    }
    
    /**
     * Erzwingt Kollisionen in der Tabelle.
     */
    @Test
    public void collisionHandling() {
        for (int i = 0; i < RANDOM_TESTS; i++)
            objTable.put(String.valueOf(i), Double.valueOf(i));
        for (int i = 0; i < 1000; i++)
            assertEquals(i, objTable.get(String.valueOf(i)).intValue());
    }
}