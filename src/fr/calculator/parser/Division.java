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
		if (a instanceof IntegerTerm) {
			IntegerTerm n = (IntegerTerm) a;
			if (b instanceof IntegerTerm) {// n/n2
				IntegerTerm n2 = (IntegerTerm) b;
				return new Fraction(n.value, n2.value).simplify();
			}
			if (b instanceof Fraction) {// n/(n2/n3) = (n*n3)/n2
				Fraction fraction = (Fraction) b;
				return new Fraction(n.value * fraction.denominator, fraction.numerator).simplify();
			}
		} else if (a instanceof Fraction) {
			Fraction fraction = (Fraction) b;
			if (b instanceof IntegerTerm) {// (num/denom)/n
				IntegerTerm n = (IntegerTerm) b;
				return new Fraction(fraction.numerator * n.value, fraction.denominator).simplify();
			}
			if (b instanceof Fraction) {// (num/denom)/(num2/denom2) = (num*denom2)/(denom*num2)
				Fraction fraction2 = (Fraction) b;
				return new Fraction(fraction.numerator * fraction2.denominator, fraction.denominator * fraction2.numerator).simplify();
			}
		}
		return this;
	}
	
	@Override
	public String toString() {
		return "Division: (" + a + ")/(" + b + ")";
	}
	
}
