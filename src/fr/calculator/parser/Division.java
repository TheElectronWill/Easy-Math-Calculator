package fr.calculator.parser;

public class Division implements Term {
	
	public Term a, b;
	
	public Division(Term a, Term b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public Term negate() {
		a = a.negate();
		return this;
	}
	
	@Override
	public Term reverse() {
		Term c = a;
		a = b;
		b = c;
		return this;
	}
	
	@Override
	public Term simplify() {
		a = a.simplify();
		b = b.simplify();
		if (a instanceof IntegerTerm && b instanceof IntegerTerm) {
			IntegerTerm termA = (IntegerTerm) a, termB = (IntegerTerm) b;
			return new Fraction(termA.value, termB.value).simplify();
		}
		return this;
	}
	
	@Override
	public String toString() {
		return "Division: (" + a + ")/(" + b + ")";
	}
	
}
