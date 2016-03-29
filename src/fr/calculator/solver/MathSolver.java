package fr.calculator.solver;
import java.util.List;
import java.util.Scanner;

import fr.calculator.SimplificationFraction;
import fr.calculator.parser.*;
public class MathSolver {
	public static String solver(List<Term> gauche, List<Term> droit) {
		int fact1 = 0, cons1 = 0, fact2 = 0, cons2 = 0;
			Term X = gauche.get(0);
			if (X instanceof Multiplication) {
				Multiplication m = (Multiplication) X;
				Term a = m.a, b = m.b;
				if (a instanceof IntegerTerm) {
					fact1 = ((IntegerTerm)a).value;				
				}
				else {
					fact1 = ((IntegerTerm)b).value;			 
				}
			}
			else {
				cons1 = ((IntegerTerm)X).value;
			}
			if (X instanceof Multiplication) {
				Multiplication m = (Multiplication) X;
				Term a = m.a, b = m.b;
				if (a instanceof IntegerTerm) {
					fact2 = ((IntegerTerm)a).value;				
				}
				else {
					fact2 = ((IntegerTerm)b).value;			 
				}
			}
			else {
				cons2 = ((IntegerTerm)X).value;
			}
		    int denom = fact1 - fact2,
		    num = cons1 - cons2;
		    Fraction frac = new Fraction(num, denom);
		    Term simplifié = frac.simplify();
		    if (simplifié instanceof IntegerTerm) {
		    	String str = String.valueOf(((IntegerTerm)simplifié).value);
		    	return str;
		    }
		    else {
		    	
		    }
	  }
}