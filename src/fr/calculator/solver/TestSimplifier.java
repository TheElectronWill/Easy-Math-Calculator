package fr.calculator.solver;

import java.util.Arrays;
import java.util.List;
import fr.calculator.parser.Division;
import fr.calculator.parser.Fraction;
import fr.calculator.parser.IntegerTerm;
import fr.calculator.parser.Literal;
import fr.calculator.parser.Multiplication;
import fr.calculator.parser.Term;

public class TestSimplifier {
	
	public static void main(String[] args) {
		IntegerTerm i1 = new IntegerTerm(10), i2 = new IntegerTerm(5);
		Literal x = new Literal("x");
		Multiplication m = new Multiplication(x, new Multiplication(i2, new Division(i1, new Fraction(i2.value, i1.value))));
		
		List<Term> terms = Arrays.asList(m);
		String beforeSimplification = Arrays.deepToString(terms.toArray());
		System.out.println("tel quel : " + beforeSimplification);
		
		List<Term> simplified = MathSimplifier.simplify(terms);
		String afterSimplification = Arrays.deepToString(simplified.toArray());
		System.out.println("simplifié: " + afterSimplification);
	}
	
}
