package fr.calculator.resolution;

import fr.calculator.analyse.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestSimplifieur {

	public static void main(String[] args) throws InterruptedException {
		testerTermes();
		System.out.println("--------------------------------------------");
		testerExpression();
		System.out.println("--------------------------------------------");
		testerExpressionAvecFonctions();
	}

	private static void testerExpressionAvecFonctions() {
		List<Terme> termes = new ArrayList<>();
		termes.add(new Variable("x"));
		termes.add(new Fonction(Fonction.NomFonction.COSINUS, new Variable("x")));
		termes.add(new Fonction(Fonction.NomFonction.EXPONENTIELLE, new Parenthese(new Rationnel(3), new Rationnel(6, 2))));
		termes.add(new Division(new Variable("x"), new Rationnel(-10)));
		termes.add(new Puissance(new Variable("x"), new NombreEntier(2)));
		ExpressionSimple expression = MathSimplifieur.simplifierExpression(termes);
		System.out.println("expression simplifiée : " + expression);
	}

	/**
	 * Teste la fonction {@link MathSimplifieur#simplifierExpression(java.util.List)}.
	 */
	private static void testerExpression() {
		NombreEntier i = new NombreEntier(5);
		Multiplication m = new Multiplication(new Variable("x"), new Division(i, new Puissance(i, new NombreEntier(2))));

		List<Terme> termes = Arrays.asList(new NombreEntier(-20), new Parenthese(m),
				new Parenthese(new NombreEntier(2), new Rationnel(25, 10)));

		System.out.println("tel quel  : " + Arrays.deepToString(termes.toArray()));

		long t0 = System.nanoTime();
		ExpressionSimple expression = MathSimplifieur.simplifierExpression(termes);
		double time = (System.nanoTime() - t0) / (1000_000.0);

		System.out.println("expression simplifiée : " + expression);
		System.out.println("durée : " + time + " millisecondes");
	}

	/**
	 * Teste la fonction {@link MathSimplifieur#simplifierTermes(java.util.List)}.
	 */
	private static void testerTermes() {
		NombreEntier i = new NombreEntier(5);
		Multiplication m = new Multiplication(new Variable("x"), new Division(i, new Puissance(i, new NombreEntier(2))));

		List<Terme> termes = Arrays.asList(new NombreEntier(-20), new Parenthese(m),
				new Parenthese(new NombreEntier(2), new Rationnel(25, 10)));

		System.out.println("tel quel  : " + Arrays.deepToString(termes.toArray()));

		long t0 = System.nanoTime();
		List<Terme> termesSimplifiés = MathSimplifieur.simplifierTermes(termes);
		double time = (System.nanoTime() - t0) / (1000_000.0);

		System.out.println("termes simplifiés : " + Arrays.deepToString(termesSimplifiés.toArray()));
		System.out.println("durée : " + time + " millisecondes");
	}

}
