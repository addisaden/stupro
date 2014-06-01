package sorting;

public enum Algorithm {
	/*
	 * Enum-Klassen sind eine abkuerzte Definition fuer Klassen mit genau
	 * festgelegter Menge von Instanzen.
	 * 
	 * Die Konstantendefinitionen
	 *  Name(Kontrukturparamter), ...
	 *  sind eine Abkuerzung fuer:
	 *  
	 *  public static final Algorithm Name =
	 *      new Algorithm(Konstruktorparameter); ...
	 *      
	 *  z.B.
	 *  public static final Algorithm JavaLibrary =
	 *      new Algorithm(new JavaLibrary());
	 *      
	 *  Enum-Konstruktoren sind immer private!
	 *  
	 *  Die Enum Klasse Algorithm hat  automatisch / per Verwerbung 
	 *  die folgenden zusaetzlichen Methoden:
	 *  
	 *  // Array aller Instanzen 
	 *  public static Algorithm[] values();
	 *  
	 *  // Zuordnung der passenden Konstante zu dem Namen per String
	 *  public static Algorithm valueOf(String name);
	 *  
	 *  // Ordnungszahl der Konstanten (wievieltes Objekt?)
	 *  public int ordinal();
	 *  
	 *  // gibt den Namen der Konstanten zurueck
	 *  public String name();
	 *  
	 *  // gibt den Namen zurueck
	 *  public String toString();
	 *  
	 *  // vergleicht zwei Enums (gemaess Reihenfolge)
	 *  public int compareTo(Algorithm otherAlgorithm)
	 */
    INSERTION_SORT(new Insertionsort()),
    SELECTION_SORT(new Selectionsort()),
    BUBBLE_SORT(new Bubblesort()),
    JAVA_LIBRARY(new JavaLibrary());
   
    private final IntSort sorter;
    private Algorithm(IntSort sorter) {
        this.sorter = sorter;
    }
    
    /**
     * Sortiere mit dem angegebenen Algorithmus.
     * 
     * @param a zu sortierendes Feld.
     */
    public void sort(int a[]) {
        sorter.sort(a);
    }
}
