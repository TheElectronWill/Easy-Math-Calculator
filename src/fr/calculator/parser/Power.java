package fr.calculator.parser;

public class Power implements Term {

	public Term n, power;

	public Power(Term n, Term power) {
		this.n = n;
		this.power = power;
	}

	@Override
	public Term reverse() {
		return new Division(new IntegerTerm(1), this);
	}

	@Override
	public Term negate() {
		n = n.negate();
		return this;
	}

	@Override
	public Term simplify() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String toString() {
		return "Power: " + n + "^" + power;
	}

}
