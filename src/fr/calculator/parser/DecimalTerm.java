package fr.calculator.parser;

public class DecimalTerm implements Term {

	public double value;

	public DecimalTerm(double value) {
		this.value = value;
	}

	@Override
	public Term reverse() {
		return new Division(new IntegerTerm(1), this);
	}

	@Override
	public Term negate() {
		value = -value;
		return this;
	}

	@Override
	public Term simplify() {
		return this;
	}

	@Override
	public String toString() {
		return "DecimalTerm: " + value;
	}

}
