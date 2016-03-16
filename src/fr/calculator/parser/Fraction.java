package fr.calculator.parser;

public class Fraction implements Term {
	
	static int gcd(int a, int b) {
		return b == 0 ? a : gcd(b, a % b);
	}
	
	public final int numerator, denominator;
	
	public Fraction(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	@Override
	public Term negate() {
		return new Fraction(-numerator, denominator);
	}
	
	@Override
	public Term reverse() {
		return new Fraction(denominator, numerator);
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
	
}
