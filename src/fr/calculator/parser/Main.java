package fr.calculator.parser;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Entrez une expression mathématique");
		Scanner sc = new Scanner(System.in);
		String expression = sc.nextLine();
		sc.close();

		long t0 = System.nanoTime();
		List<Term> terms = new MathParser(expression).parse();
		long time = System.nanoTime() - t0;
		double seconds = time / Math.pow(10, 9);
		System.out.println("Analysé en " + time + " nanosecondes, soit " + seconds + " secondes");
		System.out.println(Arrays.deepToString(terms.toArray()));
	}

}
