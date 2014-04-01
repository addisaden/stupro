package anwendung;

import bank.Bank;

/**
 * Fuehrt beispielhaft ein paar Kontobewegungen aus
 */
public class Main {
	public static void ausgabe(Bank b, int k) {
		System.out.println("Vom Konto \"" + k + "\" der Bank \"" + b.toString() +
				"\" liegt das Saldo bei " + b.kontostand(k));
	}
	
    public static void main(String[] args) {
            /* b = neue Bank (den Namen koennen Sie selbst aussuchen)
             *
             * Erzeugen Sie ein Konto k1 und ein Konto k2 bei der Bank b
             * Zahlen Sie in k1 100 Euro ein und dann 20 Euro wieder aus.
             * Geben Sie den Kontostand von k1 und von k2 aus.
             * Zahlen Sie 70 Euro nach k2 ein.
             * Ueberweisen Sie 20 Euro von k1 nach k2.
             * Geben Sie die Kontobewegungen von Konto k1 und von k2 aus.
             */
    	
    	Bank b = new Bank("Addis Bank");
    	int k1 = b.neuesKonto();
    	int k2 = b.neuesKonto();
    	
    	b.barEinzahlen(k1, 100);
    	b.barAuszahlen(k1, 20);
    	
    	ausgabe(b, k1);
    	ausgabe(b, k2);
    	
    	b.barEinzahlen(k2, 70);
    	
    	b.ueberweisen(k1, b, k2, 20);
    	
    	b.kontobewegungenAusgeben(k1);
    	b.kontobewegungenAusgeben(k2);
    }
}
