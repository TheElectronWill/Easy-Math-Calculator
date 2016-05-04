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
		Rationnel r = new Rationnel(1, 1);
		System.out.println(r);
		System.out.println(r.soustraire(new Rationnel(0, 1)));
		System.out.println(new Rationnel(1, 1).ajouter(new Rationnel(0, 1)));
		System.out.println("Entrez une expression mathématique");
		Scanner sc = new Scanner(System.in);
		String expression = sc.nextLine();
		sc.close();

		long t0 = System.nanoTime();
		List<Terme> terms = new MathAnalyseur(expression).analyser();
		long nanos = System.nanoTime() - t0;
		double millis = nanos / Math.pow(10, 6);
		double secondes = millis / 1000.0;
		System.out.println("Analysé en " + secondes + " secondes  (" + nanos + " ns = " + millis + " ms )");
		System.out.println(Arrays.deepToString(terms.toArray()));
	}

}
