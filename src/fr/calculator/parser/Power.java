package fr.calculator.parser;

public class Power implements Term {
	
	public Term n, exponent;
	
	public Power(Term n, Term power) {
		this.n = n;
		this.exponent = power;
	}
	
	@Override
	public Term reverse() {
		return new Division(new IntegerTerm(1), this);
	}
	
	public Term divide(Term t) {
		if (n.equals(t)) {// n^p / n = n^(p-1)
			if (exponent instanceof IntegerTerm)
				((IntegerTerm) exponent).value--;
			else if (exponent instanceof Fraction)
				((Fraction) exponent).add(new IntegerTerm(-1));
			else
				exponent = new Parenthesis(exponent, new IntegerTerm(-1));
			return this;
		} else if (t instanceof Power) {
			Power pow = (Power) t;
			if (n.equals(pow.n)) {// n^p1 / n^p2 = n^(p1-p2)
				if (exponent instanceof IntegerTerm) {
					if (pow.exponent instanceof IntegerTerm)
						((IntegerTerm) exponent).value -= ((IntegerTerm) pow.exponent).value;
					else if (pow.exponent instanceof Fraction)
						exponent = new Fraction(((IntegerTerm) exponent).value, 1).substract((Fraction) pow.exponent);
					else
						exponent = new Parenthesis(exponent, new IntegerTerm(-1));
					return this;
				}
				if (exponent instanceof Fraction) {
					if (pow.exponent instanceof IntegerTerm)
						((Fraction) exponent).substract((IntegerTerm) pow.exponent);
					else if (pow.exponent instanceof Fraction)
						((Fraction) exponent).substract((Fraction) pow.exponent);
					else
						exponent = new Parenthesis(exponent, new IntegerTerm(-1));
					return this;
				}
			}
		}
		return new Division(this, t);
	}
	
	@Override
	public Power negate() {
		n = n.negate();
		return this;
	}
	
	@Override
	public Term simplify() {
		n = n.simplify();
		exponent = exponent.simplify();
		if (exponent instanceof IntegerTerm) {
			int p = ((IntegerTerm) exponent).value;
			if (p == 0)
				return new IntegerTerm(1);
			if (p == 1)
				return n;
			if (n instanceof IntegerTerm) {
				int nValue = ((IntegerTerm) n).value;
				double result = Math.pow(nValue, p);// on calcule la valeur
				int integerResult = (int) result;
				return integerResult == result ? new IntegerTerm(integerResult) : this;// on ne renvoie integerResult
																						// que si la valeur est exacte
			}
			if (n instanceof Fraction) {
				Fraction frac = (Fraction) n;
				Fraction result = frac.clone();
				for (int i = 0; i < p; i++) {
					result.multiply(frac);
				}
				return result;
			}
		}
		return this;
	}
	
	@Override
	public String toString() {
		return "Power: " + n + "^" + exponent;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Power) {
			Power p = (Power) obj;
			return n.equals(p.n) && exponent.equals(p.exponent);
		}
		return false;
	}
	
}
