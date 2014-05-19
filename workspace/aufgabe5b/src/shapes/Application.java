package shapes;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import util.Command;
import util.Console;
import util.Menu;

/**
 * Steuert die Anwendung
 * 
 * @author E. Ehses, FH Koeln
 */
public final class Application {
    /**
     * Erzeugt das Menue fuer die Befehlsauswahl. Dem Menue werden
     * (anonyme) Objekte mitgeteilt, die eine Callback-Funktion fuer
     * die Befehlsausfuehrung beeinhalten.
     * <p>
     * Mit den Befehlen kann man eine Liste von Shape-Objekten erzeugen
     * und ihre Eigenschaften abfragen.
     */
    public static void main(String[] argv) {
        final List<IShape> liste = new ArrayList<IShape>();
        Menu menu = new Menu("Auswahl der Aktion");
        menu.add("Rechteck erzeugen", 'r', new Command() {
            public void run() {
                String name = Console.readLine("Name:");
                double laenge = Console.readDouble("Laenge:");
                double breite = Console.readDouble("Breite:");
                liste.add(new Rectangle(name, laenge, breite));           
            }
        });
        menu.add("Kreis erzeugen", 'k', new Command() {
            public void run() {
                String name = Console.readLine("Name:");
                double radius = Console.readDouble("Radius:");
                liste.add(new Circle(name, radius));
            }
        });
        menu.add("nach Flaeche sortiert ausgeben", 'f', new Command() {
            public void run() {
                Collections.sort(liste);
                printList("nach Flaeche sortiert: ", liste);
            }
        });
        menu.add("nach Name sortiert ausgeben", 'n', new Command() {
            public void run() {
                Collections.sort(liste, new ShapesAlphabetisch());
                printList("nach Name sortiert: ", liste);
            }
        });
        menu.add("Gesamtflaeche ausgeben", 'g', new Command() {
            public void run() {
                double summe = 0.0;
                for (IShape f : liste) summe += f.getArea();  
                System.out.printf("Gesamte Flaeche: %.2f%n", summe);
            }
        });
        menu.add("Ende", 'e', null);
        menu.runloop();             
    }

    /**
     * Gibt alle Daten eines IShape-Feldes aus.
     * 
     * @param title Ueberschrift ueber die Ausgabe.
     */
    static void printList(String title, List<IShape> liste) {
        System.out.println("\n" + title);
        for (IShape s : liste)
            System.out.printf("    %s, Flaeche: %.2f%n", s, s.getArea());
    }
}
