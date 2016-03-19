package fr.calculator.solver;

import java.util.Arrays;
import java.util.List;
import fr.calculator.parser.*;

public class TestSimplifier {
	
	public static void main(String[] args) throws InterruptedException {
		IntegerTerm i1 = new IntegerTerm(10), i2 = new IntegerTerm(5);
		Literal x = new Literal("x");
		Multiplication m = new Multiplication(x, new Multiplication(i2, new Division(i1, new Fraction(i2.value, i1.value))));
		
		List<Term> terms = Arrays.asList(new IntegerTerm(99), new Parenthesis(m),
				new Parenthesis(new IntegerTerm(123), new Fraction(12, 10)));
		String beforeSimplification = Arrays.deepToString(terms.toArray());
		System.out.println("tel quel : " + beforeSimplification);
		
		long t0 = System.nanoTime();
		List<Term> simplified = MathSimplifier.simplify(terms);
		double time = (System.nanoTime() - t0) / (1000_000.0);
		
		String afterSimplification = Arrays.deepToString(simplified.toArray());
		System.out.println("simplifié: " + afterSimplification);
		System.out.println("Simplifié en " + time + " millisecondes");
	}
	
}
