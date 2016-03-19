package fr.calculator.parser;

import java.util.Arrays;
import java.util.List;

public class Parenthesis implements Term {
	
	public final Term[] terms;
	
	public Parenthesis(List<Term> terms) {
		Term[] array = new Term[terms.size()];
		this.terms = terms.toArray(array);
	}
	
	public Parenthesis(Term... terms) {
		this.terms = terms;
	}
	
	@Override
	public Term negate() {
		// Multiplie chaque terme par -1
		for (int i = 0; i < terms.length; i++) {
			terms[i] = terms[i].negate();
		}
		return this;
	}
	
	@Override
	public Term reverse() {
		return new Division(new IntegerTerm(1), this);
	}
	
	@Override
	public Term simplify() {
		boolean mayCalculate = true;
		for (int i = 0; i < terms.length; i++) {
			Term simplified = terms[i].simplify();
			terms[i] = simplified;
			if (!(simplified instanceof IntegerTerm || simplified instanceof DecimalTerm)) {
				mayCalculate = false;
			}
		}
		if (mayCalculate) {
			double result = 0;
			for (Term t : terms) {
				if (t instanceof IntegerTerm)
					result += ((IntegerTerm) t).value;
				else
					result += ((DecimalTerm) t).value;
			}
			return new DecimalTerm(result);
		}
		return this;
	}
	
	@Override
	public String toString() {
		return "Parenthesis: " + Arrays.toString(terms);
	}
	
}
