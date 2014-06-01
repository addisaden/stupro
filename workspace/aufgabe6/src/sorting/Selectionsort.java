package sorting;
public class Selectionsort implements IntSort {
    
    /**
     * Der Algorithmus SelectionSort (direkte Auswahl) funktioniert wie folgt:
     * <p>
     * Das Teilfeld array[0] bis array[i-1] ist fertig sortiert. Um i fuer den 
     * naechsten Durchlauf erhoehen zu koennen wir in dem noch nicht sortierten
     * Teilfeld das Feldelement mit der kleinsten Zahl gesucht. Dieses tausch
     * dann seinen Inhalt mit a[i].
     * <p>
     * Ein korrekter Ablauf wird durch die folgenden Ausgaben dargestellt:
     * <pre>
     * SelectionSort: [| 7, 1, 6, 2, 3, 8, 4, 5] 
     * SelectionSort: [1| 7, 6, 2, 3, 8, 4, 5] 
     * SelectionSort: [1, 2| 6, 7, 3, 8, 4, 5] 
     * SelectionSort: [1, 2, 3| 7, 6, 8, 4, 5] 
     * SelectionSort: [1, 2, 3, 4| 6, 8, 7, 5] 
     * SelectionSort: [1, 2, 3, 4, 5| 8, 7, 6] 
     * SelectionSort: [1, 2, 3, 4, 5, 6| 7, 8] 
     * SelectionSort: [1, 2, 3, 4, 5, 6, 7, 8]
     * * </pre>
     * Der Strich | trennt den sortierten von dem unsortierten Teil.
     */
    public void sort(int[] array) {
    	for(int i = 0; i < array.length; i++) {
    		for(int j = i; j < array.length; j++) {
    			if(array[j] < array[i]) {
    				int tmp = array[i];
    				array[i] = array[j];
    				array[j] = tmp;
    			}
    		}
    	}
    }
}
