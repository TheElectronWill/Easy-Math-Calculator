package fr.calculator.analyse;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Teste le fonctionnement du {@link MathAnalyseur}.
 *
 * @author Guillaume
 *
 */
public class TestAnalyseur {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Entrez une expression mathématique, ou appuyez sur entrée utiliser l'expression de test par défaut.");
		Scanner sc = new Scanner(System.in);
		String expression = sc.nextLine();
		sc.close();
		if (expression.isEmpty()) {
			expression = "-22x + -24x^2 - +45 / 2 (56+4) - x(2+4)(3x-1) - xxxcos(2)ln(4)xexp(-x) + 2sin(0) / 2pi * -pi";
			System.out.println("L'expression par défaut va être utilisée : " + expression);
		}
		long t0 = System.nanoTime();
		List<Terme> termes = new MathAnalyseur(expression).analyser();
		long nanos = System.nanoTime() - t0;
		double millis = nanos / Math.pow(10, 6);
		System.out.println("Analysé en " + millis + " millisecondes");
		System.out.println("Résultat de l'analyse : " + Arrays.deepToString(termes.toArray()));
	}

}
