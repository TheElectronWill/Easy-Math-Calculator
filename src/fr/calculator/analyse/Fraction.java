package fr.calculator.analyse;

public class Fraction implements Terme, Cloneable {

	static int pgcd(int a, int b) {
		return b == 0 ? a : pgcd(b, a % b);
	}

	public int num, denom;

	public Fraction(int num, int denom) {
		this.num = num;
		this.denom = denom;
	}

	/**
	 * Ajoute une fraction ou un nombre entier à cette fraction.
	 *
	 * @return cette fraction
	 */
	public Fraction ajouter(Object o) {
		if (o instanceof Fraction) {
			return ajouter((Fraction) o);
		}
		if (o instanceof NombreEntier) {
			return ajouter(((NombreEntier) o).valeur);
		}
		if (o instanceof Number) {
			return ajouter(((Number) o).intValue());
		}
		throw new IllegalArgumentException("Impossible d'ajouter à une fraction un objet de type " + o.getClass());
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
	 * Ajoute un nombre entier à cette fraction.
	 *
	 * @return cette fraction
	 */
	public Fraction ajouter(int i) {
		num += i * denom;
		return this;
	}

	/**
	 * Soustrait une fraction ou un nombre entier à cette fraction.
	 *
	 * @return cette fraction
	 */
	public Fraction soustraire(Object o) {
		if (o instanceof Fraction) {
			return soustraire((Fraction) o);
		}
		if (o instanceof NombreEntier) {
			return soustraire(((NombreEntier) o).valeur);
		}
		throw new IllegalArgumentException("Impossible de soustraire à une fraction un objet de type " + o.getClass());
	}

	/**
	 * Soustrait un nombre entier à cette fraction.
	 *
	 * @return cette fraction
	 */
	public Fraction soustraire(int i) {
		num -= i * denom;
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
	public Fraction multiplier(int i) {
		num *= i;
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
	public Fraction diviser(int i) {
		denom *= i;
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
	public Terme simplifier() {
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
		return num + "/" + denom;
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
