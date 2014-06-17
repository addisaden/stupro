package tree;

import util.LinkedObjects;

public interface Node {
    /**
     * Fuegt der Liste <tt>dest</tt> den Code fuer den aktuellen Knoten hinzu.
     * @param dest Ausgabeliste
     */
    public void compileTo(LinkedObjects dest);
}
