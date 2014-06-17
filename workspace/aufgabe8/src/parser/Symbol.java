package parser;
import java.util.EnumSet;
import java.util.Set;

/**
 * Die Konstanten ADD ... benennen die unterschiedlichen Eingabesymbole.
 * Die Mengen ADD_OPS ... stehen fuer verwandte Symbole.
 */
enum Symbol {ADD, SUB, MUL, DIV, LP, RP, NUMBER, LET, ASSIGN, ID, EOL, EOF;

    static final Set<Symbol> ADD_OPS = EnumSet.of(ADD, SUB);
    static final Set<Symbol> MUL_OPS = EnumSet.of(MUL, DIV);
    static final Set<Symbol> UNARY_OPS = EnumSet.of(ADD, SUB);
}