package fr.calculator.parser;

public class IntegerTerm implements Term {
	
	public int value;
	
	public IntegerTerm(int value) {
		this.value = value;
	}
	
	@Override
	public Term negate() {
		value = -value;
		return this;
	}
	
	@Override
	public Term reverse() {
		return new Fraction(1, value);
	}
	
	@Override
	public Term simplify() {
		return this;
	}
	
	@Override
	public String toString() {
		return "IntegerTerm: " + value;
	}
	
	@Override
	public boolean equals(Object obj) {
		return (obj instanceof IntegerTerm) && value == ((IntegerTerm) obj).value;
	}
	
}
