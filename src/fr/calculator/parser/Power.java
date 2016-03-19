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
		n = n.simplify();
		power = power.simplify();
		if (n instanceof IntegerTerm && power instanceof IntegerTerm) {
			int a = ((IntegerTerm) n).value;
			int b = ((IntegerTerm) power).value;
			double result = Math.pow(a, b);
			int integerResult = (int) result;
			return result == integerResult ? new IntegerTerm(integerResult) : this;// on ne renvoie integerResult que si
																					// c'est correct (p.ex. si c'est pas
																					// trop grand)
		}
		return this;
	}
	
	@Override
	public String toString() {
		return "Power: " + n + "^" + power;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Power) {
			Power p = (Power) obj;
			return n.equals(p.n) && power.equals(p.power);
		}
		return false;
	}
	
}
