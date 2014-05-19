package util;

/**
 * Klasse zum Speichern einer Menueaktion.
 */
final class Item {
    /**
     * Auswahlbuchestabe
     */
    private final char key;
    /**
     * Text der Menuezeile.
     */
    private final String name;
    /**
     * Objekt fuer die Ausfuehrung der gewaehlten
     * Aktion. Wenn <tt>action == null</tt> ist, wird
     * anstelle der Aktion die Methode <tt>runloop</tt>
     * beendet.
     */
    private final Command action;

    /**
     * Speichert eine Menuezeile zusammen mit der auszuloesenden Aktion.
     * 
     * @param name lesbarer Name der Menuezeile
     * @param action auszufuehrende Aktion
     */
    Item(char key, String name, Command action) {
        this.key = Character.toLowerCase(key);
        this.name = name;
        this.action = action;
    }
    
    void execute() {
        action.run();
    }
    
    boolean keyEquals(char selection) {
        return key == selection;
    }
     
    @Override
    public String toString() {
        return key + ": " + name;
    }
}