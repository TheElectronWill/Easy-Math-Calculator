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
	
	/**
	 * Ajoute une autre fraction à celle-là.
	 * 
	 * @return cette fraction
	 */
	public Fraction add(Fraction f) {
		if (f.denominator == denominator) {
			numerator += f.numerator;
		} else {
			numerator = (numerator * f.denominator + f.numerator * denominator);
			denominator *= f.denominator;
		}
		return this;
	}
	
	/**
	 * Ajoute un nombre entier à cette fraction.
	 * 
	 * @return cette fraction
	 */
	public Fraction add(IntegerTerm i) {
		numerator += i.value * denominator;
		return this;
	}
	
	/**
	 * Multiplie cette fraction par une autre fraction.
	 * 
	 * @return cette fraction
	 */
	public Fraction multiply(Fraction f) {
		numerator *= f.numerator;
		denominator *= f.denominator;
		return this;
	}
	
	/**
	 * Multiplie cette fraction par une nombre entier.
	 * 
	 * @return cette fraction
	 */
	public Fraction multiply(IntegerTerm i) {
		numerator *= i.value;
		return this;
	}
	
	/**
	 * Divise cette fraction par une autre fraction.
	 * 
	 * @return cette fraction
	 */
	public Fraction divide(Fraction f) {
		numerator *= f.denominator;
		denominator *= f.numerator;
		return this;
	}
	
	/**
	 * Multiplie cette fraction par un nombre entier.
	 * 
	 * @return cette fraction
	 */
	public Fraction divide(IntegerTerm i) {
		denominator *= i.value;
		return this;
	}
	
	@Override
	public Fraction negate() {
		numerator = -numerator;
		return this;
	}
	
	@Override
	public Fraction reverse() {
		int temp = numerator;
		this.numerator = denominator;
		this.denominator = temp;
		return this;
	}
	
	@Override
	public Term simplify() {
		int gcd = gcd(numerator, denominator);
		int simplifiedNum = numerator / gcd, simplifiedDen = denominator / gcd;
		if (simplifiedNum > 0 && simplifiedDen < 0) {// Transformer a/-b en -a/b
			simplifiedNum *= -1;
			simplifiedDen *= -1;
		}
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
