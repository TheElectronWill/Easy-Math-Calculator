package fr.calculator.parser;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Teste le fonctionnement du {@link MathParser}.
 * 
 * @author Guillaume
 * 		
 */
public class TestParser {
	
	public static void main(String[] args) throws InterruptedException {
		System.out.println("Entrez une expression mathématique");
		Scanner sc = new Scanner(System.in);
		String expression = sc.nextLine();
		sc.close();
		
		long t0 = System.nanoTime();
		List<Term> terms = new MathParser(expression).parse();
		long nanos = System.nanoTime() - t0;
		double millis = nanos / Math.pow(10, 6);
		double seconds = millis / 1000.0;
		System.out.println("Analysé en " + seconds + " secondes  (" + nanos + " ns = " + millis + " ms )");
		System.out.println(Arrays.deepToString(terms.toArray()));
	}
	
}
