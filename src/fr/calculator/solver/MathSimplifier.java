package fr.calculator.solver;

import java.util.ArrayList;
import java.util.List;
import fr.calculator.parser.Term;

/**
 * Utilitaire pour simplifier les expressions mathématiques.
 * 
 * @author Guillaume
 * 		
 */
public class MathSimplifier {
	
	private MathSimplifier() {}
	
	public static List<Term> simplify(List<Term> expression) {
		List<Term> simplifiedExpression = new ArrayList<>();
		for (Term t : expression) {
			simplifiedExpression.add(t.simplify());
		}
		return simplifiedExpression;
	}
	
}
