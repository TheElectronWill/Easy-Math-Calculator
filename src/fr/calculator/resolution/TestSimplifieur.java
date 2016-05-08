package fr.calculator.resolution;

import fr.calculator.analyse.*;
import java.util.List;
import java.util.Scanner;

public class TestSimplifieur {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Entrez une expression mathématique, ou appuyez sur entrée pour utiliser l'expression de test par défaut.");
		Scanner sc = new Scanner(System.in);
		String expression = sc.nextLine();
		sc.close();
		if (expression.isEmpty()) {
			expression = "-22x + -24x^2 - +45 / 2 * (56+4) - cos(2) + 2 / 2";
			System.out.println("L'expression par défaut va être utilisée : " + expression);
		}
		long t0 = System.nanoTime();
		List<Terme> termes = new MathAnalyseur(expression).analyser();
		long t1 = System.nanoTime();
		ExpressionSimple expressionSimple = MathSimplifieur.simplifierExpression(termes);
		long t2 = System.nanoTime();
		double millisAnalyse = (t1 - t0) / Math.pow(10, 6);
		double millisSimplification = (t2 - t1) / Math.pow(10, 6);
		System.out.println("Analysé en " + millisAnalyse + " millisecondes");
		System.out.println("Smplifié en " + millisSimplification + " millisecondes");
		System.out.println("Expression simple : " + expressionSimple);
		System.out.println("Termes simplifiés : " + expressionSimple.getTermes());
	}

}
