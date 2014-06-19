package parser;
import java.util.HashMap;
import java.util.Set;


/**
 * Simple scanner used for parsing numeric expressions.
 */
class Scanner {
    /**
     * input line.
     */
    private String input;
    /**
     * current position.
     */
    private int pos = 0;
    /**
     * current character.
     */
    private char currentChar = ' ';
    /**
     * value attribute of current symbol.
     */
    private String currentValue;
    /**
     * token type of current symbol
     */
    private Symbol currentSymbol;
    
    /**
     * Mapping of characters to symbol constants.
     */
    private static final HashMap<Character, Symbol> symbols =
        new HashMap<Character, Symbol>();
    static {
        symbols.put('+', Symbol.ADD);
        symbols.put('-', Symbol.SUB);
        symbols.put('*', Symbol.MUL);
        symbols.put('/', Symbol.DIV);
        symbols.put('(', Symbol.LP);
        symbols.put(')', Symbol.RP);
        symbols.put('=', Symbol.ASSIGN);
        symbols.put(';', Symbol.EOL);
    }
    /**
     * Reads next character from input line a <tt>currentChar</tt>.
     */
    private void readNext() {
        currentChar = (pos == input.length()) ? ';' : input.charAt(pos++);
    }
    
    /**
     * Determins next symbol.
     * White space will be skipped. The symbol's identity is stored in
     * the variable <tt>currrentSymbol</tt> and the attribute value of
     * numbers is stored in <tt>currentValue</tt>. For non numeric tokens
     * <tt>currentValue</tt> is meaningless and undefined.
     * 
     * @throws IllegalArgumentException if unexpected characters are found.
     */
    private void nextToken() {
        while (Character.isWhitespace(currentChar))
            readNext();
        if (Character.isDigit(currentChar)) {
            StringBuilder b = new StringBuilder();
            scanInt(b);
            if (currentChar == '.') {
                consumeChar(b);
                scanInt(b);        
            }
            if (currentChar == 'e' || currentChar == 'E') {
                consumeChar(b);
                if (currentChar == '+' || currentChar == '-')
                    consumeChar(b);
                scanInt(b);
            }
            currentSymbol = Symbol.NUMBER;
            currentValue = b.toString();
        } else if (Character.isLetter(currentChar)) {
            StringBuilder b = new StringBuilder();
            while (Character.isLetterOrDigit(currentChar)) {
                consumeChar(b);
            }
            String id = b.toString();
            if (id.equals("let")) 
                currentSymbol = Symbol.LET;
            else if (id.equals("q"))
                currentSymbol = Symbol.EOF;
            else {
                currentSymbol = Symbol.ID;
                currentValue = id;
            }
        } else {
            currentSymbol = symbols.get(currentChar);
            if (currentSymbol == null)
                throw new IllegalArgumentException();
            readNext();
        }
    }

    /**
     * Reads an integer number
     * @param b receives the number
     */
    private void scanInt(StringBuilder b) {
        while (Character.isDigit(currentChar)) consumeChar(b);
    }
    /**
     * Appends the current character to the StringBuilder and moves on to the
     * next character.
     * @param b receives the current character
     */
    private void consumeChar(StringBuilder b) {
        b.append(currentChar);
        readNext();
    }
    
    
    /**
     * Creates a new scanner.
     * 
     * @param input input string.
     */
    Scanner(String input) {
        this.input = input;
        nextToken();
    }
    
    /**
     * Returns current lookahead token.
     * 
     * @return current symbol
     */
    Symbol lookahead() {
        return currentSymbol;
    }
    
    /**
     * Tries to match the lookahead symbol with the expected symbol.
     * If successful, lookahead proceeds to the next input symbol.
     * If not successful an exception is thrown.
     * <p>
     * If the current token is a NUMBER, its numeric value is returned.
     * Otherwise the return-value is undefined and should be ignored.
     * 
     * @param expected expected token value is returned.
     * 
     * @return numeric value of token.
     * @throws IllegalArgumentException if symbols don't match 
     */
    String match(Symbol expected) {
        if (expected != currentSymbol)
            throw new IllegalArgumentException("syntax error");
        String result = currentValue;
        nextToken();
        return result;
    }
    
    /**
     * Returns lookahead token and proceeds to next token if appropriate.
     * If the current token is contained in the set of expected token,
     * it is returned and replaced by the next token. A subsequent match is
     * no longer necessary or appropriate.
     * If the current token doesn't belong to the set of expected tokens it
     * remains unchanged.
     * 
     * @param expected set of tokens that is expected in the current context
     * @return current token
     */
    Symbol nextOf(Set<Symbol> expected) {
        Symbol result = currentSymbol;
        if (expected.contains(result)) nextToken();
        return result;
    }
}
