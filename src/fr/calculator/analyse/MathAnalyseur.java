package fr.calculator.analyse;

import fr.calculator.MathException;
import fr.calculator.analyse.Fonction.NomFonction;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * Analyseur d'expressions mathématiques.
 * <h1>Représentation interne des données</h1> Une "expression mathématique" est définie comme une suite de
 * termes qui
 * s'additionnent pour donner un résultat. Ces termes sont stockés sous la forme d'une liste, plus précisément
 * une
 * {@code List<Terme>}. Par exemple, le calcul <code>"2 + 45 - 6"</code> donne la liste suivante:
 * <code>[+2,+45,-6]</code>
 *
 * @author Guillaume
 */
public class MathAnalyseur {

	/**
	 * Une expression régulière pour détecter les opérateurs méthématiques. Accepte tout caractère visible et
	 * ignore les espaces qui le précède. Les espaces ne sont pas inclus dans le résultat grâce au look-behind
	 * {@code "?<="}.
	 * <p>
	 * Accepter tous les caractères permet de détecter les erreurs dans l'expression. Par exemple, avec
	 * l'expression régulière {@code (?<=\\s*)[-+)/*\^]}, {@code 2 2/2} est vu comme
	 * {@code terme=2, op=/, terme=2}, soit {@code 2/2}. Alors que PATTERN_OPERATEUR détecte le second "2"
	 * comme un opérateur, ce qui génère un erreur.
	 * </p>
	 */
	private static final Pattern PATTERN_OPERATEUR = Pattern.compile("(?<=\\s*)[-+)/*\\^]");//Pattern.compile("(?<=\\s*)\\S");

	private static final String DECIMAL = "[-+]?[0-9]+(\\.[0-9]+)?";//comme le "-" est juste après le crochet, il ne définit pas un intervalle
	//Dans une classe de caractères (cad "[caractères]"), les metacaractères comme + ou * se comportent comme des caractères normaux

	private static final String PARENTHESE = "\\(.*?\\)";
	private static final String FONCTION = "[a-z]+" + PARENTHESE;
	private static final Pattern PATTERN_TERME = Pattern.compile("(?<=\\s*)(" + DECIMAL + "|" + PARENTHESE + "|x|pi|" + FONCTION + ")");
	private static final Pattern PATTERN_MULTIPLICATION_IMPLICITE = Pattern.compile("(?<=(\\d|\\)|x))\\s*(?=([a-z]|\\())");

	private final String expression;
	private final Scanner sc;

	public MathAnalyseur(String expression) {
		this(expression, true);
	}

	private MathAnalyseur(String expression, boolean rendreExplicite) {
		if (rendreExplicite) {
			expression = PATTERN_MULTIPLICATION_IMPLICITE.matcher(expression).replaceAll("*");
		}
		this.expression = expression;
		this.sc = new Scanner(expression);
	}

	/**
	 * Analyse l'expression mathématique donnée dans le constructeur, et renvoie une liste de termes.
	 *
	 * @return une liste de termes correspondant à l'expression
	 */
	public List<Terme> analyser() {
		List<Terme> liste = new ArrayList<>();
		Terme t1 = termeSuivant();
		while (true) {
			String operateur = sc.findWithinHorizon(PATTERN_OPERATEUR, expression.length());
			if (operateur == null) {
				liste.add(t1);
				return liste;
			}
			switch (operateur) {
				case "+":
					liste.add(t1);
					t1 = termeSuivant();
					break;
				case "-":
					liste.add(t1);
					t1 = termeSuivant();
					t1 = new Multiplication(new Rationnel(-1), t1);
					break;
				case "*":
					Terme t2 = termeSuivant();
					t1 = new Multiplication(t1, t2);
					break;
				case "/":
					t2 = termeSuivant();
					t1 = new Division(t1, t2);
					break;
				case "^":
					t2 = termeSuivant();
					if (t1 instanceof Multiplication) {
						Multiplication m = (Multiplication) t1;
						t1 = new Multiplication(m.a, new Puissance(m.b, t2));
					} else if (t1 instanceof Division) {
						Division m = (Division) t1;
						t1 = new Division(m.a, new Puissance(m.b, t2));
					} else {
						t1 = new Puissance(t1, t2);
					}
					break;
				default:
					throw new MathException("Expression erronée : opérateur \"" + operateur + "\" invalide.");
			}
		}
	}

	private Terme termeSuivant() {
		String termeStr = sc.findWithinHorizon(PATTERN_TERME, expression.length());
		if (termeStr == null) {
			throw new MathException("Expression erronée : il manque au moins un terme");
		}
		if (termeStr.startsWith("(")) {//parenthèse
			List<Terme> contenuParenthese = new MathAnalyseur(termeStr.substring(0, termeStr.length() - 1), false).analyser();
			return new Parenthese(contenuParenthese);
		} else if (termeStr.contains("(")) {//fonction
			String[] parties = termeStr.split("\\(");
			String nomFonction = parties[0], contenuFonction = parties[1].substring(0, parties[1].length() - 1);
			Parenthese paramFonction = new Parenthese(new MathAnalyseur(contenuFonction, false).analyser());
			return new Fonction(NomFonction.get(nomFonction), paramFonction);
		} else if (termeStr.equals("x")) {//x
			return new Variable(termeStr);
		} else if (termeStr.equals("pi")) {//pi
			return new Rationnel("3.14159265");//approximation 314159265/100000000
		} else {//nombre
			return new Rationnel(termeStr);
		}
	}

}
