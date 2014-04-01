package bank;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BankTest {
    private Bank b1;
    int k1;
    private static final double EPS = 1e-6;

    @Before
    public void setUp() throws Exception {
        b1 = new Bank("b1");
        k1 = b1.neuesKonto();
    }

    @Test
    public void testToString() {
        assertEquals("b1", b1.toString());
    }

    @Test
    public void testNeuesKonto() {
        assertEquals(0., b1.kontostand(k1), EPS);
    }

    @Test
    public void testBarEinzahlen() {
        b1.barEinzahlen(k1, 120);
        assertEquals(120., b1.kontostand(k1), EPS);
    }

    @Test
    public void testBarAuszahlen() {
        b1.barEinzahlen(k1, 120);
        b1.barAuszahlen(k1, 100);
        assertEquals(20., b1.kontostand(k1), EPS);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testFalscheKontoNummer() {
        b1.barAuszahlen(k1+1, 20);
    }
    
    @Test(expected=IllegalArgumentException.class)
    public void nichtGedeckt() {
        b1.barEinzahlen(k1, 60);
        b1.barAuszahlen(k1, 100);
    }
}
