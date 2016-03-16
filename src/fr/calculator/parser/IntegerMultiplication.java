package fr.calculator.parser;

public class IntegerMultiplication implements Term {
	
	public int a, b;
	
	public IntegerMultiplication(int a, int b) {
		this.a = a;
		this.b = b;
	}
	
	@Override
	public Term negate() {
		a = -a;
		return this;
	}
	
	@Override
	public Term reverse() {
		return new Fraction(1, a * b);
	}
	
	@Override
	public Term simplify() {
		return new IntegerTerm(a * b);
	}
	
	@Override
	public String toString() {
		return "IntegerMultiplication: " + a + "*" + b;
	}
	
}
