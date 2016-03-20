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
		Term temp = a;
		a = b;
		b = temp;
		return this;
	}
	
	@Override
	public Term simplify() {
		a = a.simplify();
		b = b.simplify();
		if (a instanceof IntegerTerm) {
			IntegerTerm n = (IntegerTerm) a;
			if (b instanceof IntegerTerm) {// n/n2
				IntegerTerm n2 = (IntegerTerm) b;
				return new Fraction(n.value, n2.value).simplify();
			}
			if (b instanceof Fraction) {// n/(num/den) = (n*den)/num
				Fraction fraction = (Fraction) b;
				return new Fraction(n.value * fraction.denominator, fraction.numerator).simplify();
			}
		} else if (a instanceof Fraction) {
			Fraction fraction = (Fraction) b;
			if (b instanceof IntegerTerm) {// (num/den)/n
				IntegerTerm n = (IntegerTerm) b;
				return fraction.divide(n).simplify();
			}
			if (b instanceof Fraction) {// (num/den)/(num2/den2) = (num*den2)/(den*num2)
				Fraction fraction2 = (Fraction) b;
				return fraction.divide(fraction2).simplify();
			}
		} else if (a instanceof Power) {
			Power pow = (Power) a;
			return pow.divide(b).simplify();
		}
		if (b instanceof Power) {
			Power powB = (Power) b;
			Power powA = new Power(a, new IntegerTerm(1));
			return powA.divide(powB).simplify();
		}
		return this;
	}
	
	@Override
	public String toString() {
		return "Division: (" + a + ")/(" + b + ")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Division) {
			Division div = (Division) obj;
			return a.equals(div.a) && b.equals(div.b);
		}
		return false;
	}
	
}
