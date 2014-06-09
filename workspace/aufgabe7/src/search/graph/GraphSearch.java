package search.graph;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import search.util.IQueue;

/**
 * Die Klasse vereint einen Graphsuche-Algorithmus und die noetigen Methoden um
 * seinen Ablauf zu beobachten.
 * <p>
 * Die Beobachtung wird durch Verwendung des Beobachter-Musters unterstuetzt.
 * <p>
 * Das Loesungsverhalten des Algorithmus haengt entscheident von der
 * Suchstrategie bereitgestellten Datenstruktur ({@link search.util.IQueue}) ab.
 * Wenn diese keine Elemente "vergisst" oder "verdoppelt", kann
 * garantiert werden, dass bei einem endlichen Graphen eine Loesung gefunden
 * wird, falls sie existiert oder dass andernfalls in endlicher Zeit die
 * Nichtexistenz einer Loesung festgestellt wird. Bei unendlichen Graphen sind
 * keine allgemeinen Aussagen moeglich. Die Art und Qualitaet der Loesung haengt
 * ausschliesslich von dem Verhalten des Verfahrens ab.
 * <p>
 * Die Suche wird in einem eigenen Thread durchgeführt. Die Klasse ist
 * threadsicher. Verzögerungszeit und Ende können von der GUI jederzeit
 * verändert oder veranlasst werden Die Abfrage der Daten liefert stets den
 * aktuellen Wert..
 * <p>
 * Die Unterbrechung per <code>Thread.interrupt()</code> ist nicht vorgesehen.
 * 
 * @author Erich Ehses.
 */
public final class GraphSearch {
    private final Set<IObserver> obs = new HashSet<IObserver>(); // Beobachter
    private final ExecutorService executor = Executors
            .newSingleThreadExecutor();
    private volatile long delay = 1; // Zeitverzoegerung pro Schritt.
    private volatile boolean toSearch;
    private volatile int numberOfVisitedNodes;

    /**
     * Legt die Suchparameter fest und veranlasst den Start der Suche. Die
     * Methode wartet, bis eine noch laufende Suche beendet wurde. Sie kehrt
     * dann sofort zurueck.
     * 
     * @param strategy
     *            bestimmt das Suchverfahren.
     * @param startNode
     *            Name des Startknotens.
     * @param goalNode
     *            Name des Zielknotens.
     */
    public void startSearch (
            final SearchStrategy strategy,
            final Node startNode,
            final Node goalNode)
    {
        final IQueue<Path> openQueue = strategy.makeQueue();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                toSearch = true;
                numberOfVisitedNodes = 1;
                Set<Node> closed = new HashSet<Node>();
                Path path = new Path(startNode, goalNode);
                openQueue.put(path);
                fireModelChanged(path);
                while (toSearch && !openQueue.isEmpty()) {
                    path = openQueue.get();
                    if (path.isComplete())
                        toSearch = false;
                    else {
                        Node front = path.lastNode();
                        if (! closed.contains(front)) {
                            closed.add(front);
                            for (Node neighbour : front.neighbours()) {
                                if (! closed.contains(neighbour))
                                    openQueue.put(path.add(neighbour));
                            }
                            traceSingleStep(path);
                            numberOfVisitedNodes++;
                        }
                    }
                }
                fireModelChanged(path);
            }
        });
    }

    private void fireModelChanged(Path p) {
        for (IObserver observer : obs)
            observer.update(p);
    }

    /**
     * Anzahl der Orte, die bei der Suche betreten wurden.
     * 
     * @return Anzahl der besuchten Orte
     */
    public int numberOfVisitedNodes() {
        return numberOfVisitedNodes;
    }

    /**
     * Veraendert die Verzoegerung pro Schritt. Bei dem Wert 0 findet kein
     * Warten statt und in diesem Fall erfolgt weder eine Verlaufsmeldung noch
     * kann die Suche abgebrochen werden.
     * 
     * @param delay
     *            Verzoegerung in Millisekunden.
     */
    public void setDelay(long delay) {
        this.delay = Math.max(delay, 0);
    }

    /**
     * Bendet die Suche.
     */
    public void cancelSearch() {
        toSearch = false;
    }

    /**
     * Fragt ab, ob die Suche gerade ausgefuehrt wird.
     * 
     * @return <code>true</code>, wenn die Suche laeuft.
     */
    public boolean isNotRunning() {
        return ! toSearch;
    }

    /**
     * Fuehrt die Aktionen bei der verzoegerten Ausfuehrung eines Suchschritts
     * aus. Dazu gehoert die Ausfuehrung der Verzoegerung und die
     * Benachrichtigung aller registrierten Listener.
     * 
     * @param path
     */
    private void traceSingleStep(Path path) {
        if (delay != 0) {
            fireModelChanged(path);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException neverHappens) {
            }
        }
    }

    /**
     * Registriert einen Beobachter. Beobachter werden bei jeder Erweiterung des
     * Suchpfades informiert.
     * 
     * @param observer
     *            Beobachter.
     */
    public void addObserver(IObserver observer) {
        obs.add(observer);
    }

}