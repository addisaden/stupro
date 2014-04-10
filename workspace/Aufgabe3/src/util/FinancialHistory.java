package util;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The class <code>FinancialHistory</code>creates objects that reflect the
 * history of incomes from different sources and expenditures for different
 * reasons. The totals for money on hand and for each source of income and each
 * reason of expenditure are available.
 * <p>
 * This class is taken from<br>
 * Adele Goldberg, David Robson: <i>Smalltalk-80 The Language and its
 * implementation</i><br>
 * Addison-Wesley, 1983
 */
public final class FinancialHistory {
    /**
     * The total amount of money currently at hand.
     */
    private double cashOnHand = 0.0;
    /**
     * The mapping of income-source to amounts of money.
     */
    private final IMap<String, Double> incomes = new LinearMap<String, Double>();
    /**
     * The mapping of expenditure-reasons to amounts of money.
     */
    private final IMap<String, Double> expenditures = new LinearMap<String, Double>();

    // instance creation

    /**
     * Begin a financial history with <code>amount</code> as the amount of money
     * at hand. Only positive amounts are acceptable.
     * 
     * @param amount
     *            initial amount of money at hand
     * @return new financial history
     * @throws IllegalArgumentException
     *             illegal <code>amount</code>
     */
    public static FinancialHistory initialBalance(double amount) {
        require(amount >= 0, "Amount " + amount + " is not positive");
        FinancialHistory f = new FinancialHistory(amount);
        return f;
    }

    /**
     * Begin a financial history with 0 as the amount of money at hand.
     */
    public FinancialHistory() {
    }
    
    FinancialHistory(double amount) {
    	cashOnHand = amount;
    }

    // transaction recording

    /**
     * Remember that an amount of money, <code>amount</code>, has been received
     * from <code>source</code>. <code>amount</code> must not be negative.
     * 
     * @param source
     *            where the income comes from
     * @param amount
     *            amount of income
     * @throws IllegalArgumentException
     *             illegal <code>amount</code>
     */
    public void receiveFrom(String source, double amount) {
        // TODO: Hinweise lesen beachten!
        // source darf nicht "" sein. Meldung: "There has to be a source!"
        // amount muss groesser oder gleich 0 sein. 
        //        Meldung: "Amount 'amount' is not positive".
        //        ('amount' soll durch den uebergebenen Wert ersetzt werden)
        // require verwenden!!
        //
        // in incomes soll unter dem Key 'source' amount zu dem bereits
        //        dort gespeicherten Wert addiert werden.
        //        (totalReceivedFrom)
        // der vorhandene Geldbetrag 'cashOnHand' soll erhoeht werden.
    	
    	if(source == "") {
    		throw new IllegalArgumentException("There has to be a source!");
    	}
    	
    	if(amount < 0) {
    		throw new IllegalArgumentException("Amount 'amount' is not positive");
    	}
    	
    	incomes.put(source, totalReceivedFrom(source) + amount);
    	cashOnHand += amount;
    }

    /**
     * Remember that an amount of money, <code>amount</code>, has been spent for
     * <code>reason</code>.
     * <code>amount</cash> must not be negative and debts are not allowed.
     * 
     * @param reason
     *            reason for the expenditure
     * @param amount
     *            money spent
     * @throws IllegalArgumentException
     *             for illegal expenditures
     */
    public void spendFor(String reason, double amount) {
        require(!"".equals(reason), "There has to be a reason!");
        require(amount >= 0, "Amount " + amount + " is not positive");
        require(amount <= cashOnHand, "There is not enough money");
        expenditures.put(reason, this.totalSpentFor(reason) + amount);
        cashOnHand -= amount;
    }

    // inquiries

    /**
     * Answer the total amount of money currently on hand.
     * 
     * @return current amount of money on hand
     */
    public double cashOnHand() {
        return cashOnHand;
    }

    /**
     * Answer the total amount received from <code>source</code> so far. If
     * <code>source</code> is the empty string the result is the amount received
     * from any source.
     * 
     * @param source
     *            source of income
     * @return total amount received from <code>source</code>
     */
    public double totalReceivedFrom(String source) {
        if ("".equals(source))
            return totalReceived();
        return orElse(incomes.get(source), 0.0);
    }

    /**
     * Answer the total income.
     * 
     * @return total income
     */
    public double totalReceived() {
        return totalOf(incomes.values());
    }

    /**
     * Answer the total amount spent for <code>reason</code> so far. If
     * <code>reason</code> is the empty string the result is the amount spent
     * for any reason.
     * 
     * @param reason
     *            reason for expenditure
     * @return total amount spent for <code>reason</code>
     */
    public double totalSpentFor(String reason) {
        if ("".equals(reason))
            return totalSpent();
        return orElse(expenditures.get(reason), 0.0);
    }

    /**
     * Answer the total amount spent spent so far.
     * 
     * @return total amount spent so far.
     */
    public double totalSpent() {
        return totalOf(expenditures.values());
    }

    // private

    /**
     * Compute the sum of all Double numbers in a collection.
     * 
     * @param values
     *            the collections holding the values
     * @return sum of all values
     */
    private static double totalOf(Collection<Double> values) {
        // Summe alle Zahlen in values berechnen.
        // Hinweis: foreach-Schleife benutzen
    	double result = 0.0;
    	
    	for(Iterator<Double> it = values.iterator(); it.hasNext();) {
    		result += it.next();
    	}
    	
        return result;
    }

    /**
     * If {@code value} is {@code null} return {@code defaultValue} instead.
     * @param value normal Value
     * @param defaultValue the default
     * @return {@code value} or {@code default} if {@code null}
     */
    private static <T> T orElse(T value, T defaultValue) {
        return value != null ? value : defaultValue;
    }

    /**
     * Test whether a necessary precondition is true.
     * 
     * @param condition
     *            condition to be true
     * @param reason
     *            human understandable explanation of the precondition
     * @throws IllegalArgumentException
     *             if condition does not hold
     */
    private static void require(boolean condition, String reason) {
        if (! condition)
            throw new IllegalArgumentException(reason);
    }
}
