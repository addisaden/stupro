

/**
 * Diese Klasse erzeugt Suchschluessel als Mockobjekte fuer das Testes von
 * LinearMap-Implementierungen. <code>TestKey</code> -Objekte koennen ganz normal
 * als Suchschluessel verwendet werden. Gleichzeitig wird die Anzahl der Aufrufe
 * der Methode <code>equals</code> gezaehlt, so dass am Ende ueber die Aufrufe
 * <code>getTotalCompares</code> und <code>getCompares</code>
 * deren Zahl abgefragt werden kann.
 */
class TestKey implements Comparable<TestKey> {
    private static int totalCompares = 0;
    private int compares = 0;

    private final int value;
    private boolean calledCompareTo = false;

    /**
     * Erzeugt eine Schluessel mit definiertem Hashcode.
     * 
     * @param value der Hashcode.
     */
    public TestKey(int value) {
        this.value = value;
    }

   /**
    * Damit der HashCode nicht anstelle von equals verwendet werden kann,
    * wird er bewusst mehrdeutig definiert.
    */
    @Override
    public int hashCode() {
        return Math.abs(value % 2);
    }
    
    /**
     * Damit eine vernuenftige Ausgabe erscheint.
     */
    @Override
    public String toString() {
    	return "Key(" + value + ")";
    }

    @Override
    public boolean equals(Object x) {
        compares++;
        totalCompares++;
        if (!(x instanceof TestKey))
            return false;
        return ((TestKey) x).value == value;
    }

    public int compareTo(TestKey x) {
    	calledCompareTo = true;
        return value - x.value;
    }
    
    /**
     * Gibt die intern gespeicherte Identitaet zurueck.
     * @return interne Identitaet
     */
    public int value() {
    	return value;
    }

    /**
     * Gibt die Gesamtzahl der Vergleiche aller Schluessel zur�ck,
     * Gezaehlt werden die Aufrufe von <code>equals</code>.
     * 
     * @return Gesamtzahl der Vergleiche.
     */
    public static int getTotalCompares() {
        return totalCompares;
    }

    /**
     * Gibt die Anzahl der Vergleiche fuer diesen Schluessel zurueck,
     * Gezaehlt werden die Aufrufe von <code>equals</code>.
     * 
     * @return Anzahl der Vergleiche.
     */
    public int getCompares() {
        return compares;
    }

    /**
     * Loescht die Gesamtzahl der Vergleiche.
     * Die Anzahl der Vergleich pro Objekt ist hiervon nicht
     * betroffen.
     */
    public static void clearTotalCompares() {
        totalCompares = 0;
    }

    /**
     * Loescht die Anzahl der Vergleiche fuer dieses Objekt.
     * Die Gesamtzahl der Vergleiche ist hiervon nicht betroffen.
     */
    public void clearCompares() {
        compares = 0;
    }
    
    /**
     * Wurde compareTo aufgerufen?
     * @return true wenn compareTo aufgerufen wurde.
     */
    public boolean calledCompareTo() {
    	return calledCompareTo;
    }
}