package fr.calculator.analyse;

public class Rationnel implements Terme, Cloneable {

	private static int pgcd(int a, int b) {
		return b == 0 ? a : pgcd(b, a % b);
	}

	public int num, denom;

	public Rationnel(String decimal) {
		num = Integer.parseInt(decimal.replace(".", ""));
		denom = (int) Math.pow(10, decimal.length() - decimal.indexOf('.') - 1);
	}

	public Rationnel(double d) {
		this(String.valueOf(d));
	}

	public Rationnel(int n) {
		this(n, 1);
	}

	public Rationnel(int num, int denom) {
		if (denom == 0) {
			throw new IllegalArgumentException("Impossible de diviser par zéro");
		}
		this.num = num;
		this.denom = denom;
	}

	/**
	 * Ajoute un Rationnel ou un Number à ce rationnel.
	 *
	 * @return ce Rationnel, contenant le résultat de l'addition
	 */
	public Rationnel ajouter(Object o) {
		if (o instanceof Rationnel) {
			return ajouter((Rationnel) o);
		}
		if (o instanceof Number) {
			return ajouter(((Number) o).intValue());
		}
		throw new IllegalArgumentException("Impossible d'ajouter à un rationnel un objet de type " + o.getClass());
	}

	/**
	 * Ajoute un nombre à celui-ci.
	 *
	 * @return ce Rationnel, contenant le résultat de l'addition
	 */
	public Rationnel ajouter(Rationnel q) {
		if (q.denom == denom) {
			num += q.num;
		} else {
			num = (num * q.denom + q.num * denom);
			denom *= q.denom;
		}
		return this;
	}

	/**
	 * Ajoute un nombre à celui-ci.
	 *
	 * @return ce Rationnel, contenant le résultat de l'addition
	 */
	public Rationnel ajouter(int i) {
		num += i * denom;
		return this;
	}

	/**
	 * Soustrait un Rationnel ou un Number à ce rationnel.
	 *
	 * @return ce Rationnel, contenant le résultat de la soustraction
	 */
	public Rationnel soustraire(Object o) {
		if (o instanceof Rationnel) {
			return soustraire((Rationnel) o);
		}
		throw new IllegalArgumentException("Impossible de soustraire à un rationnel un objet de type " + o.getClass());
	}

	/**
	 * Soustrait un nombre à celui-ci.
	 *
	 * @return ce Rationnel, contenant le résultat de la soustraction
	 */
	public Rationnel soustraire(int i) {
		num -= i * denom;
		return this;
	}

	/**
	 * Soustrait un nombre à celui-ci.
	 *
	 * @return ce Rationnel, contenant le résultat de la soustraction
	 */
	public Rationnel soustraire(Rationnel q) {
		if (q.denom == denom) {
			num -= q.num;
		} else {
			num = (num * q.denom - q.num * denom);
			denom *= q.denom;
		}
		return this;
	}

	/**
	 * Multiplie ce nombre par un autre.
	 *
	 * @return ce Rationnel, contenant le résultat de la multiplication
	 */
	public Rationnel multiplier(Rationnel r) {
		num *= r.num;
		denom *= r.denom;
		return this;
	}

	/**
	 * Multiplie ce nombre par un autre.
	 *
	 * @return ce Rationnel, contenant le résultat de la multiplication
	 */
	public Rationnel multiplier(int i) {
		num *= i;
		return this;
	}

	/**
	 * Divise ce nombre par un autre.
	 *
	 * @return ce Rationnel, contenant le résultat de la division
	 */
	public Rationnel diviser(Rationnel q) {
		num *= q.denom;
		denom *= q.num;
		return this;
	}

	/**
	 * Divise ce nombre par un autre.
	 *
	 * @return ce Rationnel, contenant le résultat de la division
	 */
	public Rationnel diviser(int i) {
		denom *= i;
		return this;
	}

	@Override
	public Rationnel negatif() {
		num = -num;
		return this;
	}

	@Override
	public Rationnel inverser() {
		int temp = num;
		this.num = denom;
		this.denom = temp;
		return this;
	}

	public double decimal() {
		return (double) num / denom;
	}

	/**
	 * Simplifie ce nombre rationnel. Ce même rationnel est retourné, même si à l'issue de la simplification
	 * le
	 * dénominateur vaut 1.
	 *
	 * @return ce rationnel simplifié
	 */
	@Override
	public Rationnel simplifier() {
		int gcd = pgcd(num, denom);
		int numSimple = num / gcd, denomSimple = denom / gcd;
		if (numSimple > 0 && denomSimple < 0) {// Transformer a/-b en -a/b
			numSimple *= -1;
			denomSimple *= -1;
		}
		num = numSimple;
		denom = denomSimple;
		return this;
	}

	@Override
	public String toString() {
		return denom == 1 ? String.valueOf(num) : num + "/" + denom;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Rationnel) {
			Rationnel q = (Rationnel) obj;
			return num == q.num && denom == q.denom;
		}
		return false;
	}

	@Override
	public Rationnel clone() {
		return new Rationnel(num, denom);
	}

}
