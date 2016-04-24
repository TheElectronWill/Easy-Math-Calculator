package fr.calculator.analyse;

import fr.calculator.analyse.Fonction.NomFonction;
import java.util.ArrayList;
import java.util.List;

/**
 * Analyseur d'expressions mathématiques.
 * <h1>Représentation interne des données</h1> Une "expression mathématique" est définie comme une suite de
 * termes qui s'additionnent pour donner un résultat. Ces termes sont stockés sous la forme d'une liste, plus
 * précisément une {@code List<Terme>}. Par exemple, le calcul <code>"2 + 45 - 6"</code> donne la liste
 * suivante: <code>[+2,+45,-6]</code>
 *
 * @author Guillaume
 */
public class MathAnalyseur {

	private final String expression;
	private int pos = 0;
	private boolean negatif = false;

	public MathAnalyseur(String expression) {
		this.expression = expression.replaceAll("\\) *\\(", "\\)\\*\\(");// ajoute un * entre deux parenthèses qui se suivent sans opérateur entre elles. Les espaces entre ) et ( sont ignorés
	}

	public List<Terme> analyser() {
		List<Terme> terms = new ArrayList<>();
		while (pos < expression.length()) {
			sauterEspaces();
			Terme next = termeSuivant(terms);
			if (next != null) {
				terms.add(next);
			}
		}
		return terms;
	}

	private Terme termeSuivant(List<Terme> terms) {
		int prochainIndex = prochainIndexOuLettre('+', '-', '/', '*', '(', ')', '^');
		if (prochainIndex == -1) {
			String nombreStr = expression.substring(pos).trim();
			pos = expression.length();
			return nombreStr.isEmpty() ? null : lireNombre(nombreStr, negatif);
		}

		String nombreStr = expression.substring(pos, prochainIndex).trim();
		pos = prochainIndex;

		char operateur = expression.charAt(pos++);
		if (operateur == '(') {// on s'est arrêté à une parenthèse
			Terme term = null;
			if (!nombreStr.isEmpty()) {// Il y'a un facteur devant la parenthèse
				if (negatif) {
					negatif = false;
					term = lireNombre(nombreStr, true);
				} else {
					term = lireNombre(nombreStr, false);
				}
			} else if (negatif) {// Il y'a un signe moins devant la parenthèse
				negatif = false;
				term = new Rationnel(-1);
			}
			return term == null ? lireParentheses() : new Multiplication(term, lireParentheses());
		}

		if (nombreStr.isEmpty()) {// on n'a lu que l'opérateur
			Terme termePrecedent;
			int dernierIndex;
			switch (operateur) {
				case '-':
					negatif = !negatif;// negative=true si c'était false, et false si c'était true.
					return termeSuivant(terms);
				case '+':
					return termeSuivant(terms);
				case '*':
					dernierIndex = terms.size() - 1;
					termePrecedent = terms.remove(dernierIndex);
					Terme next = termeSuivant(terms);
					if (next instanceof Multiplication) {// pour respecter l'ordre afin que 1*2*5 = (1*2)*5 et pas 1*(2*5)
						Multiplication m = (Multiplication) next;
						return new Multiplication(new Multiplication(termePrecedent, m.a), m.b);
					} else if (next instanceof Division) {// pour que 1/2*3 = (1/2)*3 et pas 1/(2*3)
						Division d = (Division) next;
						return new Multiplication(new Division(termePrecedent, d.a), d.b);
					}
					return new Multiplication(termePrecedent, next);
				case '/':
					dernierIndex = terms.size() - 1;
					termePrecedent = terms.remove(dernierIndex);
					next = termeSuivant(terms);
					if (next instanceof Multiplication) {// pour respecter l'ordre afin que 1/2*5 = (1/2)*5 et pas 1/(2*5)
						Multiplication m = (Multiplication) next;
						return new Multiplication(new Division(termePrecedent, m.a), m.b);
					} else if (next instanceof Division) {// pour que 1/2/3 = (1/2)/3 et pas 1/(2/3)
						Division d = (Division) next;
						return new Division(new Division(termePrecedent, d.a), d.b);
					}
					return new Division(termePrecedent, next);
				case '^':
					dernierIndex = terms.size() - 1;
					termePrecedent = terms.remove(dernierIndex);
					if (termePrecedent instanceof Multiplication) {//pour respecter l'ordre afin que 3*x^2 = 3*(x^2) et pas (3*x)^2
						Multiplication m = (Multiplication) termePrecedent;
						return new Multiplication(m.a, new Puissance(m.b, termeSuivant(terms)));
					} else if (termePrecedent instanceof Division) {//pour que 3/x^2 = 3/(x^2) et pas (3/x)^2
						Division m = (Division) termePrecedent;
						return new Division(m.a, new Puissance(m.b, termeSuivant(terms)));
					}
					return new Puissance(termePrecedent, termeSuivant(terms));
				case 'x':
					if (negatif) {
						negatif = false;
						return new Multiplication(new Rationnel(-1), new Variable("x"));
					}
					return new Variable("x");
				default:
					if (estUneLettre(operateur)) {
						int indexParenthese = prochainIndex('+', '-', '/', '*', '(', ')', '^');
						String nomFonction = expression.substring(pos - 1, indexParenthese);
						pos = indexParenthese;
						if (expression.charAt(pos++) != '(') {
							throw new RuntimeException("Fonction invalide: veuillez mettre le paramètre entre parenthèses");
						}
						if (negatif) {
							negatif = false;
							return new Multiplication(new Rationnel(-1), new Fonction(NomFonction.get(nomFonction), lireParentheses()));
						}
						return new Fonction(NomFonction.get(nomFonction), lireParentheses());
					}
			}
			return null;
		}

		Terme terme;
		if (negatif) {
			negatif = false;
			terme = lireNombre(nombreStr, true);// nombre*(-1)
		} else {
			terme = lireNombre(nombreStr, false);
		}

		switch (operateur) {
			case '-':
				negatif = true;// On multipliera le prochain nombre par -1
			case '+':
				return terme;
			case '*':
				Terme next = termeSuivant(terms);
				if (next instanceof Multiplication) {// pour respecter l'ordre afin que 1*2*5 = (1*2)*5 et pas 1*(2*5)
					Multiplication m = (Multiplication) next;
					return new Multiplication(new Multiplication(terme, m.a), m.b);
				} else if (next instanceof Division) {// pour que 1/2*3 = (1/2)*3 et pas 1/(2*3)
					Division d = (Division) next;
					return new Multiplication(new Division(terme, d.a), d.b);
				}
				return new Multiplication(terme, next);
			case '/':
				next = termeSuivant(terms);
				if (next instanceof Multiplication) {// pour respecter l'ordre afin que 1/2*5 = (1/2)*5 et pas 1/(2*5)
					Multiplication m = (Multiplication) next;
					return new Multiplication(new Division(terme, m.a), m.b);
				} else if (next instanceof Division) {// pour que 1/2/3 = (1/2)/3 et pas 1/(2/3)
					Division d = (Division) next;
					return new Division(new Division(terme, d.a), d.b);
				}
				return new Division(terme, next);
			case ')':
				pos--;// Pour que la parenthèse fermante soit détectée dans la méthode readParenthesis()
				return terme;
			case '^':
				return new Puissance(terme, termeSuivant(terms));
			case 'x':
				return new Multiplication(terme, new Variable("x"));
			default:
				if (estUneLettre(operateur)) {
					int indexParenthese = prochainIndex('+', '-', '/', '*', '(', ')', '^');
					String nomFonction = expression.substring(pos - 1, indexParenthese);
					pos = indexParenthese;
					if (expression.charAt(pos++) != '(') {
						throw new RuntimeException("Fonction invalide: veuillez mettre le paramètre entre parenthèses");
					}
					if (negatif) {
						negatif = false;
						terme = terme.negatif();
					}
					Fonction fonction = new Fonction(NomFonction.get(nomFonction), lireParentheses());
					return new Multiplication(terme, fonction);
				}
				throw new RuntimeException("Opérateur invalide: " + operateur);
		}
	}

	private Parenthese lireParentheses() {
		List<Terme> terms = new ArrayList<>();// Liste des termes dans la
		// parenthèse
		while (true) {
			sauterEspaces();

			if (pos >= expression.length()) {
				throw new RuntimeException("Missing closing parenthesis");
			}

			char ch = expression.charAt(pos);
			if (ch == ')') {// Fin de la parenthèse actuelle
				pos++;
				return new Parenthese(terms);
			}

			Terme term = termeSuivant(terms);
			if (term != null) {
				terms.add(term);
			}
		}
	}

	private Terme lireNombre(String nombreStr, boolean negatif) {
		Terme t = nombreStr.contains(".") ? new Rationnel(nombreStr) : new Rationnel(Integer.parseInt(nombreStr));
		return negatif ? t.negatif() : t;
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
	 * Donne la position de l'un des caractères donnés, ou de la prochaine lettre (a-z, A-Z). La recherche
	 * commence à la position <code>pos</code>.
	 *
	 * @return la position de l'un des caractères donnés, ou de la prochaine lettre, ou <code>-</code> si
	 * aucun de ces caractères n'existe entre la position <code>pos</code> et la fin de l'expression
	 * à évaluer.
	 */
	private int prochainIndexOuLettre(char... cs) {
		for (int i = pos; i < expression.length(); i++) {
			char ch = expression.charAt(i);
			if (estUneLettre(ch)) {
				return i;
			}
			for (char c : cs) {
				if (ch == c) {
					return i;
				}
			}
		}
		return -1;
	}

	private boolean estUneLettre(char c) {
		return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z');
	}

	/**
	 * Donne la position de l'un des caractères donnés. La recherche commence à la position <code>pos</code>.
	 *
	 * @return la position de l'un des caractères donnés, ou <code>-</code> si aucun de ces caractères
	 * n'existe entre la position <code>pos</code> et la fin de l'expression à évaluer.
	 */
	private int prochainIndex(char... cs) {
		for (int i = pos; i < expression.length(); i++) {
			for (char c : cs) {
				if (expression.charAt(i) == c) {
					return i;
				}
			}
		}
		return -1;
	}

}
