package fr.calculator.analyse;

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
public class MathAnalyseur {
	
	private final String expression;
	private int pos = 0;
	private boolean negatif = false;
	
	public MathAnalyseur(String expression) {
		this.expression = expression.replaceAll("\\) *\\(", "\\)\\*\\(");// ajoute un * entre deux parenthèses qui se
																			// suivent sans
																			// opérateur entre elles. Les espaces entre
																			// ) et ( sont ignorés
	}
	
	public List<Term> analyser() {
		List<Term> terms = new ArrayList<>();
		while (pos < expression.length()) {
			sauterEspaces();
			Term next = termeSuivant(terms);
			if (next != null)
				terms.add(next);
		}
		return terms;
	}
	
	private Term termeSuivant(List<Term> terms) {
		int nextOperatorIndex = indexOf('+', '-', '/', '*', '(', ')', '^');
		if (nextOperatorIndex == -1) {
			String numberString = expression.substring(pos).trim();
			pos = expression.length();
			return numberString.isEmpty() ? null : lireNombre(numberString, negatif);
		}
		
		String numberString = expression.substring(pos, nextOperatorIndex).trim();
		pos = nextOperatorIndex;
		
		char operator = expression.charAt(pos++);
		if (operator == '(') {
			Term term = null;
			if (!numberString.isEmpty()) {// Il y'a un facteur devant la
											// parenthèse
				if (negatif) {
					negatif = false;
					term = lireNombre(numberString, true);
				} else
					term = lireNombre(numberString, false);
			} else if (negatif) {// Il y'a un signe moins devant la parenthèse
				negatif = false;
				term = new NombreEntier(-1);
			}
			return term == null ? lireParentheses() : new Multiplication(term, lireParentheses());
		}
		
		if (numberString.isEmpty()) {
			Term previousTerm;
			int lastIndex;
			switch (operator) {
				case '-':
					negatif = !negatif;// negative=true si c'était false, et false si c'était true.
					return termeSuivant(terms);
				case '+':
					return termeSuivant(terms);
				case '*':
					lastIndex = terms.size() - 1;
					previousTerm = terms.remove(lastIndex);
					return new Multiplication(previousTerm, termeSuivant(terms));
				case '/':
					lastIndex = terms.size() - 1;
					previousTerm = terms.remove(lastIndex);
					return new Division(previousTerm, termeSuivant(terms));
				case '^':
					lastIndex = terms.size() - 1;
					previousTerm = terms.remove(lastIndex);
					return new Puissance(previousTerm, termeSuivant(terms));
			}
			return null;
		}
		
		Term term;
		if (negatif) {
			negatif = false;
			term = lireNombre(numberString, true);// nombre*(-1)
		} else
			term = lireNombre(numberString, false);
			
		switch (operator) {
			case '-':
				negatif = true;// On multipliera le prochain nombre par -1
			case '+':
				return term;
			case '*':
				return new Multiplication(term, termeSuivant(terms));
			case '/':
				Term next = termeSuivant(terms);
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
				return new Puissance(term, termeSuivant(terms));
			default:
				throw new RuntimeException("Invalid operator " + operator);
		}
	}
	
	private Parenthese lireParentheses() {
		List<Term> terms = new ArrayList<>();// Liste des termes dans la
												// parenthèse
		while (true) {
			sauterEspaces();
			
			if (pos >= expression.length())
				throw new RuntimeException("Missing closing parenthesis");
				
			char ch = expression.charAt(pos);
			if (ch == ')') {// Fin de la parenthèse actuelle
				pos++;
				return new Parenthese(terms);
			}
			
			Term term = termeSuivant(terms);
			if (term != null)
				terms.add(term);
		}
	}
	
	private Term lireNombre(String nombreStr, boolean negatif) {
		Term t = nombreStr.contains(".") ? decimalVersFraction(nombreStr) : new NombreEntier(Integer.parseInt(nombreStr));
		return negatif ? t.negatif() : t;
	}
	
	private Fraction decimalVersFraction(String decimalStr) {
		int numerator = Integer.parseInt(decimalStr.replace(".", ""));
		int denominator = (int) Math.pow(10, decimalStr.length() - decimalStr.indexOf('.') - 1);
		return new Fraction(numerator, denominator);
	}
	
	/**
	 * Avance la position jusqu'à trouver un caractère qui n'est pas un espace.
	 */
	private void sauterEspaces() {
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
