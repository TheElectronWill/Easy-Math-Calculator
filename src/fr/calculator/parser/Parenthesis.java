package fr.calculator.parser;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import fr.calculator.solver.MathSimplifier;

public class Parenthesis implements Term {
	
	public List<Term> terms;
	
	public Parenthesis(List<Term> terms) {
		this.terms = terms;
	}
	
	public Parenthesis(Term... terms) {
		this.terms = Arrays.asList(terms);
	}
	
	@Override
	public Term negate() {
		// Multiplie chaque terme par -1
		ListIterator<Term> it = terms.listIterator();
		while (it.hasNext()) {
			Term t = it.next();
			it.set(t.negate());
		}
		return this;
	}
	
	@Override
	public Term reverse() {
		return new Division(new IntegerTerm(1), this);
	}
	
	@Override
	public Term simplify() {
		if (terms.isEmpty())
			return new IntegerTerm(0);
		if (terms.size() == 1)
			return terms.get(0).simplify();
			
		terms = MathSimplifier.simplify(terms);
		boolean canCalculate = true;
		for (Term t : terms) {
			if (!(t instanceof IntegerTerm || t instanceof Fraction)) {
				canCalculate = false;
				break;
			}
		}
		if (canCalculate) {
			int numerator = 0, denominator = 1;
			for (Term t : terms) {
				if (t instanceof IntegerTerm) {
					numerator += ((IntegerTerm) t).value * denominator;
				} else if (t instanceof Fraction) {
					Fraction f = (Fraction) t;
					numerator = (numerator * f.denominator) + (f.numerator * denominator);
					denominator *= f.denominator;
				}
			}
			return denominator == 1 ? new IntegerTerm(numerator) : new Fraction(numerator, denominator);
		}
		return this;
	}
	
	@Override
	public String toString() {
		String.valueOf(terms);
		return "Parenthesis: " + terms.toString();
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof Parenthesis) && terms.equals(((Parenthesis) obj).terms);
	}
	
}
