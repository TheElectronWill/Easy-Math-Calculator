package fr.calculator.parser;

public class Multiplication implements Term {
	
	public Term a, b;
	
	public Multiplication(Term a, Term b) {
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
		return new Division(new IntegerTerm(1), this);
	}
	
	@Override
	public Term simplify() {
		a = a.simplify();
		b = b.simplify();
		if (a instanceof IntegerTerm && b instanceof IntegerTerm) {
			IntegerTerm intA = (IntegerTerm) a, intB = (IntegerTerm) b;
			return new IntegerTerm(intA.value * intB.value);
		}
		return this;
	}
	
	@Override
	public String toString() {
		return "Multiplication: (" + a + ")*(" + b + ")";
	}
	
}
