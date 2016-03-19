package fr.calculator.parser;

public class Fraction implements Term {
	
	static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}
	
	public int numerator, denominator;
	
	public Fraction(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	@Override
	public Term negate() {
		numerator = -numerator;
		return this;
	}
	
	@Override
	public Term reverse() {
		int temp = numerator;
		this.numerator = denominator;
		this.denominator = temp;
		return this;
	}
	
	@Override
	public Term simplify() {
		int gcd = gcd(numerator, denominator);
		int simplifiedNum = numerator / gcd, simplifiedDen = denominator / gcd;
		return simplifiedDen == 1 ? new IntegerTerm(simplifiedNum) : new Fraction(simplifiedNum, simplifiedDen);
	}
	
	@Override
	public String toString() {
		return "Fraction: " + numerator + "/" + denominator;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Fraction) {
			Fraction frac = (Fraction) obj;
			return numerator == frac.numerator && denominator == frac.denominator;
		}
		return false;
	}
	
}
