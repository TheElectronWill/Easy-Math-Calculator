package fr.calculator.solver;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import fr.calculator.parser.Fraction;
import fr.calculator.parser.IntegerTerm;
import fr.calculator.parser.Parenthesis;
import fr.calculator.parser.Term;

/**
 * Utilitaire pour simplifier les expressions mathématiques.
 * 
 * @author Guillaume
 * 		
 */
public class MathSimplifier {
	
	private MathSimplifier() {}
	
	public static List<Term> simplify(Term... terms) {
		return simplify(Arrays.asList(terms));
	}
	
	public static List<Term> simplify(List<Term> terms) {
		Fraction constant = new Fraction(0, 1);// somme de toutes les constantes
		List<Term> simplifiedTerms = new ArrayList<>();
		for (Term t : terms) {
			t = t.simplify();
			if (t instanceof Parenthesis) {// parenthèse seule -> on développe
				for (Term pt : ((Parenthesis) t).terms) {
					pt = pt.simplify();
					if (pt instanceof IntegerTerm) {
						constant.add((IntegerTerm) t);
					} else if (pt instanceof Fraction) {
						constant.add((Fraction) t);
					} else {
						simplifiedTerms.add(pt);
					}
				}
			} else if (t instanceof IntegerTerm) {
				constant.add((IntegerTerm) t);
			} else if (t instanceof Fraction) {
				constant.add((Fraction) t);
			} else {
				simplifiedTerms.add(t);
			}
		}
		simplifiedTerms.add(constant.simplify());
		return simplifiedTerms;
	}
	
}
