package fr.calculator.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * Parseur d'expressions mathématiques.
 * <h1>Représentation interne des données</h1> Une "expression mathématique" est définie comme une suite de termes qui
 * s'additionnent pour donner un résultat. Ces termes sont stockés sous la forme d'une liste, plus précisément
 * {@code List<Term>}. Par exemple, le calcul <code>"2 + 45 - 6"</code> donne la liste suivante:
 * <code>[+2,+45,-6]</code>
 *
 * @author Guillaume
 * 		
 */
public class MathParser {
	
	private final String expression;
	private int pos = 0;
	private boolean negative = false;
	
	public MathParser(String expression) {
		this.expression = expression.replaceAll("\\) *\\(", "\\)\\*\\(");// ajoute un * entre deux parenthèses qui se
																			// suivent sans
																			// opérateur entre elles. Les espaces entre
																			// ) et ( sont ignorés
	}
	
	public List<Term> parse() {
		List<Term> terms = new ArrayList<>();
		while (pos < expression.length()) {
			skipSpaces();
			Term next = nextTerm(terms);
			if (next != null)
				terms.add(next);
		}
		return terms;
	}
	
	private Term nextTerm(List<Term> terms) {
		int nextOperatorIndex = indexOf('+', '-', '/', '*', '(', ')', '^');
		if (nextOperatorIndex == -1) {
			String numberString = expression.substring(pos).trim();
			pos = expression.length();
			return numberString.isEmpty() ? null : parseNumber(numberString, negative);
		}
		
		String numberString = expression.substring(pos, nextOperatorIndex).trim();
		pos = nextOperatorIndex;
		
		char operator = expression.charAt(pos++);
		if (operator == '(') {
			Term term = null;
			if (!numberString.isEmpty()) {// Il y'a un facteur devant la
											// parenthèse
				if (negative) {
					negative = false;
					term = parseNumber(numberString, true);
				} else
					term = parseNumber(numberString, false);
			} else if (negative) {// Il y'a un signe moins devant la parenthèse
				negative = false;
				term = new IntegerTerm(-1);
			}
			return term == null ? readParenthesis() : new Multiplication(term, readParenthesis());
		}
		
		if (numberString.isEmpty()) {
			Term previousTerm;
			int lastIndex;
			switch (operator) {
				case '-':
					negative = !negative;// negative=true si c'était false, et false si c'était true.
					return nextTerm(terms);
				case '+':
					return nextTerm(terms);
				case '*':
					lastIndex = terms.size() - 1;
					previousTerm = terms.remove(lastIndex);
					return new Multiplication(previousTerm, nextTerm(terms));
				case '/':
					lastIndex = terms.size() - 1;
					previousTerm = terms.remove(lastIndex);
					return new Division(previousTerm, nextTerm(terms));
				case '^':
					lastIndex = terms.size() - 1;
					previousTerm = terms.remove(lastIndex);
					return new Power(previousTerm, nextTerm(terms));
			}
			return null;
		}
		
		Term term;
		if (negative) {
			negative = false;
			term = parseNumber(numberString, true);// nombre*(-1)
		} else
			term = parseNumber(numberString, false);
			
		switch (operator) {
			case '-':
				negative = true;// On multipliera le prochain nombre par -1
			case '+':
				return term;
			case '*':
				return new Multiplication(term, nextTerm(terms));
			case '/':
				Term next = nextTerm(terms);
				if (next instanceof Multiplication) {// pour respecter l'ordre afin que 1/2*5 = (1/2)*5 et pas 1/(2*5)
					Multiplication m = (Multiplication) next;
					return new Multiplication(new Division(term, m.a), m.b);
				}
				return new Division(term, next);
			case ')':
				pos--;// Pour que la parenthèse fermante soit détectée dans la
						// méthode readParenthesis()
				return term;
			case '^':
				return new Power(term, nextTerm(terms));
			default:
				throw new RuntimeException("Invalid operator " + operator);
		}
	}
	
	private Parenthesis readParenthesis() {
		List<Term> terms = new ArrayList<>();// Liste des termes dans la
												// parenthèse
		while (true) {
			skipSpaces();
			
			if (pos >= expression.length())
				throw new RuntimeException("Missing closing parenthesis");
				
			char ch = expression.charAt(pos);
			if (ch == ')') {// Fin de la parenthèse actuelle
				pos++;
				return new Parenthesis(terms);
			}
			
			Term term = nextTerm(terms);
			if (term != null)
				terms.add(term);
		}
	}
	
	private Term parseNumber(String numberString, boolean negate) {
		if (negate) {
			return numberString.contains(".") ? new DecimalTerm(-Double.parseDouble(numberString))
					: new IntegerTerm(-Integer.parseInt(numberString));
		}
		return numberString.contains(".") ? new DecimalTerm(Double.parseDouble(numberString))
				: new IntegerTerm(Integer.parseInt(numberString));
	}
	
	/**
	 * Avance la position jusqu'à trouver un caractère qui n'est pas un espace.
	 */
	private void skipSpaces() {
		while (pos < expression.length() && (expression.charAt(pos) == ' ')) {
			pos++;
		}
	}
	
	/**
	 * Donne la position de l'un des caractères donnés. La recherche commence à la position <code>pos</code>.
	 *
	 * @return la position de l'un des caractères donnés, ou <code>-</code> si aucun de ces caractères n'existe entre la
	 *         position <code>pos</code> et la fin de l'expression à évaluer.
	 */
	private int indexOf(char... cs) {
		for (int i = pos; i < expression.length(); i++) {
			for (char c : cs) {
				if (expression.charAt(i) == c)
					return i;
			}
		}
		return -1;
	}
	
}
