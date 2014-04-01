package bank;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class UeberweisungTest {
    private Bank b1;
    private Bank b2;
    private int k1;
    private int k2;
    private static final double EPS = 1e-6;

    @Before
    public void setUp() throws Exception {
        b1 = new Bank("b1");
        b2 = new Bank("b2");
        k1 = b1.neuesKonto();
        k2 = b2.neuesKonto();
        b1.barEinzahlen(k1, 100);
    }

    @Test
    public void testUeberweisen() {
        int k3 = b2.neuesKonto();
        b1.ueberweisen(k1, b2, k2, 40);
        b2.ueberweisen(k2, b2, k3, 20);
        b2.ueberweisen(k3, b2, k2, 10);
        assertEquals(60., b1.kontostand(k1), EPS);
        assertEquals(30., b2.kontostand(k2), EPS);
        assertEquals(10., b2.kontostand(k3), EPS);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFalscheKontoNummer() {
        b1.ueberweisen(k1 + 1, b2, k2, 20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void falschesZiel() {
        b1.ueberweisen(k1, b2, k2 + 1, 20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativerBetrag() {
        b1.ueberweisen(k1, b2, k2, -20);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNichtGedeckt() {
        b1.ueberweisen(k1, b2, k2, 120);
    }}
