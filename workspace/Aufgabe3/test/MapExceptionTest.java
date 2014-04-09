
import org.junit.Before;
import org.junit.Test;

/**
 * In LinearMap darf {@code null} nicht als Key verwendet werden.
 * Wenn doch, soll eine {@code NullPointerException} geworfen werden.
 */
public class MapExceptionTest {
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
    }

    /**
     * put(null, ..) ist nicht erlaubt.
     */
    @Test(expected = NullPointerException.class)
    public void nullInPut() {
        objTable.put(null, "");
    }
    
    /**
     * get(null) ist nicht erlaubt.
     */
    @Test(expected = NullPointerException.class)
    public void nullInGet() {
        objTable.get(null);
    }
    
    /**
     * contains(null) ist nicht erlaubt.
     */
    @Test(expected = NullPointerException.class)
    public void nullInContainsKey() {
        objTable.containsKey(null);
    }
    
    /**
     * remove(null) ist nicht erlaubt.
     */
    @Test(expected = NullPointerException.class)
    public void nullInRemove() {
        objTable.remove(null);
    }


}