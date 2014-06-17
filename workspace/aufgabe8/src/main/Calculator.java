package main;

import java.util.Iterator;
import java.util.Scanner;

import parser.Parser;
import tree.Node;
import util.LinkedObjects;
import vm.VM;

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
                LinkedObjects program = compile(input);
                System.out.println("\nDer compilierte Code lautet: \n");
                
                Iterator<Object> pc = program.iterator();
                while (pc.hasNext()) {
                	System.out.println(pc.next());
                }
                
                System.out.println();
                System.out.println("Das Ergebnis lautet: " +
                        VM.compute(program));
            } catch (IllegalArgumentException e) {
                if (! input.equals("")) System.out.println("Syntaxfehler");
            } 
        }
    }

    private static String readLine() {
        System.out.print("\nEingabe (q = Ende)? ");
        String input = in.nextLine().trim();
        return input;
    }
    
    private static LinkedObjects compile(String input) {
        LinkedObjects program = new LinkedObjects();
        Node ast = Parser.parse(input, new TreeBuilder());
        ast.compileTo(program);
        return program;        
    }
}
