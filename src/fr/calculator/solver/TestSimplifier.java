package fr.calculator.solver;

import java.util.Arrays;
import java.util.List;

import fr.calculator.parser.Division;
import fr.calculator.parser.Fraction;
import fr.calculator.parser.IntegerTerm;
import fr.calculator.parser.Literal;
import fr.calculator.parser.Multiplication;
import fr.calculator.parser.Parenthesis;
import fr.calculator.parser.Power;
import fr.calculator.parser.Term;

public class TestSimplifier {

	public static void main(String[] args) throws InterruptedException {
		IntegerTerm i = new IntegerTerm(5);
		Multiplication m = new Multiplication(new Literal("x"), new Division(i, new Power(i, new IntegerTerm(20))));

		List<Term> terms = Arrays.asList(new Literal("x"), new IntegerTerm(-20), new Parenthesis(m),
				new Parenthesis(new IntegerTerm(2), new Fraction(25, 10)));

		String beforeSimplification = Arrays.deepToString(terms.toArray());
		System.out.println("tel quel  : " + beforeSimplification);

		long t0 = System.nanoTime();
		List<Term> simplified = MathSimplifier.simplify(terms);
		double time = (System.nanoTime() - t0) / (1000_000.0);

		String afterSimplification = Arrays.deepToString(simplified.toArray());
		System.out.println("simplifié : " + afterSimplification);
		System.out.println("durée : " + time + " millisecondes");
	}

}
