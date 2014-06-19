package main;

import java.util.NoSuchElementException;
import java.util.Scanner;

import parser.Parser;
import tree.Node;

/**
 * Einfacher Rechner fuer Ausdruecke mit Gleitkommazahlen. Die Anwendung stellt
 * eine read-eval-print Schleife dar. Sie liest eine Eingabezeite (angefordert
 * bei Fragezeichen), fuehrt eine Syntaxanalyse durch, berechnet ihren Wert und
 * gibt das Ergebnis auf dem Bildschirm aus.
 * <p>
 * Die Anwendung kann durch Eingabe von 'q' beendet werden. Leerzeichen zwischen
 * Zahlen und Operatoren werden ignoriert.
 */
final class Calculator {
    private static final Scanner in = new Scanner(System.in);

    private Calculator() {} // Wir wollen keine Objekte

    public static void main(String[] args) {
        System.out.println("Taschenrechner\n");
        while (true) {
            String input = readLine();
            try {
                Node ast = Parser.parse(input, new TreeBuilder());
                System.out.println("\n" + ast.toString() + "\n: " + ast.value());
            } catch (IllegalArgumentException e) {
                if (!input.equals(""))
                    System.out.println("Syntaxfehler");
            } catch (NoSuchElementException e) {
                if (!input.equals(""))
                    System.out.println("Nicht gefunden: " + e.getMessage());
            }
        }
    }

    private static String readLine() {
        System.out.print("\nEingabe (q = Ende)? ");
        String input = in.nextLine().trim();
        return input;
    }
}
