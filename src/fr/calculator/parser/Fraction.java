package fr.calculator.parser;

public class Fraction implements Term, Cloneable {
	
	static int pgcd(int a, int b) {
		return b == 0 ? a : pgcd(b, a % b);
	}
	
	public int num, denom;
	
	public Fraction(int num, int denom) {
		this.num = num;
		this.denom = denom;
	}
	
	/**
	 * Ajoute une autre fraction à celle-là.
	 * 
	 * @return cette fraction
	 */
	public Fraction ajouter(Fraction f) {
		if (f.denom == denom) {
			num += f.num;
		} else {
			num = (num * f.denom + f.num * denom);
			denom *= f.denom;
		}
		return this;
	}
	
	/**
	 * Soustrait un nombre entier à cette fraction.
	 * 
	 * @return cette fraction
	 */
	public Fraction soustraire(NombreEntier i) {
		num -= i.valeur * denom;
		return this;
	}
	
	/**
	 * Soustrait une autre fraction à celle-là.
	 * 
	 * @return cette fraction
	 */
	public Fraction soustraire(Fraction f) {
		if (f.denom == denom) {
			num -= f.num;
		} else {
			num = (num * f.denom - f.num * denom);
			denom *= f.denom;
		}
		return this;
	}
	
	/**
	 * Ajoute un nombre entier à cette fraction.
	 * 
	 * @return cette fraction
	 */
	public Fraction ajouter(NombreEntier i) {
		num += i.valeur * denom;
		return this;
	}
	
	/**
	 * Multiplie cette fraction par une autre fraction.
	 * 
	 * @return cette fraction
	 */
	public Fraction multiplier(Fraction f) {
		num *= f.num;
		denom *= f.denom;
		return this;
	}
	
	/**
	 * Multiplie cette fraction par une nombre entier.
	 * 
	 * @return cette fraction
	 */
	public Fraction multiplier(NombreEntier i) {
		num *= i.valeur;
		return this;
	}
	
	/**
	 * Divise cette fraction par une autre fraction.
	 * 
	 * @return cette fraction
	 */
	public Fraction diviser(Fraction f) {
		num *= f.denom;
		denom *= f.num;
		return this;
	}
	
	/**
	 * Multiplie cette fraction par un nombre entier.
	 * 
	 * @return cette fraction
	 */
	public Fraction diviser(NombreEntier i) {
		denom *= i.valeur;
		return this;
	}
	
	@Override
	public Fraction negatif() {
		num = -num;
		return this;
	}
	
	@Override
	public Fraction inverser() {
		int temp = num;
		this.num = denom;
		this.denom = temp;
		return this;
	}
	
	@Override
	public Term simplifier() {
		int gcd = pgcd(num, denom);
		int simplifiedNum = num / gcd, simplifiedDen = denom / gcd;
		if (simplifiedNum > 0 && simplifiedDen < 0) {// Transformer a/-b en -a/b
			simplifiedNum *= -1;
			simplifiedDen *= -1;
		}
		return simplifiedDen == 1 ? new NombreEntier(simplifiedNum) : new Fraction(simplifiedNum, simplifiedDen);
	}
	
	@Override
	public String toString() {
		return "Fraction: " + num + "/" + denom;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Fraction) {
			Fraction frac = (Fraction) obj;
			return num == frac.num && denom == frac.denom;
		}
		return false;
	}
	
	@Override
	public Fraction clone() {
		return new Fraction(num, denom);
	}
	
}
