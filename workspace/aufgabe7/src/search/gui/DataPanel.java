package search.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.LinkedList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import search.graph.GraphSearch;
import search.graph.Graph;
import search.graph.IObserver;
import search.graph.Node;
import search.graph.Path;
import search.graph.SearchStrategy;
import search.gui.util.ErrorDialog;
import search.gui.util.LabelledItemPanel;
import search.gui.util.RadioButtonPanel;

/**
 * Die Klasse <code>DataPanel</code> verwaltet den Benutzerdialog.
 * Von hier aus wird die Graphsuche gestartet. Das Objekt registriert
 * sich als Beobachter der Suche und stellt die aktuelle Weglaenge dar.
 * 
 * @author Erich Ehses.
 */
@SuppressWarnings({"serial","rawtypes","unchecked"})
public final class DataPanel extends JPanel implements IObserver {    
    /*
     * Konstanten fuer das Aussehen der Graphik.
     */
    private static final Color BUTTON_COLOR = Color.darkGray;
    private static final Color START_COLOR = Color.green;
    private static final Color STOP_COLOR = Color.red;

    /*
     * Modelldaten.
     */
    private final Graph graph; // der Graph, in dem gesucht wird
    private final GraphSearch solver; // der Problemloeser

    /*
     * Zahlenformate.
     */
    private final DecimalFormat lengthFormat = new DecimalFormat("0.0 km");
    private final DecimalFormat timeFormat = new DecimalFormat("0.000 s");
    private final DecimalFormat countFormat = new DecimalFormat("0");

    /*
     * Rechenzeit.
     */
    private double startTime;
    
    /*
     * Widgets.
     */
    private final JComboBox startSelection;
    private final JComboBox goalSelection;
    private final JTextField pathField = createTextField();
    private final JTextField countField = createTextField();
    private final JTextField visitedNodesField = createTextField();
    private final JTextField timeField = createTextField();
    private JTextArea pathArea;
    private final RadioButtonPanel algoSelection;
    private final JSlider delaySlider = createSlider();
    private final JButton startButton = new JButton();

    /**
     * Die Namen der Algorithmen.
     */
    private static final String[] algoNames =
        { "Zufallssuche", "Tiefensuche", "Breitensuche", "Dijkstra",
          "Bergsteigen", "A*" };

    /**
     * Die Tags der Algorithmen.
     */
    private static final SearchStrategy[] algorithms = {
            SearchStrategy.RANDOM,
            SearchStrategy.LIFO,
            SearchStrategy.FIFO,
            SearchStrategy.PATH_LENGTH,
            SearchStrategy.HILL_CLIMBING,
            SearchStrategy.A_STAR };

    /**
     * Konstruktor. Erzeugt ein fertiges Eingabepanel.
     * Wenn <code>printTree = true</code> erfolgt eine Konsolausgabe des
     * Suchbaums.
     * 
     * @param solver das suchende Objekt.
     * @param graph der Graph in dem gesucht wird.
     * @param printTree true, wenn Konsolausgabe.
     */
    public DataPanel(GraphSearch solver, Graph graph) {
        this.solver = solver;
        this.graph = graph;
        String[] nodeNames = graph.nodeNames();
        startSelection = new JComboBox(nodeNames);
        goalSelection = new JComboBox(nodeNames);
        algoSelection = new RadioButtonPanel(algoNames);
        initStartButton();
        initDisplay();
    }

	private void initStartButton() {
		setButtonToStart();
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                startOrStopApplication();
            }
        });
	}

	private void initDisplay() {
		setLayout(new BorderLayout());
        setBorder(new BevelBorder(BevelBorder.RAISED));
        add(itemList(), BorderLayout.NORTH);
        add(startButton, BorderLayout.SOUTH);
	}

    /**
     * Initialisiert die Komponenten des Unterfensters
     * fuer die Eingabe.
     */
    private LabelledItemPanel itemList() {  
        pathArea = new JTextArea(8, 0);
        pathArea.setEditable(false);
        JScrollPane pane = new JScrollPane(pathArea);
        pane.setBorder(new BevelBorder(BevelBorder.LOWERED));
        
        LabelledItemPanel items = new LabelledItemPanel();
        items.addItem("Startpunkt", startSelection);
        items.addItem("Zielpunkt", goalSelection);
        items.addItem("Weglänge", pathField);
        items.addItem("Anzahl Orte", countField);
        items.addItem("besuchte Orte", visitedNodesField);
        items.addItem("Laufzeit", timeField);
        items.addItem("Weg", pane);
        items.addItem("Algorithmus", algoSelection);
        items.addItem("Verzögerung", delaySlider);
        return items;
    }

    /**
     * Erzeugt ein mit - initialisiertes Textfeld. Texte sind rechts ausgerichtet
     * und nicht veraenderbar.
     * 
     * @return erzeugtes Textfielld-Objekt.
     */
    private JTextField createTextField() {
        JTextField field = new JTextField("-");
        field.setHorizontalAlignment(JTextField.RIGHT);
        field.setEditable(false);
        return field;
    }

    /**
     * Erzeugt einen Slider zur Einstellung der Animationsgeschwindigkeit.
     * Der Bereich der einstellbaren Zeitverzoegerung liegt zwischen 0
     * und 4 Sekunden (Default = 2).
     * @return Slider-Objekt.
     */
    private JSlider createSlider() {
        final JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 7, 4);
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.addChangeListener(new ChangeListener() {
            public void stateChanged(ChangeEvent e) {
                if (!slider.getValueIsAdjusting()) {
                    setDelayTime();
                }
            }
        });
        return slider;
    }

    /**
     * Die fest eingestellten Stufen der Verzoegerungszeit.
     * (alle Werte sind in Millisekunden)
     */
    private static final long[] DELAY_TIMES =
        { 0, 1, 100, 200, 400, 700, 1500, 3000 };

    /**
     * Uebermittelt die in delaySlider eingestellte
     * Verzoegerungszeit an den Loesungsalgorithmus
     */
    private void setDelayTime() {
        long delay = DELAY_TIMES[(int)delaySlider.getValue()];
        solver.setDelay(delay);
    }

    /**
     * Startet/stoppt die Suche.
     * Das Verhalten entspricht einem "Toggle".
     * Beim Start werden die Werte des Panels uebernommen.
     * Da die Suche in einem eigenen Thread laeuft, kehrt die
     * Methode sofort zurueck.
     */
    private void startOrStopApplication() {
        if (!solver.isNotRunning()) { // stop it.
            setButtonToStart();
            solver.cancelSearch();
        } else {                  // let's go.
            setButtonToStop();
            String startName = startSelection.getSelectedItem().toString();
            String goalName = goalSelection.getSelectedItem().toString();
            int index = algoSelection.getSelectedIndex();
            setDelayTime();
            try {
                solver.startSearch(algorithms[index], graph.findNode(startName),
                    graph.findNode(goalName));
            } catch (UnsupportedOperationException e) {
                setButtonToStart();
                new ErrorDialog(algoNames[index] + " ist nicht implementiert");
            }
        }
    }

    public void update(Path path) {
        pathArea.setText("");
        pathField.setText(lengthFormat.format(path.pathLength()));
        countField.setText(countFormat.format(path.size()));
        visitedNodesField.setText(
                countFormat.format(solver.numberOfVisitedNodes()));
        displayElapsedTime();
        if (solver.isNotRunning()) {
            final LinkedList<String> pathList = new LinkedList<String>();
            for (Node n: path.nodes()) pathList.addFirst(n.toString());
            // Aenderungen der GUI
            // sollten nur im Event-Thread erfolgen !
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
					for (String name : pathList)
					    pathArea.append(name+"\n");
                    setButtonToStart();
                }
            });
        }
    }
    
    /**
     * Startet die Zeitmessung.
     */
    private void startTimer() {
        startTime = System.currentTimeMillis();
    }
    
    /**
     * Gibt aus wie lange die Suche (bereits) dauert.
     */
    private void displayElapsedTime() {
        double totalTime = 1E-3 * (System.currentTimeMillis() - startTime);
        timeField.setText(timeFormat.format(totalTime));
    }

    /**
     * Definiert das Aussehen des Startknopfes.
     */
    private void setButtonToStart() {
        startButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        startButton.setBackground(BUTTON_COLOR);
        startButton.setForeground(START_COLOR);
        startButton.setText("start");
    }
    
    /**
     * Definiert das Aussehen des Stopknopfes.
     */
    private void setButtonToStop() {
        startButton.setFont(new Font("SansSerif", Font.BOLD, 18));
        startButton.setBackground(BUTTON_COLOR);
        startButton.setForeground(STOP_COLOR);
        startButton.setText("stop");
        startTimer();
    }
}
