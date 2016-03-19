package fr.calculator.parser;

public class IntegerTerm implements Term {
	
	public int value;
	
	public IntegerTerm(int value) {
		this.value = value;
	}
	
	@Override
	public IntegerTerm negate() {
		value = -value;
		return this;
	}
	
	@Override
	public Fraction reverse() {
		return new Fraction(1, value);
	}
	
	@Override
	public IntegerTerm simplify() {
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
