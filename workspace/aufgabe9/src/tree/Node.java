package tree;

public interface Node {
    /**
     * Berechnet den Ausdruckswert fuer diesen Baum.
     * @return Ausdruckswert
     */
    public double value();

    /**
     * Stellt den Baum als lesbaren String dar.
     * Alle Teilausdrücke werden zumindest dann geklammert, wenn das sonst
     * zu Missverstaendnissen fuehren koennte.
     * 
     * @return Stringdarstellung.
     */
    public String toString();
}
