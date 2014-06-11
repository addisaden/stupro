package search.graph;

import search.util.IQueue;
import search.util.PriorityQueue;
import search.util.RandomQueue;
import search.util.LIFOQueue;
import search.util.FIFOQueue;

/**
 * Die Klasse stellt ueber konstante Referenzen Factory-Objecte fuer
 * Queue-Datenstrukturen bereit. Das von der jeweiligen Konstanten mittels
 * <code>makeQueue</code> erzeugte <code>Queue</code> Objekt dient zur Steuerung
 * des Graphsuchalgorithmus.
 * <p>
 * Beispiele:
 * 
 * <pre>
 * Queue q = SearchStrategy.FIFO.makeQueue();
 * 
 * Path p = Solver.solve(SearchStrategy.A_STAR, start, goal);
 * </pre>
 * 
 * @author Erich Ehses.
 */
public enum SearchStrategy {
	/*
	 * In dieser Enum-Klasse sind die Aufzaehlungsobjekte Instanzen
	 * von anonymen Klassen, die ihrerseits von der Enum-Klasse
	 * SearchStrategy abgeleitet sind.
	 * 
	 * Die Initiatilisierung des RANDOM-Objekts entspricht der
	 * folgenden "normalen" Variablendeklaration:
	 * 
	 * public static final SearchStrategy RANDOM =
	 *     new SearchStrategy() {
	 *         public IQueue<Path> makeQueue() {
	 *             return new RandomQueue<Path>();
	 *         }
	 *     };
	 */
	
    /**
     * Zufallssuche. Zufaellige Auswahl des naechsten Suchknotens.
     */
    RANDOM {
        public IQueue<Path> makeQueue() {
            return new RandomQueue<Path>();
        }
    },

    /**
     * Tiefensuche. Auswahl nach dem LIFO-Prinzip.
     */
    // TODO: am Ende Kommentar entfernen!
    LIFO {
        public IQueue<Path> makeQueue() {
            return new LIFOQueue<Path>();
        }
    },

    /**
     * Breitensuche. Auswahl nach dem FIFO-Prinzip.
     */
    // TODO: am Ende Kommentar entfernen!
    FIFO {
        public IQueue<Path> makeQueue() {
            return new FIFOQueue<Path>();
        }
    },

    /**
     * Dijkstra's Algorithmus. Es wird jeweils der Knoten mit dem kuerzesten Weg
     * vom Startpunkt ausgewaehlt.
     */
    PATH_LENGTH {
        public IQueue<Path> makeQueue() {
            return new PriorityQueue<Path>(new PathLengthComparator());
        }
    },

    /**
     * Hill-Climbing. Es wird jeweils der dem Ziel am n�chsten liegende Knoten
     * ausgewaehlt.
     */
    HILL_CLIMBING {
        public IQueue<Path> makeQueue() {
            return new PriorityQueue<Path>(new HillClimbingComparator());
        }
    },

    /**
     * A*-Suche. Es wird jeweils der Knoten ausgewaehlt, fuer den die Summe von
     * zurueckgelegtem Weg und Entfernung zum Ziel am kleinsten ist.
     */
    A_STAR {
        public IQueue<Path> makeQueue() {
            return new PriorityQueue<Path>(new AStarComparator());
        }
    };

    /**
     * Erzeugt eine Datenstruktur zur Steuerung der Graphsuche.
     * 
     * @return Queue-Objekt mit geeigneter Ordnung.
     */
    public IQueue<Path> makeQueue() {
        throw new UnsupportedOperationException();
    }
}
