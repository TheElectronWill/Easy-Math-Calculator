package fr.calculator.resolution;

import fr.calculator.analyse.Division;
import fr.calculator.analyse.Fonction;
import fr.calculator.analyse.Multiplication;
import fr.calculator.analyse.Parenthese;
import fr.calculator.analyse.Puissance;
import fr.calculator.analyse.Rationnel;
import fr.calculator.analyse.Terme;
import fr.calculator.analyse.Variable;
import java.util.Arrays;
import java.util.List;

/**
 * Utilitaire pour simplifier les expressions mathématiques.
 *
 * @author Guillaume
 */
public class MathSimplifieur {

	private MathSimplifieur() {
	}

	public static List<Terme> simplifierTermes(Terme... termes) {
		return simplifierTermes(Arrays.asList(termes));
	}

	public static List<Terme> simplifierTermes(List<Terme> termes) {
		return simplifierExpression(termes).getTermes();
	}

	public static ExpressionSimple simplifierExpression(Terme... termes) {
		return simplifierExpression(Arrays.asList(termes));
	}

	public static ExpressionSimple simplifierExpression(List<Terme> termes) {
		ExpressionSimple expression = new ExpressionSimple();
		for (Terme t : termes) {
			t = t.simplifier();
			if (t instanceof Parenthese) {// parenthèse seule -> on développe
				for (Terme pt : ((Parenthese) t).termes) {
					ajouterTerme(expression, pt.simplifier());
				}
			} else {
				ajouterTerme(expression, t);
			}
		}
		return expression.simplifier();
	}

	private static void ajouterTerme(ExpressionSimple expression, Terme t) {
		if (t instanceof Rationnel) {
			expression.constante.ajouter(t);
		} else if (t instanceof Variable) {
			expression.facteurX.ajouter(1);
		} else if (t instanceof Fonction) {
			ajouterFonction(expression, t, 1);
		} else if (t instanceof Puissance) {
			ajouterPuissance(expression, t, 1);
		} else if (t instanceof Multiplication) {
			Multiplication m = (Multiplication) t;
//-- Multiplication avec une variable --
			if (m.a instanceof Variable) {
				expression.facteurX.ajouter(m.b);
			} else if (m.b instanceof Variable) {
				expression.facteurX.ajouter(m.a);
//-- Multiplication avec une puissance --
			} else if (m.a instanceof Puissance) {
				ajouterPuissance(expression, m.a, m.b);
			} else if (m.b instanceof Puissance) {
				ajouterPuissance(expression, m.b, m.a);
//-- Multiplication avec une fonction --
			} else if (m.a instanceof Fonction) {
				ajouterFonction(expression, m.a, m.b);
			} else if (m.b instanceof Fonction) {
				ajouterFonction(expression, m.b, m.a);
			} else {
				throw new RuntimeException("Impossible de simplifier la multiplication " + m);
			}
		} else if (t instanceof Division) {
			Division d = (Division) t;
//-- Division avec une variable --
			if (d.a instanceof Variable) {
				expression.facteurX.ajouter(d.b.inverser().simplifier());
//-- Division avec une puissance --
			} else if (d.a instanceof Puissance) {
				ajouterPuissance(expression, d.a, d.b.inverser().simplifier());
//-- Division avec une fonction --
			} else if (d.a instanceof Fonction) {
				ajouterFonction(expression, d.a, d.b.inverser().simplifier());
			} else {
				throw new RuntimeException("Impossible de simplifier la division " + d);
			}
		} else {
			throw new RuntimeException("Impossible de simplifier le terme " + t);
		}
	}

	private static void ajouterFonction(ExpressionSimple expression, Object objetFonction, Object facteur) {
		Fonction fonction = (Fonction) objetFonction;
		Terme param = expression.paramFonctions.get(fonction.nom);
		if (param == null) {//on n'avait pas encore rencontré cette fonction
			expression.paramFonctions.put(fonction.nom, fonction.param);
		} else//on a déjà rencontré la même fonction dans cette expression, il faut alors s'assurer que le même paramètre est donné à la fonction à chaque fois
		 if (!param.equals(fonction.param)) {//les paramètres ne sont pas identiques, donc on ne peut pas regrouper les deux occurrence de la fonction
				throw new RuntimeException("Impossible de gérer plusieurs occurrences de la même fonction si les paramètres ne sont pas à chaque fois identiques");
			}
		expression.facteurFonctions.get(fonction.nom).ajouter(facteur);
	}

	private static void ajouterPuissance(ExpressionSimple expression, Object objetPuissance, Object facteur) {
		Puissance puissance = (Puissance) objetPuissance;
		if (!(puissance.n instanceof Variable) || !(puissance.exposant instanceof Rationnel)) {
			throw new RuntimeException("Impossible de simplifier la puissance " + puissance);
		}
		Rationnel exposant = (Rationnel) puissance.exposant;
		if (exposant.num == 2 && exposant.denom == 1) {
			if (facteur instanceof Rationnel || facteur instanceof Number) {
				expression.facteurX2.ajouter(facteur);
			} else {
				throw new RuntimeException("Impossible de simplifier la multiplication de " + puissance + " par " + facteur);
			}
		} else {
			throw new RuntimeException("Impossible de simplifier la puissance " + puissance);
		}
	}

}
